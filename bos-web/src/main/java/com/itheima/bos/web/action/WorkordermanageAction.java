package com.itheima.bos.web.action;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Workordermanage;
import com.itheima.bos.service.WorkordermanageService;
import com.itheima.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage> {
	@Autowired
	private WorkordermanageService wService;
	
	public String add() throws Exception {
		// TODO Auto-generated method stub
		String f = "1";
		try{
			wService.save(model);
		}catch(Exception e){
			e.printStackTrace();
			f = "0";
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(f);
		return NONE;
	}

}
