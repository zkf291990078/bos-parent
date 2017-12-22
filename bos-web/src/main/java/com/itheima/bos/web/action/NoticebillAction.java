package com.itheima.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Noticebill;
import com.itheima.bos.service.NoticebillService;
import com.itheima.bos.web.action.base.BaseAction;
import com.itheima.crm.Customer;
import com.itheima.crm.ICustomerService;

@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {

	@Autowired
	private ICustomerService customerService;
	@Autowired
	private NoticebillService noticebillService;

	public String add() throws Exception {
		noticebillService.save(model);
		return "noticebill_add";
	}

	public String findCustomerByTelephone() throws Exception {
		// TODO Auto-generated method stub
		Customer customer = null;
		List<Customer> customers = customerService.findCustomersByTelephone(model.getTelephone());
		if (customers != null && customers.size() > 0) {
			customer = customers.get(0);
		}

		java2Json(customer, new String[] {});
		return NONE;
	}

}
