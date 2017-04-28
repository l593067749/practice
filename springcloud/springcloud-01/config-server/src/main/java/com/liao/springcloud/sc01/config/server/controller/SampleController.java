package com.liao.springcloud.sc01.config.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liaowenqiang on 2017/4/28.
 */
@Controller
public class SampleController {
    @ResponseBody
    @RequestMapping(value = "/")
    String home() {
        return "Hello World!";
    }
}
