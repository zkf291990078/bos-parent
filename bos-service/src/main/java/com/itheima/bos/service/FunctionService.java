package com.itheima.bos.service;

import java.util.List;

import com.itheima.bos.domain.Function;
import com.itheima.bos.utils.PageBean;

public interface FunctionService {
  
	public List<Function> findAll();

	public void save(Function model);

	public void queryPage(PageBean pageBean);

	public List<Function> findAllMenu();

	public List<Function> findFunctionsByRoleId(String roleId);
	
}
