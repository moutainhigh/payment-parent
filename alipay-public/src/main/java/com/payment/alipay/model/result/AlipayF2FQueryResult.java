package com.payment.alipay.model.result;

import com.alipay.api.response.AlipayTradeQueryResponse;
import com.payment.alipay.enums.TradeStatus;

/**
 * Created by liuyangkly on 15/8/27.
 */
public class AlipayF2FQueryResult extends AlipayBaseResponseBeans implements Result {
    private AlipayTradeQueryResponse response;

    public AlipayF2FQueryResult(AlipayTradeQueryResponse response) {
        this.response = response;
    }


    public void setResponse(AlipayTradeQueryResponse response) {
        this.response = response;
    }


    public AlipayTradeQueryResponse getResponse() {
        return response;
    }

    @Override
    public boolean isTradeSuccess() {
        return response != null &&
                TradeStatus.SUCCESS.equals(tradeStatus);
    }
}
