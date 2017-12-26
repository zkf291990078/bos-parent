package com.itheima.shiro;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.itheima.bos.dao.FunctionDao;
import com.itheima.bos.dao.UserDao;
import com.itheima.bos.domain.Function;
import com.itheima.bos.domain.User;

public class BOSRealm extends AuthorizingRealm {

	@Autowired
	private UserDao userDao;

	@Autowired
	private FunctionDao functionDao;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		List<Function> list = null;
		if (user.getUsername().equals("tom")) {
			DetachedCriteria criteria = DetachedCriteria.forClass(Function.class);
			list = functionDao.findDataByCriteria(criteria);
		} else {
			list = functionDao.findFunctionByUser(user.getId());
		}
		for (Function f : list) {
			info.addStringPermission(f.getCode());
		}

		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken user_token = (UsernamePasswordToken) token;
		String username = user_token.getUsername();
		User user = userDao.findUserByUsername(username);
		if (username == null) {
			return null;
		}
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		return info;
	}

}
