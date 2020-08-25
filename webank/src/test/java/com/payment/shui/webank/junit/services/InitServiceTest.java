package com.payment.shui.webank.junit.services;

import com.payment.shui.webank.junit.BasicWebankJunitTest;
import com.payment.shui.webank.services.InitService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author code
 * @Title: InitServiceTest
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/25 14:40
 */
@ExtendWith(SpringExtension.class)
public class InitServiceTest extends BasicWebankJunitTest {

    @Autowired
    public InitService initService;

    @Test
    public void init() {
        String result = initService.init("ok");
        Assertions.assertEquals("ok", result);
    }
}
