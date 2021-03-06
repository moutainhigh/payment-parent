package com.payment.alipay.http;

import com.alibaba.fastjson.JSON;
import com.payment.alipay.util.ConstantUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于发送HTTP请求
 *
 * @author code
 * @Title: HttpRequestClient
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/30上午9:48
 */
@Slf4j
public class HttpRequestClient {


    private HttpRequestClient() {

    }

    public static final String APPLICATION_JSON_VALUE = "application/json";

    /**
     * 通知分发：下游通道使用
     *
     * @param request
     * @param url
     * @return
     */
    public static HttpPost createHttpJsonPost(String request, String url) {
        return createHttpPost(request, url, APPLICATION_JSON_VALUE);
    }

    public static HttpPost createHttpPost(String request, String url, String contentType) {
        // 定义POST请求
        return createHttpPost(request, url, contentType, ConstantUtil.UTF8,60 * 1000, 60 * 1000);
    }

    public static HttpPost createHttpPost(String request,
                                          String url,
                                          String contentType,
                                          String encoding,
                                          int httpConnectTimeoutMs, int httpReadTimeoutMs) {
        HttpPost httpPost = initHttpPost(url, contentType, httpConnectTimeoutMs, httpReadTimeoutMs);
        StringEntity postEntity = new StringEntity(request, encoding);
        httpPost.setEntity(postEntity);
        return httpPost;
    }
    public static HttpPost createHttpPost(Map<String, String> request, String url, String contentType, String encoding, int httpConnectTimeoutMs, int httpReadTimeoutMs) throws UnsupportedEncodingException {
        HttpPost httpPost = initHttpPost(url, contentType, httpConnectTimeoutMs, httpReadTimeoutMs);


        List<String> keys = new ArrayList<>(request.keySet());

        List<NameValuePair> postEntity = new ArrayList<>();

        for (int i = 0; i < keys.size(); i++) {
            String name = keys.get(i);
            String value = request.get(name);
            postEntity.add(new BasicNameValuePair(name, value));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(postEntity, encoding));
        return httpPost;

    }

    private static HttpPost initHttpPost(String url, String contentType, int httpConnectTimeoutMs, int httpReadTimeoutMs) {
        // 定义POST请求
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(httpReadTimeoutMs).setConnectTimeout(httpConnectTimeoutMs).build();
        httpPost.setConfig(requestConfig);

        if (StringUtils.isNotEmpty(contentType)) {
            httpPost.addHeader(HttpHeaders.CONTENT_TYPE, contentType);
        }

        return httpPost;
    }


    public static HttpGet createHttpGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60 * 1000).setConnectTimeout(60 * 1000).build();
        httpGet.setConfig(requestConfig);
        return httpGet;
    }


    /**
     * 发起调用HTTP请求操作
     *
     * @param httpsClient
     * @param httpRequest
     * @return
     * @throws IOException
     */
    public static String execute(CloseableHttpClient httpsClient, HttpRequestBase httpRequest) throws IOException {

        try {
            CloseableHttpResponse response = httpsClient.execute(httpRequest);
            return EntityUtils.toString(response.getEntity(), ConstantUtil.UTF8);
        } finally {
            try {
                httpsClient.close();
            } catch (IOException e) {
                log.error("http资源关闭异常", e);
            }
        }
    }

    public static CloseableHttpClient createHttpClient() {
        // 因微信的服务器响应延时大约为500ms~~1.5s,因此有必要增加单一点对点最大连接数,在下一步优化中应放入配置文件里
        return HttpClients.custom().setMaxConnPerRoute(20)
                .setMaxConnTotal(20).build();
    }


    /**
     * 提取请求参数
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static String readRequestBody(HttpServletRequest request) throws IOException {
        InputStream is = request.getInputStream();
        StringBuilder buf = new StringBuilder();
        Reader isr = new InputStreamReader(is, ConstantUtil.UTF8);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            buf.append(line);
            buf.append('\n');
        }
        if (buf.length() > 0) {
            buf.deleteCharAt(buf.length() - 1);
        }

        br.close();
        is.close();
        isr.close();
        return buf.toString();
    }

    /**
     * 读取请求参数并转换为json对象
     *
     * @param request
     * @return
     */
    public static String readParametersToJson(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();

        Map<String, Object> paramsMap = new HashMap<>(map.size());
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            Object valueStr = (entry.getValue() == null) ? null : entry.getValue()[0];
            paramsMap.put(entry.getKey(), valueStr);
        }

        return JSON.toJSONString(paramsMap);
    }

}
