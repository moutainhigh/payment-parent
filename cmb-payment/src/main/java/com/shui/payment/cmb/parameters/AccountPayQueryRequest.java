package com.shui.payment.cmb.parameters;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 2.3 查询账户交易信息 请求bean
 *
 * @author code
 * @Title: AccountPayQueryRequest
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/11/115:54 PM
 */
public class AccountPayQueryRequest {
    /**
     * 分行号 附录A1
     * 分行号和分行名
     * 称不能同时为空
     */
    @JSONField(name = "BBKNBR")
    private String payAreaCode;
    /**
     * 分行名称 附录A1
     */
    @JSONField(name = "C_BBKNBR")
    private String payAreaName;
    /**
     * 账号
     */
    @JSONField(name = "ACCNBR")
    private String payAccountNo;


    /**
     * 起始日期 20170417
     * 与结束日期的间
     * 隔不能超过 100 天
     */
    @JSONField(name = "BGNDAT")
    private String startDate;
    /**
     * 结束日期 20170418
     * 与结束日期的间
     * 隔不能超过 100 天
     */
    @JSONField(name = "ENDDAT")
    private String endDate;


    /**
     * 最小金额 元
     * 默认 0.00
     */
    @JSONField(name = "LOWAMT")
    private String minAmt;
    /**
     * 最大金额 元
     * 默 认
     * 9999999999999.9
     * 9
     */
    @JSONField(name = "HGHAMT")
    private String maxAmt;
    /**
     * 借贷码
     * C：收入
     * D：支出
     */
    @JSONField(name = "AMTCDR")
    private String typeCode;


    public String getPayAreaCode() {
        return payAreaCode;
    }

    public AccountPayQueryRequest setPayAreaCode(String payAreaCode) {
        this.payAreaCode = payAreaCode;
        return this;
    }

    public String getPayAreaName() {
        return payAreaName;
    }

    public AccountPayQueryRequest setPayAreaName(String payAreaName) {
        this.payAreaName = payAreaName;
        return this;
    }

    public String getPayAccountNo() {
        return payAccountNo;
    }

    public AccountPayQueryRequest setPayAccountNo(String payAccountNo) {
        this.payAccountNo = payAccountNo;
        return this;
    }

    public String getStartDate() {
        return startDate;
    }

    public AccountPayQueryRequest setStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public String getEndDate() {
        return endDate;
    }

    public AccountPayQueryRequest setEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public String getMinAmt() {
        return minAmt;
    }

    public AccountPayQueryRequest setMinAmt(String minAmt) {
        this.minAmt = minAmt;
        return this;
    }

    public String getMaxAmt() {
        return maxAmt;
    }

    public AccountPayQueryRequest setMaxAmt(String maxAmt) {
        this.maxAmt = maxAmt;
        return this;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public AccountPayQueryRequest setTypeCode(String typeCode) {
        this.typeCode = typeCode;
        return this;
    }
}
