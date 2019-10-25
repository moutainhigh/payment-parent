package com.shui.payment.cmb.beans.querylistprotocol;

import java.util.List;

/**
 * 支付结果列表查询返回bean封装
 *
 * @author code
 * @Title: CmbQueryListResData
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/10/179:40 AM
 */
public class CmbQueryListResData {

    /**
     * RETCOD 返回代码
     * 附录A.2
     */
    private String returnCode;
    /**
     * ERRMSG 错误信息
     */
    private String returnMsg;

    private List<CmbOrderResData> successDataList;
    private List<CmbOrderResData> failDataList;
    /**
     * 其他情况的订单
     */
    private List<CmbOrderResData> otherDataList;


    public String getReturnCode() {
        return returnCode;
    }

    public CmbQueryListResData setReturnCode(String returnCode) {
        this.returnCode = returnCode;
        return this;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public CmbQueryListResData setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
        return this;
    }

    public List<CmbOrderResData> getSuccessDataList() {
        return successDataList;
    }

    public CmbQueryListResData setSuccessDataList(List<CmbOrderResData> successDataList) {
        this.successDataList = successDataList;
        return this;
    }

    public List<CmbOrderResData> getFailDataList() {
        return failDataList;
    }

    public CmbQueryListResData setFailDataList(List<CmbOrderResData> failDataList) {
        this.failDataList = failDataList;
        return this;
    }

    public List<CmbOrderResData> getOtherDataList() {
        return otherDataList;
    }

    public CmbQueryListResData setOtherDataList(List<CmbOrderResData> otherDataList) {
        this.otherDataList = otherDataList;
        return this;
    }
}
