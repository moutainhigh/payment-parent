package com.shui.payment.cmb.beans.accounttradequeryprotocol;

import java.util.List;

/**
 * @author code
 * @Title: AccountPayQueryResData
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/11/122:03 PM
 */
public class AccountPayQueryResData {
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

    private List<AccountTradeResData> resData;


    public String getReturnCode() {
        return returnCode;
    }

    public AccountPayQueryResData setReturnCode(String returnCode) {
        this.returnCode = returnCode;
        return this;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public AccountPayQueryResData setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
        return this;
    }

    public String getErrCode() {
        return errCode;
    }

    public AccountPayQueryResData setErrCode(String errCode) {
        this.errCode = errCode;
        return this;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public AccountPayQueryResData setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public List<AccountTradeResData> getResData() {
        return resData;
    }

    public AccountPayQueryResData setResData(List<AccountTradeResData> resData) {
        this.resData = resData;
        return this;
    }
}
