package com.payment.alipay.service.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.payment.alipay.config.AlipayConstants;
import com.payment.alipay.config.AlipayPropertiesConfig;
import com.payment.alipay.enums.TradeStatus;
import com.payment.alipay.model.builder.*;
import com.payment.alipay.model.result.*;
import com.payment.alipay.service.AlipayTradeService;
import com.payment.alipay.util.Utils;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liuyangkly on 15/10/28.
 */
@Slf4j
abstract class AbsAlipayTradeService extends AbsAlipayService implements AlipayTradeService {
    protected static ExecutorService executorService = Executors.newCachedThreadPool();
    protected AlipayClient client;

    @Override
    public AlipayF2FQueryResult queryTradeResult(AlipayTradeQueryRequestBuilder builder) {
        AlipayTradeQueryResponse response = tradeQuery(builder);

        AlipayF2FQueryResult result = new AlipayF2FQueryResult(response);
        if (querySuccess(response)) {
            // 查询返回该订单交易支付成功
            result.setTradeStatus(TradeStatus.SUCCESS);

        } else if (tradeError(response)) {
            // 查询发生异常，交易状态未知
            result.setTradeStatus(TradeStatus.UNKNOWN);

        } else {
            // 其他情况均表明该订单号交易失败
            result.setTradeStatus(TradeStatus.FAILED);
        }
        return result;
    }

    protected AlipayTradeQueryResponse tradeQuery(AlipayTradeQueryRequestBuilder builder) {
        validateBuilder(builder);

        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.putOtherTextParam("app_auth_token", builder.getAppAuthToken());
        request.setBizContent(builder.toJsonString());
        log.info("trade.query bizContent:" + request.getBizContent());

        return (AlipayTradeQueryResponse) getResponse(client, request);
    }

    @Override
    public AlipayF2FRefundResult tradeRefund(AlipayTradeRefundRequestBuilder builder) {
        validateBuilder(builder);

        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setNotifyUrl(builder.getNotifyUrl());
        request.putOtherTextParam("app_auth_token", builder.getAppAuthToken());
        request.setBizContent(builder.toJsonString());
        log.info("trade.refund bizContent:" + request.getBizContent());

        AlipayTradeRefundResponse response = (AlipayTradeRefundResponse) getResponse(client, request);

        AlipayF2FRefundResult result = new AlipayF2FRefundResult(response);
        setTradeStatus(response, result);
        return result;
    }

    @Override
    public AlipayF2FPrecreateResult tradePrecreate(AlipayTradePrecreateRequestBuilder builder) {
        validateBuilder(builder);

        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setNotifyUrl(builder.getNotifyUrl());
        request.putOtherTextParam("app_auth_token", builder.getAppAuthToken());
        request.setBizContent(builder.toJsonString());
        log.info("trade.precreate bizContent:" + request.getBizContent());

        AlipayTradePrecreateResponse response = (AlipayTradePrecreateResponse) getResponse(client, request);

        AlipayF2FPrecreateResult result = new AlipayF2FPrecreateResult(response);
        setTradeStatus(response, result);
        return result;
    }

    @Override
    public AlipayF2FDownloadResult downloadBill(AlipayTradeDownloadBillRequestBuilder builder) {
        validateBuilder(builder);

        AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        request.setNotifyUrl(builder.getNotifyUrl());
        request.putOtherTextParam("app_auth_token", builder.getAppAuthToken());
        request.setBizContent(builder.toJsonString());
        log.info("trade.precreate bizContent:" + request.getBizContent());

        AlipayDataDataserviceBillDownloadurlQueryResponse response = (AlipayDataDataserviceBillDownloadurlQueryResponse) getResponse(client, request);
        AlipayF2FDownloadResult result = new AlipayF2FDownloadResult(response);
        setTradeStatus(response, result);

        return result;
    }

    @Override
    public AlipayF2FCloseOrderResult closeOrder(AlipayTradeCloseRequestBuilder builder) {
        validateBuilder(builder);

        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        request.putOtherTextParam("app_auth_token", builder.getAppAuthToken());
        request.setBizContent(builder.toJsonString());
        log.info("trade.close bizContent:" + request.getBizContent());

        AlipayTradeCloseResponse response = (AlipayTradeCloseResponse) getResponse(client, request);
        AlipayF2FCloseOrderResult result = new AlipayF2FCloseOrderResult(response);
        setTradeStatus(response, result);

        return result;
    }

    /**
     * 完善返回结果信息
     *
     * @param response
     * @param result
     */
    private void setTradeStatus(AlipayResponse response, AlipayBaseResponseBeans result) {
        if (response != null && AlipayConstants.SUCCESS.equals(response.getCode())) {
            // 成功
            result.setTradeStatus(TradeStatus.SUCCESS);

        } else if (tradeError(response)) {
            // 发生异常，状态未知
            result.setTradeStatus(TradeStatus.UNKNOWN);

        } else {
            // 其他情况表明 明确失败
            result.setTradeStatus(TradeStatus.FAILED);
        }

    }


    // 根据查询结果queryResponse判断交易是否支付成功，如果支付成功则更新result并返回，如果不成功则调用撤销
    protected AlipayF2FPayResult checkQueryAndCancel(String outTradeNo, String appAuthToken, AlipayF2FPayResult result,
                                                     AlipayTradeQueryResponse queryResponse) {
        if (querySuccess(queryResponse)) {
            // 如果查询返回支付成功，则返回相应结果
            result.setTradeStatus(TradeStatus.SUCCESS);
            result.setResponse(toPayResponse(queryResponse));
            return result;
        }

        // 如果查询结果不为成功，则调用撤销
        AlipayTradeCancelRequestBuilder builder = new AlipayTradeCancelRequestBuilder().setOutTradeNo(outTradeNo);
        builder.setAppAuthToken(appAuthToken);
        AlipayTradeCancelResponse cancelResponse = cancelPayResult(builder);
        if (tradeError(cancelResponse)) {
            // 如果第一次同步撤销返回异常，则标记支付交易为未知状态
            result.setTradeStatus(TradeStatus.UNKNOWN);
        } else {
            // 标记支付为失败，如果撤销未能成功，产生的单边帐由人工处理
            result.setTradeStatus(TradeStatus.FAILED);
        }
        return result;
    }

    // 根据外部订单号outTradeNo撤销订单
    protected AlipayTradeCancelResponse tradeCancel(AlipayTradeCancelRequestBuilder builder) {
        validateBuilder(builder);

        AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
        request.putOtherTextParam("app_auth_token", builder.getAppAuthToken());
        request.setBizContent(builder.toJsonString());
        log.info("trade.cancel bizContent:" + request.getBizContent());

        return (AlipayTradeCancelResponse) getResponse(client, request);
    }

    // 轮询查询订单支付结果
    protected AlipayTradeQueryResponse loopQueryResult(AlipayTradeQueryRequestBuilder builder) {
        AlipayTradeQueryResponse queryResult = null;
        for (int i = 0; i < AlipayPropertiesConfig.getMaxQueryRetry(); i++) {
            Utils.sleep(AlipayPropertiesConfig.getQueryDuration());

            AlipayTradeQueryResponse response = tradeQuery(builder);
            if (response != null) {
                if (stopQuery(response)) {
                    return response;
                }
                queryResult = response;
            }
        }
        return queryResult;
    }

    // 判断是否停止查询
    protected boolean stopQuery(AlipayTradeQueryResponse response) {
        if (AlipayConstants.SUCCESS.equals(response.getCode())) {
            if ("TRADE_FINISHED".equals(response.getTradeStatus()) ||
                    "TRADE_SUCCESS".equals(response.getTradeStatus()) ||
                    "TRADE_CLOSED".equals(response.getTradeStatus())) {
                // 如果查询到交易成功、交易结束、交易关闭，则返回对应结果
                return true;
            }
        }
        return false;
    }

    // 根据外部订单号outTradeNo撤销订单
    protected AlipayTradeCancelResponse cancelPayResult(AlipayTradeCancelRequestBuilder builder) {
        AlipayTradeCancelResponse response = tradeCancel(builder);
        if (cancelSuccess(response)) {
            // 如果撤销成功，则返回撤销结果
            return response;
        }

        // 撤销失败
        if (needRetry(response)) {
            // 如果需要重试，首先记录日志，然后调用异步撤销
            log.warn("begin async cancel request:" + builder);
            asyncCancel(builder);
        }
        return response;
    }

    // 异步撤销
    protected void asyncCancel(final AlipayTradeCancelRequestBuilder builder) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < AlipayPropertiesConfig.getMaxCancelRetry(); i++) {
                    Utils.sleep(AlipayPropertiesConfig.getCancelDuration());

                    AlipayTradeCancelResponse response = tradeCancel(builder);
                    if (cancelSuccess(response) ||
                            !needRetry(response)) {
                        // 如果撤销成功或者应答告知不需要重试撤销，则返回撤销结果（无论撤销是成功失败，失败人工处理）
                        return;
                    }
                }
            }
        });
    }

    // 将查询应答转换为支付应答
    protected AlipayTradePayResponse toPayResponse(AlipayTradeQueryResponse response) {
        AlipayTradePayResponse payResponse = new AlipayTradePayResponse();
        // 只有查询明确返回成功才能将返回码设置为10000，否则均为失败
        payResponse.setCode(querySuccess(response) ? AlipayConstants.SUCCESS : AlipayConstants.FAILED);
        // 补充交易状态信息
        StringBuilder msg = new StringBuilder(response.getMsg())
                .append(" tradeStatus:")
                .append(response.getTradeStatus());
        payResponse.setMsg(msg.toString());
        payResponse.setSubCode(response.getSubCode());
        payResponse.setSubMsg(response.getSubMsg());
        payResponse.setBody(response.getBody());
        payResponse.setParams(response.getParams());

        // payResponse应该是交易支付时间，但是response里是本次交易打款给卖家的时间,是否有问题
        // payResponse.setGmtPayment(response.getSendPayDate());
        payResponse.setBuyerLogonId(response.getBuyerLogonId());
        payResponse.setFundBillList(response.getFundBillList());
        payResponse.setOpenId(response.getOpenId());
        payResponse.setOutTradeNo(response.getOutTradeNo());
        payResponse.setReceiptAmount(response.getReceiptAmount());
        payResponse.setTotalAmount(response.getTotalAmount());
        payResponse.setTradeNo(response.getTradeNo());
        return payResponse;
    }

    // 撤销需要重试
    protected boolean needRetry(AlipayTradeCancelResponse response) {
        return response == null ||
                "Y".equals(response.getRetryFlag());
    }

    // 查询返回“支付成功”
    protected boolean querySuccess(AlipayTradeQueryResponse response) {
        return response != null &&
                AlipayConstants.SUCCESS.equals(response.getCode()) &&
                ("TRADE_SUCCESS".equals(response.getTradeStatus()) ||
                        "TRADE_FINISHED".equals(response.getTradeStatus())
                );
    }

    // 撤销返回“撤销成功”
    protected boolean cancelSuccess(AlipayTradeCancelResponse response) {
        return response != null &&
                AlipayConstants.SUCCESS.equals(response.getCode());
    }

    // 交易异常，或发生系统错误
    protected boolean tradeError(AlipayResponse response) {
        return response == null ||
                AlipayConstants.ERROR.equals(response.getCode());
    }
}
