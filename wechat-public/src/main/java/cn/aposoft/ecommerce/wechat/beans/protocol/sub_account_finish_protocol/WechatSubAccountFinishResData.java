package cn.aposoft.ecommerce.wechat.beans.protocol.sub_account_finish_protocol;


import cn.aposoft.ecommerce.wechat.beans.protocol.BaseResponseBeans;

/**
 * @author code
 * @Title: WechatSubAccountFinishResData
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/12/21下午6:25
 */
public class WechatSubAccountFinishResData extends BaseResponseBeans {

    /**
     * 微信订单号
     */
    private String transaction_id;
    /**
     * 商户分账单号
     */
    private String out_order_no;
    /**
     * 商户分账金额
     */
    private int amount;
    /**
     * 微信分账单号 分账完结操作中实际结算给子商户的金额。单位分。
     */
    private String order_id;

    public String getTransaction_id() {
        return transaction_id;
    }

    public WechatSubAccountFinishResData setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
        return this;
    }

    public String getOut_order_no() {
        return out_order_no;
    }

    public WechatSubAccountFinishResData setOut_order_no(String out_order_no) {
        this.out_order_no = out_order_no;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public WechatSubAccountFinishResData setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public String getOrder_id() {
        return order_id;
    }

    public WechatSubAccountFinishResData setOrder_id(String order_id) {
        this.order_id = order_id;
        return this;
    }
}
