package com.itheima.bos.dao;

import java.util.List;

import com.itheima.bos.dao.base.IBaseDao;
import com.itheima.bos.domain.Region;

public interface RegionDao extends IBaseDao<Region> {

	List<Region> findDataByq(String q);

}
