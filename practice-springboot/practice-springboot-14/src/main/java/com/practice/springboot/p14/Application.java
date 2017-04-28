package com.practice.springboot.p14;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration //配置文件
@EnableAutoConfiguration //启用默认的spring配置方式
@ComponentScan //定义扫描的basepack


/**
 * 这是启动 jar方式
 */
public class Application {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(new Object[]{Application.class}, args);
	}

}