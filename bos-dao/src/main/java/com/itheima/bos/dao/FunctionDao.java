package com.itheima.bos.dao;

import java.util.List;

import com.itheima.bos.dao.base.IBaseDao;
import com.itheima.bos.domain.Function;

public interface FunctionDao extends IBaseDao<Function> {

	List<Function> findFunctionByUser(String id);

}
