package com.payment.alipay.enums;

/**
 * @author code
 * @Title: BillTypeEnum
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/16下午3:21
 */
public enum BillTypeEnum {


    /**
     * 微信对账单类型
     * ALL，返回当日所有订单信息，默认值
     * SUCCESS，返回当日成功支付的订单
     * REFUND，返回当日退款订单
     * REVOKED，已撤销的订单
     */
    ALL("ALL"), SUCCESS("SUCCESS"),

    REFUND("REFUND"), REVOKED("REVOKED"),

    //-----支付宝部分------
    /**
     * trade指商户基于支付宝交易收单的业务账单；
     * signcustomer是指基于商户支付宝余额收入及支出等资金变动的帐务账单。
     */

    TRADE("trade"), SIGNCUSTOMER("signcustomer");
    private String value;

    private BillTypeEnum(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }

}
