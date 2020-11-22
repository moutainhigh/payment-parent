package com.payment.alipay.http;

import com.alibaba.fastjson.JSON;
import com.payment.alipay.config.BaseConfig;
import com.payment.alipay.exception.NetWorkException;
import com.payment.alipay.util.ConstantUtil;
import com.payment.alipay.util.EncryptContant;
import com.payment.alipay.util.PaymentUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.util.MimeTypeUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * HTTP请求相关，通过代理方式进行访问
 *
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
    private final ConcurrentMap<String, CloseableHttpClient> httpsClients = new ConcurrentHashMap<String, CloseableHttpClient>();


    private HttpRequestUtilImpl() {
        this(200, 60 * 1000, 60 * 1000);
    }

    private HttpRequestUtilImpl(int connectionPerRoute, int httpConnectTimeoutMs, int httpReadTimeoutMs) {
        this.connectionPerRoute = connectionPerRoute;
        this.httpConnectTimeoutMs = httpConnectTimeoutMs;
        this.httpReadTimeoutMs = httpReadTimeoutMs;
        client = createHttpClient();
    }

    @Override
    public String post(String request, String url) throws IOException, NetWorkException {

        return generalPost(request, url, MimeTypeUtils.TEXT_XML_VALUE, client, ConstantUtil.UTF8);
    }

    @Override
    public String post(String request, String url, String encoding) throws IOException, NetWorkException {
        return generalPost(request, url, null, client, encoding);
    }

    @Override
    public String postMap(Map<String, String> mapRequest, String url) throws IOException, NetWorkException {
        return generalPost(mapRequest, url, client, ConstantUtil.UTF8);
    }



    @Override
    public String get(String url,String encoding) throws IOException {
        return generalGet(url, client, encoding);
    }

    /**
     * EntityUtils.toByteArray(entity)
     *
     * @param url
     * @return
     * @throws IOException
     */
    @Override
    public byte[] getBytes(String url) throws IOException {
        HttpGet httpGet = HttpRequestClient.createHttpGet(url);
        CloseableHttpResponse response = client.execute(httpGet);
        return EntityUtils.toByteArray(response.getEntity());
    }


    private CloseableHttpClient createHttpClient() {
        // 因微信的服务器响应延时大约为500ms~~1.5s,因此有必要增加单一点对点最大连接数,在下一步优化中应放入配置文件里
        CloseableHttpClient client = HttpClients.custom().setMaxConnPerRoute(connectionPerRoute)
                .setMaxConnTotal(connectionPerRoute).build();
        return client;
    }

    /**
     * 获取该类的实例
     *
     * @param config
     * @return
     */
    public static final HttpRequestUtilImpl getInstance(BaseConfig config) {
        int connectionPerRoute = 200;
        //连接超时时间，毫秒
        int httpConnectTimeoutMs = 60 * 1000;
        //读取超时时间，毫秒
        int httpReadTimeoutMs = 60 * 1000;
        if (config != null) {
            if (StringUtils.isNotEmpty(config.getConnectionsPerRoute())) {
                connectionPerRoute = Integer.parseInt(config.getConnectionsPerRoute());
            }
            if (config.getHttpConnectTimeoutMs() > 0) {
                httpConnectTimeoutMs = config.getHttpConnectTimeoutMs();
            }
            if (config.getHttpReadTimeoutMs() > 0) {
                httpReadTimeoutMs = config.getHttpReadTimeoutMs();
            }
        }

        return new HttpRequestUtilImpl(connectionPerRoute, httpConnectTimeoutMs, httpReadTimeoutMs);
    }

//    /**
//     * 返回配置微信商户的p12文件的加密连接客户端
//     *
//     * @return
//     */
//    private CloseableHttpClient getHttpsClient(BaseWechatConfig config) {
//
//        CloseableHttpClient httpsClient = httpsClients.get(config.getMchID());
//        if (httpsClient == null) {
//            try {
//                //putIfAbsent方法保证返回值为同一个，该方法本身赋值后便会有对应的返回值返回
//                httpsClient = httpsClients.putIfAbsent(config.getMchID(), getPkcs12Client(config.getMchID(), config.getCertStream()));
//                if (httpsClient == null) {
//                    httpsClient = httpsClients.get(config.getMchID());
//                }
//            } catch (Exception e) {
//                log.error("当执行微信交易过程中,尝试创建客户端失败,请检查.", e);
//            }
//        }
//        return httpsClient;
//    }

    private CloseableHttpClient getPkcs12Client(String mchId, InputStream instream) throws Exception {
        KeyStore keyStore = KeyStore.getInstance(EncryptContant.PKCS12);

        try {
            keyStore.load(instream, mchId.toCharArray());
        } catch (Exception e) {
            throw e;
        }
        // Trust own CA and all self-signed certs
        // 私有key在微信中默认以mchId作为密钥
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mchId.toCharArray()).build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        CloseableHttpClient httpclient = HttpClients.custom().setMaxConnPerRoute(connectionPerRoute)
                .setMaxConnTotal(connectionPerRoute).setSSLSocketFactory(sslsf).build();

        return httpclient;
    }


    /**
     * 发起get请求
     *
     * @param url
     * @param httpsClient
     * @param encode
     * @return
     */
    private String generalGet(String url, CloseableHttpClient httpsClient, String encode) throws IOException {
        HttpGet httpGet = HttpRequestClient.createHttpGet(url);
        return execute(httpsClient, httpGet, encode);
    }


    private String generalPost(Map<String, String> request, String url, CloseableHttpClient httpsClient, String encoding) throws IOException, NetWorkException {
        HttpPost httpPost = HttpRequestClient.createHttpPost(request, url, null, encoding, httpConnectTimeoutMs, httpReadTimeoutMs);
        return executeHttp(JSON.toJSONString(request), url, httpsClient, httpPost, encoding);
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
    private String generalPost(String request, String url, String mimeType, CloseableHttpClient httpsClient, String encoding) throws IOException, NetWorkException {
        HttpPost httpPost = HttpRequestClient.createHttpPost(request, url, mimeType, encoding, httpConnectTimeoutMs, httpReadTimeoutMs);
        return executeHttp(request, url, httpsClient, httpPost, encoding);
    }

    /**
     * 发起调用HTTP请求操作
     *
     * @param httpsClient
     * @param httpUriRequest
     * @return
     * @throws IOException
     */


    private String execute(CloseableHttpClient httpsClient, HttpUriRequest httpUriRequest, String encode) throws IOException {

        CloseableHttpResponse response = httpsClient.execute(httpUriRequest);
        return EntityUtils.toString(response.getEntity(), encode);
    }

    private String executeHttp(String request, String url, CloseableHttpClient httpsClient, HttpPost httpPost, String encoding) throws IOException, NetWorkException {
        String result;
        try {
            String threadName = System.currentTimeMillis() + PaymentUtil.generateString(5);
            log.info(threadName + " - Request:{url:" + url + ", body: " + request + "}");
            result = execute(httpsClient, httpPost, encoding);
            log.info(threadName + " - Response:{url:" + url + ", body: " + result + "}");
        } catch (ParseException | IOException e) {
            log.error(
                    "Exception:{ message:  请求远程服务时发生 " + e.getCause().toString() + "! " + e.getMessage() + "}",
                    e);
            throw new NetWorkException(e.getMessage());
        }
        return result;
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
        for (CloseableHttpClient client1 : httpsClients.values()) {
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
