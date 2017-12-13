package liao.practice.sc02.serverzipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

/**
 * Created by liaowenqiang on 2017/12/13.
 */
@SpringBootApplication
@EnableZipkinServer
public class ServerZipkinApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerZipkinApplication.class,args);
    }
}
