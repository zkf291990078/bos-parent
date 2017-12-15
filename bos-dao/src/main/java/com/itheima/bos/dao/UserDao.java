package com.itheima.bos.dao;

import com.itheima.bos.dao.base.IBaseDao;
import com.itheima.bos.domain.User;

public interface UserDao extends IBaseDao<User> {
   public User findUserByUsernameAndPassword(String username,String password);
}
