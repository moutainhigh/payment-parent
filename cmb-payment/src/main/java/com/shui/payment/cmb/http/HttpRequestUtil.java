package com.shui.payment.cmb.http;


import java.io.IOException;
import java.util.Map;

/**
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
    String post(String request, String url) throws IOException;
    String post(String request, String url, String encoding) throws IOException;

    String postMap(Map<String, String> request, String url) throws IOException;


    String get(String url) throws IOException;
    byte[] getBytes(String url) throws IOException;

}
