package com.payment.alipay.junit;

import com.payment.alipay.AlipayPaymentServiceHandlerImpl;
import com.payment.alipay.PaymentServiceHandler;
import com.payment.alipay.config.AlipayPropertiesConfig;
import com.payment.alipay.http.HttpRequestUtil;
import com.payment.alipay.http.HttpRequestUtilImpl;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * 普通商户单元测试基础类
 *
 * @author code
 * @Title: AppJunitBaseConfig
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/13上午11:35
 */

public class AppJunitGeneralBaseConfig {

    //支付宝测试环境
    private final String alipayFile = "/Users/yujinshui/Desktop/支付账户配置/alipay/alipay.properties";
    //支付宝生产环境
//    private final String alipayFile = "/Users/yujinshui/Desktop/千丁互联/千丁云中台-支付系统/支付账户配置/alipay/alipay_pro.properties";

    protected HttpRequestUtil httpUtil = null;
    protected PaymentServiceHandler alipayPaymentServiceHandler = null;


    //账户配置类config对应的配置文件中已经添加了子商户信息，所以此处的bean对象不需要二次赋值也可以

    protected void initAlipayConfig() {
        new AlipayPropertiesConfig(alipayFile);
        httpUtil = HttpRequestUtilImpl.getInstance(null);
        alipayPaymentServiceHandler = new AlipayPaymentServiceHandlerImpl(httpUtil);
    }


}
