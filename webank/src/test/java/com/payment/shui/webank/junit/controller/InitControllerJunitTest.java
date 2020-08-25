package com.payment.shui.webank.junit.controller;

import com.alibaba.fastjson.JSON;
import com.payment.shui.webank.WebankApplication;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

    @Test
    public void index() {

        String response = this.restTemplate.getForObject("http://127.0.0.1:" + port + "/index", String.class);
        System.out.println("返回结果：" + JSON.toJSONString(response));
        Assertions.assertThat(response).contains("webank project success");


    }
}
