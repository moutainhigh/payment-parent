package cn.aposoft.ecommerce.wechat.beans.subaccount;

import cn.aposoft.ecommerce.wechat.params.SubAccountParams;

/**
 * @author code
 * @Title: SubAccountParamsDTO
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/1/9下午2:50
 */
public class SubAccountParamsDTO implements SubAccountParams {
    /**
     * 选填。签名类型，目前只支持HMAC-SHA256
     */
    private String sign_type;
    /**
     * 必填。微信支付订单号
     */
    private String transaction_id;
    /**
     * 必填。商户系统内部的分账单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一分账单号多次请求等同一次。
     */
    private String out_order_no;
    /**
     * 必填。分账接收方列表 不超过50个 json对象
     */
    private String receivers;

    public SubAccountParamsDTO setSign_type(String sign_type) {
        this.sign_type = sign_type;
        return this;
    }

    public SubAccountParamsDTO setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
        return this;
    }

    public SubAccountParamsDTO setOut_order_no(String out_order_no) {
        this.out_order_no = out_order_no;
        return this;
    }

    public SubAccountParamsDTO setReceivers(String receivers) {
        this.receivers = receivers;
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
    public String getReceivers() {
        return receivers;
    }
}
