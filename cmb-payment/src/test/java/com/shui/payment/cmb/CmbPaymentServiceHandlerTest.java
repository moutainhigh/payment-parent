package com.shui.payment.cmb;

import com.alibaba.fastjson.JSON;
import com.shui.payment.cmb.beans.accountprotocol.AccountDetailListResData;
import com.shui.payment.cmb.beans.accounttradequeryprotocol.AccountPayQueryResData;
import com.shui.payment.cmb.beans.directpayprotocol.CmbBatchDirectPayResData;
import com.shui.payment.cmb.beans.directpayprotocol.CmbDirectPayResData;
import com.shui.payment.cmb.beans.querylistprotocol.CmbQueryListResData;
import com.shui.payment.cmb.enums.BizTypeEnum;
import com.shui.payment.cmb.parameters.AccountDetailRequest;
import com.shui.payment.cmb.parameters.AccountPayQueryRequest;
import com.shui.payment.cmb.parameters.DirectPayRequest;
import com.shui.payment.cmb.parameters.PayQueryListRequest;
import com.shui.payment.cmb.util.CmbUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 银企直联招行
 *
 * @author code
 * @Title: CmbPaymentServiceHandlerTest
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/10/1611:32 AM
 */
public class CmbPaymentServiceHandlerTest extends AppJunitGeneralBaseConfig {
    @Before
    public void testReady() {
        initCmbConfig();
    }
    @Test
    public void bankAccountDetailQuery() throws Exception {

        List<AccountDetailRequest> request = new ArrayList<>();

        AccountDetailRequest ar = new AccountDetailRequest();
//        ar.setPayAccountNo("6214850119760798").setPayAreaCode("10");
//        ar.setPayAccountNo("755915681010811").setPayAreaCode("75");

        request.add(ar);

        AccountDetailListResData response = cmbPaymentServiceHandler.bankAccountDetailQuery(request, AccountDetailListResData.class);

        System.out.println("返回结果：" + JSON.toJSONString(response));
        Assert.assertEquals("SUCCESS", response.getReturnCode());
    }

    @Test
    public void bankAccountTradeQuery() throws Exception {
        AccountPayQueryRequest request = getAccountPayQueryRequest();
        AccountPayQueryResData result = cmbPaymentServiceHandler.bankAccountTradeQuery(request, AccountPayQueryResData.class);
        System.out.println("返回结果："+JSON.toJSONString(result));
        Assert.assertEquals("SUCCESS", result.getReturnCode());
    }

    private AccountPayQueryRequest getAccountPayQueryRequest() {
        AccountPayQueryRequest request = new AccountPayQueryRequest();
        request//.setPayAreaCode("75")
                .setStartDate("20191111")
                .setEndDate("20191112");
        return request;
    }

    @Test
    public void query() throws Exception {
        PayQueryListRequest request = getPayQueryListRequest();
        CmbQueryListResData result = cmbPaymentServiceHandler.bankPayBatchQuery(request, CmbQueryListResData.class);
        System.out.println("批量查询返回结果：" + JSON.toJSONString(result));
        Assert.assertEquals("SUCCESS", result.getReturnCode());

    }

    private PayQueryListRequest getPayQueryListRequest() {
        PayQueryListRequest request = new PayQueryListRequest();

        request.setBizCode(BizTypeEnum.N02031.name())
                .setBizMode("00001")
//                .setDateType("A")
                .setStartDate("20190217")
                .setEndDate("20190217");
        return request;
    }

    @Test
    public void bankBatchPay() throws Exception {
        DirectPayRequest request = getDirectPayRequest();
        DirectPayRequest request1 = getDirectPayRequest();
        request1.setOrderAmt("2.00")
                .setDescription("第二笔交易");


        List<DirectPayRequest> list = new ArrayList<>();
        list.add(request);
        list.add(request1);
        CmbBatchDirectPayResData resData = cmbPaymentServiceHandler.bankBatchPay(list, CmbBatchDirectPayResData.class);
        System.out.println("返回结果：" + JSON.toJSONString(resData));
        Assert.assertEquals("SUCCESS", resData.getReturnCode());
//        Assert.assertEquals("NTE", resData.getRequestStatus());
//        Assert.assertEquals("S",resData.getBizResult());

    }


    private DirectPayRequest getDirectPayRequest() {
        DirectPayRequest request = new DirectPayRequest();
        request.setAcceptAccountNo("6225880230001175")
//                .setAcceptAccountName("刘五")
                .setAcceptBankAddress("招商银行重庆分行营业部")
                .setBankInnerFlag("Y")
                .setQdsOrderNo(CmbUtil.generateOrderNo("bank_"))
//                .setCityCode("")
                .setCny("10")
                .setDescription("描述")
                .setOrderAmt("1.01")
                .setSettleMode("F");
        return request;
    }

}
