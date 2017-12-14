package com.itheima.bos.dao;

import com.itheima.bos.domain.User;

public interface UserDao {
   public User findUserByUsernameAndPassword(String username,String password);
}
