package com.itheima.bos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.UserDao;
import com.itheima.bos.domain.Role;
import com.itheima.bos.domain.User;
import com.itheima.bos.service.UserService;
import com.itheima.bos.utils.MD5Utils;
import com.itheima.bos.utils.PageBean;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		String password = MD5Utils.md5(user.getPassword());
		User u = userDao.findUserByUsernameAndPassword(user.getUsername(), password);
		return u;
	}

	@Override
	public void editPassword(String id, String password) {
		// TODO Auto-generated method stub
		password = MD5Utils.md5(password);
		userDao.executeUpdate("user.editPwd", password, id);
	}

	@Override
	public void save(User model, String[] roleIds) {
		// TODO Auto-generated method stub
		for (String id : roleIds) {
			Role role = new Role(id);
			model.getRoles().add(role);
		}
		String password = model.getPassword();
		password = MD5Utils.md5(password);
		model.setPassword(password);
		userDao.save(model);
	}

	@Override
	public void queryPage(PageBean pageBean) {
		// TODO Auto-generated method stub
		userDao.queryPageBean(pageBean);
	}

}
