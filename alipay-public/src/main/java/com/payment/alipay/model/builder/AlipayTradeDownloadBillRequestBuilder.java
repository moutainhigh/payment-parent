package com.payment.alipay.model.builder;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.StringUtils;

/**
 * 对账单下载请求bean
 *
 * @author code
 * @Title: AlipayTradeDownloadBillRequestBuilder
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/9/163:37 PM
 */
public class AlipayTradeDownloadBillRequestBuilder extends RequestBuilder {

    private BizContent bizContent = new BizContent();

    public AlipayTradeDownloadBillRequestBuilder setBizContent(BizContent bizContent) {
        this.bizContent = bizContent;
        return this;
    }

    @Override
    public boolean validate() {

        if (StringUtils.isEmpty(bizContent.billType) || StringUtils.isEmpty(bizContent.billDate)) {
            throw new IllegalStateException("billType and billDate can not be NULL!");
        }
        return true;
    }

    @Override
    public Object getBizContent() {
        return bizContent;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AlipayTradeDownloadBillRequestBuilder{");
        sb.append("bizContent=").append(bizContent);
        sb.append(", super=").append(super.toString());
        sb.append('}');
        return sb.toString();
    }


    public String getBillType() {
        return bizContent.billType;
    }

    public void setBillType(String billType) {
        bizContent.billType = billType;
    }

    public String getBillDate() {
        return bizContent.billDate;
    }

    public void setBillDate(String billDate) {
        bizContent.billDate = billDate;
    }


    public static class BizContent {
        // 账单类型,商户通过接口或商户经开放平台授权后其所属服务商通过接口可以获取以下账单类型：trade、signcustomer；trade指商户基于支付宝交易收单的业务账单；signcustomer是指基于商户支付宝余额收入及支出等资金变动的帐务账单
        @SerializedName("bill_type")
        private String billType;

        // 账单时间：日账单格式为yyyy-MM-dd，最早可下载2016年1月1日开始的日账单；月账单格式为yyyy-MM，最早可下载2016年1月开始的月账单。
        @SerializedName("bill_date")
        private String billDate;


        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("BizContent{");
            sb.append("billType='").append(billType).append('\'');
            sb.append(", billDate='").append(billDate).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
