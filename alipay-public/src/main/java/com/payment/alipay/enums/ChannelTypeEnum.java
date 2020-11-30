package com.payment.alipay.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 通道类型，每一个渠道的每一个账户，分配一个枚举类型
 *
 * @author code
 * @Title: ChannelTypeEnum
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/14上午9:47
 */
public enum ChannelTypeEnum {

    //支付宝
    ALIPAY;

    //渠道枚举map
    private static Map<String, ChannelTypeEnum> mapEnum = new HashMap<>();

    static {
        for (ChannelTypeEnum channelTypeEnum : ChannelTypeEnum.values()) {
            mapEnum.put(channelTypeEnum.name(), channelTypeEnum);
        }
    }

    public static ChannelTypeEnum getEnum(String name) {
        return mapEnum.get(name);
    }


    /**
     * 检查是否为支付宝渠道
     *
     * @param channelTypeEnum
     * @return
     */
    public static boolean isAlipayChannel(ChannelTypeEnum channelTypeEnum) {
        return ChannelTypeEnum.ALIPAY == channelTypeEnum;
    }


}
