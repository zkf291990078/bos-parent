package com.itheima.bos.web.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.User;
import com.itheima.bos.service.UserService;
import com.itheima.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	private String checkcode;
	@Autowired
	private UserService userService;

	public String login() throws Exception {
		// TODO Auto-generated method stub
		String keycode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		if (StringUtils.isNotBlank(checkcode) && checkcode.equals(keycode)) {
			User user = userService.login(model);
			if (user != null) {
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
				return "home";
			} else {
				this.addActionError("用户名或密码不正确");
				return LOGIN;
			}
		} else {
			this.addActionError("验证码不正确");
			return LOGIN;
		}
	}

	public String logout() throws Exception {
		// TODO Auto-generated method stub
		ServletActionContext.getRequest().getSession().invalidate();
		return LOGIN;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

}
