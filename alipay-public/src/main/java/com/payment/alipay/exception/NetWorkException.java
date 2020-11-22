package com.payment.alipay.exception;

/**
 * 网络连接异常，暂定银企直联支付接口专用
 *
 * @author code
 * @Title: NetWorkException
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/10/2311:40 AM
 */
public class NetWorkException extends BasicException {
    public NetWorkException() {
        super();
    }

    public NetWorkException(String msg) {
        super(msg);
    }
}
