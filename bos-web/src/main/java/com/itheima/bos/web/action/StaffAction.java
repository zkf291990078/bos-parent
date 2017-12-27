package com.itheima.bos.web.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Staff;
import com.itheima.bos.service.StaffService;
import com.itheima.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {

	@Autowired
	private StaffService staffService;

	private String ids;

	/**
	 * 添加取派员
	 */
	public String add() {
		if (StringUtils.isNotBlank(model.getId())) {
			staffService.edit(model);
		} else {
			if (StringUtils.isBlank(model.getDeltag())) {
				model.setDeltag("0");
			}
			staffService.save(model);
		}

		return LIST;
	}

	public String pageQuery() throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = pageBean.getDetachedCriteria();
		if (StringUtils.isNotBlank(model.getTelephone())) {
			criteria.add(Restrictions.like("telephone", "%" + model.getTelephone() + "%"));
		}
		if (StringUtils.isNotBlank(model.getName())) {
			criteria.add(Restrictions.like("name", "%" + model.getName() + "%"));
		}
		staffService.queryPageBean(pageBean);
		java2Json(pageBean, new String[] { "currentPage", "detachedCriteria", "pageSize", "decidedzones" });
		return NONE;
	}

	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		staffService.deleteBatch(ids);
		return LIST;
	}

	public String restoreBatch() throws Exception {
		// TODO Auto-generated method stub
		staffService.restoreBatch(ids);
		return LIST;
	}

	public String listajax() throws Exception {
		// TODO Auto-generated method stub
		List<Staff> staffs = staffService.findStaffNoDele();
		java2Json(staffs, new String[] { "decidedzones" });
		return NONE;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
