package com.gc.system.utils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 * 扩展认证默认过滤
 * @author zhuangwentao
 */
public class FormAuthenticationCaptchaFilter extends FormAuthenticationFilter {

	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = MD5Util.md5Hex(getPassword(request));
		return new UsernamePasswordToken(username,password);
	}

}