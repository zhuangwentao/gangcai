package com.gc.system.web;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gc.common.web.BaseController;

/**
 * @author zhuang <br>
 *         业务逻辑的controller
 */
@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {
	/**
	 * 跳转热销商品页面
	 */
	@RequestMapping(value = "hotsale")
	public String forwardHotsale() {
		return "logic/hotsale";
	}

	/**
	 * 跳转资源单页面
	 */
	@RequestMapping(value = "resourcelist")
	public String forwardResourcelist() {
		return "logic/resourcelist";
	}

	/**
	 * 跳转搜现货页面
	 */
	@RequestMapping(value = "goods")
	public String forwardGoods() {
		return "logic/goods";
	}

	/**
	 * 跳转上传资源单页面
	 */
	@RequiresRoles("user")
	@RequestMapping(value = "uploadResourceList")
	public String forwardUploadResourceList() {
		return "logic/uploadResourceList";
	}

	/**
	 * 登录页面
	 */
	@RequestMapping(value = "login")
	public String forwardLogin() {
		return "auth/login";
	}

	/**
	 * 跳转到用户中心
	 */
	@RequestMapping(value = "usercenter")
	public String forwardUsercenter() {
		return "uc/usercenter";
	}

	/**
	 * 跳转到资源单管理中心
	 */
	@RequestMapping(value = "rlManager")
	public String forwardRlManager() {
		return "uc/rlManager";
	}

	/**
	 * 跳转到今日热卖管理中心
	 */
	@RequestMapping(value = "hsManager")
	public String forwardHsManager() {
		return "uc/hsManager";
	}

	/**
	 * 跳转到用户管理中心
	 */
	@RequiresRoles("user")
	@RequestMapping(value = "userManager")
	public String forwardUserManager() {
		return "uc/userManager";
	}

	/**
	 * 跳转到个人信息管理中心
	 */
	@RequiresRoles("user")
	@RequestMapping(value = "persionInfo")
	public String forwardPersionInfo() {
		return "uc/persionInfo";
	}

	/**
	 * 跳转到修改密码
	 */
	@RequiresRoles("user")
	@RequestMapping(value = "changePwd")
	public String forwardChangePwd() {
		return "uc/changePwd";
	}

	/**
	 * 跳转到404
	 */
	@RequestMapping(value = "404")
	public String forward404() {
		return "errpage/404";
	}
}
