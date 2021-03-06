package com.payment.shui.webank.beans.protocol;

/**
 * @author code
 * @Title: TokenBean
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/25 16:40
 */
public class TokenReqData {
    /**
     * 应用ID
     */
    private String app_id;
    /**
     * 应用密钥
     */
    private String secret;
    /**
     * 授权类型
     */
    private String grant_type;
    /**
     * 版本号
     */
    private String version;

    public String getApp_id() {
        return app_id;
    }

    public TokenReqData setApp_id(String app_id) {
        this.app_id = app_id;
        return this;
    }

    public String getSecret() {
        return secret;
    }

    public TokenReqData setSecret(String secret) {
        this.secret = secret;
        return this;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public TokenReqData setGrant_type(String grant_type) {
        this.grant_type = grant_type;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public TokenReqData setVersion(String version) {
        this.version = version;
        return this;
    }
}
