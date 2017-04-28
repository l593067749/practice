package com.practice.springboot.p14.controller;

import com.practice.springboot.p14.exception.MyException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by liaowenqiang on 2016/10/11.
 * 这是以thmleaf为模版的响应页面
 */
@Controller
@RequestMapping("/thmleaf")
public class ThmleafController {

    java.lang.String befor="/page/thmleaf_Tem";
    @RequestMapping("/")
    public String index(){
        return befor+"/index";
    }
    @RequestMapping("/login")
    public String login(){
        return befor+"/login";
    }

    @RequestMapping("/userAdd")
    public String addUser(){
        return befor+"/user_add";
    }
    @RequestMapping("/userDel")
    public String delUser(){
        return befor+"/user_del";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception{
// 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");

        System.out.println("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理.
        return befor+"/login";
    }
    @RequestMapping("/error")
    public String getThrow() throws Exception{
        throw new Exception("访问这个肯定报错！");
    }
    @RequestMapping("/error_json")
    @ResponseBody
    public String getJsonThrow() throws MyException{
        throw new MyException("访问这个肯定报错！");
    }
}
