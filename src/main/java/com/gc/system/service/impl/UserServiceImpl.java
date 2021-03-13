package com.gc.system.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gc.common.utils.StringUtils;
import com.gc.common.web.PageResult;
import com.gc.system.dao.UserDao;
import com.gc.system.entity.User;
import com.gc.system.service.UserService;
import com.gc.system.utils.MD5Util;
import com.gc.system.utils.SendmailUtil;
import com.gc.system.vo.UserVO;

/**
 * 
 * @author zhuang_wt
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void addUser(User user) throws Exception {
		user.setDelFlag("0");
		user.setCreateDate(new Date());
		userDao.save(user);
	}

	@Override
	public User queryUserbyName(String userName) {
		return userDao.queryUserbyName(userName);
	}

	@Override
	public void updatechangePwd(User user) {
		userDao.changePwd(user);
	}

	@Override
	public PageResult<User> queryuserManagerForPage(Integer currentPage,
			Integer pageSize, UserVO userVO) {
		return userDao.queryuserManagerForPage(currentPage, pageSize, userVO);
	}

	@Override
	public void updatechangeRole(String id) {
		User user = userDao.findById(Integer.valueOf(id));
		if (user != null) {
			if ("user".equals(user.getRole())) {
				user.setRole("admin");
			} else if ("admin".equals(user.getRole())) {
				user.setRole("user");
			}
			userDao.update(user);
		}
	}

	@Override
	public void updatepInfoUpdate(User user) {
		User entity = userDao.findById(user.getId());
		if (entity != null) {
			entity.setEmail(user.getEmail());
			entity.setCompany(user.getCompany());
			entity.setPhone(user.getPhone());
			userDao.update(entity);
		}
	}

	@Override
	public User checkLoginNameAndEmail(User user) {
		return userDao.checkLoginNameAndEmail(user);
	}

	@Override
	public void updatePwdAndPost(User user) throws Exception {
		String newPwd = "4dsmf4";

		UUID uuid = UUID.randomUUID();
		String str = uuid.toString().replace("-", "").toLowerCase();
		if (!StringUtils.isEmpty(str) && str.length() > 6) {
			newPwd = str.substring(0, 6);
		}
		// 修改数据库
		user.setPassword(MD5Util.md5Hex(newPwd));
		userDao.changePwd(user);

		// 发送邮件
		postEmail(user, newPwd);
	}

	// 发送邮件
	private void postEmail(User user, String newPwd) throws Exception {
		SendmailUtil se = new SendmailUtil();
		String title = "[买卖钢材网maisteel]-密码重置";
		// String content2 = "Hi " + user.getLoginName()
		// + "您好：<br>&nbsp;&nbsp;您的新密码为：" + newPwd
		// + "。<br>请尽快登录并修改您的密码。<br>本邮件由系统自动发出，请勿回复！<br><hr>买卖钢材网maisteel";

		StringBuffer content = new StringBuffer();
		content.append("<div align=\"center\">");
		content.append("<div align=\"center\" style=\"padding:10px 20px 20px 20px;font-family: lucida Grande,Verdana,Microsoft YaHei;width:40%;border: 2px solid #3598dc; \">");
		content.append("<h3 align=\"left\">Hi ");
		content.append(user.getLoginName());
		content.append("：</h3>");
		content.append("<h5 align=\"left\">您好!</h5>");
		content.append("<p align=\"left\">您的新密码为：<span style=\"font-family:Verdana; font-weight:bold; color:#F60; font-size:14px;\">");
		content.append(newPwd);
		content.append("</span>。请尽快登录并修改您的密码。");
		content.append("<br><br>本邮件由系统自动发出，请勿回复！<p>");
		content.append("<hr>");
		content.append("<font color=\"#323335\" size=\"2\"> Copyright &copy;2016 买卖钢材网maisteel 版权所有 </font>");
		content.append("</div></div>");

		try {
			se.doSendHtmlEmail(title, content.toString(), user.getEmail());
		} catch (Exception e) {
			throw new Exception();
		}
	}
}
