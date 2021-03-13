package com.gc.system.service;

import com.gc.common.web.PageResult;
import com.gc.system.entity.User;
import com.gc.system.vo.UserVO;

/**
 */
public interface UserService {

	public void addUser(User user) throws Exception;

	public User queryUserbyName(String userName);

	public void updatechangePwd(User user)throws Exception;

	public PageResult<User> queryuserManagerForPage(Integer currentPage,
			Integer pageSize, UserVO userVO);

	public void updatechangeRole(String id2)throws Exception;

	public void updatepInfoUpdate(User user)throws Exception;

	public User checkLoginNameAndEmail(User user)throws Exception;

	public void updatePwdAndPost(User user)throws Exception;

}
