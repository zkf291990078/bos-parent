package com.itheima.bos.service;

import com.itheima.bos.domain.User;
import com.itheima.bos.utils.PageBean;

public interface UserService  {

	public User login(User user);

	public void editPassword(String id, String password);

	public void save(User model, String[] roleIds);

	public void queryPage(PageBean pageBean);
	
	
}
