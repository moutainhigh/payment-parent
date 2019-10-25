package com.shui.payment.cmb;

import com.shui.payment.cmb.parameters.DirectPayRequest;
import com.shui.payment.cmb.parameters.PayQueryListRequest;
import com.shui.payment.cmb.parameters.PayQueryRequest;

/**
 * @author code
 * @Title: CmbBankPaymentService
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/10/255:12 PM
 */
public interface CmbBankPaymentService {
    /**
     * 银行支付接口
     *
     * @param directPayRequest
     * @param responseClass
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T bankPay(DirectPayRequest directPayRequest, Class<T> responseClass) throws Exception;

    /**
     * 按照日期批量查询支付结果
     *
     * @param request
     * @param responseClass
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T bankPayBatchQuery(PayQueryListRequest request, Class<T> responseClass) throws Exception;

    /**
     * 支付结果查询-按业务参考号
     * @param request
     * @param responseClass
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T bankPayQuery(PayQueryRequest request, Class<T> responseClass) throws Exception;
}
