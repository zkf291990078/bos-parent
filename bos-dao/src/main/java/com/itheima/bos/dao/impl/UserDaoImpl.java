package com.itheima.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itheima.bos.dao.UserDao;
import com.itheima.bos.dao.base.impl.BaseDaoImpl;
import com.itheima.bos.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User findUserByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		String hql = "from User where username=? and password=?";
		List users = getHibernateTemplate().find(hql, username, password);
		if (users != null && users.size() > 0) {
			return (User) users.get(0);
		}
		return null;
	}

	@Override
	public User findUserByUsername(String username) {
		// TODO Auto-generated method stub
		String hql = "from User where username=? ";
		List users = getHibernateTemplate().find(hql, username);
		if (users != null && users.size() > 0) {
			return (User) users.get(0);
		}
		return null;
	}

}
