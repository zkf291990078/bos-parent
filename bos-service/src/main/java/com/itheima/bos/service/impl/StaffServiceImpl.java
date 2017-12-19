package com.itheima.bos.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.StaffDao;
import com.itheima.bos.domain.Staff;
import com.itheima.bos.service.StaffService;
import com.itheima.bos.utils.PageBean;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
	@Autowired
	private StaffDao staffDao;

	@Override
	public void save(Staff model) {
		// TODO Auto-generated method stub
		staffDao.save(model);
	}

	@Override
	public void queryPageBean(PageBean pageBean) {
		// TODO Auto-generated method stub
		staffDao.queryPageBean(pageBean);
	}

	@Override
	public void deleteBatch(String ids) {
		// TODO Auto-generated method stub
		String[] values = ids.split(",");
		for (String id : values) {
			staffDao.executeUpdate("staff.editDel", id);
		}
	}

	@Override
	public void edit(Staff model) {
		// TODO Auto-generated method stub
		staffDao.saveOrUpdate(model);
	}

	@Override
	public List<Staff> findStaffNoDele() {
		// TODO Auto-generated method stub
		DetachedCriteria criteria=DetachedCriteria.forClass(Staff.class);
		criteria.add(Restrictions.eq("deltag", "0"));
		return staffDao.findDataByCriteria(criteria);
	}

}
