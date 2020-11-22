package com.payment.shui.webank.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author code
 * @Title: AppTest
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/25 14:05
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BasicWebankJunitTest {

    @BeforeEach
    public void setUp(){
        System.out.println("使用Junit5进行测试");
    }
}
