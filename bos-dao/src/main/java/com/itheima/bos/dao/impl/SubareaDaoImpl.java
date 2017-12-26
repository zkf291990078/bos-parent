package com.itheima.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itheima.bos.dao.SubareaDao;
import com.itheima.bos.dao.base.impl.BaseDaoImpl;
import com.itheima.bos.domain.Subarea;

@Repository
public class SubareaDaoImpl extends BaseDaoImpl<Subarea> implements SubareaDao {

	@Override
	public List<Object> findSubareasGroupByProvince() {
		// TODO Auto-generated method stub
		String hql="select r.province,count(*) from Subarea s left join s.region r group by r.province";
		return (List<Object>) getHibernateTemplate().find(hql);
	}

}
