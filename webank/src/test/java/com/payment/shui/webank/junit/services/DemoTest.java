package com.payment.shui.webank.junit.services;

import com.payment.shui.webank.beans.InitBean;
import com.payment.shui.webank.utils.WebankUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author code
 * @Title: DemoTest
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/25 17:41
 */
public class DemoTest {

    @Test
    public void demoSign() {
        String httpBody = "{\"REQUEST_TIME\": \"20190829163531\", \"SERVICE_CODE\": \"10002\", \"WX_OPEN_ID\": \"oAUZp5LGzWcXKP1k7m8N16TKlaC8\", \"SF_SUC_TIME\": \"20191124003946\", \"ID_NO\": \"411528199110024452\", \"DEVICE_TYPE\": \"1\", \"DEVICE_LOGIN_COUNT\": \"0\", \"MERCHANT_ID\": \"260125411000001\", \"WX_UNION_ID\": \"otkO4v0ieKtCBqq8f4jaiifu7jzA\"}";
        InitBean bean = getInitBean();
        List list = bean.getList();
        list.add(httpBody);

        String sign = WebankUtil.sign(list, bean.getTicket());
        System.out.println(sign);
        Assertions.assertEquals("07DC5F50333B99D1D99C80AEE3863701C107C6B7", sign);
    }

    private InitBean getInitBean() {
        InitBean bean = new InitBean();
        bean.setApp_id("wxhiw5xt6q4qtmwcrn")
                .setNonce("489c60c97daef599efd10cfda46860a1")
                .setTicket("58b8ea10e5487b88e555feec27fb48201fd788c05b2d424234537b73c7282b70");

        return bean;
    }
}
