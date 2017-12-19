package com.itheima.bos.web.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Subarea;
import com.itheima.bos.service.SubareaService;
import com.itheima.bos.web.action.base.BaseAction;

@Controller
public class SubareaAction extends BaseAction<Subarea> {

	@Autowired
	private SubareaService subareaService;
	
	public String add() throws Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(model.getId())) {
			subareaService.edit(model);
		} else {
		
			subareaService.save(model);
		}

		return LIST;
	}
}
