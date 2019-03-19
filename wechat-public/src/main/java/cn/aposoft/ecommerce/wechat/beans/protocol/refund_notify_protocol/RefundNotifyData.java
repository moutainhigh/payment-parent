package cn.aposoft.ecommerce.wechat.beans.protocol.refund_notify_protocol;

/**
 * @author code
 * @Title: RefundNotifyData
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/10/23下午2:06
 */
public class RefundNotifyData {


    /**
     * 微信订单号
     */
    private String transaction_id;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 微信退款单号
     */
    private String refund_id;
    /**
     * 支付系统退款单号，用于对接方使用
     */
    private String qds_refund_no;
    /**
     * 通道返回的外部商户订单号，此处同指支付系统订单号，
     */
    private String out_refund_no;



    /**
     * 商户退款单号
     */
    private String merchant_refund_no;
    /**
     * 订单金额
     */
    private int total_fee;
    /**
     * 应结订单金额
     */
    private int settlement_total_fee;
    /**
     * 申请退款金额
     */
    private int refund_fee;
    /**
     * 退款金额
     */
    private int settlement_refund_fee;
    /**
     * 退款状态
     */
    private String refund_status;
    /**
     * 退款成功时间
     */
    private String success_time;
    /**
     * 退款入账账户
     */
    private String refund_recv_accout;
    /**
     * 退款资金来源
     */
    private String refund_account;
    /**
     * 退款发起来源
     */
    private String refund_request_source;


    public String getTransaction_id() {
        return transaction_id;
    }

    public RefundNotifyData setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
        return this;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public RefundNotifyData setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
        return this;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public RefundNotifyData setRefund_id(String refund_id) {
        this.refund_id = refund_id;
        return this;
    }

    public String getQds_refund_no() {
        return qds_refund_no;
    }

    public RefundNotifyData setQds_refund_no(String qds_refund_no) {
        this.qds_refund_no = qds_refund_no;
        return this;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    /**
     * 赋值操作
     * @param out_refund_no
     * @return
     */
    public RefundNotifyData setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
        this.qds_refund_no = out_refund_no;
        return this;
    }

    public String getMerchant_refund_no() {
        return merchant_refund_no;
    }

    public RefundNotifyData setMerchant_refund_no(String merchant_refund_no) {
        this.merchant_refund_no = merchant_refund_no;
        return this;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public RefundNotifyData setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
        return this;
    }

    public int getSettlement_total_fee() {
        return settlement_total_fee;
    }

    public RefundNotifyData setSettlement_total_fee(int settlement_total_fee) {
        this.settlement_total_fee = settlement_total_fee;
        return this;
    }

    public int getRefund_fee() {
        return refund_fee;
    }

    public RefundNotifyData setRefund_fee(int refund_fee) {
        this.refund_fee = refund_fee;
        return this;
    }

    public int getSettlement_refund_fee() {
        return settlement_refund_fee;
    }

    public RefundNotifyData setSettlement_refund_fee(int settlement_refund_fee) {
        this.settlement_refund_fee = settlement_refund_fee;
        return this;
    }

    public String getRefund_status() {
        return refund_status;
    }

    public RefundNotifyData setRefund_status(String refund_status) {
        this.refund_status = refund_status;
        return this;
    }

    public String getSuccess_time() {
        return success_time;
    }

    public RefundNotifyData setSuccess_time(String success_time) {
        this.success_time = success_time;
        return this;
    }

    public String getRefund_recv_accout() {
        return refund_recv_accout;
    }

    public RefundNotifyData setRefund_recv_accout(String refund_recv_accout) {
        this.refund_recv_accout = refund_recv_accout;
        return this;
    }

    public String getRefund_account() {
        return refund_account;
    }

    public RefundNotifyData setRefund_account(String refund_account) {
        this.refund_account = refund_account;
        return this;
    }

    public String getRefund_request_source() {
        return refund_request_source;
    }

    public RefundNotifyData setRefund_request_source(String refund_request_source) {
        this.refund_request_source = refund_request_source;
        return this;
    }
}
