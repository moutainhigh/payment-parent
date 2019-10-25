package com.shui.payment.cmb.beans.querylistprotocol;

/**
 * 3.3 支付结果列表查询 NTQRYSTNY1 部分
 *
 * @author code
 * @Title: CmbQueryListReqData
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/10/159:47 AM
 */
public class CmbQueryListReqData {

    /**
     * 业务类型
     * N02020: 内部转帐
     * N02030: 支付
     * N02031: 直接支付
     * N02040: 集团支付
     * N02041: 直接集团支付
     */
    private String BUSCOD;
    /**
     * 业务模式
     * 指定的单一模式
     * 如00001
     */
    private String BUSMOD;
    /**
     * 起始日期
     * 起始结束日期间隔不可超过一周
     */
    private String BGNDAT;
    /**
     * 结束日期
     * 起始结束日期间隔不可超过一周
     */
    private String ENDDAT;

    //允许为空的部分

    /**
     * 日期类型  默认为A
     * A：经办日期；
     * B：期望日期
     */
    private String DATFLG;
    /**
     * 保留字 50
     */
    private String RSV50Z;

    public String getBUSCOD() {
        return BUSCOD;
    }

    public CmbQueryListReqData setBUSCOD(String BUSCOD) {
        this.BUSCOD = BUSCOD;
        return this;
    }

    public String getBUSMOD() {
        return BUSMOD;
    }

    public CmbQueryListReqData setBUSMOD(String BUSMOD) {
        this.BUSMOD = BUSMOD;
        return this;
    }

    public String getBGNDAT() {
        return BGNDAT;
    }

    public CmbQueryListReqData setBGNDAT(String BGNDAT) {
        this.BGNDAT = BGNDAT;
        return this;
    }

    public String getENDDAT() {
        return ENDDAT;
    }

    public CmbQueryListReqData setENDDAT(String ENDDAT) {
        this.ENDDAT = ENDDAT;
        return this;
    }

    public String getDATFLG() {
        return DATFLG;
    }

    public CmbQueryListReqData setDATFLG(String DATFLG) {
        this.DATFLG = DATFLG;
        return this;
    }

    public String getRSV50Z() {
        return RSV50Z;
    }

    public CmbQueryListReqData setRSV50Z(String RSV50Z) {
        this.RSV50Z = RSV50Z;
        return this;
    }
}
