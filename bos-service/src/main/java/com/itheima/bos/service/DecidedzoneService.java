package com.itheima.bos.service;

import com.itheima.bos.domain.Decidedzone;
import com.itheima.bos.utils.PageBean;

public interface DecidedzoneService {
	
	public void saveDecidedzone(Decidedzone zone ,String[] values);

	public void pageQuery(PageBean pageBean);



}
