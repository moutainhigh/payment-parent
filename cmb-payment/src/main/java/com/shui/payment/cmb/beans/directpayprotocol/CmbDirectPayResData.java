package com.shui.payment.cmb.beans.directpayprotocol;

/**
 * 直接支付返回结果封装
 *
 * @author code
 * @Title: CmbDirectPayResData
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/10/1611:24 AM
 */
public class CmbDirectPayResData {

    /**
     * RETCOD
     */
    private String returnCode;
    /**
     * ERRMSG
     */
    private String returnMsg;
    /**
     * SQRNBR 流水号
     * 批量经办时，用来表示第几笔记录。
     */
    private String channelSerialNo;
    /**
     * YURREF 业务参考号
     */
    private String qdsOrderNo;
    /**
     * REQNBR 流程实例号
     */
    private String channelOrderNo;

    /**
     * REQSTS 请求状态
     * 附录 A.5
     */
    private String requestStatus;


    /**
     * RTNFLG 业务处理结果
     * 附录 A.6
     */
    private String bizResult;
    /**
     * OPRSQN 待处理操作序列
     */
    private String pendingSequence;
    /**
     * OPRALS 操作别名
     */
    private String operateAlias;
    /**
     * ERRCOD 错误码
     */
    private String errCode;
    /**
     * ERRTXT 错误文本
     */
    private String errMsg;

    public String getReturnCode() {
        return returnCode;
    }

    public CmbDirectPayResData setReturnCode(String returnCode) {
        this.returnCode = returnCode;
        return this;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public CmbDirectPayResData setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
        return this;
    }

    public String getChannelSerialNo() {
        return channelSerialNo;
    }

    public CmbDirectPayResData setChannelSerialNo(String channelSerialNo) {
        this.channelSerialNo = channelSerialNo;
        return this;
    }

    public String getQdsOrderNo() {
        return qdsOrderNo;
    }

    public CmbDirectPayResData setQdsOrderNo(String qdsOrderNo) {
        this.qdsOrderNo = qdsOrderNo;
        return this;
    }

    public String getChannelOrderNo() {
        return channelOrderNo;
    }

    public CmbDirectPayResData setChannelOrderNo(String channelOrderNo) {
        this.channelOrderNo = channelOrderNo;
        return this;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public CmbDirectPayResData setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
        return this;
    }

    public String getBizResult() {
        return bizResult;
    }

    public CmbDirectPayResData setBizResult(String bizResult) {
        this.bizResult = bizResult;
        return this;
    }

    public String getPendingSequence() {
        return pendingSequence;
    }

    public CmbDirectPayResData setPendingSequence(String pendingSequence) {
        this.pendingSequence = pendingSequence;
        return this;
    }

    public String getOperateAlias() {
        return operateAlias;
    }

    public CmbDirectPayResData setOperateAlias(String operateAlias) {
        this.operateAlias = operateAlias;
        return this;
    }

    public String getErrCode() {
        return errCode;
    }

    public CmbDirectPayResData setErrCode(String errCode) {
        this.errCode = errCode;
        return this;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public CmbDirectPayResData setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
