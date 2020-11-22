package com.payment.alipay.config;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Properties;

/**
 * @author code
 * @Title: AbstractPropertiesConfig
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/7/262:17 PM
 */
@Slf4j
public abstract class AbstractPropertiesConfig {

    /**
     * Properties属性赋值方法
     *
     * @param p
     */
    protected abstract void setPropertiesValues(Properties p);

    /**
     * 赋值异常的提示信息
     *
     * @param fileName
     * @return
     */
    protected abstract String exceptionString(String fileName);

    protected void getFileProperties(String fileName, String encoding) {
        try (FileInputStream fis = new FileInputStream(fileName);
             InputStreamReader reader = new InputStreamReader(fis, encoding)) {
            Properties p = new Properties();
            p.load(reader);
            setPropertiesValues(p);
        } catch (IOException e) {
            throw new RuntimeException(exceptionString(fileName), e);
        }
    }

    protected void setFieldValue(Object config, Map.Entry<String, String> entry) {
        try {
            //在使用jacoco进行覆盖率检测时，map里面会加入一个 $jacocoData 的 key信息,不影响生产环境，仅做覆盖率检测时使用
            //注解产生的log属性也需要进行排除
            if ("$jacocoData|log".contains(entry.getKey())) {
                return;
            }
            // 属性为空的情况，源码里面使用了异常抛出的方式进行的处理
            Field field = config.getClass().getDeclaredField(entry.getKey());

            field.setAccessible(true);

            field.set(config, entry.getValue());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error("属性配置异常", e);
        }
    }
}
