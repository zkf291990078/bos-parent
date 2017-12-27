package com.itheima.bos.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.DecidedzoneDao;
import com.itheima.bos.dao.NoticebillDao;
import com.itheima.bos.dao.WorkbillDao;
import com.itheima.bos.domain.Decidedzone;
import com.itheima.bos.domain.Noticebill;
import com.itheima.bos.domain.Staff;
import com.itheima.bos.domain.User;
import com.itheima.bos.domain.Workbill;
import com.itheima.bos.service.NoticebillService;
import com.itheima.bos.utils.BOSUtils;
import com.itheima.bos.utils.PageBean;
import com.itheima.crm.ICustomerService;

@Service
@Transactional
public class NoticebillServiceImpl implements NoticebillService {

	@Autowired
	private NoticebillDao noticebillDao;
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private DecidedzoneDao decidedzoneDao;
	@Autowired
	private WorkbillDao workbillDao;

	@Override
	public void save(Noticebill noticebill) {
		// TODO Auto-generated method stub
		// 设置当前登录用户
		User user = BOSUtils.getLoginUser();
		noticebill.setUser(user);
		noticebillDao.save(noticebill);
		// 获取客户的取件地址
		String address = noticebill.getPickaddress();
		// 远程调用crm服务，根据取件地址查询定区id
		String decidedzone_id = customerService.findDecidedzoneIdByAddress(address);
		if (StringUtils.isNoneBlank(decidedzone_id)) {
			// 查询到了定区id，可以完成自动分单
			Decidedzone decidedzone = decidedzoneDao.findById(decidedzone_id);
			Staff staff = decidedzone.getStaff();
			noticebill.setStaff(staff);// 业务通知单关联取派员对象
			// 设置分单类型为：自动分单
			noticebill.setOrdertype(Noticebill.ORDERTYPE_AUTO);

			// 为取派员产生一个工单
			Workbill workbill = new Workbill();
			// 追单次数
			workbill.setAttachbilltimes(0);
			// 创建时间，当前系统时间
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
			// 工单关联页面通知单
			workbill.setNoticebill(noticebill);
			// 取件状态
			workbill.setPickstate(Workbill.PICKSTATE_NO);
			// 备注信息
			workbill.setRemark(noticebill.getRemark());
			// 工单关联取派员
			workbill.setStaff(staff);
			// 工单类型
			workbill.setType(Workbill.TYPE_1);
			workbillDao.save(workbill);

			// 调用短信平台，发送短信
		} else {
			// 没有查询到定区id，不能完成自动分单
			noticebill.setOrdertype(Noticebill.ORDERTYPE_MAN);
		}

	}

	@Override
	public void queryPage(PageBean pageBean) {
		// TODO Auto-generated method stub
		noticebillDao.queryPageBean(pageBean);
	}

}
