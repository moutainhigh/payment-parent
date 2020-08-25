package com.payment.shui.webank.channel.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author code
 * @Title: WebankConfig
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/25 15:31
 */
@Component
public class WebankConfig {
    @Value("${webank.app_id}")
    public  String appId;

    @Value("${webank.secret}")
    public  String secret;

    @Value("${f.webank.http.url}")
    public  String httpUrl;

    @Value("${access_token.path.url}")
    public  String accessTokenUrl;

    @Value("${ticket.path.url}")
    public String apiTicketUrl;

    public String getHttpUrl() {
        return httpUrl;
    }

    public String getAppId() {
        return appId;
    }

    public String getSecret() {
        return secret;
    }

    public String getAccessTokenUrl() {
        return accessTokenUrl;
    }

    public String getApiTicketUrl() {
        return apiTicketUrl;
    }
}
