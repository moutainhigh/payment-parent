package com.shui.payment.cmb.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Properties;

/**
 * 银企直联配置类
 *
 * @author code
 * @Title: CmbPropertiesConfig
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/10/1511:29 AM
 */
@Slf4j
public class CmbPropertiesConfig {
    /**
     * 登录用户名
     */
    private String LOGIN_NAME;
    /**
     * 系统默认付款账户
     */
    private String PAY_ACCOUNT_NO;
    /**
     * 系统默认付款账户所属分行地区代码，参见附录A1
     */
    private String PAY_AREA_CODE;
    /**
     * 2.3 查询账户交易信息func名称 GetTransInfo
     */
    private String FUNC_GETTRANSINFO;
    /**
     * 3.6 直接支付func名称
     */
    private String FUNC_DCPAYMNT;
    /**
     * 3.3 支付结果列表查询func名称
     */
    private String FUNC_NTQRYSTN;
    /**
     * 3.11 支付结果查询-按业务参考号
     */
    private String FUNC_NTQRYSTY;
    /**
     * 前置机URL地址
     */
    private String CMB_URL;


    public CmbPropertiesConfig(String fileName) {
        getFileProperties(fileName, "UTF-8");
        log.info("cmb配置：" + JSON.toJSONString(this));


    }

    public CmbPropertiesConfig(Properties p) {
        if (p == null) {
            return;
        }
        setPropertiesValues(p);

    }

    private void getFileProperties(String fileName, String encoding) {
        try (FileInputStream fis = new FileInputStream(fileName);
             InputStreamReader reader = new InputStreamReader(fis, encoding)) {
            Properties p = new Properties();
            p.load(reader);
            setPropertiesValues(p);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setPropertiesValues(Properties p) {
        LOGIN_NAME = p.getProperty("LOGIN_NAME");
        PAY_ACCOUNT_NO = p.getProperty("PAY_ACCOUNT_NO");
        PAY_AREA_CODE = p.getProperty("PAY_AREA_CODE");
        FUNC_DCPAYMNT = p.getProperty("FUNC_DCPAYMNT");
        FUNC_NTQRYSTN = p.getProperty("FUNC_NTQRYSTN");
        FUNC_NTQRYSTY = p.getProperty("FUNC_NTQRYSTY");
        FUNC_GETTRANSINFO = p.getProperty("FUNC_GETTRANSINFO");
        CMB_URL = p.getProperty("CMB_URL");
    }

    public void setConfigMap(Map<String, String> map, CmbPropertiesConfig config) {
        if (map == null || map.isEmpty()) {
            return;
        }
        //通过反射的方式进行属性赋值操作
        for (Map.Entry<String, String> entry : map.entrySet()) {
            setFieldValue(config, entry);
        }
    }

    private void setFieldValue(Object config, Map.Entry<String, String> entry) {
        try {
            // 属性为空的情况，源码里面使用了异常抛出的方式进行的处理
            Field field = config.getClass().getDeclaredField(entry.getKey());

            field.setAccessible(true);

            field.set(config, entry.getValue());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error("属性配置异常", e);
        }
    }


    public String getLOGIN_NAME() {
        return LOGIN_NAME;
    }

    public String getPAY_ACCOUNT_NO() {
        return PAY_ACCOUNT_NO;
    }

    public String getPAY_AREA_CODE() {
        return PAY_AREA_CODE;
    }

    public String getFUNC_GETTRANSINFO() {
        return FUNC_GETTRANSINFO;
    }

    public String getFUNC_DCPAYMNT() {
        return FUNC_DCPAYMNT;
    }

    public String getFUNC_NTQRYSTN() {
        return FUNC_NTQRYSTN;
    }

    public String getFUNC_NTQRYSTY() {
        return FUNC_NTQRYSTY;
    }

    public String getCMB_URL() {
        return CMB_URL;
    }
}
