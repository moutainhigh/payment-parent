package com.shui.payment.cmb;

import com.alibaba.fastjson.JSON;
import com.shui.payment.cmb.config.CmbPropertiesConfig;
import com.shui.payment.cmb.http.HttpRequestUtil;
import com.shui.payment.cmb.util.CmbUtil;

import java.util.Map;

/**
 * @author code
 * @Title: AbstractCmbPaymentServiceHandler
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/10/1511:37 AM
 */
public abstract class AbstractCmbPaymentServiceHandler {

    protected CmbPropertiesConfig cmbConfig;
    protected HttpRequestUtil httpRequestUtil;
    /**
     * 将请求参数转化为map类型返回
     *
     * @param request
     * @param clazz
     * @param <T>
     * @return
     */
    protected <T> Map getRequestMap(Object request, Class<T> clazz) {
        T t = JSON.parseObject(JSON.toJSONString(request), clazz);
        return CmbUtil.objectToMap(t);
    }
}
