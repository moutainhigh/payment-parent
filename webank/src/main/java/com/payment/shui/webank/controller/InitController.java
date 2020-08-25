package com.payment.shui.webank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author code
 * @Title: InitController
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/25 14:25
 */
@RestController
public class InitController {

    @GetMapping("/index")
    public String index() {
        return "webank project success";
    }
}
