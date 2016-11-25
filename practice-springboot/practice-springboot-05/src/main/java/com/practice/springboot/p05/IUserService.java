package com.practice.springboot.p05;

import com.practice.springboot.p05.entity.User;

import java.util.List;

/**
 * Created by liaowenqiang on 2016/9/27.
 */
public interface IUserService {
   public void bathAddUser(List<User> users);
   public  void addUser() throws Exception;
}
