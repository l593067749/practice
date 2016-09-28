package com.practice.springboot.p02;

import com.practice.springboot.p02.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@EnableAutoConfiguration
public class Application {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private User2Mapper user2Mapper;
	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

	@RequestMapping("/addUser")
	String addUser(){
		String name="张三"+new Random().nextInt(100);
		userMapper.insert(name,20);
		User u=userMapper.findByName(name);
		return u.getName().equals(name)+"";
	}
	@RequestMapping("/addUserByUser")
	String addUser2(){
		String name="张三"+new Random().nextInt(100);
		User user=new User();
		user.setName(name);
		user.setAge(30);
		userMapper.insertByUser(user);
		User u=userMapper.findByName(name);
		return u.getName().equals(name)+"";
	}
	@RequestMapping("/addUserByMap")
	String addUser3(){
		String name="张三"+new Random().nextInt(100);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("name",name);
		map.put("age",40);
		userMapper.insertByMap(map);
		User u=userMapper.findByName(name);
		return u.getName().equals(name)+"";
	}

	@RequestMapping("/test")
	String test(){
		StringBuffer message=new StringBuffer();
		String name="张三"+new Random().nextInt(100);
		User user=new User();
		user.setName(name);
		user.setAge(30);
		userMapper.insertByUser(user);
		List<User> list=userMapper.findAll();
		message.append("findAll:"+list.toString()+"\n\t");
		User u=userMapper.findByName(name);
		u.setName("修改名称");
		userMapper.update(u);
		u=user2Mapper.findById(u.getId());
		message.append("update:"+u+"\n\t");
		userMapper.delete(u.getId());
		list=userMapper.findAll();
		message.append("delete:"+list.toString()+"\n\t");
		User user1=new User();
		user1.setId(new Long(1));
		user1=user2Mapper.getByIdByXmlMapper(user1);
		message.append("uid-1:"+user1);
		return message.toString();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
}