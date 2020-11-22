package com.payment.alipay.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 付款模式类型
 *
 * @author code
 * @Title: PaymentModeEnum
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/5/282:57 PM
 */
public enum PaymentModeEnum {


    /**
     * 服务商模式
     */
    SERVER_MERCHANT(1),
    /**
     * 普通商户模式
     */
    GENERAL_MERCHANT(2),;

    private int code;

    private PaymentModeEnum(int code) {
        this.code = code;
    }

    //订单枚举map
    private static Map<String, PaymentModeEnum> mapEnum = new HashMap<>();


    public int getCode() {
        return code;
    }

    static {
        for (PaymentModeEnum paymentModeEnum : PaymentModeEnum.values()) {
            mapEnum.put(paymentModeEnum.name(), paymentModeEnum);
            mapEnum.put(String.valueOf(paymentModeEnum.getCode()), paymentModeEnum);
        }
    }

    /**
     * 根据名称获取枚举类型
     *
     * @param name
     * @return
     */
    public static PaymentModeEnum getEnum(String name) {
        return mapEnum.get(name);
    }

    /**
     * 根据code码获取枚举类型
     *
     * @param code
     * @return
     */
    public static PaymentModeEnum getEnum(int code) {
        return mapEnum.get(String.valueOf(code));
    }
}
