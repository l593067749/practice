package com.practice.springboot.p06.controller;

import com.practice.springboot.p06.common.Pagination;
import com.practice.springboot.p06.common.PaginationStatementInterceptor;
import com.practice.springboot.p06.common.properties.ConnectionSettings;
import com.practice.springboot.p06.dao.UserMapper;
import com.practice.springboot.p06.entity.User;
import com.practice.springboot.p06.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by liaowenqiang on 2016/9/28.
 */
@RestController
public class FristController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserService userService;
    @Autowired
    private ConnectionSettings connectionSettings;
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
        u=userMapper.findById(u.getId());
        message.append("update:"+u+"\n\t");
        userMapper.delete(u.getId());
        list=userMapper.findAll();
        message.append("delete:"+list.toString()+"\n\t");
        User user1=new User();
        user1.setId(new Long(1));
        user1=userMapper.getByIdByXmlMapper(user1);
        message.append("uid-1:"+user1);
        return message.toString();
    }
    @RequestMapping(value = "/queryAllByPage", method = RequestMethod.GET)
    public Pagination<User> queryAll(@RequestParam(value = "pageNum", required = false, defaultValue="1") Integer pageNum,
                                     @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize) {
        //PageHelper.startPage(pageNum, pageSize);
        PaginationStatementInterceptor.startPage(pageNum,pageSize);
        List<User> list = userMapper.findAll();
        return  PaginationStatementInterceptor.endPage();
    }
    @RequestMapping("/addUsers")
    public String bathAddUser(){
        List<User> users=new ArrayList<User>();
        for(int i=0;i<5;i++){
            User user=new User();
            user.setName(i+"-user");
            user.setAge(i*10+1);
            if(i/4==1){
                user.setName(System.currentTimeMillis()+"-user-"+System.currentTimeMillis());
            }

            users.add(user);
        }
        userService.bathAddUser(users);
        return "";
    }
    @RequestMapping("/proTest")
    public String propertiesTest(){
        return connectionSettings.toString();
    }
}
