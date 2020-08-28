package com.payment.shui.webank.channel;

import com.payment.shui.webank.beans.protocol.TokenReqData;
import com.payment.shui.webank.beans.protocol.TokenResData;
import com.payment.shui.webank.beans.protocol.WebankResData;
import com.payment.shui.webank.channel.constant.WebankConfig;

/**
 * @author code
 * @Title: ChannelService
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2020/8/2515:30
 */
public interface ChannelService {

    public WebankConfig getWebankConfig();

    /**
     * 获取token
     * @param reqData
     * @return
     */
    public WebankResData getAccessToken(TokenReqData reqData);
}
