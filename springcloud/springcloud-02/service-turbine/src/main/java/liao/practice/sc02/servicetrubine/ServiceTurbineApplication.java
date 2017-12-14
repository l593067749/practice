package liao.practice.sc02.servicetrubine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * Created by liaowenqiang on 2017/12/13.
 */
@SpringBootApplication
@EnableTurbine
public class ServiceTurbineApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        //new SpringApplication(ServiceTurbineApplication.class, args);
        new SpringApplicationBuilder(ServiceTurbineApplication.class).web(true).run(args);
    }
}
