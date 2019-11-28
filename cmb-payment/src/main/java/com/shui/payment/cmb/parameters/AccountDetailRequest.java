package com.shui.payment.cmb.parameters;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 2.2 查询账户详细信息request
 *
 * @author code
 * @Title: AccountDetailRequest
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/11/2510:24 AM
 */
public class AccountDetailRequest {
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

    public String getPayAreaCode() {
        return payAreaCode;
    }

    public AccountDetailRequest setPayAreaCode(String payAreaCode) {
        this.payAreaCode = payAreaCode;
        return this;
    }

    public String getPayAreaName() {
        return payAreaName;
    }

    public AccountDetailRequest setPayAreaName(String payAreaName) {
        this.payAreaName = payAreaName;
        return this;
    }

    public String getPayAccountNo() {
        return payAccountNo;
    }

    public AccountDetailRequest setPayAccountNo(String payAccountNo) {
        this.payAccountNo = payAccountNo;
        return this;
    }
}
