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
    //微信公众号，默认
    WECHAT
    //微信小程序支付(由于小程序支付的交易类型与公众号相同，故需要在此通过渠道类型进行定位)
    , WECHAT_APPLET
    //微信，企业付款
    , WECHAT_ENTERPRISE
    //支付宝
    , ALIPAY
    //融韵通
    , RONG_YUN_TONG,
    //银企直联-招行
    BANK_CMB;

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
     * 检查是否为微信通道（公众号，小程序）
     *
     * @param channelTypeEnum
     * @return
     */
    public static boolean isWechatChannel(ChannelTypeEnum channelTypeEnum) {
        return ChannelTypeEnum.WECHAT == channelTypeEnum
                || ChannelTypeEnum.WECHAT_APPLET == channelTypeEnum;
    }

    /**
     * 是否为微信企业付款渠道
     *
     * @param channelTypeEnum
     * @return
     */
    public static boolean isWechatEnterPriseChannel(ChannelTypeEnum channelTypeEnum) {
        return ChannelTypeEnum.WECHAT_ENTERPRISE == channelTypeEnum;
    }

    /**
     * 检查是否为融韵通渠道
     *
     * @param channelTypeEnum
     * @return
     */
    public static boolean isRongYunTongChannel(ChannelTypeEnum channelTypeEnum) {
        return ChannelTypeEnum.RONG_YUN_TONG == channelTypeEnum;
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

    /**
     * 检查是否为银企直联通道
     *
     * @param channelTypeEnum
     * @return
     */
    public static boolean isBankCmbChannel(ChannelTypeEnum channelTypeEnum) {
        return ChannelTypeEnum.BANK_CMB == channelTypeEnum;
    }


}
