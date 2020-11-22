package com.payment.shui.webank.beans.protocol;

/**公共返回部分
 * @author code
 * @Title: WebankResData
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/25 16:46
 */
public class WebankResData {

    private String msg;
    private String code;
    /**
     * 生成xxx时间
     */
    private String transactionTime;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }
}
