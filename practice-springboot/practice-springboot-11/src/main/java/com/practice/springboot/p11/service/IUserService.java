package com.practice.springboot.p11.service;

import com.practice.springboot.p11.entity.User;

import java.util.List;

/**
 * Created by liaowenqiang on 2016/9/27.
 */
public interface IUserService {
   public void bathAddUser(List<User> users);

   /**
    * 有缓存的应用
    * @param id
    * @return
    */
   public User getCacheUserById(Long id);

   /**
    * 无缓存
    * @param id
    * @return
    */
   public User getUserById(Long id);

   /**
    * 根据name拿到用户
    * @param name
    * @return
    */
   public User getUserByName(String name);
}
