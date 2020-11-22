package com.payment.alipay;

import com.alibaba.fastjson.JSON;
import com.payment.alipay.dto.QdsOrderDTO;
import com.payment.alipay.dto.QdsQueryDTO;
import com.payment.alipay.dto.RefundOrder;
import com.payment.alipay.dto.RefundQueryDTO;
import com.payment.alipay.enums.BillTypeEnum;
import com.payment.alipay.enums.PaymentModeEnum;
import com.payment.alipay.enums.TradeTypeEnum;
import com.payment.alipay.exception.DownloadFailedException;
import com.payment.alipay.exception.UrlException;
import com.payment.alipay.http.HttpRequestUtil;
import com.payment.alipay.util.ConstantUtil;
import com.payment.alipay.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * 各通道公共处理类
 *
 * @author code
 * @Title: AbstractPaymentServiceHandler
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/29下午5:29
 */
@Slf4j
public abstract class AbstractPaymentServiceHandler {

//    protected BaseWechatConfig config;
//    protected BaseRongYunTongConfig rongYunTongConfig;
    protected HttpRequestUtil httpRequestUtil;



    protected void writeBillToServer(ExecutorService pool, String statementDate, BillTypeEnum billType, String response, String statementFilePath, String preName) {

        Runnable runnable = () -> {
            try {
                writeBillTolocal(statementDate, billType, response, statementFilePath, preName);
            } catch (Exception e) {
                log.error("对账单写入异常", e);
            }
        };
        pool.execute(runnable);
    }

    /**
     * 对账单本地写入
     *
     * @param statementDate     对账单日期
     * @param billType          账单类型
     * @param response          文件信息
     * @param statementFilePath 对账单文件存放路径
     * @param preName           对账单文件前缀名称
     * @throws DownloadFailedException
     * @throws IOException
     */
    protected void writeBillTolocal(String statementDate, BillTypeEnum billType, String response, String statementFilePath, String preName) throws DownloadFailedException, IOException {
        //保存对账单数据至服务器

        if (StringUtils.isEmpty(response)) {
            log.error("[PUBLIC_STATEMENT_BILL_DOWNLOAD_FAIL]对账单下载失败,response={}", response);
            throw new DownloadFailedException("对账单下载失败 - " + response);
        }

        //默认检测对账单路径是否存在，存在，则生成文件记录，不存在，则不进行文件数据记录
        if (StringUtils.isNotEmpty(statementFilePath)) {
            String path = statementFilePath;
            //mock部分
            String mockPath = System.getProperty("mockStatementPath");
            path = StringUtils.isEmpty(mockPath) ? path : mockPath;

            String pathName;
            //判断路径是否以左斜线结尾，并生成相应的路径
            if (path.endsWith(ConstantUtil.LEFT_LINE)) {
                pathName = path + statementDate + File.separator + preName + "-" + billType.name() + ".csv";
            } else {
                pathName = path + File.separator + statementDate + File.separator + preName + "-" + billType.name() + ".csv";
            }
            log.info("保存对账单数据至服务器，路径：{}", pathName);
            FileUtils.write2Local(response, pathName);
        }
    }

    protected void checkXmlRequest(Object object, String xml) {
        if (StringUtils.isEmpty(xml)) {
            log.error("请求参数bean中channelType通道类型错误，无法获取对应的xml内容,object={}", JSON.toJSONString(object));
            throw new NullPointerException("请求参数中channelType通道类型错误，无法获取对应的xml内容");
        }
    }

    /**
     * 检查下单请求必填参数是否为空
     *
     * @param qdsOrderDTO
     */
    protected void checkOrderDTO(QdsOrderDTO qdsOrderDTO) {
        if (qdsOrderDTO == null) {
            throw new NullPointerException();
        }
        if (StringUtils.isEmpty(qdsOrderDTO.getBody())) {
            throw new NullPointerException("body is null");
        }
        if (StringUtils.isEmpty(qdsOrderDTO.getOrderNo())) {
            throw new NullPointerException("OrderNo is null");
        }
        if (qdsOrderDTO.getOrderAmt() <= 0) {
            throw new NullPointerException("OrderAmt is zero");
        }
        if (StringUtils.isEmpty(qdsOrderDTO.getIp())) {
            throw new NullPointerException("IP is null");
        }
//        if (StringUtils.isEmpty(qdsOrderDTO.getTradeType())) {
//            throw new NullPointerException("TradeType is null");
//        }
        if (StringUtils.isEmpty(qdsOrderDTO.getSignType())) {
            throw new NullPointerException("SignType is null");
        }
        //JSAPI类型需要判断openid参数是否存在
        if (isJsApiTradeTypeNotOk(qdsOrderDTO)) {
            throw new NullPointerException("openid or subOpenid is null");
        }

        if (qdsOrderDTO.getChannelType() == null) {
            throw new NullPointerException("ChannelType is null");
        }

    }

    /**
     * 检查URL是否OK
     *
     * @param msg
     * @param url
     * @throws UrlException
     */
    public void checkUrlIsOk(String msg, String... url) throws UrlException {
        if (StringUtils.isAnyEmpty(url)) {
            throw new UrlException(msg + " is null");
        }
    }

    private boolean isJsApiTradeTypeNotOk(QdsOrderDTO qdsOrderDTO) {
        boolean flag = TradeTypeEnum.JSAPI.name().equals(qdsOrderDTO.getTradeType())
                && PaymentModeEnum.SERVER_MERCHANT == PaymentModeEnum.getEnum(qdsOrderDTO.getIsPub());
        if (flag) {
            return StringUtils.isEmpty(qdsOrderDTO.getSubOpenid());
        }

        return false;
    }

    /**
     * 检查订单查询请求必填参数是否为空
     *
     * @param queryDTO
     */
    protected void checkQueryDTO(QdsQueryDTO queryDTO) {
        if (queryDTO == null
                || StringUtils.isEmpty(queryDTO.getQdsOrderNo())) {
            throw new NullPointerException("qdsOrderNo is null");
        }
        if (queryDTO.getChannelType() == null) {
            throw new NullPointerException("channelTypeEnum is null");
        }
    }


    protected void checkRefundQuery(RefundQueryDTO dto) {
        if (dto == null) {
            throw new NullPointerException();
        }
        if (StringUtils.isEmpty(dto.getChannelRefundNo()) && StringUtils.isEmpty(dto.getQdsRefundNo())) {
            throw new IllegalArgumentException("ChannelRefundNo and QdsRefundNo is null");
        }
    }

    protected void checkDownloadBillDTO(String statementDate, BillTypeEnum billType) {
        if (StringUtils.isEmpty(statementDate) || billType == null) {
            throw new NullPointerException("对账单下载必填参数为空");
        }
    }

    protected void checkRefundOrder(RefundOrder dto) {
        if (dto == null) {
            throw new NullPointerException();
        }
        if (dto.getOrderAmt() <= 0) {
            throw new IllegalArgumentException("OrderAmt is false");
        }
        if (StringUtils.isEmpty(dto.getQdsOrderNo()) && StringUtils.isEmpty(dto.getChannelOrderNo())) {
            throw new IllegalArgumentException("QdsOrderNo and ChannelOrderNo is null");
        }
        if (dto.getRefundAmt() <= 0) {
            throw new IllegalArgumentException("RefundAmt is false");
        }
        if (StringUtils.isEmpty(dto.getRefundOrderNo())) {
            throw new IllegalArgumentException("RefundOrderNo is false");
        }
    }
}
