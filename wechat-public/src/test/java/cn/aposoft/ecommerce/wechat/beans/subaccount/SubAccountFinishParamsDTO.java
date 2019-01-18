package cn.aposoft.ecommerce.wechat.beans.subaccount;

import cn.aposoft.ecommerce.wechat.params.SubAccountFinishParams;

/**
 * @author code
 * @Title: SubAccountFinishParamsDTO
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/1/18下午4:29
 */
public class SubAccountFinishParamsDTO implements SubAccountFinishParams {
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

    public SubAccountFinishParamsDTO setSign_type(String sign_type) {
        this.sign_type = sign_type;
        return this;
    }

    public SubAccountFinishParamsDTO setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
        return this;
    }

    public SubAccountFinishParamsDTO setOut_order_no(String out_order_no) {
        this.out_order_no = out_order_no;
        return this;
    }

    public SubAccountFinishParamsDTO setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public SubAccountFinishParamsDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String getSign_type() {
        return sign_type;
    }

    @Override
    public String getTransaction_id() {
        return transaction_id;
    }

    @Override
    public String getOut_order_no() {
        return out_order_no;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
