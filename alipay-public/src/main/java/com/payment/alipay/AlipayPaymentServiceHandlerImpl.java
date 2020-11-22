package com.payment.alipay;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.payment.alipay.config.AlipayConstants;
import com.payment.alipay.config.AlipayPropertiesConfig;
import com.payment.alipay.dto.*;
import com.payment.alipay.enums.BillTypeEnum;
import com.payment.alipay.enums.SignTypeEnum;
import com.payment.alipay.enums.TradeStatus;
import com.payment.alipay.exception.DownloadFailedException;
import com.payment.alipay.exception.ResponseException;
import com.payment.alipay.http.HttpRequestUtil;
import com.payment.alipay.model.builder.*;
import com.payment.alipay.model.result.AlipayF2FDownloadResult;
import com.payment.alipay.model.result.AlipayF2FQueryResult;
import com.payment.alipay.service.impl.AlipayMonitorServiceImpl;
import com.payment.alipay.service.impl.AlipayTradeServiceImpl;
import com.payment.alipay.service.impl.AlipayTradeWithHBServiceImpl;
import com.payment.alipay.util.AmtUtil;
import com.payment.alipay.util.ConstantUtil;
import com.payment.alipay.util.DateUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author code
 * @Title: AlipayPaymentServiceHandlerImpl
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/29上午9:23
 */
public class AlipayPaymentServiceHandlerImpl extends AbstractAlipayPaymentServiceHandler implements PaymentServiceHandler, AutoCloseable {

    public AlipayPaymentServiceHandlerImpl(HttpRequestUtil httpUtil) {
        //config为空，则说明已经在外部进行了配置实例化操作

        this.httpRequestUtil = httpUtil;

        /** 使用Configs提供的默认参数
         *  AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
         */
        tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();

        // 支付宝当面付2.0服务（集成了交易保障接口逻辑）
        tradeWithHBService = new AlipayTradeWithHBServiceImpl.ClientBuilder().build();

        /** 如果需要在程序中覆盖Configs提供的默认参数, 可以使用ClientBuilder类的setXXX方法修改默认参数 否则使用代码中的默认设置 */
        monitorService = new AlipayMonitorServiceImpl.ClientBuilder()
                .setGatewayUrl("http://mcloudmonitor.com/gateway.do").setCharset("GBK")
                .setFormat("json").build();

    }
//创建固定个数的线程池,通过线程池的方式完成对账单文件的写入以及对账单对象数据的组装，以节省时间。
    //noinspection AlibabaThreadPoolCreation

    static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("downloadBillFromRongYunTong-pool-%d").build();
    private static ExecutorService downloadAlipayPool = new ThreadPoolExecutor(2, 2,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());


    @Override
    public <T> T pay(QdsOrderDTO qdsOrderDTO, Class<T> clazz) throws Exception {
        checkOrderDTO(qdsOrderDTO);
        //创建请求对象
        AlipayTradePrecreateRequestBuilder request = getOrderRequest(qdsOrderDTO);
        //发起请求，获得返回结果,jar包已经进行了验签封装
        return (T) tradeService.tradePrecreate(request);
    }

    @Override
    public <T> T bizPay(QdsOrderDTO qdsOrderDTO, Class<T> responseClass) throws Exception {
        return null;
    }


    @Override
    public <T> T query(QdsQueryDTO queryDTO, Class<T> responseClass) throws Exception {
        checkQueryDTO(queryDTO);
        AlipayTradeQueryRequestBuilder request = new AlipayTradeQueryRequestBuilder();
        request.setOutTradeNo(queryDTO.getQdsOrderNo())
                .setTradeNo(queryDTO.getChannelOrderNo())
        ;
        AlipayF2FQueryResult queryResult = tradeService.queryTradeResult(request);
        checkAlipayResponse(queryResult);
        return (T) queryResult;
    }

    private void checkAlipayResponse(AlipayF2FQueryResult queryResult) throws ResponseException {
        if (!queryResult.isTradeSuccess() && !AlipayConstants.SUCCESS.equals(queryResult.getResponse().getCode())) {
            throw new ResponseException("通道返回失败,调用结果：" + JSON.toJSONString(queryResult.getResponse()));
        }
    }

    @Override
    public <T> List<T> downloadBillFromChannel(String statementDate, BillTypeEnum billType) throws Exception {
        BillTypeEnum billTypeEnum = BillTypeEnum.TRADE;
        checkDownloadBillDTO(statementDate, billTypeEnum);
        AlipayTradeDownloadBillRequestBuilder builder = new AlipayTradeDownloadBillRequestBuilder();
        builder.setBillDate(DateUtils.dateStringToString(statementDate, DateUtils.DATE_FORMAT, DateUtils.FORMAT_PATTERN));
        builder.setBillType(billTypeEnum.getValue());
        AlipayF2FDownloadResult downBillResult = tradeService.downloadBill(builder);
        if (downBillResult.getTradeStatus() == TradeStatus.UNKNOWN) {
            throw new DownloadFailedException("支付宝对账单下载异常");
        }
        String responseData = downloadBill(downBillResult.getResponse().getBillDownloadUrl());
        if (responseData == null) {
            throw new DownloadFailedException("支付宝对账单不存在");
        }
        writeBillToServer(downloadAlipayPool, statementDate, billTypeEnum, responseData, AlipayPropertiesConfig.getStatementFilePath(), AlipayPropertiesConfig.getAppid());

        //返回参数对象解析
        Callable<List<T>> billList = () -> (List<T>) convertAlipayBill(statementDate, responseData);
        FutureTask<List<T>> billTask = new FutureTask<>(billList);
        downloadAlipayPool.submit(billTask);

        return billTask.get();
    }


    @Override
    public <T> T refund(RefundOrder refundOrder, Class<T> responseClass) throws Exception {
        checkRefundOrder(refundOrder);

        AlipayTradeRefundRequestBuilder request = getRefundRequest(refundOrder);
        return (T) tradeService.tradeRefund(request);
    }

    private AlipayTradeRefundRequestBuilder getRefundRequest(RefundOrder refundOrder) {
        AlipayTradeRefundRequestBuilder builder = new AlipayTradeRefundRequestBuilder();

        builder.setOutTradeNo(refundOrder.getQdsOrderNo())
                .setRefundAmount(AmtUtil.getRmb(refundOrder.getRefundAmt()))
                .setRefundReason(refundOrder.getRefundReason())
                .setOutRequestNo(refundOrder.getRefundOrderNo())
                .setStoreId(ConstantUtil.ALIPAY_SORE_NAME);
        return builder;
    }


    @Override
    public <T> T closeOrder(CloseDTO closeOrderDTO, Class<T> responseClass) {

        AlipayTradeCloseRequestBuilder request = getCloseRequest(closeOrderDTO);

        return (T) tradeService.closeOrder(request);
    }

    private AlipayTradeCloseRequestBuilder getCloseRequest(CloseDTO closeOrderDTO) {
        AlipayTradeCloseRequestBuilder builder = new AlipayTradeCloseRequestBuilder();
        builder.setOutTradeNo(closeOrderDTO.getQdsOrderNo());
        return builder;
    }

    @Override
    public void close() throws Exception {
        httpRequestUtil.close();
    }

    @Override
    public <T> T refundQuery(RefundQueryDTO refundQueryDTO, Class<T> responseClass) throws Exception {
        return null;
    }

    @Override
    public boolean verifySign(String data, String signType) {
        return false;
    }

    @Override
    public String createSign(Map<String, String> paramsMap, SignTypeEnum signType) throws Exception {
        return null;
    }

}
