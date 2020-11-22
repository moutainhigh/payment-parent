package com.payment.alipay.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 退款查询bean
 *
 * @author code
 * @Title: RefundQueryDTO
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/31下午6:30
 */
public class RefundQueryDTO extends BasicDTO {


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
    private String qdsRefundNo;

    /**
     * 通道退款单号
     */
    @JSONField(name = "refund_id")
    private String channelRefundNo;


    public String getChannelOrderNo() {
        return channelOrderNo;
    }

    public RefundQueryDTO setChannelOrderNo(String channelOrderNo) {
        this.channelOrderNo = channelOrderNo;
        return this;
    }

    public String getQdsOrderNo() {
        return qdsOrderNo;
    }

    public RefundQueryDTO setQdsOrderNo(String qdsOrderNo) {
        this.qdsOrderNo = qdsOrderNo;
        return this;
    }

    public String getQdsRefundNo() {
        return qdsRefundNo;
    }

    public RefundQueryDTO setQdsRefundNo(String qdsRefundNo) {
        this.qdsRefundNo = qdsRefundNo;
        return this;
    }

    public String getChannelRefundNo() {
        return channelRefundNo;
    }

    public RefundQueryDTO setChannelRefundNo(String channelRefundNo) {
        this.channelRefundNo = channelRefundNo;
        return this;
    }

}
