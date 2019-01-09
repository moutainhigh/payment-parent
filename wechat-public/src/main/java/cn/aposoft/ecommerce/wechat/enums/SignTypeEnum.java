package cn.aposoft.ecommerce.wechat.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 签名类型
 *
 * @author code
 * @Title: SignTypeEnum
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/9/9上午11:15
 */
public enum SignTypeEnum {
    MD5, HMACSHA256
    //无加密操作
    , NO_ENCRYPT;


    private static Map<String, SignTypeEnum> mapEnum = new HashMap<>();

    static {
        for (SignTypeEnum signTypeEnum : SignTypeEnum.values()) {
            mapEnum.put(signTypeEnum.name(), signTypeEnum);
        }
    }

    public static SignTypeEnum getEnum(String key) {
        return mapEnum.get(key);
    }
}
