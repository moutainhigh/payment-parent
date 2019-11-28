package com.shui.payment.cmb;

import com.shui.payment.cmb.parameters.*;

import java.util.List;

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
     * 3.6 银行支付接口-支持批量支付
     *
     * @param listRequest
     * @param responseClass
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T bankBatchPay(List<DirectPayRequest> listRequest, Class<T> responseClass) throws Exception;

    /**
     * 3.3 按照日期批量查询支付结果
     *
     * @param request
     * @param responseClass
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T bankPayBatchQuery(PayQueryListRequest request, Class<T> responseClass) throws Exception;

    /**
     * 3.11 支付结果查询-按业务参考号
     *
     * @param request
     * @param responseClass
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T bankPayQuery(PayQueryRequest request, Class<T> responseClass) throws Exception;

    /**
     * 2.3 查询账户交易信息
     *
     * @param request
     * @param responseClass
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T bankAccountTradeQuery(AccountPayQueryRequest request, Class<T> responseClass) throws Exception;

    /**
     * 2.2 查询账户详细信息
     * 支持多账户查询。
     *
     * @param listRequest
     * @param responseClass
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T bankAccountDetailQuery(List<AccountDetailRequest> listRequest, Class<T> responseClass) throws Exception;
}
