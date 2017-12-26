package com.itheima.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.RoleDao;
import com.itheima.bos.domain.Function;
import com.itheima.bos.domain.Role;
import com.itheima.bos.service.RoleService;
import com.itheima.bos.utils.PageBean;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public void save(Role model, String functionIds) {
		// TODO Auto-generated method stub
		String[] idStrings = functionIds.split(",");
		for (String id : idStrings) {
			Function function = new Function();
			function.setId(id);
			model.getFunctions().add(function);
		}

		roleDao.save(model);
	}

	@Override
	public void findPageQuery(PageBean pageBean) {
		// TODO Auto-generated method stub
		roleDao.queryPageBean(pageBean);
	}

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleDao.findAll();
	}

}
