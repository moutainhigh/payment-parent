package com.payment.alipay.model.builder;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.StringUtils;

/**
 * 关单接口
 *
 * @author code
 * @Title: AlipayTradeCloseRequestBuilder
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/9/232:06 PM
 */
public class AlipayTradeCloseRequestBuilder extends RequestBuilder {

    private BizContent bizContent = new BizContent();

    public AlipayTradeCloseRequestBuilder setBizContent(BizContent bizContent) {
        this.bizContent = bizContent;
        return this;
    }

    @Override
    public boolean validate() {

        if (StringUtils.isEmpty(bizContent.outTradeNo) && StringUtils.isEmpty(bizContent.tradeNo)) {
            throw new IllegalStateException("outTradeNo and tradeNo can not be NULL!");
        }

        return true;
    }

    @Override
    public Object getBizContent() {
        return bizContent;
    }

    public String getOutTradeNo() {
        return bizContent.outTradeNo;
    }

    public AlipayTradeCloseRequestBuilder setOutTradeNo(String outTradeNo) {
        bizContent.outTradeNo = outTradeNo;
        return this;
    }

    public String getTradeNo() {
        return bizContent.tradeNo;
    }

    public AlipayTradeCloseRequestBuilder setTradeNo(String tradeNo) {
        bizContent.tradeNo = tradeNo;
        return this;
    }

    public String getOperatorId() {
        return bizContent.operatorId;
    }

    public AlipayTradeCloseRequestBuilder setOperatorId(String operatorId) {
        bizContent.operatorId = operatorId;
        return this;
    }

    public static class BizContent {
        // 订单支付时传入的商户订单号,和支付宝交易号不能同时为空。 trade_no,out_trade_no如果同时存在优先取trade_no
        @SerializedName("out_trade_no")
        private String outTradeNo;

        // 该交易在支付宝系统中的交易流水号。最短 16 位，最长 64 位。和out_trade_no不能同时为空，如果同时传了 out_trade_no和 trade_no，则以 trade_no为准。
        @SerializedName("trade_no")
        private String tradeNo;
        //卖家端自定义的的操作员 ID
        @SerializedName("operator_id")
        private String operatorId;


        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("BizContent{");
            sb.append("outTradeNo='").append(outTradeNo).append('\'');
            sb.append(", tradeNo='").append(tradeNo).append('\'');
            sb.append(", operatorId='").append(operatorId).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
