package liao.practice.springclound02.configtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by liaowenqiang on 2017/5/5.
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigClientTestApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ConfigClientTestApplication.class);
    }
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ConfigClientTestApplication.class, args);
    }
}
