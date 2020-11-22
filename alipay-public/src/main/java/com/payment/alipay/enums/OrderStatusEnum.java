package com.payment.alipay.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单状态
 *
 * @author code
 * @Title: OrderStatusEnum
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/10下午4:53
 */
public enum OrderStatusEnum {
    //微信渠道
    //支付系统订单状态
    PROCESSING//处理中/退款处理中
    , SUCCESS//成功/退款成功(终态)
    , FAIL//失败(终态)
    , REFUND_SUCCESS//退款成功(终态)
    , REFUND_PROCESSING//退款处理中，申请退款使用
    //退款异常(终态)
    , CHANGE, ORDERCLOSE//关闭订单(终态)


    //渠道查询返回的订单状态
    , NOTPAY//未支付---PROCESSING
    , USERPAYING//用户支付中---PROCESSING
    , REFUND//转入退款---REFUND_SUCCESS(终态)

    , CLOSED//已关闭---ORDERCLOSE(终态)

    , REVOKED//已撤销(刷卡支付)---FAIL(终态)

    , PAYERROR//支付失败(其他原因，如银行返回失败)---FAIL(终态)

    //退款查询结果状态
    , REFUNDCLOSE//退款关闭。(终态)


    //融韵通渠道

    , WAIT//未支付
    , PAY//支付成功
    , CANCEL//取消交易

    //支付宝渠道
    , WAIT_BUYER_PAY//（交易创建，等待买家付款
    , TRADE_CLOSED//（未付款交易超时关闭，或支付完成后全额退款）
    , TRADE_SUCCESS//（交易支付成功）
    , TRADE_FINISHED//（交易结束，不可退款）
    ;
    //订单枚举map
    private static Map<String, OrderStatusEnum> mapEnum = new HashMap<>();

    static {
        for (OrderStatusEnum statusEnum : OrderStatusEnum.values()) {
            mapEnum.put(statusEnum.name().toLowerCase(), statusEnum);
        }
    }

    public static OrderStatusEnum getEnum(OrderStatusEnum statusEnum) {
        return mapEnum.get(statusEnum.name());
    }

    public static OrderStatusEnum getEnum(String statusEnum) {
        if (StringUtils.isEmpty(statusEnum)) {
            return null;
        }
        return mapEnum.get(statusEnum.toLowerCase());
    }


    /**
     * 根据订单状态返回对应的转化后的订单状态
     * 融韵通与微信渠道，暂时没有冲突
     * 包含：支付宝，融韵通，微信
     *
     * @param orderState
     * @return
     */
    public static OrderStatusEnum getOrderStatusEnum(String orderState) {
        OrderStatusEnum orderStatus = OrderStatusEnum.getEnum(orderState);

        if (orderStatus == null) {
            throw new IllegalArgumentException("此订单状态参数不存在");
        }
        switch (orderStatus) {
            //微信
            case NOTPAY:
            case USERPAYING:
                return OrderStatusEnum.PROCESSING;
            case CLOSED:
                return OrderStatusEnum.ORDERCLOSE;
            case REVOKED:
            case PAYERROR:
                return OrderStatusEnum.FAIL;

            case REFUND://微信，融韵通公共部分
                return OrderStatusEnum.REFUND_SUCCESS;
            //融韵通
            case WAIT:
            case CANCEL:
                return OrderStatusEnum.PROCESSING;
            case PAY:
            case SUCCESS:
                return OrderStatusEnum.SUCCESS;
            //支付宝
            case WAIT_BUYER_PAY://（交易创建，等待买家付款
                return OrderStatusEnum.PROCESSING;
            case TRADE_CLOSED://（未付款交易超时关闭，或支付完成后全额退款）
                return OrderStatusEnum.ORDERCLOSE;
            case TRADE_SUCCESS://（交易支付成功）

            case TRADE_FINISHED://TODO 4.x 暂时无法明确定义该枚举类型返回（交易结束，不可退款）
                return OrderStatusEnum.SUCCESS;
            default:
                return orderStatus;

        }
    }

}
