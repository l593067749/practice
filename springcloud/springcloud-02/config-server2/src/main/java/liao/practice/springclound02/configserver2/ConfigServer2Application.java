package liao.practice.springclound02.configserver2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by liaowenqiang on 2017/5/5.
 */
@EnableConfigServer
@SpringBootApplication
//注册服务配置
@EnableDiscoveryClient
public class ConfigServer2Application extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ConfigServer2Application.class);
    }
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ConfigServer2Application.class, args);
    }
}
