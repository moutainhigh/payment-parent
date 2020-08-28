package com.payment.shui.webank.junit.channel;

import com.alibaba.fastjson.JSON;
import com.payment.shui.webank.beans.protocol.TokenReqData;
import com.payment.shui.webank.beans.protocol.TokenResData;
import com.payment.shui.webank.beans.protocol.WebankResData;
import com.payment.shui.webank.channel.ChannelService;
import com.payment.shui.webank.channel.constant.WebankConfig;
import com.payment.shui.webank.junit.BasicWebankJunitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author code
 * @Title: ChannelServiceTest
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/25 15:37
 */
public class ChannelServiceTest extends BasicWebankJunitTest {

    @Autowired
    private ChannelService channelService;

    @Test
    public void getWebankConfig() {
        WebankConfig config = channelService.getWebankConfig();
        System.out.println(JSON.toJSONString(config));
        Assertions.assertNotNull(config);
    }

    @Test
    public void getAccessToken() {

        TokenReqData reqData = getTokenReqData();
        WebankResData resData = channelService.getAccessToken(reqData);
        System.out.println("token response:" + JSON.toJSONString(resData));
        Assertions.assertNotNull(resData);

    }

    private TokenReqData getTokenReqData() {
        WebankConfig config = channelService.getWebankConfig();

        TokenReqData reqData = new TokenReqData();
        reqData.setApp_id(config.getAppId())
                .setGrant_type("")
                .setSecret(config.getSecret())
                .setVersion(config.getVersion_2());
        return reqData;
    }

}
