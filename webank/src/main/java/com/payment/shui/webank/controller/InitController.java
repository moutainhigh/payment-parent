package com.payment.shui.webank.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.payment.shui.webank.beans.InitBean;
import com.payment.shui.webank.beans.ResPonseInfo;
import com.payment.shui.webank.channel.constant.ConstantUtil;
import com.payment.shui.webank.enums.ResCodeEnum;
import com.payment.shui.webank.httpclient.HttpRequestClient;
import com.payment.shui.webank.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author code
 * @Title: InitController
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/25 14:25
 */
@Slf4j
@RestController
public class InitController {

    @GetMapping("/index")
    public String index() {
        return "webank project success";
    }

    /**
     * 微众银行调用合作方接口demo
     * TODO 待测试
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/webank")
    public Map<String, Object> webankInvokePartner(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String json = HttpRequestClient.readRequestBody(request);


        Map<String, Object> reqMap = JSON.parseObject(json);
        String transName = (String) reqMap.get("transName");
        String plainContent = (String) reqMap.get("Plain");
        String signature = (String) reqMap.get("Signature");
        String timestamp = (String) reqMap.get("Timestamp");
        String SeqNo = (String) reqMap.get("SeqNo");
        String certSalt = "random";
        String tmpSignStr = plainContent + certSalt + timestamp;
        String tmpSign = Hashing.sha256().hashString(tmpSignStr,
                Charsets.UTF_8).toString();

        ResCodeEnum resData = ResCodeEnum.SUCCESS;
        if (StringUtils.isAnyEmpty(plainContent, signature)) {
            log.info("缺少Plain或Signature，transName:{}, Plain: {}, Signature: {}",
                    transName, plainContent, signature);
            resData = ResCodeEnum.ERR_PARAM;
        } else if (!tmpSign.equals(signature)) {
            //验证签名 signature
            log.error("签名验证失败，plainContent: {},signature: {}", plainContent,
                    signature);
            log.info("Correct Signature: " + tmpSign);
            resData = ResCodeEnum.ERR_SIGN;
        } else {
            //TODO 解析业务报文内容
            String plainStr = URLDecoder.decode(plainContent, ConstantUtil.UTF8);
            log.info("content : " + plainContent);
        }
        reqMap.put("Plain", URLEncoder.encode(getResString(resData),ConstantUtil.UTF8));
        return reqMap;
    }

    /**
     * 单元测试检测http封装使用
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/jsonPost", produces = "application/json")
    public InitBean jsonPost(HttpServletRequest request) {
        String type = request.getParameter("type");
        System.out.println("Request type = " + type);

        InitBean bean = new InitBean();
        bean.setType(type)
                .setApp_id("random_appId")
                .setNonce(RandomUtil.generateAlphaString(5));
        return bean;
    }

    /**
     * 单元测试检测http封装使用
     *
     * @param type
     * @return
     */
    @GetMapping(value = "/paramPost", produces = "application/json")
    public InitBean paramPost(String type) {
        System.out.println("Request type = " + type);

        InitBean bean = new InitBean();
        bean.setType(type)
                .setApp_id("random_appId")
                .setNonce(RandomUtil.generateAlphaString(3));
        return bean;
    }

    /**
     * 返回基本结果参数封装
     * @param res
     * @return
     */
    private ResPonseInfo getResponse(ResCodeEnum res){
        ResPonseInfo info = new ResPonseInfo();
        info.setCODE(res.getCode())
                .setDESC(res.getDesc());
        return info;
    }
    private String getResString(ResCodeEnum res){
        ResPonseInfo info = new ResPonseInfo();
        info.setCODE(res.getCode())
                .setDESC(res.getDesc());
        return JSON.toJSONString(info);
    }
}
