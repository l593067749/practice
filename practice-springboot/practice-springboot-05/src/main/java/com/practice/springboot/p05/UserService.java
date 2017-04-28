package com.practice.springboot.p05;

import com.practice.springboot.p05.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liaowenqiang on 2016/9/27.
 */
@Service("userService")

public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Resource(name = "userService2")
    private UserService2 userService;
    public void bathAddUser(List<User> users) {
        for(User user:users){
            userMapper.insertByUser(user);
        }
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
    public  void addUser() throws Exception {
       try {
            userService.addUser3();//这个方法没事务
        }catch (Exception e){

        }
       User user=new User();
       userMapper.insertByUser(user);

        /*try {
            userService.addUser2();
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }
}
