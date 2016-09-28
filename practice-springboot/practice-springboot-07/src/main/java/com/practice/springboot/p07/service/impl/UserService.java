package com.practice.springboot.p07.service.impl;

import com.practice.springboot.p07.dao.UserMapper;
import com.practice.springboot.p07.entity.User;
import com.practice.springboot.p07.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liaowenqiang on 2016/9/27.
 */
@Service
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Transactional
    public void bathAddUser(List<User> users) {
        for(User user:users){
            userMapper.insertByUser(user);
        }
    }
}
