package com.shui.payment.cmb.parameters;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 3.11 支付结果查询-按业务参考号
 *
 * @author code
 * @Title: PayQueryRequest
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/10/176:19 PM
 */
public class PayQueryRequest {
    /**
     * BUSCOD 业务类型
     */
    private String bizType;
    /**
     * YURREF 业务参考号
     */
    private String qdsOrderNo;
    /**
     * 起始日期 20170417
     * 起始结束日期间隔不可超过一周
     */
    @JSONField(name = "BGNDAT")
    private String startDate;
    /**
     * 结束日期 20170418
     * 起始结束日期间隔不可超过一周
     */
    @JSONField(name = "ENDDAT")
    private String endDate;

    public String getBizType() {
        return bizType;
    }

    public PayQueryRequest setBizType(String bizType) {
        this.bizType = bizType;
        return this;
    }

    public String getQdsOrderNo() {
        return qdsOrderNo;
    }

    public PayQueryRequest setQdsOrderNo(String qdsOrderNo) {
        this.qdsOrderNo = qdsOrderNo;
        return this;
    }

    public String getStartDate() {
        return startDate;
    }

    public PayQueryRequest setStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public String getEndDate() {
        return endDate;
    }

    public PayQueryRequest setEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }
}
