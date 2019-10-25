package com.shui.payment.cmb;

import com.shui.payment.cmb.config.CmbPropertiesConfig;
import com.shui.payment.cmb.http.HttpRequestUtil;
import com.shui.payment.cmb.http.HttpRequestUtilImpl;
import com.shui.payment.cmb.impl.CmbPaymentServiceHandlerImpl;

/**
 * @author code
 * @Title: AppJunitGeneralBaseConfig
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/10/255:46 PM
 */
public class AppJunitGeneralBaseConfig {
    protected CmbBankPaymentService cmbPaymentServiceHandler = null;
    protected HttpRequestUtil httpUtil = null;

    String cmbFile="/Users/yujinshui/Desktop/千丁互联/千丁云中台-支付系统/支付账户配置/cmb/application.properties";

    protected void initCmbConfig() {

        CmbPropertiesConfig cmbPropertiesConfig = new CmbPropertiesConfig(cmbFile);
        httpUtil = HttpRequestUtilImpl.getInstance();
        cmbPaymentServiceHandler = new CmbPaymentServiceHandlerImpl(cmbPropertiesConfig, httpUtil);
    }
}
