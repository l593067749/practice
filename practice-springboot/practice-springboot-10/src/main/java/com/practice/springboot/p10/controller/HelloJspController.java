package com.practice.springboot.p10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by liaowenqiang on 2016/9/28.
 */
@Controller
@RequestMapping("/hello")
public class HelloJspController {

    @GetMapping("/")
    public String index(Map<String,Object> map){
        map.put("hello",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return "/hello/index";
    }
    @RequestMapping("/index")
    public ModelAndView getListaUtentiView(){
        ModelMap model = new ModelMap();
        model.addAttribute("hello", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return new ModelAndView("index", model);
    }
}
