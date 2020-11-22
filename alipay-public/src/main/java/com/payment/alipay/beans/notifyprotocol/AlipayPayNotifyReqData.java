package com.payment.alipay.beans.notifyprotocol;

/**
 * 支付宝通知bean
 *
 * @author code
 * @Title: AlipayPayNotifyReqData
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/9/1011:49 AM
 */
public class AlipayPayNotifyReqData {
    /**
     * 交易创建时间
     * 该笔交易创建的时间。格式为yyyy-MM-dd HH:mm:ss
     */
    private String gmt_create;

    /**
     * 卖家支付宝账号
     */
    private String seller_email;
    /**
     * 订单标题
     * 商品的标题/交易标题/订单标题/订单关键字等，是请求时对应的参数，原样通知回来
     */
    private String subject;
    /**
     * 签名
     * 请参考异步返回结果的验签
     */
    private String sign;
    /**
     * 商品描述
     * 该订单的备注、描述、明细等。对应请求时的body参数，原样通知回来
     */
    private String body;
    /**
     * 买家支付宝用户号
     * 买家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字
     */
    private String buyer_id;
    /**
     * 开票金额
     * 用户在交易中支付的可开发票的金额
     */
    private String invoice_amount;
    /**
     * 通知校验ID
     */
    private String notify_id;
    /**
     * 支付金额信息
     * 支付成功的各个渠道金额信息，详见资金明细信息说明
     */
    private String fund_bill_list;
    /**
     * 通知的类型
     */
    private String notify_type;
    /**
     * 交易目前所处的状态
     */
    private String trade_status;
    /**
     * 实收金额
     * 商家在交易中实际收到的款项，单位为元
     */
    private String receipt_amount;
    /**
     * 付款金额
     * 用户在交易中支付的金额
     */
    private String buyer_pay_amount;
    /**
     * 支付宝分配给开发者的应用Id
     */
    private String app_id;
    /**
     * 签名类型
     * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
     */
    private String sign_type;
    /**
     * 卖家支付宝用户号
     */
    private String seller_id;
    /**
     * 交易付款时间
     * 该笔交易的买家付款时间。格式为yyyy-MM-dd HH:mm:ss
     */
    private String gmt_payment;
    /**
     * 通知时间
     * 通知的发送时间。格式为yyyy-MM-dd HH:mm:ss
     */
    private String notify_time;

    /**
     * 原支付请求的商户订单号
     */
    private String out_trade_no;
    /**
     * 本次交易支付的订单金额，单位为人民币（元）
     */
    private String total_amount;
    /**
     * 支付宝交易凭证号
     */
    private String trade_no;

    /**
     * 买家支付宝账号
     */
    private String buyer_logon_id;
    /**
     * 集分宝金额
     * 使用集分宝支付的金额
     */
    private String point_amount;



    /**
     * 编码
     */
    private String charset;
    /**
     * 版本号
     */
    private String version;
    /**
     * 同app_id
     */
    private String auth_app_id;


    public String getGmt_create() {
        return gmt_create;
    }

    public AlipayPayNotifyReqData setGmt_create(String gmt_create) {
        this.gmt_create = gmt_create;
        return this;
    }

    public String getSeller_email() {
        return seller_email;
    }

    public AlipayPayNotifyReqData setSeller_email(String seller_email) {
        this.seller_email = seller_email;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public AlipayPayNotifyReqData setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getSign() {
        return sign;
    }

    public AlipayPayNotifyReqData setSign(String sign) {
        this.sign = sign;
        return this;
    }

    public String getBody() {
        return body;
    }

    public AlipayPayNotifyReqData setBody(String body) {
        this.body = body;
        return this;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public AlipayPayNotifyReqData setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
        return this;
    }

    public String getInvoice_amount() {
        return invoice_amount;
    }

    public AlipayPayNotifyReqData setInvoice_amount(String invoice_amount) {
        this.invoice_amount = invoice_amount;
        return this;
    }

    public String getNotify_id() {
        return notify_id;
    }

    public AlipayPayNotifyReqData setNotify_id(String notify_id) {
        this.notify_id = notify_id;
        return this;
    }

    public String getFund_bill_list() {
        return fund_bill_list;
    }

    public AlipayPayNotifyReqData setFund_bill_list(String fund_bill_list) {
        this.fund_bill_list = fund_bill_list;
        return this;
    }

    public String getNotify_type() {
        return notify_type;
    }

    public AlipayPayNotifyReqData setNotify_type(String notify_type) {
        this.notify_type = notify_type;
        return this;
    }

    public String getTrade_status() {
        return trade_status;
    }

    public AlipayPayNotifyReqData setTrade_status(String trade_status) {
        this.trade_status = trade_status;
        return this;
    }

    public String getReceipt_amount() {
        return receipt_amount;
    }

    public AlipayPayNotifyReqData setReceipt_amount(String receipt_amount) {
        this.receipt_amount = receipt_amount;
        return this;
    }

    public String getBuyer_pay_amount() {
        return buyer_pay_amount;
    }

    public AlipayPayNotifyReqData setBuyer_pay_amount(String buyer_pay_amount) {
        this.buyer_pay_amount = buyer_pay_amount;
        return this;
    }

    public String getApp_id() {
        return app_id;
    }

    public AlipayPayNotifyReqData setApp_id(String app_id) {
        this.app_id = app_id;
        return this;
    }

    public String getSign_type() {
        return sign_type;
    }

    public AlipayPayNotifyReqData setSign_type(String sign_type) {
        this.sign_type = sign_type;
        return this;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public AlipayPayNotifyReqData setSeller_id(String seller_id) {
        this.seller_id = seller_id;
        return this;
    }

    public String getGmt_payment() {
        return gmt_payment;
    }

    public AlipayPayNotifyReqData setGmt_payment(String gmt_payment) {
        this.gmt_payment = gmt_payment;
        return this;
    }

    public String getNotify_time() {
        return notify_time;
    }

    public AlipayPayNotifyReqData setNotify_time(String notify_time) {
        this.notify_time = notify_time;
        return this;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public AlipayPayNotifyReqData setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
        return this;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public AlipayPayNotifyReqData setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
        return this;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public AlipayPayNotifyReqData setTrade_no(String trade_no) {
        this.trade_no = trade_no;
        return this;
    }

    public String getBuyer_logon_id() {
        return buyer_logon_id;
    }

    public AlipayPayNotifyReqData setBuyer_logon_id(String buyer_logon_id) {
        this.buyer_logon_id = buyer_logon_id;
        return this;
    }

    public String getPoint_amount() {
        return point_amount;
    }

    public AlipayPayNotifyReqData setPoint_amount(String point_amount) {
        this.point_amount = point_amount;
        return this;
    }

    public String getCharset() {
        return charset;
    }

    public AlipayPayNotifyReqData setCharset(String charset) {
        this.charset = charset;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public AlipayPayNotifyReqData setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getAuth_app_id() {
        return auth_app_id;
    }

    public AlipayPayNotifyReqData setAuth_app_id(String auth_app_id) {
        this.auth_app_id = auth_app_id;
        return this;
    }
}
