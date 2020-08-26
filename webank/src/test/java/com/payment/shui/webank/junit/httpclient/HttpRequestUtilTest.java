package com.payment.shui.webank.junit.httpclient;

import com.alibaba.fastjson.JSON;
import com.payment.shui.webank.channel.constant.ConstantUtil;
import com.payment.shui.webank.httpclient.*;
import com.payment.shui.webank.utils.RandomUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author code
 * @Title: HttpRequestUtilTest
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/26 14:41
 */
public class HttpRequestUtilTest {

    HttpRequestUtil httpRequestUtil;

    @BeforeEach
    public void setUp() {
        HttpClientConfig httpClientConfig = new HttpClientConfigImpl();
        httpRequestUtil = HttpRequestUtilImpl.getInstance(httpClientConfig);
    }

    @Test
    public void httpTest() throws IOException {
        String response = httpRequestUtil.get("http://www.baidu.com/");
        System.out.println("返回结果："+response);
    }

    /**
     * 启动项目，爱可以进行该测试
     * @throws IOException
     */
    @Test
    public void httpPostTest() throws IOException {
        CloseableHttpResponse response = httpRequestUtil.httpGet( "http://localhost:8280/paramPost?type=gold");
        System.out.println("返回结果："+ EntityUtils.toString(response.getEntity()));
        System.out.println("返回码："+response.getStatusLine().getStatusCode());
        Assertions.assertEquals(200,response.getStatusLine().getStatusCode());
    }

    /**
     * 启动项目，爱可以进行该测试
     * @throws IOException
     */
    @Test
    public void httpParamPostTest() throws IOException {
        Map<String, String> map = new HashMap<>();
        String ranStr = RandomUtil.generateAlphaString(4);
        map.put("type", ranStr);
        String response = httpRequestUtil.mapPost(map, "http://localhost:8280/jsonPost");
        System.out.println("返回结果："+ response);
        Assertions.assertEquals(ranStr, JSON.parseObject(response).getString("type"));
    }
}
