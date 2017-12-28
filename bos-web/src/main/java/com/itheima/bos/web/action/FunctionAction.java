package com.itheima.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Function;
import com.itheima.bos.service.FunctionService;
import com.itheima.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {

	@Autowired
	private FunctionService functionService;
	
	private String roleId;

	public String add() throws Exception {
		if (model.getParentFunction() != null && model.getParentFunction().getId().equals("")) {
			model.setParentFunction(null);
		}
		functionService.save(model);

		return LIST;
	}

	public String listajax() throws Exception {
		// TODO Auto-generated method stub
		List<Function> list = functionService.findAll();
		java2Json(list, new String[] { "parentFunction", "roles" });
		return NONE;
	}

	public String pageQuery() throws Exception {
		// TODO Auto-generated method stub
		int currentPage = Integer.parseInt(model.getPage());
		pageBean.setCurrentPage(currentPage);
		functionService.queryPage(pageBean);
		java2Json(pageBean, new String[] { "parentFunction", "roles", "children" });
		return NONE;
	}

	public String findAllMenu() throws Exception {
		// TODO Auto-generated method stub
		List<Function> list= functionService.findAllMenu();
		java2Json(list,  new String[] { "parentFunction", "roles", "children" });
		return NONE;
	}
	
	public String findFunctionsByRoleId() throws Exception {
		// TODO Auto-generated method stub
		List<Function> list= functionService.findFunctionsByRoleId(roleId);
		java2Json(list, new String[] { "parentFunction", "roles", "children" });
		return NONE;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	
}
