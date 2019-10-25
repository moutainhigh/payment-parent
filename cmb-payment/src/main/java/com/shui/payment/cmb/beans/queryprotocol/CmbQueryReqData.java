package com.shui.payment.cmb.beans.queryprotocol;

/**
 * 3.11 支付结果查询-按业务参考号 NTQRYSTYX1 部分
 *
 * @author code
 * @Title: CmbQueryListReqData
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/10/159:47 AM
 */
public class CmbQueryReqData {

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
     * 业务参考号
     * 只查询指定的业务参考号交易（不提供模糊查询）
     */
    private String YURREF;
    /**
     * 起始日期 20170501
     * 起始结束日期间隔不可超过一周
     */
    private String BGNDAT;
    /**
     * 结束日期 20170501
     * 起始结束日期间隔不可超过一周
     */
    private String ENDDAT;


    public String getBUSCOD() {
        return BUSCOD;
    }

    public CmbQueryReqData setBUSCOD(String BUSCOD) {
        this.BUSCOD = BUSCOD;
        return this;
    }

    public String getYURREF() {
        return YURREF;
    }

    public CmbQueryReqData setYURREF(String YURREF) {
        this.YURREF = YURREF;
        return this;
    }

    public String getBGNDAT() {
        return BGNDAT;
    }

    public CmbQueryReqData setBGNDAT(String BGNDAT) {
        this.BGNDAT = BGNDAT;
        return this;
    }

    public String getENDDAT() {
        return ENDDAT;
    }

    public CmbQueryReqData setENDDAT(String ENDDAT) {
        this.ENDDAT = ENDDAT;
        return this;
    }


}
