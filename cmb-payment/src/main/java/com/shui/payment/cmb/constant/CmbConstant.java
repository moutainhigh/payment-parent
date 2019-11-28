package com.shui.payment.cmb.constant;

/**
 * 银企直联常量参数
 *
 * @author code
 * @Title: CmbConstant
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/10/152:59 PM
 */
public class CmbConstant {

    private CmbConstant() {
    }
    //2.2 查询账户详细信息
    public static final String DATA_TYPE_SDKACINFX = "SDKACINFX";
    //2.3 查询账户交易信息 输入接口的母标签
    public static final String DATA_TYPE_SDKTSINFX = "SDKTSINFX";
    //3.3 支付结果查询列表 数据类型标签
    public static final String DATA_TYPE_BATCH_QUERY = "NTQRYSTNY1";
    //3.6 直接支付 输入接口的母标签 业务请求参数 1
    public static final String DATA_TYPE_SDKPAYRQX = "SDKPAYRQX";
    //3.6 直接支付 输入接口的母标签 交易请求参数 2
    public static final String DATA_TYPE_DCOPDPAYX = "DCOPDPAYX";

    //3.11 支付结果查询-按业务参考号 输入接口的母标签
    public static final String DATA_TYPE_QUERY = "NTQRYSTYX1";

    //3.6 直接支付返回结果内容 xml标签名
    public static final String NTQPAYRQZ = "NTQPAYRQZ";
    //3.3，3.11 返回结果内容 xml标签名
    public static final String NTSTLLSTZ = "NTSTLLSTZ";
    //2.3 返回结果内容 xml标签名
    public static final String NTQTSINFZ = "NTQTSINFZ";
    //2.2 返回结果内容 xml标签名
    public static final String NTQACINFZ = "NTQACINFZ";


}
