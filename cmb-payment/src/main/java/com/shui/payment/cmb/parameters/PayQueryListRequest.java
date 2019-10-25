package com.shui.payment.cmb.parameters;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 支付结果列表查询 请求参数
 *
 * @author code
 * @Title: PayQueryListRequest
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/10/162:35 PM
 */
public class PayQueryListRequest {


    /**
     * 业务类型
     * N02020: 内部转帐
     * N02030: 支付
     * N02031: 直接支付
     * N02040: 集团支付
     * N02041: 直接集团支付
     */
    @JSONField(name = "BUSCOD")
    private String bizCode;
    /**
     * 业务模式
     * 指定的单一模式
     * 如00001
     */
    @JSONField(name = "BUSMOD")
    private String bizMode;
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

    //允许为空的部分

    /**
     * 日期类型  默认为A
     * A：经办日期；
     * B：期望日期
     */
    @JSONField(name = "DATFLG")
    private String dateType;
    /**
     * 保留字 50
     */
    @JSONField(name = "RSV50Z")
    private String remark;

    public String getBizCode() {
        return bizCode;
    }

    public PayQueryListRequest setBizCode(String bizCode) {
        this.bizCode = bizCode;
        return this;
    }

    public String getBizMode() {
        return bizMode;
    }

    public PayQueryListRequest setBizMode(String bizMode) {
        this.bizMode = bizMode;
        return this;
    }

    public String getStartDate() {
        return startDate;
    }

    public PayQueryListRequest setStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public String getEndDate() {
        return endDate;
    }

    public PayQueryListRequest setEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public String getDateType() {
        return dateType;
    }

    public PayQueryListRequest setDateType(String dateType) {
        this.dateType = dateType;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public PayQueryListRequest setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
