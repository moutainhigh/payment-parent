package com.payment.shui.webank.beans.protocol;

/**
 * @author code
 * @Title: TokenResponse
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/25 16:44
 */
public class TokenResData extends WebankResData {

    private String access_token;
    /**
     * 效的绝对时间戳，单位毫秒
     */
    private String expire_time;
    /**
     * 最大生存时间，单位秒，默认为3600秒
     */
    private String expire_in;

    public String getAccess_token() {
        return access_token;
    }

    public TokenResData setAccess_token(String access_token) {
        this.access_token = access_token;
        return this;
    }

    public String getExpire_time() {
        return expire_time;
    }

    public TokenResData setExpire_time(String expire_time) {
        this.expire_time = expire_time;
        return this;
    }

    public String getExpire_in() {
        return expire_in;
    }

    public TokenResData setExpire_in(String expire_in) {
        this.expire_in = expire_in;
        return this;
    }
}
