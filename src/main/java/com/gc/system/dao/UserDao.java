package com.gc.system.dao;

import com.gc.common.persistence.BaseDao;
import com.gc.common.web.PageResult;
import com.gc.system.entity.User;
import com.gc.system.vo.UserVO;

/**
 * @author zhuang
 */
public interface UserDao extends BaseDao<User> {

	public  User queryUserbyName(String userName);

	public void changePwd(User user);

	public PageResult<User> queryuserManagerForPage(Integer currentPage,
			Integer pageSize, UserVO userVO);

	public User checkLoginNameAndEmail(User user);
}
