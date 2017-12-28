package com.itheima.bos.service;

import java.util.List;

import com.itheima.bos.domain.Role;
import com.itheima.bos.utils.PageBean;

public interface RoleService {


	public void save(Role model, String functionIds);

	public void findPageQuery(PageBean pageBean);

	public List<Role> findAll();

	public void edit(Role model, String functionIds);
	
}

