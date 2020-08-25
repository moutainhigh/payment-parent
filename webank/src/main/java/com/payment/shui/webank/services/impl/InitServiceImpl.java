package com.payment.shui.webank.services.impl;

import com.payment.shui.webank.services.InitService;
import org.springframework.stereotype.Service;

/**
 * @author code
 * @Title: InitServiceImpl
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/25 14:39
 */
@Service
public class InitServiceImpl implements InitService {
    @Override
    public String init(String value) {

        return value;
    }
}
