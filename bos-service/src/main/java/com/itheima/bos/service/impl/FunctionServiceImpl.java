package com.itheima.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.FunctionDao;
import com.itheima.bos.domain.Function;
import com.itheima.bos.domain.User;
import com.itheima.bos.service.FunctionService;
import com.itheima.bos.utils.BOSUtils;
import com.itheima.bos.utils.PageBean;

@Service
@Transactional
public class FunctionServiceImpl implements FunctionService {

	@Autowired
	private FunctionDao functionDao;

	@Override
	public List<Function> findAll() {
		// TODO Auto-generated method stub

		return functionDao.findAll();
	}

	@Override
	public void save(Function model) {
		// TODO Auto-generated method stub
		functionDao.save(model);
	}

	@Override
	public void queryPage(PageBean pageBean) {
		// TODO Auto-generated method stub
		functionDao.queryPageBean(pageBean);
	}

	@Override
	public List<Function> findAllMenu() {
		// TODO Auto-generated method stub
		List<Function> list=null;
		User user= BOSUtils.getLoginUser();
		if(user.getUsername().equals("tom")){
			list= functionDao.findAllMenu();
		}else{
			list=functionDao.findAllMenuByUser(user.getId());
		}
		return list;
	}

	@Override
	public List<Function> findFunctionsByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return functionDao.finFunctionByRoleId(roleId);
	}

}
