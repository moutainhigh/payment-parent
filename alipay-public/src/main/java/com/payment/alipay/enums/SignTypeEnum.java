package com.payment.alipay.enums;

import java.io.Serializable;

/**
 * 签名类型枚举类
 * @author code
 * @Title: SignTypeEnum
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/9/5下午2:12
 */
public enum SignTypeEnum implements Serializable {

    //微信签名类型
    MD5("MD5"),HMACSHA256("HmacSHA256")

    ,RSA("RSA")
    ;
    private String name;
    private SignTypeEnum(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
