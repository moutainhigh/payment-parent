package com.payment.alipay.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayRequest;
import com.alipay.api.AlipayResponse;
import com.payment.alipay.model.builder.RequestBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by liuyangkly on 15/10/22.
 */
@Slf4j
abstract class AbsAlipayService {

    // 验证bizContent对象
    protected void validateBuilder(RequestBuilder builder) {
        if (builder == null) {
            throw new NullPointerException("builder should not be NULL!");
        }

        if (!builder.validate()) {
            throw new IllegalStateException("builder validate failed! " + builder.toString());
        }
    }

    // 调用AlipayClient的execute方法，进行远程调用
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected AlipayResponse getResponse(AlipayClient client, AlipayRequest request) {
        try {
            AlipayResponse response = client.execute(request);
            if (response != null) {
                log.info(response.getBody());
            }
            return response;

        } catch (AlipayApiException e) {
            log.error("getAlipayResponse exception:" + e.getMessage());
            return null;
        }
    }
}
