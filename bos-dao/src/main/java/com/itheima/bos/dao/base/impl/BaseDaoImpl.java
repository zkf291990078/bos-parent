package com.itheima.bos.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.bos.dao.base.IBaseDao;
import com.itheima.bos.utils.PageBean;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	private Class<T> clazz;

	@Resource // 根据类型注入spring工厂中的会话工厂对象sessionFactory
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public BaseDaoImpl() {
		// TODO Auto-generated constructor stub
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types = type.getActualTypeArguments();
		clazz = (Class<T>) types[0];
	}

	@Override
	public void save(T t) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(t);
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(t);
	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(t);
	}

	@Override
	public T findById(Serializable id) {
		// TODO Auto-generated method stub

		return getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		String hql = "from " + clazz.getSimpleName();
		return (List<T>) getHibernateTemplate().find(hql);
	}

	@Override
	public void executeUpdate(String queryName, Object... objects) {
		// TODO Auto-generated method stub
		Session session = getSessionFactory().getCurrentSession();
		Query query = session.getNamedQuery(queryName);
		int i = 0;
		for (Object o : objects) {
			query.setParameter(i++, o);
		}
		query.executeUpdate();
	}

	@Override
	public void queryPageBean(PageBean pageBean) {
		int currentPage = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		int firstResult = (currentPage - 1) * pageSize;
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> counts = (List<Long>) getHibernateTemplate().findByCriteria(detachedCriteria);
		pageBean.setTotal(counts.get(0).intValue());
		detachedCriteria.setProjection(null);
		List list = getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, pageSize);
		pageBean.setRows(list);
	}

	@Override
	public void saveOrUpdate(T t) {
		// TODO Auto-generated method stub
		getHibernateTemplate().saveOrUpdate(t);
	}

}
