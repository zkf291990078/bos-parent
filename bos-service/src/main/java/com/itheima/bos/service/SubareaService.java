package com.itheima.bos.service;

import java.util.List;

import com.itheima.bos.domain.Subarea;
import com.itheima.bos.utils.PageBean;

public interface SubareaService {

	void edit(Subarea model);

	void save(Subarea model);

	void queryPage(PageBean pageBean);

	List<Subarea> findAll();

}
