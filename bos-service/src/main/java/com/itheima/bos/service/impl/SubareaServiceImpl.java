package com.itheima.bos.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.SubareaDao;
import com.itheima.bos.domain.Subarea;
import com.itheima.bos.service.SubareaService;
import com.itheima.bos.utils.PageBean;

@Service
@Transactional
public class SubareaServiceImpl implements SubareaService {

	@Autowired
	private SubareaDao subareaDao;

	@Override
	public void edit(Subarea model) {
		// TODO Auto-generated method stub
		subareaDao.saveOrUpdate(model);
	}

	@Override
	public void save(Subarea model) {
		// TODO Auto-generated method stub
		subareaDao.save(model);
	}

	@Override
	public void queryPage(PageBean pageBean) {
		// TODO Auto-generated method stub
		subareaDao.queryPageBean(pageBean);
	}

	@Override
	public List<Subarea> findAll() {
		// TODO Auto-generated method stub
		return subareaDao.findAll();
	}

	@Override
	public List<Subarea> findSubareaNoDecidedzone() {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(Subarea.class);
		criteria.add(Restrictions.isNull("decidedzone"));

		return subareaDao.findDataByCriteria(criteria);
	}

	@Override
	public List<Subarea> findSssoginSubarea(String decidedzone_id) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(Subarea.class);
		criteria.add(Restrictions.eq("decidedzone.id", decidedzone_id));
		return subareaDao.findDataByCriteria(criteria);
	}

	@Override
	public List<Object> findSubareasGroupByProvince() {
		// TODO Auto-generated method stub
		
		return subareaDao.findSubareasGroupByProvince();
	}

}
