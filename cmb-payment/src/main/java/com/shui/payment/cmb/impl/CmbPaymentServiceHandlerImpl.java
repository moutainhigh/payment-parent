package com.shui.payment.cmb.impl;

import com.shui.payment.cmb.AbstractCmbPaymentServiceHandler;
import com.shui.payment.cmb.CmbBankPaymentService;
import com.shui.payment.cmb.beans.directpayprotocol.CmbDirectPayReqData;
import com.shui.payment.cmb.beans.querylistprotocol.CmbQueryListReqData;
import com.shui.payment.cmb.beans.queryprotocol.CmbQueryReqData;
import com.shui.payment.cmb.config.CmbPropertiesConfig;
import com.shui.payment.cmb.constant.CmbConstant;
import com.shui.payment.cmb.constant.ConstantUtil;
import com.shui.payment.cmb.enums.BizTypeEnum;
import com.shui.payment.cmb.http.HttpRequestUtil;
import com.shui.payment.cmb.parameters.DirectPayRequest;
import com.shui.payment.cmb.parameters.PayQueryListRequest;
import com.shui.payment.cmb.parameters.PayQueryRequest;
import com.shui.payment.cmb.parser.CmbRequestUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 招行-银企直联渠道
 *
 * @author code
 * @Title: CmbPaymentServiceHandlerImpl
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/10/1511:35 AM
 */
public class CmbPaymentServiceHandlerImpl extends AbstractCmbPaymentServiceHandler implements CmbBankPaymentService, AutoCloseable {

    public CmbPaymentServiceHandlerImpl(CmbPropertiesConfig config, HttpRequestUtil httpRequestUtil) {
        this.cmbConfig = config;
        this.httpRequestUtil = httpRequestUtil;

    }

    @Override
    public void close() throws Exception {
        httpRequestUtil.close();
    }

    @Override
    public <T> T bankPay(DirectPayRequest request, Class<T> responseClass) throws Exception {
        //其中任何一个为空，则都需要启用系统配置
        if (StringUtils.isEmpty(request.getPayAccountNo())
                || StringUtils.isEmpty(request.getPayAreaCode())) {
            request.setPayAccountNo(cmbConfig.getPAY_ACCOUNT_NO())
                    .setPayAreaCode(cmbConfig.getPAY_AREA_CODE());
        }


        Map<String, String> map = getRequestMap(request, CmbDirectPayReqData.class);
        String requestString = CmbRequestUtil.getDirectPayRequestStr(cmbConfig.getFUNC_DCPAYMNT(),
                cmbConfig.getLOGIN_NAME(), BizTypeEnum.N02031.name(), map);

        String response = httpRequestUtil.post(requestString, cmbConfig.getCMB_URL(), ConstantUtil.GBK);

        // 返回结果封装
        return (T) CmbRequestUtil.processPayResult(response);
    }

    @Override
    public <T> T bankPayBatchQuery(PayQueryListRequest request, Class<T> responseClass) throws Exception {


        Map<String, String> map = getRequestMap(request, CmbQueryListReqData.class);

        String requestString = CmbRequestUtil.getQueryRequestStr(cmbConfig.getFUNC_NTQRYSTN(),
                cmbConfig.getLOGIN_NAME(), CmbConstant.DATA_TYPE_BATCH_QUERY, map);

        String response = httpRequestUtil.post(requestString, cmbConfig.getCMB_URL(), ConstantUtil.GBK);

        return (T) CmbRequestUtil.processPayBatchQueryResult(response, CmbConstant.NTSTLLSTZ);
    }

    @Override
    public <T> T bankPayQuery(PayQueryRequest request, Class<T> responseClass) throws Exception {
        Map<String, String> map = getRequestMap(request, CmbQueryReqData.class);

        String requestString = CmbRequestUtil.getQueryRequestStr(cmbConfig.getFUNC_NTQRYSTY(),
                cmbConfig.getLOGIN_NAME(), CmbConstant.DATA_TYPE_QUERY, map);

        //TODO 4.4 处理查询流程

        return null;
    }
}
