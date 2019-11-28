package com.shui.payment.cmb.beans.accountprotocol;

/**
 * 2.2 查询账户详细信息 支持多账户查询
 *
 * @author code
 * @Title: AccountDetailReqData
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/11/2511:14 AM
 */
public class AccountDetailReqData {
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

    public String getBBKNBR() {
        return BBKNBR;
    }

    public AccountDetailReqData setBBKNBR(String BBKNBR) {
        this.BBKNBR = BBKNBR;
        return this;
    }

    public String getC_BBKNBR() {
        return C_BBKNBR;
    }

    public AccountDetailReqData setC_BBKNBR(String c_BBKNBR) {
        C_BBKNBR = c_BBKNBR;
        return this;
    }

    public String getACCNBR() {
        return ACCNBR;
    }

    public AccountDetailReqData setACCNBR(String ACCNBR) {
        this.ACCNBR = ACCNBR;
        return this;
    }
}
