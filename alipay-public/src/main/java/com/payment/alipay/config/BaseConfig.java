package com.payment.alipay.config;

/**
 * @author code
 * @Title: BaseConfig
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/7/153:49 PM
 */
public interface BaseConfig {
    /**
     * 支付URL
     */

    public String getPayUrl();


    /**
     * 退款-请求URL
     */

    public String getRefundUrl();

    /**
     * 订单查询-请求URL
     */

    public String getOrderQueryUrl();

    /**
     * 对账单路径
     *
     * @return
     */
    public String getStatementPath();


    /**
     * 关闭订单URL
     */

    public String getCloseOrderUrl();

    /**
     * 下载订单对账单地址
     */

    public String getDownloadBillUrl();

    /**
     * 退款查询地址
     */

    public String getRefundQueryUrl();


    /**
     * 订单交易返回URL
     */

    public String getNotifyUrl();



    public String getRefundNotifyUrl();


    public String getConnectionsPerRoute();

    /**
     * 连接超时时间，毫秒
     *
     * @return
     */

    public int getHttpConnectTimeoutMs();

    /**
     * 读取超时时间，毫秒
     *
     * @return
     */

    public int getHttpReadTimeoutMs();
}
