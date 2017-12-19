package com.itheima.bos.service;

import java.util.List;

import com.itheima.bos.domain.Region;
import com.itheima.bos.utils.PageBean;

public interface RegionService {

	public void  saveBatch(List<Region> regions);

	public List<Region> findAll();

	public void queryPage(PageBean pageBean);

	public List<Region> findDataByq(String q);
}
