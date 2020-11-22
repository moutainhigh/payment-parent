package com.payment.alipay.dto;


import com.payment.alipay.enums.ChannelTypeEnum;
import com.payment.alipay.enums.TradeTypeEnum;

import java.io.Serializable;

/**
 * 公共部分 构建paymentHandler对象使用
 *
 * @author code
 * @Title: BasicDTO
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/5/3110:22 AM
 */
public class BasicDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 要读取的配置文件名称
     */
    private String configFileName;
    /**
     * 必传
     * 子商户公众账号ID  wx8888888888888888
     */
    private String subAppId;
    /**
     * 必传
     * 微信支付分配的子商户号  1900000109
     */
    private String subMchId;

    /**
     * 通道类型,WECHAT,ALIPAY
     */
    private ChannelTypeEnum channelType;

    /**
     * 支付类型
     */
    private TradeTypeEnum tradeTypeEnum;

    public String getConfigFileName() {
        return configFileName;
    }

    public BasicDTO setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
        return this;
    }

    public String getSubAppId() {
        return subAppId;
    }

    public BasicDTO setSubAppId(String subAppId) {
        this.subAppId = subAppId;
        return this;
    }

    public String getSubMchId() {
        return subMchId;
    }

    public BasicDTO setSubMchId(String subMchId) {
        this.subMchId = subMchId;
        return this;
    }

    public ChannelTypeEnum getChannelType() {
        return channelType;
    }

    public BasicDTO setChannelType(ChannelTypeEnum channelType) {
        this.channelType = channelType;
        return this;
    }


    public TradeTypeEnum getTradeTypeEnum() {
        return tradeTypeEnum;
    }

    public BasicDTO setTradeTypeEnum(TradeTypeEnum tradeTypeEnum) {
        this.tradeTypeEnum = tradeTypeEnum;
        return this;
    }
}
