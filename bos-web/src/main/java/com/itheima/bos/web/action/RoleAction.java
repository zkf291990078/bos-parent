package com.itheima.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Role;
import com.itheima.bos.service.RoleService;
import com.itheima.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	@Autowired
	private RoleService roleService;

	private String functionIds;

	public String add() throws Exception {
		// TODO Auto-generated method stub
		roleService.save(model, functionIds);
		return LIST;
	}

	public String pageQuery() throws Exception {
		// TODO Auto-generated method stub
		roleService.findPageQuery(pageBean);
		java2Json(pageBean, new String[] { "functions", "users" });
		return NONE;
	}

	public String findAll() throws Exception {
		// TODO Auto-generated method stub
		List<Role> list = roleService.findAll();
		java2Json(list, new String[] { "functions", "users" });
		return NONE;
	}
	public String edit() throws Exception {
		// TODO Auto-generated method stub
		roleService.edit(model, functionIds);
		return LIST;
	}
	

	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}

}
