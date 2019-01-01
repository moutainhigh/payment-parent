package cn.aposoft.ecommerce.wechat.beans.protocol.sub_account_finish_protocol;

import cn.aposoft.ecommerce.wechat.beans.protocol.BaseRequestBeans;

/**
 * 完结分账请求bean
 *
 * @author code
 * @Title: WechatSubAccountFinishReqData
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/12/21下午6:22
 */
public class WechatSubAccountFinishReqData extends BaseRequestBeans {

    /**
     * 签名类型，目前只支持HMAC-SHA256
     */
    private String sign_type;
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
     * 分账完结描述
     */
    private String description;

    public String getSign_type() {
        return sign_type;
    }

    public WechatSubAccountFinishReqData setSign_type(String sign_type) {
        this.sign_type = sign_type;
        return this;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public WechatSubAccountFinishReqData setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
        return this;
    }

    public String getOut_order_no() {
        return out_order_no;
    }

    public WechatSubAccountFinishReqData setOut_order_no(String out_order_no) {
        this.out_order_no = out_order_no;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public WechatSubAccountFinishReqData setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public WechatSubAccountFinishReqData setDescription(String description) {
        this.description = description;
        return this;
    }
}
