package com.itheima.bos.dao.base;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {
	public void save(T t);

	public void delete(T t);

	public void update(T t);

	public T findById(Serializable id);

	public List<T> findAll();
	
	public void executeUpdate(String queryName,Object... objects );
}
