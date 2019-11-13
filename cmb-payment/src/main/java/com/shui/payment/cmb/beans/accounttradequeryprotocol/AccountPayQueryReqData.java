package com.shui.payment.cmb.beans.accounttradequeryprotocol;


/**
 * @author code
 * @Title: AccountPayQueryReqData
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/11/116:10 PM
 */
public class AccountPayQueryReqData {

    /**
     * 分行号 附录A1
     * 分行号和分行名
     * 称不能同时为空
     */
    private String BBKNBR;
    /**
     * 分行名称 附录A1
     */
    private String C_BBKNBR;
    /**
     * 账号
     */
    private String ACCNBR;


    /**
     * 起始日期 20170417
     * 与结束日期的间
     * 隔不能超过 100 天
     */
    private String BGNDAT;
    /**
     * 结束日期 20170418
     * 与结束日期的间
     * 隔不能超过 100 天
     */
    private String ENDDAT;


    /**
     * 最小金额 元
     * 默认 0.00
     */
    private String LOWAMT;
    /**
     * 最大金额 元
     * 默 认
     * 9999999999999.9
     * 9
     */
    private String HGHAMT;
    /**
     * 借贷码
     * C：收入
     * D：支出
     */
    private String AMTCDR;

    public String getBBKNBR() {
        return BBKNBR;
    }

    public AccountPayQueryReqData setBBKNBR(String BBKNBR) {
        this.BBKNBR = BBKNBR;
        return this;
    }

    public String getC_BBKNBR() {
        return C_BBKNBR;
    }

    public AccountPayQueryReqData setC_BBKNBR(String c_BBKNBR) {
        C_BBKNBR = c_BBKNBR;
        return this;
    }

    public String getACCNBR() {
        return ACCNBR;
    }

    public AccountPayQueryReqData setACCNBR(String ACCNBR) {
        this.ACCNBR = ACCNBR;
        return this;
    }

    public String getBGNDAT() {
        return BGNDAT;
    }

    public AccountPayQueryReqData setBGNDAT(String BGNDAT) {
        this.BGNDAT = BGNDAT;
        return this;
    }

    public String getENDDAT() {
        return ENDDAT;
    }

    public AccountPayQueryReqData setENDDAT(String ENDDAT) {
        this.ENDDAT = ENDDAT;
        return this;
    }

    public String getLOWAMT() {
        return LOWAMT;
    }

    public AccountPayQueryReqData setLOWAMT(String LOWAMT) {
        this.LOWAMT = LOWAMT;
        return this;
    }

    public String getHGHAMT() {
        return HGHAMT;
    }

    public AccountPayQueryReqData setHGHAMT(String HGHAMT) {
        this.HGHAMT = HGHAMT;
        return this;
    }

    public String getAMTCDR() {
        return AMTCDR;
    }

    public AccountPayQueryReqData setAMTCDR(String AMTCDR) {
        this.AMTCDR = AMTCDR;
        return this;
    }
}
