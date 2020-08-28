package com.payment.shui.webank.httpclient;

/**
 * http接口配置参数类，实际应用中需要进行实现该类的函数
 *
 * @author code
 * @Title: HttpClientConfig
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/26 14:34
 */
public interface HttpConfig {

    // 单一主机最大并发连接数:默认为2,这里增大到200,避免高并发时,因此导致支付阻塞.
    public int getConnectionPerRoute();

    //连接超时时间，毫秒
    public int getHttpConnectTimeoutMs();

    //读取超时时间，毫秒
    public int getHttpReadTimeoutMs();
    /**
     * 参考值如下：
     *  private int connectionPerRoute = 200;
     *     //连接超时时间，毫秒
     *     private int httpConnectTimeoutMs = 60 * 1000;
     *     //读取超时时间，毫秒
     *     private int httpReadTimeoutMs = 60 * 1000;
     */
}
