package com.practice.springboot.p14.common.properties;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by liaowenqiang on 2016/9/30.
 */
@Component
@ConfigurationProperties(prefix="mycas")
public class CasSettings  {
    private  String serverUrlPrefix;
    private  String shiroServerUrlPrefix;
    private String casFilterUrlPattern;
    private String casLogoutUrl;
    private String loginUrl;

    public String getCasFilterUrlPattern() {
        casFilterUrlPattern="/shiro-cas";
        return casFilterUrlPattern;
    }

    public String getLoginUrl(){
        return this.getServerUrlPrefix()+"/login" + "?service=" + this.getShiroServerUrlPrefix() + this.casFilterUrlPattern;
    }
    public String getCasLogoutUrl(){
        return this.getServerUrlPrefix() + "/logout"+ "?service=" + this.getShiroServerUrlPrefix() + this.casFilterUrlPattern;
    }
    public String getServerUrlPrefix() {
        return serverUrlPrefix;
    }

    public void setServerUrlPrefix(String serverUrlPrefix) {
        this.serverUrlPrefix = serverUrlPrefix;
    }

    public String getShiroServerUrlPrefix() {
        return shiroServerUrlPrefix;
    }

    public void setShiroServerUrlPrefix(String shiroServerUrlPrefix) {
        this.shiroServerUrlPrefix = shiroServerUrlPrefix;
    }

}
