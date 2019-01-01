package cn.aposoft.ecommerce.wechat.beans.protocol.sub_account_query_protocol;

import cn.aposoft.ecommerce.wechat.beans.protocol.BaseRequestBeans;

/**
 * 分账查询请求bean
 *
 * @author code
 * @Title: WechatSubAccountQueryReqData
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/12/21上午11:57
 */
public class WechatSubAccountQueryReqData extends BaseRequestBeans {
//mch_id,sub_mch_id,transaction_id,out_order_no,nonce_str,sign,sign_type


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


    public String getTransaction_id() {
        return transaction_id;
    }

    public WechatSubAccountQueryReqData setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
        return this;
    }

    public String getOut_order_no() {
        return out_order_no;
    }

    public WechatSubAccountQueryReqData setOut_order_no(String out_order_no) {
        this.out_order_no = out_order_no;
        return this;
    }

    public String getSign_type() {
        return sign_type;
    }

    public WechatSubAccountQueryReqData setSign_type(String sign_type) {
        this.sign_type = sign_type;
        return this;
    }
}
