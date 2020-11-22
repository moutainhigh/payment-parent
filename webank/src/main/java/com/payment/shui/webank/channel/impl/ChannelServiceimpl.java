package com.payment.shui.webank.channel.impl;

import com.alibaba.fastjson.JSON;
import com.payment.shui.webank.beans.protocol.TokenReqData;
import com.payment.shui.webank.beans.protocol.TokenResData;
import com.payment.shui.webank.beans.protocol.WebankResData;
import com.payment.shui.webank.channel.ChannelService;
import com.payment.shui.webank.channel.constant.WebankConfig;
import com.payment.shui.webank.httpclient.HttpConfig;
import com.payment.shui.webank.httpclient.HttpClientConfig;
import com.payment.shui.webank.httpclient.HttpRequestUtil;
import com.payment.shui.webank.httpclient.HttpRequestUtilImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author code
 * @Title: ChannelServiceimpl
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/25 15:35
 */
@Service
public class ChannelServiceimpl implements ChannelService {

    @Autowired
    private WebankConfig webankConfig;

    @Override
    public WebankConfig getWebankConfig() {
        return webankConfig;
    }

    private WebankResData getResData(){
        WebankResData data = new WebankResData();
        data.setCode("1001");
        data.setMsg("error info");
        return data;
    }

    @Override
    public WebankResData getAccessToken(TokenReqData reqData) {
        String tokenUrl = getTokenUrl(reqData);

        HttpConfig httpConfig = new HttpClientConfig();
        HttpRequestUtil httpRequestUtil = HttpRequestUtilImpl.getInstance(httpConfig);

        try {
            String response = httpRequestUtil.get(tokenUrl);
            return JSON.parseObject(response,TokenResData.class);
        } catch (IOException e) {
            e.printStackTrace();
            return getResData();
        }


    }

    private String getTokenUrl(TokenReqData reqData) {
        String tokenBaseUrl = webankConfig.getHttpUrl() + webankConfig.getAccessTokenUrl();
        String url = tokenBaseUrl
                + "?app_id=" + reqData.getApp_id()
                + "&secret=" + reqData.getSecret()
                + "&grant_type=client_credential"
                + "&version=" + reqData.getVersion();

        return url;
    }
}
