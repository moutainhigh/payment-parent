package com.payment.alipay.model.result;

import com.alipay.api.response.AlipayTradeRefundResponse;
import com.payment.alipay.enums.TradeStatus;

/**
 * Created by liuyangkly on 15/8/27.
 */
public class AlipayF2FRefundResult extends AlipayBaseResponseBeans implements Result {

    private AlipayTradeRefundResponse response;

    public AlipayF2FRefundResult(AlipayTradeRefundResponse response) {
        this.response = response;
    }


    public void setResponse(AlipayTradeRefundResponse response) {
        this.response = response;
    }


    public AlipayTradeRefundResponse getResponse() {
        return response;
    }

    @Override
    public boolean isTradeSuccess() {
        return response != null &&
                TradeStatus.SUCCESS.equals(tradeStatus);
    }
}
