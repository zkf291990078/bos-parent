package com.itheima.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Decidedzone;
import com.itheima.bos.domain.Subarea;
import com.itheima.bos.service.DecidedzoneService;
import com.itheima.bos.service.SubareaService;
import com.itheima.bos.web.action.base.BaseAction;
import com.itheima.crm.Customer;
import com.itheima.crm.ICustomerService;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {

	// private String[] subareaid;
	// public void setSubareaids(String[] subareaid) {
	// this.subareaid = subareaid;
	// }
	//
	// public String[] getSubareaid() {
	// return subareaid;
	// }

	@Autowired
	private DecidedzoneService decidedzoneService;
	@Autowired
	private ICustomerService proxy;
	@Autowired
	private SubareaService subareaService;
	private List<Integer> customerIds;
	private String decidedzone_id;

	public void setDecidedzone_id(String decidedzone_id) {
		this.decidedzone_id = decidedzone_id;
	}

	public List<Integer> getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}

	public String add() throws Exception {
		// TODO Auto-generated method stub
		decidedzoneService.saveDecidedzone(model, model.getSubareaid());

		return LIST;
	}

	public String pageQuery() throws Exception {
		// TODO Auto-generated method stub
		decidedzoneService.pageQuery(pageBean);
		java2Json(pageBean, new String[] { "currentPage", "detachedCriteria", "pageSize", "subareas", "decidedzones" });
		return NONE;
	}

	public String findListNotAssociation() throws Exception {
		List<Customer> list = proxy.findListNotAssociation();
		java2Json(list, new String[] {});
		return NONE;
	}

	public String findListHasAssociation() throws Exception {
		// TODO Auto-generated method stub
		List<Customer> list = proxy.findListByAssociation(model.getId());
		java2Json(list, new String[] {});
		return NONE;
	}

	public String assigncustomerstodecidedzone() throws Exception {
		// TODO Auto-generated method stub
		proxy.assignCustomersToDecidedzone(model.getId(), customerIds);
		return LIST;
	}

	public String queryAssoginSubarea() throws Exception {
		// TODO Auto-generated method stub
		List<Subarea> subareas = subareaService.findSssoginSubarea(decidedzone_id);
		java2Json(subareas, new String[]{"decidedzone","subareas"});
		return NONE;
	}
}
