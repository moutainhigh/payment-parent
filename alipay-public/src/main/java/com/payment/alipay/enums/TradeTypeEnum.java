package com.payment.alipay.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付类型
 *
 * @author code
 * @Title: TradeTypeEnum
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/13下午4:00
 */
public enum TradeTypeEnum {


    //微信支付类型
    /**
     * 小程序，公众号支付
     */
    JSAPI,
    /**
     * 二维码支付
     */
    NATIVE,
    /**
     * 退款
     */
    REFUND,
    /**
     * h5支付
     */
    MWEB,
    /**
     * APP支付
     */
    APP,

    //融韵通支付类型
    /**
     * 移动支付类型
     */
    WAP,
    ;

    private static Map<String, TradeTypeEnum> mapEnum = new HashMap<>();

    static {
        for (TradeTypeEnum typeEnum : TradeTypeEnum.values()) {
            mapEnum.put(typeEnum.name(), typeEnum);
        }
    }

    public static TradeTypeEnum getEnum(String typeEnum) {
        return mapEnum.get(typeEnum);
    }
}
