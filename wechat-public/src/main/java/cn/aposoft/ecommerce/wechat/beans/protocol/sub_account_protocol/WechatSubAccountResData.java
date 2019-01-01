package cn.aposoft.ecommerce.wechat.beans.protocol.sub_account_protocol;

import cn.aposoft.ecommerce.wechat.beans.protocol.BaseResponseBeans;

/**
 * 请求分账返回bean
 *
 * @author code
 * @Title: WechatSubAccountResData
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/12/18下午3:04
 */
public class WechatSubAccountResData extends BaseResponseBeans {

    /**
     * 微信支付订单号
     */
    private String transaction_id;
    /**
     * 调用接口提供的商户系统内部的分账单号
     */
    private String out_order_no;
    /**
     * 微信分账单号，微信系统返回的唯一标识
     */
    private String order_id;


    public String getTransaction_id() {
        return transaction_id;
    }

    public WechatSubAccountResData setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
        return this;
    }

    public String getOut_order_no() {
        return out_order_no;
    }

    public WechatSubAccountResData setOut_order_no(String out_order_no) {
        this.out_order_no = out_order_no;
        return this;
    }

    public String getOrder_id() {
        return order_id;
    }

    public WechatSubAccountResData setOrder_id(String order_id) {
        this.order_id = order_id;
        return this;
    }
}
