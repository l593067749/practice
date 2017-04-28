package com.practice.springboot.p05;

import com.practice.springboot.p05.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liaowenqiang on 2016/11/25.
 */
@Service(value = "userService2")
public class UserService2 {
    @Autowired
    private UserMapper userMapper;
    //@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = java.lang.Exception.class)
    public  void addUser(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        User user=new User();
        user.setAge(1);
        user.setName("UserService2-"+"addUser:加了事务");
        userMapper.insertByUser(user);
        User user2=new User();
        userMapper.insertByUser(user2);

    }
    public  void addUser2() throws Exception{
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        User user=new User();
        user.setAge(1);
        user.setName("UserService2-"+"addUser2:没加事务");
        userMapper.insertByUser(user);
        User user2=new User();
        userMapper.insertByUser(user2);

    }
    public void addUser3(){
        User user=new User();
        user.setAge(1);
        user.setName("UserService2-"+"addUser3:没加事务");
        userMapper.insertByUser(user);
    }
}
