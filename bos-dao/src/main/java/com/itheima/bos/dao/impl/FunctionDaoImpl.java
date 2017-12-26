package com.itheima.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itheima.bos.dao.FunctionDao;
import com.itheima.bos.dao.base.impl.BaseDaoImpl;
import com.itheima.bos.domain.Function;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements FunctionDao {

	@Override
	public List<Function> findAll() {

		String hql = "from Function f where f.parentFunction is NULL";
		List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public List<Function> findFunctionByUser(String id) {
		// TODO Auto-generated method stub
		String hql="select distinct f from Function f left join f.roles r left join r.users u where u.id=?";
		List<Function> list=(List<Function>) this.getHibernateTemplate().find(hql, id);
		return list;
	}

}
