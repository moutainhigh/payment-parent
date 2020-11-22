package com.payment.alipay;


import com.payment.alipay.dto.*;
import com.payment.alipay.enums.BillTypeEnum;
import com.payment.alipay.enums.SignTypeEnum;

import java.util.List;
import java.util.Map;


/**
 * @author code
 * @Title: PaymentServiceHandler
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/19上午10:13
 */
public interface PaymentServiceHandler extends AutoCloseable {

    /**
     * 实现支付订单的发送及返回值的返回
     * 不需要证书
     *
     * @param qdsOrderDTO   下单请求信息
     * @param responseClass 返回结果对象类型
     * @return 订单预支付处理结果
     * @author
     */
    <T> T pay(QdsOrderDTO qdsOrderDTO, Class<T> responseClass) throws Exception;

    /**
     * 企业付款接口
     *
     * @param qdsOrderDTO
     * @param responseClass
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T bizPay(QdsOrderDTO qdsOrderDTO, Class<T> responseClass) throws Exception;

    /**
     * 订单查询
     *
     * @param queryDTO
     * @param responseClass 返回结果对象bean
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T query(QdsQueryDTO queryDTO, Class<T> responseClass) throws Exception;

    /**
     * 对账单下载
     *
     * @param statementDate
     * @param billType
     * @return
     * @throws Exception
     */
    <T> List<T> downloadBillFromChannel(String statementDate, BillTypeEnum billType) throws Exception;

    /**
     * 退款
     *
     * @param refundOrder
     * @param responseClass 返回结果对象bean
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T refund(RefundOrder refundOrder, Class<T> responseClass) throws Exception;

    /**
     * 退款查询
     *
     * @param refundQueryDTO
     * @param responseClass  返回结果对象bean
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T refundQuery(RefundQueryDTO refundQueryDTO, Class<T> responseClass) throws Exception;

    /**
     * 签名校验,不必区分渠道
     *
     * @param data     待校验的签名数据
     * @param signType 签名类型
     * @return
     */
    boolean verifySign(String data, String signType) throws Exception;

    /**
     * 获取签名值
     *
     * @param paramsMap
     * @param signType
     * @return
     */
    String createSign(Map<String, String> paramsMap, SignTypeEnum signType) throws Exception;

    <T> T closeOrder(CloseDTO closeOrderDTO, Class<T> responseClass) throws Exception;


    @Override
    void close() throws Exception;
}
