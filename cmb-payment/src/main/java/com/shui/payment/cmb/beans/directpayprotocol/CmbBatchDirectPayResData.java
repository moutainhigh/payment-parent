package com.shui.payment.cmb.beans.directpayprotocol;

import java.util.List;

/**
 * 多笔交易  直接支付返回结果封装
 *
 * @author code
 * @Title: CmbDirectPayResData
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/10/1611:24 AM
 */
public class CmbBatchDirectPayResData {

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

    private List<CmbDirectPayResData> resDataList;


    public String getReturnCode() {
        return returnCode;
    }

    public CmbBatchDirectPayResData setReturnCode(String returnCode) {
        this.returnCode = returnCode;
        return this;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public CmbBatchDirectPayResData setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
        return this;
    }


    public String getErrCode() {
        return errCode;
    }

    public CmbBatchDirectPayResData setErrCode(String errCode) {
        this.errCode = errCode;
        return this;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public CmbBatchDirectPayResData setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public List<CmbDirectPayResData> getResDataList() {
        return resDataList;
    }

    public CmbBatchDirectPayResData setResDataList(List<CmbDirectPayResData> resDataList) {
        this.resDataList = resDataList;
        return this;
    }
}
