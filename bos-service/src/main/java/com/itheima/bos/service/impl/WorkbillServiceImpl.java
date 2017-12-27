package com.itheima.bos.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.WorkbillDao;
import com.itheima.bos.domain.Workbill;
import com.itheima.bos.service.WorkbillService;

@Service
@Transactional
public class WorkbillServiceImpl implements WorkbillService {

	@Autowired
	private WorkbillDao workbillDao;

	@Override
	public void saveByMan(Workbill model) {
		// TODO Auto-generated method stub

		// 追单次数
		model.setAttachbilltimes(0);
		// 创建时间，当前系统时间
		model.setBuildtime(new Timestamp(System.currentTimeMillis()));

		// 取件状态
		model.setPickstate(Workbill.PICKSTATE_NO);
		// 备注信息
		model.setRemark("无");

		// 工单类型
		model.setType(Workbill.TYPE_1);
		workbillDao.save(model);
	}

}
