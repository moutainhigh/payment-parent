package com.payment.alipay;

import com.payment.alipay.beans.downloadprotocol.AlipayDownloadBillResData;
import com.payment.alipay.config.AlipayPropertiesConfig;
import com.payment.alipay.dto.QdsOrderDTO;
import com.payment.alipay.model.builder.AlipayTradePrecreateRequestBuilder;
import com.payment.alipay.service.AlipayMonitorService;
import com.payment.alipay.service.AlipayTradeService;
import com.payment.alipay.util.AmtUtil;
import com.payment.alipay.util.ConstantUtil;
import com.payment.alipay.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author code
 * @Title: AbstractAlipayPaymentServiceHandler
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/29上午9:23
 */
public abstract class AbstractAlipayPaymentServiceHandler extends AbstractPaymentServiceHandler {
    // 支付宝当面付2.0服务
    protected static AlipayTradeService tradeService;

    // 支付宝当面付2.0服务（集成了交易保障接口逻辑）
    protected static AlipayTradeService tradeWithHBService;

    // 支付宝交易保障接口服务，供测试接口api使用，请先阅读readme.txt
    protected static AlipayMonitorService monitorService;

    /**
     * 构建支付宝下单参数请求
     *
     * @param qdsOrderDTO
     * @return
     */
    protected AlipayTradePrecreateRequestBuilder getOrderRequest(QdsOrderDTO qdsOrderDTO) {
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder();

        builder.setSubject(qdsOrderDTO.getBody())
                .setBody(qdsOrderDTO.getDeviceInfo())
                .setTotalAmount(AmtUtil.getRmb(qdsOrderDTO.getOrderAmt()))
                .setOutTradeNo(qdsOrderDTO.getOrderNo())
                // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
                // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
                .setUndiscountableAmount(StringUtils.isEmpty(qdsOrderDTO.getUndiscountableAmount()) ?
                        "0" : qdsOrderDTO.getUndiscountableAmount())

                // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
                // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
                //.setSellerId(sellerId)

                // 商户操作员编号，添加此参数可以为商户操作员做销售统计
                .setOperatorId(ConstantUtil.ALIPAY_FILE_NAME)

                //(必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
                .setStoreId(ConstantUtil.ALIPAY_SORE_NAME)

                //业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
                //.setExtendParams(extendParams)
                // 支付超时，定义为120分钟
                .setTimeoutExpress(getTimeoutExpress(qdsOrderDTO.getTimeExpire()))

                //支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
                .setNotifyUrl(AlipayPropertiesConfig.getNotifyUrl())
//                .setNotifyUrl("http://b28934a8.ngrok.io/notify/alipay/receive")
//                .setGoodsDetailList(goodsDetailList)
        ;

        return builder;
    }

    /**
     * 下单超时时间设置。默认120分钟
     *
     * @param timeExpire
     * @return
     */
    private String getTimeoutExpress(Integer timeExpire) {
        return DateUtils.timeSuperLevel(timeExpire == null ? 120 : timeExpire) + "m";
    }


    /**
     * 下载下来的是一个【账号_日期.csv.zip】文件（zip压缩文件名，里面有多个.csv文件）
     * 账号_日期_业务明细 ： 支付宝业务明细查询
     * 账号_日期_业务明细(汇总)：支付宝业务汇总查询
     * <p>
     * 注意：如果数据量比较大，该方法可能需要更长的执行时间
     *
     * @param billDownLoadUrl
     * @return
     * @throws IOException
     */
    protected String downloadBill(String billDownLoadUrl) throws IOException {
        String ordersStr = "";
        byte[] data = httpRequestUtil.getBytes(billDownLoadUrl);

        ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(data), Charset.forName("GBK"));
        ZipEntry zipEntry;
        try {
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    String name = zipEntry.getName();
                    // 只要明细不要汇总
                    if (name.contains("汇总")) {
                        continue;
                    }
                    byte[] byteBuff = new byte[4096];
                    int bytesRead = 0;
                    while ((bytesRead = zipInputStream.read(byteBuff)) != -1) {
                        byteArrayOutputStream.write(byteBuff, 0, bytesRead);
                    }
                    ordersStr = byteArrayOutputStream.toString("GBK");
                } finally {
                    byteArrayOutputStream.close();
                    zipInputStream.closeEntry();
                }
            }
        } finally {
            if (zipInputStream != null) {
                zipInputStream.close();
            }
        }

        if (ordersStr.equals("")) {
            return null;
        }
        return ordersStr;

    }

    /**
     * 解析支付宝对账单数据为对象格式
     *
     * @param statementDate
     * @param responseData
     * @return
     */
    protected List<AlipayDownloadBillResData> convertAlipayBill(String statementDate, String responseData) {
        String[] bills = responseData.split("\r\n");
        List<String> billList = Arrays.asList(bills);
        billList = billList.parallelStream().map(item -> item.replace("\t", "")).collect(Collectors.toList());

        List<AlipayDownloadBillResData> list = new ArrayList<>();

        billList.stream().filter(bill -> (isAlipayDataLine(bill.split(",")))).forEach(bill -> {
            AlipayDownloadBillResData data = convertAlipayLineData(bill.split(","), statementDate);
            list.add(data);
        });

        return list;
    }

    /**
     * 组装支付宝对账单数据为对象形式
     *
     * @param lineData
     * @param statementDate
     * @return
     */
    private AlipayDownloadBillResData convertAlipayLineData(String[] lineData, String statementDate) {
        AlipayDownloadBillResData data = new AlipayDownloadBillResData();
        data.setChannelOrderNo(lineData[0])
                .setQdsOrderNo(lineData[1])
                .setTradeType(lineData[2])
                .setSubject(lineData[3])
                .setOrderTime(lineData[4])
                .setTimeEnd(lineData[5])
                .setStoreId(lineData[6])
                .setStoreName(lineData[7])
                .setOperatorId(lineData[8])
                .setTerminalId(lineData[9])
                .setUserInfo(lineData[10])
                .setTotalAmount(lineData[11])
                .setActualAmount(lineData[12])
                .setAlipayRedPacket(lineData[13])
                .setIntegralAmount(lineData[14])
                .setAlipayPreferent(lineData[15])
                .setMerchantPreferent(lineData[16])
                .setTicketAmount(lineData[17])
                .setTicketName(lineData[18])
                .setMerchantRedAmount(lineData[19])
                .setCardAmount(lineData[20])
                .setQdsRefundNo(lineData[21])
                .setServerAmount(lineData[22])
                .setSubAccountAmount(lineData[23])
                .setStatementDate(Integer.valueOf(statementDate))
                .setProjectName(lineData.length == 25 ? lineData[24] : null);
        return data;
    }

    /**
     * 判断是否为真正的支付宝对账单数据
     *
     * @param lineData
     * @return
     */
    private boolean isAlipayDataLine(String[] lineData) {
        return !(StringUtils.equals("支付宝交易号", lineData[0]) || (lineData.length != 24 && lineData.length != 25));
    }
}
