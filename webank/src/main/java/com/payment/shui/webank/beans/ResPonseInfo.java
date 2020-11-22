package com.payment.shui.webank.beans;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author code
 * @Title: ResPonseInfo
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/27 11:53
 */
public class ResPonseInfo {
    @JSONField(name = "CODE")
    private String CODE;
    @JSONField(name = "DESC")
    private String DESC;

    public String getCODE() {
        return CODE;
    }

    public ResPonseInfo setCODE(String CODE) {
        this.CODE = CODE;
        return this;
    }

    public String getDESC() {
        return DESC;
    }

    public ResPonseInfo setDESC(String DESC) {
        this.DESC = DESC;
        return this;
    }
}
