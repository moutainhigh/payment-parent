package com.shui.payment.cmb.parameters;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 3.6 直接支付接口 请求参数 中间部分
 *
 * @author code
 * @Title: CmbDirectPayReqData
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/10/144:55 PM
 */
public class DirectPayRequest {

    /**
     * 支付系统订单号
     * 用于标识该笔业务的编号，企业银行编号+业务类型+业务参考号必须唯一。
     * 企业可以自定义业务参考号，也可使用银行缺省值（单笔支付），批量支付须由企业提供。
     * 直联必须用企业提供
     */
    @JSONField(name = "YURREF")
    private String qdsOrderNo;
    /**
     * 付方帐号
     * 企业用于付款的转出帐号，该帐号的币种类型必须与币种字段相符
     */
    @JSONField(name = "DBTACC")
    private String payAccountNo;
    /**
     * 付方开户地区代码
     * 付方帐号的开户行所在地区，如北京、上海、深圳等。
     * 付方开户地区和付方开户地区代码不能同时为空，同 时 有 值 时DBTBBK 有效。
     * 附录A.1
     */
    @JSONField(name = "DBTBBK")
    private String payAreaCode;
    /**
     * 交易金额
     */
    @JSONField(name = "TRSAMT")
    private String orderAmt;
    /**
     * 币种代码
     * 币种代码和币种名称不能同时为空同时有值时CCYNBR 有效。。币种暂时只支持 10(人民币)
     */
    @JSONField(name = "CCYNBR")
    private String cny = "10";
    /**
     * 结算方式代码,只对跨行交易有效
     * N：普通
     * F：快速
     */
    @JSONField(name = "STLCHN")
    private String settleMode;
    /**
     * 用途
     * 对应对账单中的摘要 NARTXT
     */
    @JSONField(name = "NUSAGE")
    private String description;
    /**
     * 收方帐号
     * 收款企业的转入帐号，该帐号的币种类型必须与币种字段相符
     */
    @JSONField(name = "CRTACC")
    private String acceptAccountNo;
    /**
     * 收方帐户名
     * <p>
     * 收款方企业的转入帐号的帐户名称。
     * 收方帐户名与收方长户名不能同时为空
     */
    @JSONField(name = "CRTNAM")
    private String acceptAccountName;
    /**
     * 收方长户名
     * <p>
     * 收款方企业的转入帐号的帐户名称。
     * 收方帐户名与收方长户名不能同时为空
     */
    @JSONField(name = "LRVEAN")
    private String acceptAccountLongName;


    /**
     * 系统内外标志
     * Y：招行； N：非招行
     */
    @JSONField(name = "BNKFLG")
    private String bankInnerFlag;
    /**
     * 收方开户行
     * 跨行支付（BNKFLG=N）必填
     */
    @JSONField(name = "CRTBNK")
    private String acceptDepositBank;
    /**
     * 城市代码
     * 附录 A.18
     * CRTFLG 不为 Y 时行内支付必填。
     * 行内支付填写，为空则不支持收方识别功能
     */
    @JSONField(name = "CTYCOD")
    private String cityCode;
    /**
     * 收方行地址
     * 跨 行 支 付（ BNKFLG=N）必填；CRTFLG 不为 Y时行内支付必填。
     * <p>
     * 例如：广东省深圳市南山区
     */
    @JSONField(name = "CRTADR")
    private String acceptBankAddress;


    public String getQdsOrderNo() {
        return qdsOrderNo;
    }

    public DirectPayRequest setQdsOrderNo(String qdsOrderNo) {
        this.qdsOrderNo = qdsOrderNo;
        return this;
    }

    public String getPayAccountNo() {
        return payAccountNo;
    }

    public DirectPayRequest setPayAccountNo(String payAccountNo) {
        this.payAccountNo = payAccountNo;
        return this;
    }

    public String getPayAreaCode() {
        return payAreaCode;
    }

    public DirectPayRequest setPayAreaCode(String payAreaCode) {
        this.payAreaCode = payAreaCode;
        return this;
    }

    public String getOrderAmt() {
        return orderAmt;
    }

    public DirectPayRequest setOrderAmt(String orderAmt) {
        this.orderAmt = orderAmt;
        return this;
    }

    public String getCny() {
        return cny;
    }

    public DirectPayRequest setCny(String cny) {
        this.cny = cny;
        return this;
    }

    public String getSettleMode() {
        return settleMode;
    }

    public DirectPayRequest setSettleMode(String settleMode) {
        this.settleMode = settleMode;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DirectPayRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getAcceptAccountNo() {
        return acceptAccountNo;
    }

    public DirectPayRequest setAcceptAccountNo(String acceptAccountNo) {
        this.acceptAccountNo = acceptAccountNo;
        return this;
    }

    public String getBankInnerFlag() {
        return bankInnerFlag;
    }

    public DirectPayRequest setBankInnerFlag(String bankInnerFlag) {
        this.bankInnerFlag = bankInnerFlag;
        return this;
    }

    public String getAcceptDepositBank() {
        return acceptDepositBank;
    }

    public DirectPayRequest setAcceptDepositBank(String acceptDepositBank) {
        this.acceptDepositBank = acceptDepositBank;
        return this;
    }

    public String getCityCode() {
        return cityCode;
    }

    public DirectPayRequest setCityCode(String cityCode) {
        this.cityCode = cityCode;
        return this;
    }

    public String getAcceptBankAddress() {
        return acceptBankAddress;
    }

    public DirectPayRequest setAcceptBankAddress(String acceptBankAddress) {
        this.acceptBankAddress = acceptBankAddress;
        return this;
    }


    public String getAcceptAccountName() {
        return acceptAccountName;
    }

    public DirectPayRequest setAcceptAccountName(String acceptAccountName) {
        this.acceptAccountName = acceptAccountName;
        return this;
    }

    public String getAcceptAccountLongName() {
        return acceptAccountLongName;
    }

    public DirectPayRequest setAcceptAccountLongName(String acceptAccountLongName) {
        this.acceptAccountLongName = acceptAccountLongName;
        return this;
    }
}
