package com.itheima.bos.web.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.User;
import com.itheima.bos.service.UserService;
import com.itheima.bos.utils.BOSUtils;
import com.itheima.bos.utils.MD5Utils;
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
			Subject subject = SecurityUtils.getSubject();
			AuthenticationToken token = new UsernamePasswordToken(model.getUsername(),
					MD5Utils.md5(model.getPassword()));
			try {
				subject.login(token);
				User user = (User) subject.getPrincipal();
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
			} catch (Exception e) {
				// TODO: handle exception
				this.addActionError("用户不存在或密码不正确");
				return LOGIN;
			}

			return "home";
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

	public String editPassword() throws Exception {
		// TODO Auto-generated method stub
		User user = BOSUtils.getLoginUser();

		int f = 1;
		try {
			userService.editPassword(user.getId(), model.getPassword());
		} catch (Exception e) {
			// TODO: handle exception
			f = 0;
		}
		ServletActionContext.getResponse().setContentType("text/html,charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(f);
		return NONE;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

}
