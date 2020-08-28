package com.payment.shui.webank.httpclient;

import com.alibaba.fastjson.JSON;
import com.payment.shui.webank.channel.constant.ConstantUtil;
import com.payment.shui.webank.channel.constant.WebankConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author code
 * @Title: HttpRequestUtilImpl
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/21下午9:09
 */
@Slf4j
public class HttpRequestUtilImpl implements HttpRequestUtil {

    // 单一主机最大并发连接数:默认为2,这里增大到200,避免高并发时,因此导致支付阻塞.
    private int connectionPerRoute = 200;
    //连接超时时间，毫秒
    private int httpConnectTimeoutMs = 60 * 1000;
    //读取超时时间，毫秒
    private int httpReadTimeoutMs = 60 * 1000;
    // 用于发送普通http连接的client
    private CloseableHttpClient client = null;

    // 用于发送https带有证书的连接client
    private final ConcurrentMap<String, CloseableHttpClient> httpsClientMap = new ConcurrentHashMap<>();

    private HttpRequestUtilImpl(int connectionPerRoute, int httpConnectTimeoutMs, int httpReadTimeoutMs) {
        this.connectionPerRoute = connectionPerRoute;
        this.httpConnectTimeoutMs = httpConnectTimeoutMs;
        this.httpReadTimeoutMs = httpReadTimeoutMs;
        client = createHttpClient();
    }

    @Override
    public String xmlPost(String request, String url) throws IOException {
        return generalPost(request, url, HttpRequestClient.TEXT_XML_VALUE, client, ConstantUtil.UTF8);

    }


    @Override
    public String keyCertXmlPost(String request, WebankConfig config, String url) throws Exception {
        return generalPost(request, url, HttpRequestClient.TEXT_XML_VALUE, getHttpsClient(config), ConstantUtil.UTF8);
    }

    @Override
    public String get(String url) throws IOException {
        return generalGet(url, null, client, ConstantUtil.UTF8);
    }

    @Override
    public String get(String url, Map<String, String> requestMap) throws IOException {
        return generalGet(url, requestMap, client, ConstantUtil.UTF8);
    }

    @Override
    public String mapPost(Map<String, String> mapRequest, String url) throws IOException {
        return generalPost(mapRequest, url, client, ConstantUtil.UTF8);
    }

    @Override
    public CloseableHttpResponse httpGet(String url) throws IOException {
        HttpGet httpGet = HttpRequestClient.createHttpGet(url);
        return executeResponse(client, httpGet, ConstantUtil.UTF8);
    }

    @Override
    public CloseableHttpResponse httpPost(String request, String url, String contentType) throws IOException {
        HttpPost httpPost = HttpRequestClient.createHttpPost(request, url, contentType, ConstantUtil.UTF8, httpConnectTimeoutMs, httpReadTimeoutMs);

        return executeResponse(client, httpPost, ConstantUtil.UTF8);
    }

    private String generalPost(Map<String, String> request,
                               String url,
                               CloseableHttpClient httpsClient,
                               String encoding) throws IOException {
        HttpPost httpPost = HttpRequestClient.createHttpPost(request, url, null, encoding, httpConnectTimeoutMs, httpReadTimeoutMs);
        return executeHttp(JSON.toJSONString(request), url, httpsClient, httpPost, encoding);
    }

    /**
     * 发起get请求
     *
     * @param url
     * @param httpsClient
     * @param encode
     * @return
     */
    private String generalGet(String url, Map<String, String> requestMap, CloseableHttpClient httpsClient, String encode) throws IOException {
        HttpGet httpGet = HttpRequestClient.createHttpGet(url, "", requestMap);

        String request = requestMap == null ? null : JSON.toJSONString(requestMap);
        return executeHttp(request, url, httpsClient, httpGet, encode);
    }

    private CloseableHttpClient createHttpClient() {
        // 因微信的服务器响应延时大约为500ms~~1.5s,因此有必要增加单一点对点最大连接数,在下一步优化中应放入配置文件里
        CloseableHttpClient client = HttpClients.custom().setMaxConnPerRoute(connectionPerRoute)
                .setMaxConnTotal(connectionPerRoute).build();
        return client;
    }

    /**
     * 获取该类的实例
     */
    public static final HttpRequestUtilImpl getInstance(HttpClientConfig httpClientConfig) {

        return new HttpRequestUtilImpl(httpClientConfig.getConnectionPerRoute(),
                httpClientConfig.getHttpConnectTimeoutMs(),
                httpClientConfig.getHttpReadTimeoutMs());
    }

    /**
     * 返回配置微众的加密连接客户端
     *
     * @return
     */
    private CloseableHttpClient getHttpsClient(WebankConfig config) {

        CloseableHttpClient httpsClient = httpsClientMap.get(config.getSecret());
        if (httpsClient == null) {
            try {
                //putIfAbsent方法保证返回值为同一个，该方法本身赋值后便会有对应的返回值返回
                httpsClient = httpsClientMap.putIfAbsent(config.getSecret(), HttpClientBuilder.create().build());
                if (httpsClient == null) {
                    httpsClient = httpsClientMap.get(config.getSecret());
                }
            } catch (Exception e) {
                log.error("交易过程中,尝试创建客户端失败,请检查.", e);
            }
        }
        return httpsClient;
    }


    /**
     * 发起post请求
     *
     * @param request
     * @param url
     * @param mimeType
     * @param httpsClient
     * @param encoding
     * @return
     * @throws IOException
     */
    private String generalPost(String request,
                               String url,
                               String mimeType,
                               CloseableHttpClient httpsClient,
                               String encoding) throws IOException {
        HttpPost httpPost = HttpRequestClient.createHttpPost(request, url, mimeType, encoding, httpConnectTimeoutMs, httpReadTimeoutMs);
        return executeHttp(request, url, httpsClient, httpPost, encoding);
    }

    private String executeHttp(String request, String url, CloseableHttpClient httpsClient, HttpUriRequest httpUriRequest, String encoding) throws IOException {
        String result;
        try {
            String threadName = System.currentTimeMillis() + "";
            log.info(threadName + " - Request:{url:" + url + ", body: " + request + "}");
            result = execute(httpsClient, httpUriRequest, encoding);
            log.info(threadName + " - Response:{url:" + url + ", body: " + result + "}");
        } catch (ParseException | IOException e) {
            log.error(
                    "Exception:{ message:  请求远程服务时发生 " + e.getCause() + "! " + e.getMessage() + "}",
                    e);
            throw e;
        }
        return result;
    }


    /**
     * 发起调用HTTP请求操作
     *
     * @param httpsClient
     * @param httpUriRequest
     * @return
     * @throws IOException
     */
    private String execute(CloseableHttpClient httpsClient, HttpUriRequest httpUriRequest, String encoding) throws IOException {
        CloseableHttpResponse response = executeResponse(httpsClient, httpUriRequest, encoding);

        return EntityUtils.toString(response.getEntity(), encoding);
    }

    private CloseableHttpResponse executeResponse(CloseableHttpClient httpsClient, HttpUriRequest httpUriRequest, String encoding) throws IOException {
        return httpsClient.execute(httpUriRequest);

    }

    /**
     * 当应用停止时,应调用此方法,避免由未释放资源需要释放
     *
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        IOException ex = null;
        try {
            if (client != null) {
                client.close();
            }
        } catch (IOException e) {
            ex = e;
        }
        for (CloseableHttpClient client1 : httpsClientMap.values()) {
            if (client1 != null) {
                try {
                    client1.close();
                } catch (IOException e) {
                    if (ex == null) {
                        ex = e;
                    }
                }
            }
        }
        if (ex != null) {
            throw ex;
        }
    }
}
