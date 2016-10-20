package com.practice.springboot.p12.entity;

import org.springframework.util.StringUtils;

import java.io.Serializable;

public class User implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    private String password;
    // 省略getter和setter


    public String getPassword() {
        if(StringUtils.isEmpty(password)){
            //name+123456 md5
            //注意 密码在加密的时候，是用sqlt+用户密码组合的字符串进行加密
            return "a66abb5684c45962d887564f08346e8d";
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public String toString(){
        return this.id+"_"+this.name+"_"+this.age+"_"+this.getPassword();
    }
}