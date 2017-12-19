package com.itheima.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.SubareaDao;
import com.itheima.bos.domain.Subarea;
import com.itheima.bos.service.SubareaService;

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

}
