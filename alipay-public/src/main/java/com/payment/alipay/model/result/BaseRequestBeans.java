package com.payment.alipay.model.result;

import org.apache.commons.lang3.StringUtils;

import java.security.SignatureException;

/**
 * @author code
 * @Title: BaseRequestBeans
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/11上午11:56
 */

public class BaseRequestBeans {
//    private String appid;
//    private String mch_id;
//    private String sub_appid;
//    private String sub_mch_id;
//    private String nonce_str;
//    private String sign;
//
//    /**
//     * sign创建操作
//     *
//     * @param key      账户密钥
//     * @param obj      请求对象bean
//     * @param signType 签名类型
//     */
////    public void generateSign(String key, Object obj, String signType) throws SignatureException {
////        //随机字符串，不长于32 位
////        if (StringUtils.isEmpty(getNonce_str())) {//如果已赋值，不再重复赋值
////            setNonce_str(PaymentUtil.generateString(32));
////        }
////        //根据API给的签名规则进行签名
////        String signValue = null;
////        try {
////            if (StringUtils.isEmpty(signType) || EncryptContant.MD5.equals(signType)) {
////                signValue = WechatSignature.generateSignatureWithMD5(XmlUtil.objectToMap(obj), key);
////            } else if (EncryptContant.HMACSHA256.equals(signType)) {
////                signValue = WechatSignature.generateSignatureWithHMACSHA256(XmlUtil.objectToMap(obj), key);
////            }
////        } catch (Exception e) {
////            throw new SignatureException("签名创建异常", e);
////        }
////        setSign(signValue);//把签名数据设置到Sign这个属性中
////    }
//
//    public String getAppid() {
//        return appid;
//    }
//
//    public BaseRequestBeans setAppid(String appid) {
//        this.appid = appid;
//        return this;
//    }
//
//    public String getMch_id() {
//        return mch_id;
//    }
//
//    public BaseRequestBeans setMch_id(String mch_id) {
//        this.mch_id = mch_id;
//        return this;
//    }
//
//    public String getSub_appid() {
//        return sub_appid;
//    }
//
//    public BaseRequestBeans setSub_appid(String sub_appid) {
//        this.sub_appid = sub_appid;
//        return this;
//    }
//
//    public String getSub_mch_id() {
//        return sub_mch_id;
//    }
//
//    public BaseRequestBeans setSub_mch_id(String sub_mch_id) {
//        this.sub_mch_id = sub_mch_id;
//        return this;
//    }
//
//
//    public String getNonce_str() {
//        return nonce_str;
//    }
//
//    public BaseRequestBeans setNonce_str(String nonce_str) {
//        this.nonce_str = nonce_str;
//        return this;
//    }
//
//    public String getSign() {
//        return sign;
//    }
//
//    public BaseRequestBeans setSign(String sign) {
//        this.sign = sign;
//        return this;
//    }
}
