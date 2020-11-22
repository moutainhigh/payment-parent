package com.payment.alipay.model.result;

import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.payment.alipay.enums.TradeStatus;

/**
 * 对账单查询返回bean
 *
 * @author code
 * @Title: AlipayF2FDownloadResult
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/9/164:12 PM
 */
public class AlipayF2FDownloadResult extends AlipayBaseResponseBeans implements Result {
    private AlipayDataDataserviceBillDownloadurlQueryResponse response;

    public AlipayF2FDownloadResult(AlipayDataDataserviceBillDownloadurlQueryResponse response) {
        this.response = response;
    }

    public AlipayDataDataserviceBillDownloadurlQueryResponse getResponse() {
        return response;
    }

    public void setResponse(AlipayDataDataserviceBillDownloadurlQueryResponse response) {
        this.response = response;
    }

    @Override
    public boolean isTradeSuccess() {
        return response != null &&
                TradeStatus.SUCCESS.equals(tradeStatus);
    }
}
