package com.payment.alipay.model.result;

import com.alipay.api.response.AlipayTradePayResponse;
import com.payment.alipay.enums.TradeStatus;

/**
 * Created by liuyangkly on 15/8/26.
 */
public class AlipayF2FPayResult extends AlipayBaseResponseBeans implements Result {
    private AlipayTradePayResponse response;

    public AlipayF2FPayResult(AlipayTradePayResponse response) {
        this.response = response;
    }


    public void setResponse(AlipayTradePayResponse response) {
        this.response = response;
    }


    public AlipayTradePayResponse getResponse() {
        return response;
    }

    @Override
    public boolean isTradeSuccess() {
        return response != null &&
                TradeStatus.SUCCESS.equals(tradeStatus);
    }
}
