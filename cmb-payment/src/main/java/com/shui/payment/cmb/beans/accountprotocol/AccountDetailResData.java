package com.shui.payment.cmb.beans.accountprotocol;

/**
 * @author code
 * @Title: AccountDetailResData
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/11/2511:39 AM
 */
public class AccountDetailResData {

    /**
     * 币种 A.3 货币代码表
     * CCYNBR
     */
    private String currency;
    /**
     * 币种名称 A.3 货币代码表
     * C_CCYNBR
     */
    private String currencyName;
    /**
     * 科目
     * ACCITM
     */
    private String course;
    /**
     * 分行号
     * BBKNBR
     */
    private String payAreaCode;
    /**
     * 账号
     * ACCNBR
     */
    private String payAccountNo;
    /**
     * 注解
     * ACCNAM
     */
    private String comment;
    /**
     * 上日余额
     * ACCBLV
     * 当 INTCOD='S'时，这个字段的值显示为"头寸额度（集团支付子公司余额）"是子公司的虚拟余额
     */
    private String cashAccountBalance;
    /**
     * 联机余额
     * ONLBLV
     */
    private String onlineBalance;
    /**
     * 冻结余额
     * HLDBLV
     */
    private String freezeBalance;
    /**
     * 可用余额
     * AVLBLV
     */
    private String availableBalance;
    /**
     * 透支额度
     * LMTOVR
     */
    private String overdraftAmtLimit;
    /**
     * 账户状态
     * STSCOD A=活动， B=冻结， C=关户
     */
    private String accountStatus;
    /**
     * 利息码 S=子公司虚拟余额
     * INTCOD
     */
    private String interestCode;
    /**
     * 年利率
     * C_INTRAT
     */
    private String interestRate;
    /**
     * 开户日
     * OPNDAT
     */
    private String openAccountDate;
    /**
     * 到期日
     * MUTDAT
     */
    private String maturityDate;
    /**
     * 利率类型 A.35 利率类型码
     * INTTYP
     */
    private String interestType;
    /**
     * 存期
     * DPSTXT
     * 定期时，取值：一天,七天,一个月,三个月,六个月,一年,二年,三年,四年,五年
     */
    private String depositTerm;

    public String getCurrency() {
        return currency;
    }

    public AccountDetailResData setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public AccountDetailResData setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
        return this;
    }

    public String getCourse() {
        return course;
    }

    public AccountDetailResData setCourse(String course) {
        this.course = course;
        return this;
    }

    public String getPayAreaCode() {
        return payAreaCode;
    }

    public AccountDetailResData setPayAreaCode(String payAreaCode) {
        this.payAreaCode = payAreaCode;
        return this;
    }

    public String getPayAccountNo() {
        return payAccountNo;
    }

    public AccountDetailResData setPayAccountNo(String payAccountNo) {
        this.payAccountNo = payAccountNo;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public AccountDetailResData setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getCashAccountBalance() {
        return cashAccountBalance;
    }

    public AccountDetailResData setCashAccountBalance(String cashAccountBalance) {
        this.cashAccountBalance = cashAccountBalance;
        return this;
    }

    public String getOnlineBalance() {
        return onlineBalance;
    }

    public AccountDetailResData setOnlineBalance(String onlineBalance) {
        this.onlineBalance = onlineBalance;
        return this;
    }

    public String getFreezeBalance() {
        return freezeBalance;
    }

    public AccountDetailResData setFreezeBalance(String freezeBalance) {
        this.freezeBalance = freezeBalance;
        return this;
    }

    public String getAvailableBalance() {
        return availableBalance;
    }

    public AccountDetailResData setAvailableBalance(String availableBalance) {
        this.availableBalance = availableBalance;
        return this;
    }

    public String getOverdraftAmtLimit() {
        return overdraftAmtLimit;
    }

    public AccountDetailResData setOverdraftAmtLimit(String overdraftAmtLimit) {
        this.overdraftAmtLimit = overdraftAmtLimit;
        return this;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public AccountDetailResData setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
        return this;
    }

    public String getInterestCode() {
        return interestCode;
    }

    public AccountDetailResData setInterestCode(String interestCode) {
        this.interestCode = interestCode;
        return this;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public AccountDetailResData setInterestRate(String interestRate) {
        this.interestRate = interestRate;
        return this;
    }

    public String getOpenAccountDate() {
        return openAccountDate;
    }

    public AccountDetailResData setOpenAccountDate(String openAccountDate) {
        this.openAccountDate = openAccountDate;
        return this;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public AccountDetailResData setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
        return this;
    }

    public String getInterestType() {
        return interestType;
    }

    public AccountDetailResData setInterestType(String interestType) {
        this.interestType = interestType;
        return this;
    }

    public String getDepositTerm() {
        return depositTerm;
    }

    public AccountDetailResData setDepositTerm(String depositTerm) {
        this.depositTerm = depositTerm;
        return this;
    }
}
