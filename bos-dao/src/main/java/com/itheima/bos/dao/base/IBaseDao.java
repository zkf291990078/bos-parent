package com.itheima.bos.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.bos.utils.PageBean;

public interface IBaseDao<T> {
	public void save(T t);

	public void delete(T t);

	public void update(T t);

	public T findById(Serializable id);

	public List<T> findAll();
	
	public void executeUpdate(String queryName,Object... objects );
	
	public void queryPageBean(PageBean pageBean);
	public void saveOrUpdate(T t);
	public List<T> findDataByCriteria(DetachedCriteria criteria);
}
