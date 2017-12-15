package com.itheima.bos.service;

import com.itheima.bos.domain.User;

public interface UserService  {

	public User login(User user);

	public void editPassword(String id, String password);
	
}
