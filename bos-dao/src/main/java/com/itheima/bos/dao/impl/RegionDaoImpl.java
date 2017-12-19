package com.itheima.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itheima.bos.dao.RegionDao;
import com.itheima.bos.dao.base.impl.BaseDaoImpl;
import com.itheima.bos.domain.Region;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements RegionDao {

	@Override
	public List<Region> findDataByq(String q) {
		// TODO Auto-generated method stub
		String hql = "from Region r where r.shortcode like ? or r.citycode like ? or r.province like ? or r.city like ? or r.district like ?";
		List<Region> list = (List<Region>) getHibernateTemplate().find(hql, "%" + q + "%", "%" + q + "%", "%" + q + "%",
				"%" + q + "%", "%" + q + "%");
		return list;
	}

}
