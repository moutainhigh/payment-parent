package com.shui.payment.cmb.beans.directpayprotocol;

/**
 * 3.6 直接支付接口 DCOPDPAYX 部分
 *
 * @author code
 * @Title: CmbDirectPayReqData
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/10/144:55 PM
 */
public class CmbDirectPayReqData {

    /**
     * 用于标识该笔业务的编号，企业银行编号+业务类型+业务参考号必须唯一。
     * 企业可以自定义业务参考号，也可使用银行缺省值（单笔支付），批量支付须由企业提供。
     * 直联必须用企业提供
     */
    private String YURREF;
    /**
     * 付方帐号
     * 企业用于付款的转出帐号，该帐号的币种类型必须与币种字段相符
     */
    private String DBTACC;
    /**
     * 付方开户地区代码
     * 付方帐号的开户行所在地区，如北京、上海、深圳等。
     * 付方开户地区和付方开户地区代码不能同时为空，同 时 有 值 时DBTBBK 有效。
     */
    private String DBTBBK;
    /**
     * 交易金额
     */
    private String TRSAMT; //交易金额
    /**
     * 币种代码
     * 币种代码和币种名称不能同时为空同时有值时CCYNBR 有效。。币种暂时只支持 10(人民币)
     */
    private String CCYNBR; //币种代码
    /**
     * 结算方式代码,只对跨行交易有效
     * N：普通
     * F：快速
     */
    private String STLCHN;
    /**
     * 用途
     * 对应对账单中的摘要 NARTXT
     */
    private String NUSAGE;
    /**
     * 收方帐号
     * 收款企业的转入帐号，该帐号的币种类型必须与币种字段相符
     */
    private String CRTACC;
    /**
     * 系统内外标志
     * Y：招行； N：非招行
     */
    private String BNKFLG;
    /**
     * 收方开户行
     * 跨行支付（BNKFLG=N）必填
     */
    private String CRTBNK;
    /**
     * 城市代码
     * 附录 A.18
     * CRTFLG 不为 Y 时行内支付必填。
     * 行内支付填写，为空则不支持收方识别功能
     */
    private String CTYCOD;
    /**
     * 收方行地址
     * 跨 行 支 付（ BNKFLG=N）必填；CRTFLG 不为 Y时行内支付必填。
     * <p>
     * 例如：广东省深圳市南山区
     */
    private String CRTADR;


    //允许为空的参数
    /**
     * 收方帐户名 (可为空)
     */
    private String CRTNAM;
    /**
     * 收方长户名
     * 收款方企业的转入帐号的帐户名称。
     * 收方帐户名与收方长户名不能同时为空
     */
    private String LRVEAN;
    /**
     * 期望日
     * 默认为当前日期
     */
    private String EPTDAT;
    /**
     * 期望时间
     * 默认为‘ 000000’
     */
    private String EPTTIM;
    /**
     * 业务摘要
     */
    private String BUSNAR;
    /**
     * 收方行号
     * 人行自动支付收方联行号
     */
    private String BRDNBR;

    public String getYURREF() {
        return YURREF;
    }

    public CmbDirectPayReqData setYURREF(String YURREF) {
        this.YURREF = YURREF;
        return this;
    }

    public String getDBTACC() {
        return DBTACC;
    }

    public CmbDirectPayReqData setDBTACC(String DBTACC) {
        this.DBTACC = DBTACC;
        return this;
    }

    public String getDBTBBK() {
        return DBTBBK;
    }

    public CmbDirectPayReqData setDBTBBK(String DBTBBK) {
        this.DBTBBK = DBTBBK;
        return this;
    }

    public String getTRSAMT() {
        return TRSAMT;
    }

    public CmbDirectPayReqData setTRSAMT(String TRSAMT) {
        this.TRSAMT = TRSAMT;
        return this;
    }

    public String getCCYNBR() {
        return CCYNBR;
    }

    public CmbDirectPayReqData setCCYNBR(String CCYNBR) {
        this.CCYNBR = CCYNBR;
        return this;
    }

    public String getSTLCHN() {
        return STLCHN;
    }

    public CmbDirectPayReqData setSTLCHN(String STLCHN) {
        this.STLCHN = STLCHN;
        return this;
    }

    public String getNUSAGE() {
        return NUSAGE;
    }

    public CmbDirectPayReqData setNUSAGE(String NUSAGE) {
        this.NUSAGE = NUSAGE;
        return this;
    }

    public String getCRTACC() {
        return CRTACC;
    }

    public CmbDirectPayReqData setCRTACC(String CRTACC) {
        this.CRTACC = CRTACC;
        return this;
    }

    public String getBNKFLG() {
        return BNKFLG;
    }

    public CmbDirectPayReqData setBNKFLG(String BNKFLG) {
        this.BNKFLG = BNKFLG;
        return this;
    }

    public String getCRTBNK() {
        return CRTBNK;
    }

    public CmbDirectPayReqData setCRTBNK(String CRTBNK) {
        this.CRTBNK = CRTBNK;
        return this;
    }

    public String getCTYCOD() {
        return CTYCOD;
    }

    public CmbDirectPayReqData setCTYCOD(String CTYCOD) {
        this.CTYCOD = CTYCOD;
        return this;
    }

    public String getCRTADR() {
        return CRTADR;
    }

    public CmbDirectPayReqData setCRTADR(String CRTADR) {
        this.CRTADR = CRTADR;
        return this;
    }

    public String getCRTNAM() {
        return CRTNAM;
    }

    public CmbDirectPayReqData setCRTNAM(String CRTNAM) {
        this.CRTNAM = CRTNAM;
        return this;
    }

    public String getLRVEAN() {
        return LRVEAN;
    }

    public CmbDirectPayReqData setLRVEAN(String LRVEAN) {
        this.LRVEAN = LRVEAN;
        return this;
    }

    public String getEPTDAT() {
        return EPTDAT;
    }

    public CmbDirectPayReqData setEPTDAT(String EPTDAT) {
        this.EPTDAT = EPTDAT;
        return this;
    }

    public String getEPTTIM() {
        return EPTTIM;
    }

    public CmbDirectPayReqData setEPTTIM(String EPTTIM) {
        this.EPTTIM = EPTTIM;
        return this;
    }

    public String getBUSNAR() {
        return BUSNAR;
    }

    public CmbDirectPayReqData setBUSNAR(String BUSNAR) {
        this.BUSNAR = BUSNAR;
        return this;
    }

    public String getBRDNBR() {
        return BRDNBR;
    }

    public CmbDirectPayReqData setBRDNBR(String BRDNBR) {
        this.BRDNBR = BRDNBR;
        return this;
    }
}
