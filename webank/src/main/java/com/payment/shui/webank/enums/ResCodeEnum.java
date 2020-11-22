package com.payment.shui.webank.enums;

/**
 * 微众调用合作方返回码定义
 *
 * @author code
 * @Title: ResCodeEnum
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/27 11:47
 */
public enum ResCodeEnum {
    SUCCESS("0000", "系统处理成功"),
    ERR_CONTEXT("0001", "报文格式错误"),
    ERR_PARAM("0002", "未满足接口输入要求"),
    ERR_CONTEXT_DECODE("0103", "报文解析失败"),
    ERR_SIGN("0010", "签名验证失败"),
    ERR_TIMESTAMP("0012", "时间戳过期"),
    ERR_SSL("0016", "SSL认证失败"),


    ;

    private String code;
    private String desc;

    private ResCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
