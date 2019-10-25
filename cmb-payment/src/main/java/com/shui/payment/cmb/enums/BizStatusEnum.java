package com.shui.payment.cmb.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * A.6 业务处理结果
 *
 * @author code
 * @Title: BizStatusEnum
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/10/1710:38 AM
 */
public enum BizStatusEnum {
    S("银行支付成功"),
    F("银行支付失败"),
    B("银行支付被退票"),
    R("企业审批否决"),
    D("企业过期不审批"),
    C("企业撤销"),
    M("商务支付"),
    V("委托贷款被借款方拒绝"),
    U("银行挂账"),
    T("退款"),
    PROCESSING("消息处理中");

    private String msg;

    private BizStatusEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }


    private static Map<String, BizStatusEnum> mapEnum = new HashMap<>();

    static {
        for (BizStatusEnum bizStatusEnum : BizStatusEnum.values()) {
            mapEnum.put(bizStatusEnum.name(), bizStatusEnum);
        }
    }

    public static BizStatusEnum getEnum(String val) {
        return mapEnum.get(val);
    }
}
