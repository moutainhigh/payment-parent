package cn.aposoft.ecommerce.wechat.beans.subaccount;

import cn.aposoft.ecommerce.wechat.params.SubAccountQueryParams;

/**
 * @author code
 * @Title: SubAccountQueryParamsDTO
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/1/9下午7:11
 */
public class SubAccountQueryParamsDTO implements SubAccountQueryParams {
    /**
     * 微信订单号
     */
    private String transaction_id;
    /**
     * 商户分账单号
     */
    private String out_order_no;
    /**
     * 签名类型,目前只支持HMAC-SHA256
     */
    private String sign_type;

    public SubAccountQueryParamsDTO setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
        return this;
    }

    public SubAccountQueryParamsDTO setOut_order_no(String out_order_no) {
        this.out_order_no = out_order_no;
        return this;
    }

    public SubAccountQueryParamsDTO setSign_type(String sign_type) {
        this.sign_type = sign_type;
        return this;
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
    public String getSign_type() {
        return sign_type;
    }
}
