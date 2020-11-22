package com.payment.alipay.dto;


import com.alibaba.fastjson.annotation.JSONField;

/**
 * 关闭订单请求参数
 *
 * @author code
 * @Title: CloseDTO
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/10/26下午2:59
 */
public class CloseDTO extends BasicDTO {



    /**
     * 支付系统订单号
     */
    @JSONField(name = "out_trade_no")
    private String qdsOrderNo;



    public String getQdsOrderNo() {
        return qdsOrderNo;
    }

    public CloseDTO setQdsOrderNo(String qdsOrderNo) {
        this.qdsOrderNo = qdsOrderNo;
        return this;
    }


}
