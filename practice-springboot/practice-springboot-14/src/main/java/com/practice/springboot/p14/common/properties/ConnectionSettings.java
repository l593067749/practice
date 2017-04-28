package com.practice.springboot.p14.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * 配置属性测试
 */
@Component
@ConfigurationProperties(prefix="connection")
public class ConnectionSettings {
    private String username;
    private InetAddress remoteAddress;
    /**
     * 宽松的匹配方式 ：
     * person.firstName	标准驼峰规则
     person.first-name	虚线表示，推荐用于.properties和.yml文件中
     PERSON_FIRST_NAME	大写形式，使用系统环境变量时推荐
     */
    private String randomValue;
    // ... getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public InetAddress getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(InetAddress remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public String getRandomValue() {
        return randomValue;
    }

    public void setRandomValue(String randomValue) {
        this.randomValue = randomValue;
    }

    public String toString(){
        return "{username:"+username+",remoteAddress:"+remoteAddress.toString()+",randomValue:"+randomValue+"}";
    }
}