package com.itheima.bos.service;

import java.util.List;

import com.itheima.bos.domain.Staff;
import com.itheima.bos.utils.PageBean;

public interface StaffService {

	void save(Staff model);

	void queryPageBean(PageBean pageBean);

	void deleteBatch(String ids);

	void edit(Staff model);

	List<Staff> findStaffNoDele();

}
