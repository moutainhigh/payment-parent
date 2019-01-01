package cn.aposoft.ecommerce.wechat.beans.protocol.sub_account_protocol;

import cn.aposoft.ecommerce.wechat.beans.protocol.BaseRequestBeans;

/**
 * 请求分账bean
 *
 * @author code
 * @Title: WechatSubAccountReqData
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/12/18下午3:04
 */
public class WechatSubAccountReqData extends BaseRequestBeans {

    /**
     * 必填。签名类型，目前只支持HMAC-SHA256
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


    public String getSign_type() {
        return sign_type;
    }

    public WechatSubAccountReqData setSign_type(String sign_type) {
        this.sign_type = sign_type;
        return this;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public WechatSubAccountReqData setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
        return this;
    }

    public String getOut_order_no() {
        return out_order_no;
    }

    public WechatSubAccountReqData setOut_order_no(String out_order_no) {
        this.out_order_no = out_order_no;
        return this;
    }

    public String getReceivers() {
        return receivers;
    }

    public WechatSubAccountReqData setReceivers(String receivers) {
        this.receivers = receivers;
        return this;
    }
}
