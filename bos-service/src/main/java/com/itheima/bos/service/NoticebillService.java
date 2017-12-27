package com.itheima.bos.service;

import java.util.List;

import com.itheima.bos.domain.Noticebill;
import com.itheima.bos.utils.PageBean;

public interface NoticebillService {

	public void save(Noticebill noticebill);


	public void queryPage(PageBean pageBean);
}
