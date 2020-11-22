package com.payment.alipay.dto;


import com.payment.alipay.enums.OrderStatusEnum;
import com.payment.alipay.enums.PaymentModeEnum;

import java.util.Date;


/**
 * qds_order-dto
 */
public class QdsOrderDTO extends BasicDTO {

    /**
     * 分账次数
     */
    private int subAccountTime;

    /**
     * 签名类型
     */
    private String signType;

    private String ip;

    /**
     * 子商户表主键ID
     */
    private Long subMerchantId = 0L;

    /**
     * 用户id(业务参数)
     */
    private String userId;


    /**
     * 缴费类型(业务参数)，物业，购物等等。1,物业，2，购物，0,默认(也可以是不传值)
     */
    private Integer paymentType = 0;

    /**
     * 商户订单号
     */
    private String merchantOrderNo;

    /**
     * 我方订单号，订单生成规则。qd_yyyyMMddHHmmssSSS+8位随机数(大小写字母+数字随机8位)
     */
    private String orderNo;

    /**
     * 通道预支付交易会话标识(微信，支付宝等)，得到返回结果时生成
     */
    private String channelPrepayNo;

    /**
     * project_name 项目名称
     */
    private String deviceInfo;

    /**
     * 商品描述,企业付款对应desc字段
     */
    private String body;

    /**
     * 商品详情
     */
    private String detail;

    /**
     * PROCESSING//处理中     ,
     * SUCCESS//成功     ,
     * FAILED//失败     ,
     * REFUND_SUCCESS//退款成功     ,
     * CHANGE//退款异常     ,
     * REFUNDCLOSE//退款关闭，
     * 默认处理中PROCESSING
     */
    private OrderStatusEnum orderStatus;

    /**
     * 订单金额，分
     */
    private int orderAmt;

    /**
     * 退款金额，分
     */
    private int refundAmt;

    /**
     * 是否存在退款记录，不论是否成功,默认不存在，0。存在，1
     */
    private Integer hasRefunded;

    /**
     * 币种类型，CNY
     */
    private String currency;

    /**
     * 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
     */
    private String attach;

    /**
     * 通道订单结束日期,yyyyMMdd，用于日期范围查找使用，订单23：59分生成，0：01支付结束，则时间为第二天的时间
     */
    private Integer orderTimeIndex;

    /**
     * 订单开始时间
     */
    private Long orderGenerateTime;

    /**
     * 交易超时时间，单位秒，默认两小时（7200），为空的情况下也算默认情况
     */
    private Integer timeExpire;

    /**
     * 交易完成时间
     */
    private Date tradeTime;

    /**
     * 订单失败原因，return_msg信息
     */
    private String failReason;

    /**
     * "交易类型，SAPI公众号支付
     * NATIVE扫码支付
     * APPAPP支付"
     */
    private String tradeType;

    /**
     * trade_type=JSAPI，此参数必传，用户在主商户appid下的唯一标识。openid和sub_openid可以选传其中之一，如果选择传sub_openid,则必须传sub_appid。
     */
    private String openid;

    /**
     * 子商户openID，参考openID说明
     */
    private String subOpenid;

    /**
     * 场景信息
     */
    private String extraParam;

    /**
     *
     */
    private Long createAt;

    /**
     *
     */
    private Long updateAt;

    private String channelOrderNo;

    /**
     * 选填，是否分账
     * Y-是，需要分账
     * N-否，不分账
     * <p>
     * 字母要求大写，不传默认不分账
     */
    private String isSubAccount;
    /**
     * 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
     */
    private String undiscountableAmount;

    /**
     * PaymentModeEnum.
     * 是否为服务商模式的支付，默认 1
     * 1，服务商模式。
     * 2，普通商户
     */
    private int isPub = PaymentModeEnum.SERVER_MERCHANT.getCode();

    /**
     * 微信企业支付使用 校验用户姓名选项 Y，校验，N，不校验
     */
    private String checkName;
    /**
     * 微信企业支付使用 收款用户姓名 收款用户真实姓名。
     * 如果check_name设置为FORCE_CHECK，则必填用户真实姓名
     */
    private String receiveUserName;


    public int getSubAccountTime() {
        return subAccountTime;
    }

    public QdsOrderDTO setSubAccountTime(int subAccountTime) {
        this.subAccountTime = subAccountTime;
        return this;
    }

    public String getSignType() {
        return signType;
    }

    public QdsOrderDTO setSignType(String signType) {
        this.signType = signType;
        return this;
    }


    public String getIp() {
        return ip;
    }

    public QdsOrderDTO setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public Long getSubMerchantId() {
        return subMerchantId;
    }

    public QdsOrderDTO setSubMerchantId(Long subMerchantId) {
        this.subMerchantId = subMerchantId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public QdsOrderDTO setUserId(String userId) {
        this.userId = userId;
        return this;
    }


    public Integer getPaymentType() {
        return paymentType;
    }

    public QdsOrderDTO setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public QdsOrderDTO setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
        return this;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public QdsOrderDTO setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public String getChannelPrepayNo() {
        return channelPrepayNo;
    }

    public QdsOrderDTO setChannelPrepayNo(String channelPrepayNo) {
        this.channelPrepayNo = channelPrepayNo;
        return this;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public QdsOrderDTO setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
        return this;
    }

    public String getBody() {
        return body;
    }

    public QdsOrderDTO setBody(String body) {
        this.body = body;
        return this;
    }

    public String getDetail() {
        return detail;
    }

    public QdsOrderDTO setDetail(String detail) {
        this.detail = detail;
        return this;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public QdsOrderDTO setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public int getOrderAmt() {
        return orderAmt;
    }

    public QdsOrderDTO setOrderAmt(int orderAmt) {
        this.orderAmt = orderAmt;
        return this;
    }

    public int getRefundAmt() {
        return refundAmt;
    }

    public QdsOrderDTO setRefundAmt(int refundAmt) {
        this.refundAmt = refundAmt;
        return this;
    }

    public Integer getHasRefunded() {
        return hasRefunded;
    }

    public QdsOrderDTO setHasRefunded(Integer hasRefunded) {
        this.hasRefunded = hasRefunded;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public QdsOrderDTO setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public String getAttach() {
        return attach;
    }

    public QdsOrderDTO setAttach(String attach) {
        this.attach = attach;
        return this;
    }

    public Integer getOrderTimeIndex() {
        return orderTimeIndex;
    }

    public QdsOrderDTO setOrderTimeIndex(Integer orderTimeIndex) {
        this.orderTimeIndex = orderTimeIndex;
        return this;
    }

    public Long getOrderGenerateTime() {
        return orderGenerateTime;
    }

    public QdsOrderDTO setOrderGenerateTime(Long orderGenerateTime) {
        this.orderGenerateTime = orderGenerateTime;
        return this;
    }

    public Integer getTimeExpire() {
        return timeExpire;
    }

    public QdsOrderDTO setTimeExpire(Integer timeExpire) {
        this.timeExpire = timeExpire;
        return this;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public QdsOrderDTO setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
        return this;
    }

    public String getFailReason() {
        return failReason;
    }

    public QdsOrderDTO setFailReason(String failReason) {
        this.failReason = failReason;
        return this;
    }

    public String getTradeType() {
        return tradeType;
    }

    public QdsOrderDTO setTradeType(String tradeType) {
        this.tradeType = tradeType;
        return this;
    }

    public String getOpenid() {
        return openid;
    }

    public QdsOrderDTO setOpenid(String openid) {
        this.openid = openid;
        return this;
    }

    public String getSubOpenid() {
        return subOpenid;
    }

    public QdsOrderDTO setSubOpenid(String subOpenid) {
        this.subOpenid = subOpenid;
        return this;
    }

    public String getExtraParam() {
        return extraParam;
    }

    public QdsOrderDTO setExtraParam(String extraParam) {
        this.extraParam = extraParam;
        return this;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public QdsOrderDTO setCreateAt(Long createAt) {
        this.createAt = createAt;
        return this;
    }

    public Long getUpdateAt() {
        return updateAt;
    }

    public QdsOrderDTO setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
        return this;
    }

    public String getChannelOrderNo() {
        return channelOrderNo;
    }

    public QdsOrderDTO setChannelOrderNo(String channelOrderNo) {
        this.channelOrderNo = channelOrderNo;
        return this;
    }

    public String getIsSubAccount() {
        return isSubAccount;
    }

    public QdsOrderDTO setIsSubAccount(String isSubAccount) {
        this.isSubAccount = isSubAccount;
        return this;
    }

    public int getIsPub() {
        return isPub;
    }

    public QdsOrderDTO setIsPub(int isPub) {
        this.isPub = isPub;
        return this;
    }

    public String getUndiscountableAmount() {
        return undiscountableAmount;
    }

    public QdsOrderDTO setUndiscountableAmount(String undiscountableAmount) {
        this.undiscountableAmount = undiscountableAmount;
        return this;
    }

    public String getCheckName() {
        return checkName;
    }

    public QdsOrderDTO setCheckName(String checkName) {
        this.checkName = checkName;
        return this;
    }

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public QdsOrderDTO setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
        return this;
    }
}