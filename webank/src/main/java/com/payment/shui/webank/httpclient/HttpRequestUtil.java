package com.payment.shui.webank.httpclient;

import com.payment.shui.webank.channel.constant.WebankConfig;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.util.Map;

/** 对接httpclient请求操作
 * @author code
 * @Title: HttpRequestUtil
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/21下午9:06
 */
public interface HttpRequestUtil extends AutoCloseable {
    /**
     * 不带证书的post操作
     *
     * @param request
     * @param url     请求的url地址
     * @return
     * @throws IOException
     * @author Yujinshui
     */
    String xmlPost(String request, String url) throws IOException;

    /**
     * 带证书的post操作
     *
     * @param request
     * @param config
     * @param url     请求的url地址
     * @return
     * @throws Exception
     * @author Yujinshui
     */
    String keyCertXmlPost(String request, WebankConfig config, String url) throws Exception;

    String get(String url) throws IOException;
    String get(String url,Map<String,String> requestMap) throws IOException;

    String mapPost(Map<String, String> mapRequest, String url) throws IOException;
    CloseableHttpResponse httpGet(String url) throws IOException;

    CloseableHttpResponse httpPost(String request, String url, String contentType) throws IOException;
}
