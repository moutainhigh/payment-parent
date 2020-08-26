package com.payment.shui.webank.beans;

/**微众调用合作方
 * @author code
 * @Title: PartnerBean
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/26 11:40
 */
public class PartnerBean {
    /**
     * 接口ID，由微众业务系统与合作方约定
     */
    private String transName;
    /**
     * 业务报文体（微众的业务请求内容或合作方的响应内容）
     * Plain字段产生步骤:
     * 1. 将业务报文体（可以包含集合或数组）转成标准JSON字符串；
     * 2. 将JSON字符串用UrlEncode编码。
     */
    private String Plain;
    /**
     * 请求的唯一流水编号。
     */
    private String SeqNo;
    /**
     * 签名串，签名串是对Plain字段的签名
     */
    private String Signature;
}
