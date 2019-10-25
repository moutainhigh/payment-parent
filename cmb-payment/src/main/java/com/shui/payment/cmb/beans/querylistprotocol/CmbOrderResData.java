package com.shui.payment.cmb.beans.querylistprotocol;

/**
 * 支付结果列表查询返回bean NTSTLLSTZ 部分
 * 附录参考《招商银行银企直联接口说明书5.36.0》
 *
 * @author code
 * @Title: CmbOrderResData
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/10/179:51 AM
 */
public class CmbOrderResData {
    /**
     * 银行交易流水号
     * REQNBR 流程实例号
     */
    private String channelOrderNo;
    /**
     * BUSCOD 业务编码
     * 附录 A.4
     */
    private String bizType;
    /**
     * BUSMOD 业务模式
     */
    private String bizMode;
    /**
     * DBTBBK 转出分行号
     * 附录 A.1 招行分行
     */
    private String cmbBranchNo;
    /**
     * DBTACC 付方账号
     */
    private String payAccountNo;
    /**
     * CRTBBK 收方分行号
     * 附录 A.1 招行分行
     */
    private String acceptBranchNo;
    /**
     * CRTACC 收方帐号
     */
    private String acceptAccountNo;
    /**
     * CRTNAM 收方名称
     */
    private String acceptAccountName;
    /**
     * CCYNBR 币种
     * 附录 A.3 货币代码表
     */
    private String cny;
    /**
     * TRSAMT 交易金额 元
     */
    private String orderAmt;
    /**
     * EPTDAT 期望日
     */
    private String queryDate;
    /**
     * EPTTIM 期望时间
     */
    private String queryTime;
    /**
     * OPRDAT 经办日
     */
    private String operateDate;
    /**
     * YURREF 对方参考号 支付系统订单号
     */
    private String qdsOrderNo;
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
     * ATHFLG 是否有附件信息
     */
    private String containFile;
    /**
     * RSV30Z 保留字
     */
    private String remark;
    /**
     * ERRTXT 转账失败原因
     */
    private String errMsg;


    public String getChannelOrderNo() {
        return channelOrderNo;
    }

    public CmbOrderResData setChannelOrderNo(String channelOrderNo) {
        this.channelOrderNo = channelOrderNo;
        return this;
    }

    public String getBizType() {
        return bizType;
    }

    public CmbOrderResData setBizType(String bizType) {
        this.bizType = bizType;
        return this;
    }

    public String getBizMode() {
        return bizMode;
    }

    public CmbOrderResData setBizMode(String bizMode) {
        this.bizMode = bizMode;
        return this;
    }

    public String getCmbBranchNo() {
        return cmbBranchNo;
    }

    public CmbOrderResData setCmbBranchNo(String cmbBranchNo) {
        this.cmbBranchNo = cmbBranchNo;
        return this;
    }

    public String getPayAccountNo() {
        return payAccountNo;
    }

    public CmbOrderResData setPayAccountNo(String payAccountNo) {
        this.payAccountNo = payAccountNo;
        return this;
    }

    public String getAcceptBranchNo() {
        return acceptBranchNo;
    }

    public CmbOrderResData setAcceptBranchNo(String acceptBranchNo) {
        this.acceptBranchNo = acceptBranchNo;
        return this;
    }

    public String getAcceptAccountNo() {
        return acceptAccountNo;
    }

    public CmbOrderResData setAcceptAccountNo(String acceptAccountNo) {
        this.acceptAccountNo = acceptAccountNo;
        return this;
    }

    public String getAcceptAccountName() {
        return acceptAccountName;
    }

    public CmbOrderResData setAcceptAccountName(String acceptAccountName) {
        this.acceptAccountName = acceptAccountName;
        return this;
    }

    public String getCny() {
        return cny;
    }

    public CmbOrderResData setCny(String cny) {
        this.cny = cny;
        return this;
    }

    public String getOrderAmt() {
        return orderAmt;
    }

    public CmbOrderResData setOrderAmt(String orderAmt) {
        this.orderAmt = orderAmt;
        return this;
    }

    public String getQueryDate() {
        return queryDate;
    }

    public CmbOrderResData setQueryDate(String queryDate) {
        this.queryDate = queryDate;
        return this;
    }

    public String getQueryTime() {
        return queryTime;
    }

    public CmbOrderResData setQueryTime(String queryTime) {
        this.queryTime = queryTime;
        return this;
    }

    public String getOperateDate() {
        return operateDate;
    }

    public CmbOrderResData setOperateDate(String operateDate) {
        this.operateDate = operateDate;
        return this;
    }

    public String getQdsOrderNo() {
        return qdsOrderNo;
    }

    public CmbOrderResData setQdsOrderNo(String qdsOrderNo) {
        this.qdsOrderNo = qdsOrderNo;
        return this;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public CmbOrderResData setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
        return this;
    }

    public String getBizResult() {
        return bizResult;
    }

    public CmbOrderResData setBizResult(String bizResult) {
        this.bizResult = bizResult;
        return this;
    }

    public String getContainFile() {
        return containFile;
    }

    public CmbOrderResData setContainFile(String containFile) {
        this.containFile = containFile;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public CmbOrderResData setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public CmbOrderResData setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
