package com.payment.shui.webank.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author code
 * @Title: InitBean
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/25 16:36
 */
public class InitBean {
    private String app_id;
    private String version="2.0.0";
    private String nonce;
    private String ticket;
    private String sign;
    private String type;


    public String getApp_id() {
        return app_id;
    }

    public InitBean setApp_id(String app_id) {
        this.app_id = app_id;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public InitBean setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getNonce() {
        return nonce;
    }

    public InitBean setNonce(String nonce) {
        this.nonce = nonce;
        return this;
    }

    public String getTicket() {
        return ticket;
    }

    public InitBean setTicket(String ticket) {
        this.ticket = ticket;
        return this;
    }

    public String getSign() {
        return sign;
    }

    public InitBean setSign(String sign) {
        this.sign = sign;
        return this;
    }

    public String getType() {
        return type;
    }

    public InitBean setType(String type) {
        this.type = type;
        return this;
    }

    public List getList(){
        List values = new ArrayList();
        values.add(app_id);
        values.add(version);
        values.add(nonce);
        values.add(type);
        return values;
    }
}
