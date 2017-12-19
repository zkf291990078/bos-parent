package com.itheima.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Decidedzone;
import com.itheima.bos.service.DecidedzoneService;
import com.itheima.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {
	
//	private String[] subareaid; 
//	public void setSubareaids(String[] subareaid) {
//		this.subareaid = subareaid;
//	}
//
//	public String[] getSubareaid() {
//		return subareaid;
//	}

	@Autowired
	private DecidedzoneService decidedzoneService;
	
	public String add() throws Exception {
		// TODO Auto-generated method stub
		decidedzoneService.saveDecidedzone(model, model.getSubareaid());
		
		return LIST;
	}
	
		public String pageQuery() throws Exception {
			// TODO Auto-generated method stub
			decidedzoneService.pageQuery(pageBean);
			java2Json(pageBean, new String[]{ "currentPage", "detachedCriteria", "pageSize","subareas","decidedzones"});
			return NONE;
		}
	

}
