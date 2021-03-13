/**
 * File Name:UserRealm.java
 * Package Name:com.gc.common.shiro
 * Date:Jan 7, 20165:41:36 PM
 */

package com.gc.common.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gc.common.mapper.CommonConstant;
import com.gc.common.utils.StringUtils;
import com.gc.system.entity.User;
import com.gc.system.service.UserService;

/**
 * ClassName:UserRealm <br/>
 * 
 * @author zhuangwentao
 * @version
 * @since JDK 1.6
 * @see
 */
public class UserRealm extends AuthorizingRealm {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserRealm.class);

	@Autowired
	private UserService userService;

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {

		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String userName = token.getUsername();
		String psd = String.valueOf(token.getPassword());

		if (StringUtils.isBlank(userName) || StringUtils.isBlank(psd)) {
			return null;
		}
		User user = null;
		try {
			user = userService.queryUserbyName(userName);
		} catch (Exception e) {
			LOGGER.error("登录用户查询失败", e);
		}

		if (user != null) {
			ShiroUser shiroUser = new ShiroUser(user.getId(),
					user.getLoginName(), user.getLoginName());
			Session session = SecurityUtils.getSubject().getSession();
			session.setAttribute(CommonConstant.USER_SESSION, user);
			return new SimpleAuthenticationInfo(shiroUser, user.getPassword(),
					getName());
		} else {
			return null;
		}

	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {

		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		String username = shiroUser.getLoginName();

		User user = null;
		try {
			user = userService.queryUserbyName(username);
		} catch (Exception e) {
			LOGGER.error("登录用户查询失败", e);
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		if (user != null) {
			info.addRole("user");

			info.addStringPermission("deleteResourcelist");// 删除资源单

			if ("admin".equals(user.getRole())) {
				// 管理员
				info.addRole("admin");
				info.addStringPermission("adminPC");// 管理员个人中心
			} else if ("user".equals(user.getRole())) {
				// 一般用户
				// info.addRole("user");
				info.addStringPermission("userPC");// 用户个人中心
			}
		}
		return info;
	}

}
