package com.itheima.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.WorkordermanageDao;
import com.itheima.bos.domain.Workordermanage;
import com.itheima.bos.service.WorkordermanageService;

@Service
@Transactional
public class WorkordermanageServiceImpl implements WorkordermanageService {
	@Autowired
	private WorkordermanageDao workordermanageDao;

	@Override
	public void save(Workordermanage workordermanage) {
		// TODO Auto-generated method stub
		workordermanageDao.save(workordermanage);
	}

}
