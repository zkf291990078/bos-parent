package com.itheima.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.RegionDao;
import com.itheima.bos.domain.Region;
import com.itheima.bos.service.RegionService;
import com.itheima.bos.utils.PageBean;

@Service
@Transactional
public class RegionServiceImpl implements RegionService {

	@Autowired
	private RegionDao regionDao;

	@Override
	public void saveBatch(List<Region> regions) {
		// TODO Auto-generated method stub
		for (Region region : regions) {
			regionDao.saveOrUpdate(region);
		}
	}

	@Override
	public List<Region> findAll() {
		// TODO Auto-generated method stub
		return regionDao.findAll();
	}

	@Override
	public void queryPage(PageBean pageBean) {
		// TODO Auto-generated method stub
		regionDao.queryPageBean(pageBean);
	}

	@Override
	public List<Region> findDataByq(String q) {
		// TODO Auto-generated method stub
		
		return regionDao.findDataByq(q);
	}

}
