package com.payment.alipay.beans.downloadprotocol;

/**
 * 支付宝对账单返回结果对象
 *
 * @author code
 * @Title: AlipayDownloadBillResData
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/9/174:44 PM
 */
public class AlipayDownloadBillResData {
    /**
     * 对账单日期
     */
    private Integer statementDate;
    /**
     * 对接方项目名称
     */
    private String projectName;
    /**
     * 支付宝订单号
     */
    private String channelOrderNo;

    /**
     * 商户订单号-支付系统订单号
     */
    private String qdsOrderNo;

    /**
     * 业务类型
     */
    private String tradeType;
    /**
     * 商品名称
     */
    private String subject;

    /**
     * 交易创建时间
     */
    private String orderTime;
    /**
     * 支付完成时间
     */
    private String timeEnd;
    /**
     * 门店编号
     */
    private String storeId;
    /**
     * 门店名称
     */
    private String storeName;
    /**
     * 操作员
     */
    private String operatorId;
    /**
     * 终端号
     */
    private String terminalId;


    /**
     * 付款方-对方账户
     */
    private String userInfo;

    /**
     * 交易金额 元
     */
    private String totalAmount;
    /**
     * 商家实收金额 元
     */
    private String actualAmount;
    /**
     * 支付宝红包 元
     */
    private String alipayRedPacket;

    /**
     * 集分宝 元
     */
    private String integralAmount;
    /**
     * 支付宝优惠 元
     */
    private String alipayPreferent;
    /**
     * 商家优惠 元
     */
    private String merchantPreferent;
    /**
     * 券核销金额 元
     */
    private String ticketAmount;
    /**
     * 券名称
     */
    private String ticketName;
    /**
     * 商家红包消费金额 元
     */
    private String merchantRedAmount;
    /**
     * 卡消费金额 元
     */
    private String cardAmount;
    /**
     * 退款批次号/请求号
     */
    private String qdsRefundNo;
    /**
     * 服务费 元
     */
    private String serverAmount;
    /**
     * 分润 元
     */
    private String subAccountAmount;


    public String getChannelOrderNo() {
        return channelOrderNo;
    }

    public AlipayDownloadBillResData setChannelOrderNo(String channelOrderNo) {
        this.channelOrderNo = channelOrderNo;
        return this;
    }

    public String getQdsOrderNo() {
        return qdsOrderNo;
    }

    public AlipayDownloadBillResData setQdsOrderNo(String qdsOrderNo) {
        this.qdsOrderNo = qdsOrderNo;
        return this;
    }

    public String getTradeType() {
        return tradeType;
    }

    public AlipayDownloadBillResData setTradeType(String tradeType) {
        this.tradeType = tradeType;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public AlipayDownloadBillResData setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public AlipayDownloadBillResData setOrderTime(String orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public AlipayDownloadBillResData setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
        return this;
    }

    public String getStoreId() {
        return storeId;
    }

    public AlipayDownloadBillResData setStoreId(String storeId) {
        this.storeId = storeId;
        return this;
    }

    public String getStoreName() {
        return storeName;
    }

    public AlipayDownloadBillResData setStoreName(String storeName) {
        this.storeName = storeName;
        return this;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public AlipayDownloadBillResData setOperatorId(String operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public AlipayDownloadBillResData setTerminalId(String terminalId) {
        this.terminalId = terminalId;
        return this;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public AlipayDownloadBillResData setUserInfo(String userInfo) {
        this.userInfo = userInfo;
        return this;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public AlipayDownloadBillResData setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public String getActualAmount() {
        return actualAmount;
    }

    public AlipayDownloadBillResData setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
        return this;
    }

    public String getAlipayRedPacket() {
        return alipayRedPacket;
    }

    public AlipayDownloadBillResData setAlipayRedPacket(String alipayRedPacket) {
        this.alipayRedPacket = alipayRedPacket;
        return this;
    }

    public String getIntegralAmount() {
        return integralAmount;
    }

    public AlipayDownloadBillResData setIntegralAmount(String integralAmount) {
        this.integralAmount = integralAmount;
        return this;
    }

    public String getAlipayPreferent() {
        return alipayPreferent;
    }

    public AlipayDownloadBillResData setAlipayPreferent(String alipayPreferent) {
        this.alipayPreferent = alipayPreferent;
        return this;
    }

    public String getMerchantPreferent() {
        return merchantPreferent;
    }

    public AlipayDownloadBillResData setMerchantPreferent(String merchantPreferent) {
        this.merchantPreferent = merchantPreferent;
        return this;
    }

    public String getTicketAmount() {
        return ticketAmount;
    }

    public AlipayDownloadBillResData setTicketAmount(String ticketAmount) {
        this.ticketAmount = ticketAmount;
        return this;
    }

    public String getTicketName() {
        return ticketName;
    }

    public AlipayDownloadBillResData setTicketName(String ticketName) {
        this.ticketName = ticketName;
        return this;
    }

    public String getMerchantRedAmount() {
        return merchantRedAmount;
    }

    public AlipayDownloadBillResData setMerchantRedAmount(String merchantRedAmount) {
        this.merchantRedAmount = merchantRedAmount;
        return this;
    }

    public String getCardAmount() {
        return cardAmount;
    }

    public AlipayDownloadBillResData setCardAmount(String cardAmount) {
        this.cardAmount = cardAmount;
        return this;
    }

    public String getQdsRefundNo() {
        return qdsRefundNo;
    }

    public AlipayDownloadBillResData setQdsRefundNo(String qdsRefundNo) {
        this.qdsRefundNo = qdsRefundNo;
        return this;
    }

    public String getServerAmount() {
        return serverAmount;
    }

    public AlipayDownloadBillResData setServerAmount(String serverAmount) {
        this.serverAmount = serverAmount;
        return this;
    }

    public String getSubAccountAmount() {
        return subAccountAmount;
    }

    public AlipayDownloadBillResData setSubAccountAmount(String subAccountAmount) {
        this.subAccountAmount = subAccountAmount;
        return this;
    }


    public Integer getStatementDate() {
        return statementDate;
    }

    public AlipayDownloadBillResData setStatementDate(Integer statementDate) {
        this.statementDate = statementDate;
        return this;
    }

    public String getProjectName() {
        return projectName;
    }

    public AlipayDownloadBillResData setProjectName(String projectName) {
        this.projectName = projectName;
        return this;
    }
}
