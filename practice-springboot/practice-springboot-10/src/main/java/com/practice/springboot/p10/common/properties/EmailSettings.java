package com.practice.springboot.p10.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by liaowenqiang on 2016/9/30.
 */
@Component
@ConfigurationProperties(prefix="mail")
public class EmailSettings {
    private String formEmail;
    private String toEmail;

    public String getFormEmail() {
        return formEmail;
    }

    public void setFormEmail(String formEmail) {
        this.formEmail = formEmail;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }
}
