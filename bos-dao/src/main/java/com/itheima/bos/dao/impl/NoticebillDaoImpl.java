package com.itheima.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itheima.bos.dao.NoticebillDao;
import com.itheima.bos.dao.base.impl.BaseDaoImpl;
import com.itheima.bos.domain.Noticebill;

@Repository
public class NoticebillDaoImpl extends BaseDaoImpl<Noticebill> implements NoticebillDao {

//	@Override
//	public List<Noticebill> findManNotice() {
//		// TODO Auto-generated method stub
//		String hql="from Noticebill n where n.ordertype='人工分单'";
//		List<Noticebill> list=(List<Noticebill>) getHibernateTemplate().find(hql);
//		return list;
//	}

}
