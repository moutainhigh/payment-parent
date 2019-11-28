package com.shui.payment.cmb.beans.accountprotocol;

import java.util.List;

/**
 * @author code
 * @Title: AccountDetailListResData
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/11/2511:39 AM
 */
public class AccountDetailListResData {
    /**
     * RETCOD
     */
    private String returnCode;
    /**
     * ERRMSG
     */
    private String returnMsg;

    /**
     * ERRCOD 错误码
     */
    private String errCode;
    /**
     * ERRTXT 错误文本
     */
    private String errMsg;

    private List<AccountDetailResData> accountDetailResDataList;

    public String getReturnCode() {
        return returnCode;
    }

    public AccountDetailListResData setReturnCode(String returnCode) {
        this.returnCode = returnCode;
        return this;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public AccountDetailListResData setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
        return this;
    }

    public String getErrCode() {
        return errCode;
    }

    public AccountDetailListResData setErrCode(String errCode) {
        this.errCode = errCode;
        return this;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public AccountDetailListResData setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public List<AccountDetailResData> getAccountDetailResDataList() {
        return accountDetailResDataList;
    }

    public AccountDetailListResData setAccountDetailResDataList(List<AccountDetailResData> accountDetailResDataList) {
        this.accountDetailResDataList = accountDetailResDataList;
        return this;
    }
}
