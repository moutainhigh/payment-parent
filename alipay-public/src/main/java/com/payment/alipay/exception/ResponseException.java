package com.payment.alipay.exception;

/**
 * @author code
 * @Title: ResponseException
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/10/27下午4:59
 */
public class ResponseException extends BasicException {

    public ResponseException(){
        super();
    }
    public ResponseException(String msg){
        super(msg);
    }
}
