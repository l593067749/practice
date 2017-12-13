package liao.practice.springclound02.eurekaclient02;

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
public class EurekaClient02Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EurekaClient02Application.class);
    }
    public static void main(String[] args) throws Exception {
        SpringApplication.run(EurekaClient02Application.class, args);
    }
}
