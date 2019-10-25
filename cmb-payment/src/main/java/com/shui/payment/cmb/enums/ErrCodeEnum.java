package com.shui.payment.cmb.enums;

/**
 * dubbo接口对外返回errorCode编码
 *
 * @author code
 * @Title: ErrCodeEnum
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/10/234:47 PM
 */
public enum ErrCodeEnum {
    NET_CONN_FAIL("网络连接失败"),
    EXECUTE_FAIL("执行失败"),
    DATA_FALSE("数据格式错误"),
    OTHER_FAIL("其他错误"),
    UNKNOWN("状态未知"),
    ;


    private String msg;
    private ErrCodeEnum(String msg){
        this.msg = msg;
    }


    public String getMsg() {
        return msg;
    }
}
