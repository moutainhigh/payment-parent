package com.payment.shui.webank.junit.controller;

import com.alibaba.fastjson.JSON;
import com.payment.shui.webank.WebankApplication;
import com.payment.shui.webank.beans.ResPonseInfo;
import com.payment.shui.webank.channel.constant.ConstantUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author code
 * @Title: InitControllerJunitTest
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/25 14:27
 */
@ExtendWith(SpringExtension.class)//可以保证TestRestTemplate 和 @LocalServerPort注解的生效
//使用本地的一个随机端口启动服务；
@SpringBootTest(classes = WebankApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InitControllerJunitTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String url = "http://127.0.0.1:";

    @BeforeEach
    public void setUp() {
        url += port;
    }

    @Test
    public void index() {

        String response = this.restTemplate.getForObject(url + "/index", String.class);
        System.out.println("返回结果：" + JSON.toJSONString(response));
        Assertions.assertEquals("webank project success",response);

    }

    @Test
    public void webank() throws UnsupportedEncodingException {
        Map<String, String> mapVariables = getMap();
        String jsonRequest = JSON.toJSONString(mapVariables);
        Map<String, Object> response = this.restTemplate.postForObject(url + "/webank", jsonRequest, Map.class);
        System.out.println("返回结果：" + JSON.toJSONString(response));
        Assertions.assertNotNull(response);
//        Assertions.assertEquals("0010",response.getCODE());
    }

    private Map<String, String> getMap() throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();

        map.put("transName", "TLMerchantQuery");
        map.put("Plain", URLEncoder.encode("{\n" +
                "\"merchantno\":\"00000006\",\"product_cd\":\"230001\",\"req_nbr\":\"tbt001\"}", ConstantUtil.UTF8));
        map.put("Signature", "1cb54c406c06033967b4790bc31c6e60");
        map.put("Timestamp", "1519700632680");
        map.put("SeqNo", "1519700632680112122");
        String salt = "random";


        return map;
    }
}
