package com.gc.system.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gc.common.mapper.CommonConstant;
import com.gc.common.web.BaseController;
import com.gc.common.web.PageResult;
import com.gc.system.entity.User;
import com.gc.system.service.UserService;
import com.gc.system.utils.MD5Util;
import com.gc.system.vo.UserVO;

/**
 * @author zhuang <br>
 *         权限逻辑的controller
 */
@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AuthController.class);
	@Autowired
	private UserService userService;

	/**
	 * 保存
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public String addUser(User user) {
		String pwd = user.getPassword();
		// 密码简单加密 没加盐值
		user.setPassword(MD5Util.md5Hex(user.getPassword()));
		try {
			userService.addUser(user);
		} catch (Exception e) {
			LOGGER.error("保存用户信息失败", e);
			return "fail";
		}

		// 登入系统
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(
				user.getLoginName(), MD5Util.md5Hex(pwd));
		subject.login(token);

		return "success";
	}

	/**
	 * 登录
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public String login(UserVO userVO) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(
				userVO.getLoginName(), MD5Util.md5Hex(userVO.getPassword()));
		if("Y".equals(userVO.getIsRememberMe())){
			token.setRememberMe(true);
		}
		try {
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		if (subject.isAuthenticated() ) {
			// 登录成功
			return "success";
		} else {
			// 登录失败
			return "fail";
		}
	}

	/**
	 * 注册时候检查重名
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "checkLoginName/{loginName}", method = RequestMethod.GET)
	@ResponseBody
	public String checkLoginName(@PathVariable("loginName") String loginName) {
		User user = userService.queryUserbyName(loginName);
		if (user == null) {
			return "success";
		} else {
			return "fail";
		}
	}

	/**
	 * 找回密码时候校验用户名和密码是否一致
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "checkLoginNameAndEmail", method = RequestMethod.POST)
	@ResponseBody
	public String checkLoginNameAndEmail(User user) {
		User result;
		try {
			result = userService.checkLoginNameAndEmail(user);
			if(result!=null){
				return "success";
			}else{
				return "fail";
			}
		} catch (Exception e) {
			LOGGER.error("找回密码时候校验用户名和密码是否一致执行失败", e);
			return "fail";
		}
	}
	/**
	 * 找回密码时候重置密码并发送邮件
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "resetPwdAndPost", method = RequestMethod.POST)
	@ResponseBody
	public String resetPwdAndPost(User user) {
		try {
			userService.updatePwdAndPost(user);
		} catch (Exception e) {
			LOGGER.error("找回密码时候重置密码并发送邮件失败", e);
			return "fail";
		}
		return "success";
	}
	/**
	 * 退出
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "logout")
	public String logout(Model model) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "auth/login";
	}

	/**
	 * 查询用户<br>
	 * 分页查询
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param resourceList
	 * @return
	 */
	@RequestMapping(value = "userManagerPage", method = RequestMethod.GET)
	@ResponseBody
	public PageResult<User> queryuserManagerForPage(Integer currentPage,
			Integer pageSize, UserVO userVO) {

		currentPage = (currentPage - 1) * pageSize;// 分页查询是按起始序号

		PageResult<User> result = null;
		try {
			result = this.userService.queryuserManagerForPage(currentPage,
					pageSize, userVO);
		} catch (Exception e) {
			LOGGER.error("查询用户失败", e);
			return result;
		}
		return result;
	}

	@RequestMapping(value = "changeRole/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String changeRole(@PathVariable("id") String id) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(
				CommonConstant.USER_SESSION);

		if (user.getId() == Integer.valueOf(id) || "1".equals(id)) {
			return "self";
		}
		try {
			// 参数：操作id
			userService.updatechangeRole(id);
		} catch (Exception e) {
			LOGGER.info("变更权限失败", e);
			return "fail";
		}
		return "success";
	}
}
