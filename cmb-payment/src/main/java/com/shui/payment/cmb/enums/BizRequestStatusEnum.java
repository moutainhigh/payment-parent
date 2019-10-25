package com.shui.payment.cmb.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * A.5 业务请求状态
 *
 * @author code
 * @Title: BizRequestStatusEnum
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/10/1710:13 AM
 */
public enum BizRequestStatusEnum {
    AUT("等待审批"),
    NTE("终审完毕"),
    WCF("订单待确认（商务支付用）"),
    BNK("银行处理中"),//默认状态
    FIN("完成"),
    ACK("等待确认(委托贷款用)"),
    APD("待银行确认(国际业务用)"),
    OPR("数据接收中（代发）"),
    ;
    private String msg;

    private BizRequestStatusEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }


    private static Map<String, BizRequestStatusEnum> mapEnum = new HashMap<>();

    static {
        for (BizRequestStatusEnum channelTypeEnum : BizRequestStatusEnum.values()) {
            mapEnum.put(channelTypeEnum.name(), channelTypeEnum);
        }
    }

    public static BizRequestStatusEnum getEnum(String val) {
        return mapEnum.get(val);
    }
}
