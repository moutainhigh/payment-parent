package com.shui.payment.cmb.beans.accounttradequeryprotocol;

/**
 * 2.3 返回bean  NTQTSINFZ部分
 *
 * @author code
 * @Title: AccountTradeResData
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/11/122:04 PM
 */
public class AccountTradeResData {


    /**
     * 交易日 交易发生的日期
     * ETYDAT
     */
    private String tradeDate;
    /**
     * 交易时间 交易发生的时间，只有小时有效
     * ETYTIM
     */
    private String tradeTime;
    /**
     * 借贷标记 C:贷； D:借
     * AMTCDR
     */
    private String typeCode;

    /**
     * 流水号 银行会计系统交易流水号
     * REFNBR
     */
    private String bankOrderNo;


    /**
     * 业务参考号
     * YURREF
     */
    private String qdsOrderNo;
    /**
     * 流程实例号 企业银行交易序号，唯一标示企业银行客户端发起的一笔交易
     * REQNBR
     */
    private String channelOrderNo;


    /**
     * 交易类型 附录A9
     * TRSCOD
     */
    private String tradeType;
    /**
     * 摘要
     * 若为企业银行客户端经办的交易，则该字段为用途信息（ 4.0 版代发代扣业务除外）若为其它渠道经办的交易，则该字段为交易的简单说明和注解。
     * NARYUR
     */
    private String desc;
    /**
     * 借方金额企业为借方时的交易金额（正数）
     * TRSAMTD
     */
    private String borrowAmt;

    /**
     * 贷方金额 企业为贷方时的交易金额（正数）
     * TRSAMTC
     */
    private String loanAmt;

    /**
     * 余额 帐户的联机余额
     * TRSBLV
     */
    private String balanceAmt;


    /**
     * 业务名称
     * BUSNAM
     */
    private String bizName;
    /**
     * 用途
     * NUSAGE
     */
    private String purpose;

    /**
     * 业务摘要 对业务的简单说明或注解。企业银行客户端录入的摘要信息
     * BUSNAR
     */
    private String bizDesc;

    /**
     * 转出：为收方，转入：为付方
     * 收/付方开户地区 收/付方帐号开户行所在地区，如北京、上海、深圳等
     * C_RPYBBK
     */
    private String clientAreaName;
    /**
     * 转出：为收方，转入：为付方
     * 收/付方名称
     * RPYNAM
     */
    private String clientAccountName;
    /**
     * 转出：为收方，转入：为付方
     * 收/付方账号 收/付方的转入或转出帐号
     * RPYACC
     */
    private String clientAccountNo;
    /**
     * 转出：为收方，转入：为付方
     * 收、付方开户行行号
     * RPYBBN
     */
    private String clientBranchNo;
    /**
     * 转出：为收方，转入：为付方
     * 收、付方开户行行名
     * RPYBNK
     */
    private String clientBranchName;
    /**
     * 转出：为收方，转入：为付方
     * 收/付方开户行地址
     * RPYADR
     */
    private String clientBranchAddress;
    /**
     * 母/子公司帐号的开户行所在地区，如北京、上海、深圳等
     * C_GSBBBK
     */
    private String alphaCompArea;
    /**
     * 母/子公司帐号
     * GSBACC
     */
    private String alphaCompAccountNo;
    /**
     * 母/子公司名称
     * GSBNAM
     */
    private String alphaCompName;
    /**
     * 信息标志
     * INFFLG
     */
    private String infoFlag;
    /**
     * 是否有附件信息标志 Y/N
     * ATHFLG
     */
    private String includeAttachment;
    /**
     * 票据号
     * CHKNBR
     */
    private String billNo;
    /**
     * 冲账标志 *为冲帐， X 为补帐（冲账交易与原交易借贷相反）
     * RSVFLG
     */
    private String reverseFlag;
    /**
     * 扩展摘要 有效位数为 16
     * NAREXT
     */
    private String appendDesc;

    public String getTradeDate() {
        return tradeDate;
    }

    public AccountTradeResData setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
        return this;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public AccountTradeResData setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
        return this;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public AccountTradeResData setTypeCode(String typeCode) {
        this.typeCode = typeCode;
        return this;
    }

    public String getBankOrderNo() {
        return bankOrderNo;
    }

    public AccountTradeResData setBankOrderNo(String bankOrderNo) {
        this.bankOrderNo = bankOrderNo;
        return this;
    }

    public String getQdsOrderNo() {
        return qdsOrderNo;
    }

    public AccountTradeResData setQdsOrderNo(String qdsOrderNo) {
        this.qdsOrderNo = qdsOrderNo;
        return this;
    }

    public String getChannelOrderNo() {
        return channelOrderNo;
    }

    public AccountTradeResData setChannelOrderNo(String channelOrderNo) {
        this.channelOrderNo = channelOrderNo;
        return this;
    }

    public String getTradeType() {
        return tradeType;
    }

    public AccountTradeResData setTradeType(String tradeType) {
        this.tradeType = tradeType;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public AccountTradeResData setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public String getBorrowAmt() {
        return borrowAmt;
    }

    public AccountTradeResData setBorrowAmt(String borrowAmt) {
        this.borrowAmt = borrowAmt;
        return this;
    }

    public String getLoanAmt() {
        return loanAmt;
    }

    public AccountTradeResData setLoanAmt(String loanAmt) {
        this.loanAmt = loanAmt;
        return this;
    }

    public String getBalanceAmt() {
        return balanceAmt;
    }

    public AccountTradeResData setBalanceAmt(String balanceAmt) {
        this.balanceAmt = balanceAmt;
        return this;
    }

    public String getBizName() {
        return bizName;
    }

    public AccountTradeResData setBizName(String bizName) {
        this.bizName = bizName;
        return this;
    }

    public String getPurpose() {
        return purpose;
    }

    public AccountTradeResData setPurpose(String purpose) {
        this.purpose = purpose;
        return this;
    }

    public String getBizDesc() {
        return bizDesc;
    }

    public AccountTradeResData setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc;
        return this;
    }

    public String getClientAreaName() {
        return clientAreaName;
    }

    public AccountTradeResData setClientAreaName(String clientAreaName) {
        this.clientAreaName = clientAreaName;
        return this;
    }

    public String getClientAccountName() {
        return clientAccountName;
    }

    public AccountTradeResData setClientAccountName(String clientAccountName) {
        this.clientAccountName = clientAccountName;
        return this;
    }

    public String getClientAccountNo() {
        return clientAccountNo;
    }

    public AccountTradeResData setClientAccountNo(String clientAccountNo) {
        this.clientAccountNo = clientAccountNo;
        return this;
    }

    public String getClientBranchNo() {
        return clientBranchNo;
    }

    public AccountTradeResData setClientBranchNo(String clientBranchNo) {
        this.clientBranchNo = clientBranchNo;
        return this;
    }

    public String getClientBranchName() {
        return clientBranchName;
    }

    public AccountTradeResData setClientBranchName(String clientBranchName) {
        this.clientBranchName = clientBranchName;
        return this;
    }

    public String getClientBranchAddress() {
        return clientBranchAddress;
    }

    public AccountTradeResData setClientBranchAddress(String clientBranchAddress) {
        this.clientBranchAddress = clientBranchAddress;
        return this;
    }

    public String getAlphaCompArea() {
        return alphaCompArea;
    }

    public AccountTradeResData setAlphaCompArea(String alphaCompArea) {
        this.alphaCompArea = alphaCompArea;
        return this;
    }

    public String getAlphaCompAccountNo() {
        return alphaCompAccountNo;
    }

    public AccountTradeResData setAlphaCompAccountNo(String alphaCompAccountNo) {
        this.alphaCompAccountNo = alphaCompAccountNo;
        return this;
    }

    public String getAlphaCompName() {
        return alphaCompName;
    }

    public AccountTradeResData setAlphaCompName(String alphaCompName) {
        this.alphaCompName = alphaCompName;
        return this;
    }

    public String getInfoFlag() {
        return infoFlag;
    }

    public AccountTradeResData setInfoFlag(String infoFlag) {
        this.infoFlag = infoFlag;
        return this;
    }

    public String getIncludeAttachment() {
        return includeAttachment;
    }

    public AccountTradeResData setIncludeAttachment(String includeAttachment) {
        this.includeAttachment = includeAttachment;
        return this;
    }

    public String getBillNo() {
        return billNo;
    }

    public AccountTradeResData setBillNo(String billNo) {
        this.billNo = billNo;
        return this;
    }

    public String getReverseFlag() {
        return reverseFlag;
    }

    public AccountTradeResData setReverseFlag(String reverseFlag) {
        this.reverseFlag = reverseFlag;
        return this;
    }

    public String getAppendDesc() {
        return appendDesc;
    }

    public AccountTradeResData setAppendDesc(String appendDesc) {
        this.appendDesc = appendDesc;
        return this;
    }
}
