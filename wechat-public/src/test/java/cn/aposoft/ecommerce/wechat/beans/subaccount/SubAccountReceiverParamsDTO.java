package cn.aposoft.ecommerce.wechat.beans.subaccount;

import cn.aposoft.ecommerce.wechat.params.SubAccountReceiverParams;

/**
 * @author code
 * @Title: SubAccountReceiverParamsDTO
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/1/15上午9:21
 */
public class SubAccountReceiverParamsDTO implements SubAccountReceiverParams {
    /**
     * 分账接收方对象，json格式
     */
    private String receiver;

    public SubAccountReceiverParamsDTO setReceiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    @Override
    public String getReceiver() {
        return receiver;
    }
}
