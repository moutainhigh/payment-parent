package com.payment.shui.webank.controller;

import com.payment.shui.webank.beans.InitBean;
import com.payment.shui.webank.utils.RandomUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author code
 * @Title: InitController
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/25 14:25
 */
@RestController
public class InitController {

    @GetMapping("/index")
    public String index() {
        return "webank project success";
    }

    public String webank(HttpServletRequest request, HttpServletResponse response){

        return null;
    }

    @PostMapping(value ="/jsonPost",produces = "application/json")
    public InitBean httpPostJunit(HttpServletRequest request){
        String type = request.getParameter("type");
        System.out.println("Request type = " + type);

        InitBean bean = new InitBean();
        bean.setType(type)
                .setApp_id("random_appId")
                .setNonce(RandomUtil.generateAlphaString(5));
        return bean;
    }

    @GetMapping(value ="/paramPost",produces = "application/json")
    public InitBean httpPostJunit1(String type){
        System.out.println("Request type = " + type);

        InitBean bean = new InitBean();
        bean.setType(type)
                .setApp_id("random_appId")
                .setNonce(RandomUtil.generateAlphaString(3));
        return bean;
    }
}
