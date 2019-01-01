package cn.aposoft.ecommerce.wechat.beans.protocol.sub_account_query_protocol;

import cn.aposoft.ecommerce.wechat.beans.protocol.BaseResponseBeans;

/**
 * @author code
 * @Title: WechatSubAccountQueryResData
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/12/21上午11:58
 */
public class WechatSubAccountQueryResData extends BaseResponseBeans {
    /**
     * 微信订单号
     */
    private String transaction_id;
    /**
     * 支付系统分账单号
     */
    private String out_order_no;
    /**
     * 微信分账单号
     */
    private String order_id;
    /**
     * 分账单状态
     */
    private String status;
    /**
     * 关单原因
     */
    private String close_reason;
    /**
     * 账接收方列表
     */
    private String receivers;

    public String getTransaction_id() {
        return transaction_id;
    }

    public WechatSubAccountQueryResData setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
        return this;
    }

    public String getOut_order_no() {
        return out_order_no;
    }

    public WechatSubAccountQueryResData setOut_order_no(String out_order_no) {
        this.out_order_no = out_order_no;
        return this;
    }

    public String getOrder_id() {
        return order_id;
    }

    public WechatSubAccountQueryResData setOrder_id(String order_id) {
        this.order_id = order_id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public WechatSubAccountQueryResData setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getClose_reason() {
        return close_reason;
    }

    public WechatSubAccountQueryResData setClose_reason(String close_reason) {
        this.close_reason = close_reason;
        return this;
    }

    public String getReceivers() {
        return receivers;
    }

    public WechatSubAccountQueryResData setReceivers(String receivers) {
        this.receivers = receivers;
        return this;
    }
}
