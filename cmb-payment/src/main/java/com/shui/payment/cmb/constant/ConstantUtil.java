package com.shui.payment.cmb.constant;

/**
 * 通用常量工具类
 *
 * @author code
 * @Title: ConstantUtil
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/10/249:50 AM
 */
public class ConstantUtil {
    private ConstantUtil(){

    }

    /**
     * 普通商户配置文件名称，用于读取配置信息
     */
    public static final String GENERAL_FILE_NAME = "wechatgeneral";
    /**
     * 融韵通配置文件标识
     */
    public static final String RONG_YUN_TONG_FILE_NAME = "rongyuntongpay";
    /**
     * 支付宝配置
     */
    public static final String ALIPAY_FILE_NAME = "alipay";
    /**
     * 银企直联配置
     */
    public static final String BANK_FILE_NAME = "application";
    /**
     * 服务商配置文件名称，用于读取配置信息
     */
    public static final String SERVER_FILE_NAME = "wechatpay";

    /**
     * 支付宝下单参数，门店默认值
     */
    public static final String ALIPAY_SORE_NAME = "alipay_store";


    public static final String UTF8 = "UTF-8";
    public static final String GBK = "GBK";
    public static final String GB18030 = "GB18030";


    public static final String DATA = "data";
    public static final String SIGN = "sign";
    public static final String SIGN_TYPE = "sign_type";

    public static final String PAGE_SIZE = "pageSize";
    public static final String PAGE_NO = "pageNo";
    public static final String LEFT_LINE = "/";

    //未知项目使用该名称,测试专用
    public static final String OTHER = "OTHER";
    //丁老板标识
    public static final String QDING_BOSS = "QDING_BOSS";
    public static final String QDING_BOSS_DESC = "丁老板";


    public static final String RETURN_SUCCESS = "SUCCESS";
    public static final String RETURN_FAIL = "FAIL";


    //结算方式代码-普通
    public static final String SETTLE_N = "N";
    //结算方式代码-快速
    public static final String SETTLE_F = "F";
    //系统内外标志-招行
    public static final String BANK_Y = "Y";
    //系统内外标志-非招行
    public static final String BANK_N = "N";
}
