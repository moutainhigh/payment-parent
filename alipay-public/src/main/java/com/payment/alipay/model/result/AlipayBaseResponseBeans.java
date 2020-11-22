package com.payment.alipay.model.result;


import com.payment.alipay.enums.TradeStatus;

/**
 * @author code
 * @Title: AlipayBaseResponseBeans
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/9/1111:45 AM
 */
public class AlipayBaseResponseBeans extends BaseResponseBeans {
    protected TradeStatus tradeStatus;

    public TradeStatus getTradeStatus() {
        return tradeStatus;
    }

    public AlipayBaseResponseBeans setTradeStatus(TradeStatus tradeStatus) {
        this.tradeStatus = tradeStatus;
        return this;
    }
}
