package com.payment.alipay.model.result;

import com.alipay.api.response.AlipayTradeCloseResponse;
import com.payment.alipay.enums.TradeStatus;

/**
 * 关单接口返回对象
 *
 * @author code
 * @Title: AlipayF2FCloseOrderResult
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/9/232:28 PM
 */
public class AlipayF2FCloseOrderResult extends AlipayBaseResponseBeans implements Result {

    private AlipayTradeCloseResponse response;

    public AlipayF2FCloseOrderResult(AlipayTradeCloseResponse response) {
        this.response = response;
    }

    public AlipayTradeCloseResponse getResponse() {
        return response;
    }

    public AlipayF2FCloseOrderResult setResponse(AlipayTradeCloseResponse response) {
        this.response = response;
        return this;
    }

    @Override
    public boolean isTradeSuccess() {
        return response != null &&
                TradeStatus.SUCCESS.equals(tradeStatus);
    }
}
