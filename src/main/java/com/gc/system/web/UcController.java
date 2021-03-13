package com.gc.system.web;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gc.common.mapper.CommonConstant;
import com.gc.common.web.BaseController;
import com.gc.common.web.PageResult;
import com.gc.system.entity.ResourceList;
import com.gc.system.entity.User;
import com.gc.system.service.ResourceListService;
import com.gc.system.service.UserService;
import com.gc.system.utils.MD5Util;

/**
 * @author zhuang <br>
 *         权限逻辑的controller
 */
@Controller
@RequestMapping("/uc")
public class UcController extends BaseController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UcController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private ResourceListService resourceListService;

	/**
	 * 个人信息。
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "persionInfo", method = RequestMethod.GET)
	@ResponseBody
	public User getPersionInfo() {
		Subject subject = SecurityUtils.getSubject();
		User curruser = (User) subject.getSession().getAttribute(
				CommonConstant.USER_SESSION);
		User user = userService.queryUserbyName(curruser.getLoginName());
		return user;
	}
	/**
	 * 检查密码输入是否正确。
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "checkPwd", method = RequestMethod.POST)
	@ResponseBody
	public String checkPwd(User user) {
		Subject subject = SecurityUtils.getSubject();
		User curruser = (User) subject.getSession().getAttribute(
				CommonConstant.USER_SESSION);
		if(curruser==null){
			return "fail";
		}
		User userDb = userService.queryUserbyName(curruser.getLoginName());
		String pwd=MD5Util.md5Hex(user.getPassword());
		if(pwd.equals(userDb.getPassword())){
			//成功
			return "success";
		}else{
			return "fail";
		}
	}
	/**
	 * 修改密码。
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "changePwd", method = RequestMethod.POST)
	@ResponseBody
	public String changePwd(User user) {
		Subject subject = SecurityUtils.getSubject();
		User curruser = (User) subject.getSession().getAttribute(
				CommonConstant.USER_SESSION);
		if(curruser==null){
			LOGGER.info("未登录");
			return "fail";
		}
		String pwd=MD5Util.md5Hex(user.getPassword());
		user.setLoginName(curruser.getLoginName());
		user.setPassword(pwd);
		try {
			userService.updatechangePwd(user);
		} catch (Exception e) {
			LOGGER.info("修改密码失败",e);
			return "fail";
		}
		return "success";
	}
	
	
	/**
	 * 资源单页面查询个人资源单<br>
	 * 分页查询
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param resourceList
	 * @return
	 */
	@RequestMapping(value = "rlManagerPage", method = RequestMethod.GET)
	@ResponseBody
	public PageResult<ResourceList> queryResourceListListForPage(
			Integer currentPage, Integer pageSize, ResourceList resourceList) {

		currentPage = (currentPage - 1) * pageSize;// 分页查询是按起始序号

		PageResult<ResourceList> result = null;
		// shiro来得到用户 id
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(
				CommonConstant.USER_SESSION);
		resourceList.setUploderUser(user.getId());
		try {
			result = this.resourceListService.findResourceListListForpageByUser(
					currentPage, pageSize, resourceList);
		} catch (Exception e) {
			LOGGER.error("资源单页面查询个人资源单失败", e);
			return result;
		}
		return result;
	}
	@RequestMapping(value = "pInfoUpdate", method = RequestMethod.POST)
	@ResponseBody
	public String pInfoUpdate(User user){
		try {
			userService.updatepInfoUpdate(user);
		} catch (Exception e) {
			LOGGER.info("修改个人信息失败",e);
			return "fail";
		}
		return "success";
		
	}
}
