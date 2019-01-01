package cn.aposoft.ecommerce.wechat.beans;

import cn.aposoft.ecommerce.wechat.enums.BillTypeEnum;
import cn.aposoft.ecommerce.wechat.params.DownloadBillParams;

/**
 * @author code
 * @Title: DownloadBillParamsDTO
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/19下午8:53
 */
public class DownloadBillParamsDTO implements DownloadBillParams {
    private String bill_date;
    private BillTypeEnum bill_type;

    public DownloadBillParamsDTO setBill_date(String bill_date) {
        this.bill_date = bill_date;
        return this;
    }

    public DownloadBillParamsDTO setBill_type(BillTypeEnum bill_type) {
        this.bill_type = bill_type;
        return this;
    }

    @Override
    public String getBill_date() {
        return bill_date;
    }

    @Override
    public BillTypeEnum getBill_type() {
        return bill_type;
    }
}
