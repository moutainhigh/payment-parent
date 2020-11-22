package com.payment.alipay.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 订单查询bean
 *
 * @author code
 * @Title: QdsQueryDTO
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/29上午10:27
 */
public class QdsQueryDTO extends BasicDTO {


    /**
     * 千丁云系统订单号
     */
    @JSONField(name = "out_trade_no")
    private String qdsOrderNo;
    /**
     * 渠道订单号
     */
    private String channelOrderNo;


    public String getQdsOrderNo() {
        return qdsOrderNo;
    }

    public QdsQueryDTO setQdsOrderNo(String qdsOrderNo) {
        this.qdsOrderNo = qdsOrderNo;
        return this;
    }

    public String getChannelOrderNo() {
        return channelOrderNo;
    }

    public QdsQueryDTO setChannelOrderNo(String channelOrderNo) {
        this.channelOrderNo = channelOrderNo;
        return this;
    }
}
