package com.payment.shui.webank.beans.protocol;

/**
 * @author code
 * @Title: TicketsBean
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/25 17:31
 */
public class TicketsBean {
    /**
     * ticket失效的绝对时间戳，单位毫秒
     */
    private String expire_time;
    /**
     * ticket的最大生存时间，单位秒，平台纬度默认为3600秒，用户纬度默认为300秒
     */
    private int expire_in;
    /**
     * ticket值
     */
    private String value;

    public String getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(String expire_time) {
        this.expire_time = expire_time;
    }

    public int getExpire_in() {
        return expire_in;
    }

    public void setExpire_in(int expire_in) {
        this.expire_in = expire_in;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
