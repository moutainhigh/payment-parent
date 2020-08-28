package com.payment.shui.webank.httpclient;

/** httpclient参数配置信息
 * @author code
 * @Title: HttpClientConfigImpl
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/26 14:36
 */
public class HttpClientConfig implements HttpConfig {
    private int connectionPerRoute = 200;
    //连接超时时间，毫秒
    private int httpConnectTimeoutMs = 60 * 1000;
    //读取超时时间，毫秒
    private int httpReadTimeoutMs = 60 * 1000;

    @Override
    public int getConnectionPerRoute() {
        return connectionPerRoute;
    }
    @Override
    public int getHttpConnectTimeoutMs() {
        return httpConnectTimeoutMs;
    }
    @Override
    public int getHttpReadTimeoutMs() {
        return httpReadTimeoutMs;
    }
}
