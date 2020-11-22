package com.payment.alipay.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.payment.alipay.enums.ChannelTypeEnum;

import java.io.Serializable;

/**
 * 退款申请bean
 * 使用@JSONField要求属性名首字母小写
 *
 * @author code
 * @Title: RefundOrder
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/31上午9:47
 */
public class RefundOrder implements Serializable {


    /**
     * 通道订单号
     */
    @JSONField(name = "transaction_id")
    private String channelOrderNo;

    /**
     * 千丁云系统订单号
     */
    @JSONField(name = "out_trade_no")
    private String qdsOrderNo;


    /**
     * 千丁云系统退款订单号
     */
    @JSONField(name = "out_refund_no")
    private String refundOrderNo;

    /**
     * 订单总金额
     */
    @JSONField(name = "total_fee")
    private int orderAmt;
    /**
     * 退款金额
     */
    @JSONField(name = "refund_fee")
    private int refundAmt;

    @JSONField(name = "notify_url")
    private String refundNotifyUrl;

    /**
     * 退款原因
     */
    @JSONField(name = "refund_desc")
    private String refundReason;
    /**
     * 支付通道类型
     */
    private ChannelTypeEnum channelType;


    public String getChannelOrderNo() {
        return channelOrderNo;
    }

    public RefundOrder setChannelOrderNo(String channelOrderNo) {
        this.channelOrderNo = channelOrderNo;
        return this;
    }

    public String getQdsOrderNo() {
        return qdsOrderNo;
    }

    public RefundOrder setQdsOrderNo(String qdsOrderNo) {
        this.qdsOrderNo = qdsOrderNo;
        return this;
    }

    public String getRefundOrderNo() {
        return refundOrderNo;
    }

    public RefundOrder setRefundOrderNo(String refundOrderNo) {
        this.refundOrderNo = refundOrderNo;
        return this;
    }

    public int getOrderAmt() {
        return orderAmt;
    }

    public RefundOrder setOrderAmt(int orderAmt) {
        this.orderAmt = orderAmt;
        return this;
    }

    public int getRefundAmt() {
        return refundAmt;
    }

    public RefundOrder setRefundAmt(int refundAmt) {
        this.refundAmt = refundAmt;
        return this;
    }

    public ChannelTypeEnum getChannelType() {
        return channelType;
    }

    public RefundOrder setChannelType(ChannelTypeEnum channelType) {
        this.channelType = channelType;
        return this;
    }

    public String getRefundNotifyUrl() {
        return refundNotifyUrl;
    }

    public RefundOrder setRefundNotifyUrl(String refundNotifyUrl) {
        this.refundNotifyUrl = refundNotifyUrl;
        return this;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public RefundOrder setRefundReason(String refundReason) {
        this.refundReason = refundReason;
        return this;
    }
}
