package com.payment.alipay.exception;

/**
 * 从第三方下载信息失败异常
 * @author code
 * @Title: DownloadFailedException
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/16下午3:06
 */
public class DownloadFailedException extends BasicException {

    public DownloadFailedException(){
        super();
    }
    public DownloadFailedException(String msg){
        super(msg);
    }
}
