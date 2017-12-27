package com.itheima.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Workbill;
import com.itheima.bos.service.WorkbillService;
import com.itheima.bos.web.action.base.BaseAction;
@Controller
@Scope("prototype")
public class WorkbillAction extends BaseAction<Workbill> {

	@Autowired
	private WorkbillService workbillService;
	
	public String add() throws Exception {
		// TODO Auto-generated method stub
		workbillService.saveByMan(model);
		return LIST;
	}
}
