package com.practice.springboot.p06;


import com.practice.springboot.p06.common.Pagination;
import com.practice.springboot.p06.common.PaginationStatementInterceptor;
import com.practice.springboot.p06.common.properties.ConnectionSettings;
import com.practice.springboot.p06.dao.UserMapper;
import com.practice.springboot.p06.entity.User;
import com.practice.springboot.p06.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Configuration //配置文件
@EnableAutoConfiguration //启用默认的spring配置方式
@ComponentScan //定义扫描的basepack
public class Application {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(new Object[]{Application.class}, args);
	}

}