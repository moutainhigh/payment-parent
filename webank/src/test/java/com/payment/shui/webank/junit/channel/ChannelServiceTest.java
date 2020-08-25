package com.payment.shui.webank.junit.channel;

import com.alibaba.fastjson.JSON;
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
    public void getWebankConfig(){
        WebankConfig config = channelService.getWebankConfig();
        System.out.println(JSON.toJSONString(config));
        Assertions.assertNotNull(config);
    }

}
