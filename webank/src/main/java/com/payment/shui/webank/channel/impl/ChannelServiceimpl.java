package com.payment.shui.webank.channel.impl;

import com.payment.shui.webank.channel.ChannelService;
import com.payment.shui.webank.channel.constant.WebankConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
