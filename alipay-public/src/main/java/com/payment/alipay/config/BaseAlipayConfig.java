package com.payment.alipay.config;


/**
 * 暂时无用
 * @author code
 * @Title: BaseAlipayConfig
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/9/911:19 AM
 */
public interface BaseAlipayConfig extends BaseConfig {


    /**
     * 支付宝openapi域名
     *
     * @return
     */
    public String getOpenApiDomain();

    /**
     * 支付宝mcloudmonitor域名
     *
     * @return
     */
    public String getMcloudApiDomain();

    /**
     * 商户partner id
     *
     * @return
     */
    public String getPid();

    /**
     * 商户应用id
     *
     * @return
     */
    public String getAppid();

    /**
     * RSA私钥，用于对商户请求报文加签
     *
     * @return
     */
    public String getKey();

    /**
     * RSA公钥，仅用于验证开发者网关
     *
     * @return
     */
    public String getPublicKey();

    /**
     * 支付宝RSA公钥，用于验签支付宝应答
     *
     * @return
     */
    public String getAlipayPublicKey();

    /**
     * 签名类型
     *
     * @return
     */
    public String getSignType();

    /**
     * 最大查询次数
     *
     * @return
     */
    public int getMaxQueryRetry();

    /**
     * 查询间隔（毫秒）
     *
     * @return
     */
    public long getQueryDuration();

    /**
     * 最大撤销次数
     *
     * @return
     */
    public int getMaxCancelRetry();

    /**
     * 撤销间隔（毫秒）
     *
     * @return
     */
    public long getCancelDuration();

    /**
     * 交易保障线程第一次调度延迟（秒）
     *
     * @return
     */
    public long getHeartbeatDelay();

    /**
     * 交易保障线程调度间隔（秒）
     *
     * @return
     */
    public long getHeartbeatDuration();
}
