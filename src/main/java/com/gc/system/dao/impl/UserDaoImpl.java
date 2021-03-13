package com.gc.system.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gc.common.persistence.impl.BaseDaoImpl;
import com.gc.common.web.PageResult;
import com.gc.system.dao.UserDao;
import com.gc.system.entity.HotsalesGoods;
import com.gc.system.entity.User;
import com.gc.system.utils.DateUtil;
import com.gc.system.vo.UserVO;

/**
 * @author zhuang
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User queryUserbyName(String userName) {
		String hql=" from User where  loginName =:loginName ";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("loginName", userName);
		return super.get(hql, params);
	}

	@Override
	public void changePwd(User user) {
		String hql="update User set password=:password  where  loginName =:loginName ";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("loginName", user.getLoginName());
		params.put("password", user.getPassword());
		super.updateHQL(hql, params);
	}

	@Override
	public PageResult<User> queryuserManagerForPage(Integer currentPage,
			Integer pageSize, UserVO userVO) {

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select id,login_name,role,email,phone,company,create_date  ");
		sql.append(" from user where login_name like :loginName and role like :role and email like :email and phone like :phone and company like :company ");
		
		if (userVO.getCreateDateStr() != null
				&& !"".equals(userVO.getCreateDateStr().trim())) {
			sql.append(" and substring(create_date,1,10) = :createDate ");
			map.put("createDate", userVO.getCreateDateStr());
		}
		sql.append("order by create_date desc ");
		
		String hqlCount = " select count(*) from (" + sql.toString() +" )a  " ;
		map.put("loginName",
				"%"
						+ (userVO.getLoginName() == null ? ""
								: userVO.getLoginName().trim()) + "%");
		map.put("role",
				"%"
						+ (userVO.getRole() == null ? ""
								: userVO.getRole().trim()) + "%");
		map.put("email", "%"
				+ (userVO.getEmail()== null ? ""
						: userVO.getEmail().trim()) + "%");
		map.put("phone", "%"
				+ (userVO.getPhone()== null ? ""
						: userVO.getPhone().trim()) + "%");
		map.put("company",
				"%"
						+ (userVO.getCompany() == null ? ""
								: userVO.getCompany().trim()) + "%");

		PageResult<Object[]> list = nativeQuerySQLObject(sql.toString(),
				hqlCount, map, currentPage, pageSize);

		PageResult<User> result = new PageResult<User>();
		result.setCurrentIndex(list.getCurrentIndex());
		result.setCurrentPage(list.getCurrentPage());
		result.setPageList(list.getPageList());
		result.setPageSize(list.getPageSize());
		result.setTotalCount(list.getTotalCount());
		result.setTotalPage(list.getTotalPage());

		List<User> content = new ArrayList<User>();
		User entity = null;
		for (Object[] row : list.getContent()) {
			entity = new User();
			entity.setId(Integer.valueOf(row[0].toString()));// id
			entity.setLoginName(row[1].toString());// LoginName
			entity.setRole(row[2].toString());// role
			entity.setEmail(row[3].toString());// email
			entity.setPhone(row[4].toString());// phone
			entity.setCompany(row[5].toString());// company
			entity.setCreateDate(DateUtil.stringToDate(row[6].toString(),
					"yyyy-MM-dd"));// CreateDate
			content.add(entity);
		}
		result.setContent(content);
		return result;

	}

	@Override
	public User checkLoginNameAndEmail(User user) {
		String hql=" from User where loginName = :loginName and email = :email  ";
		Map<String,String> params=new HashMap<String,String>();
		params.put("loginName", user.getLoginName());
		params.put("email", user.getEmail());
		List<User> result=super.query(hql, params);
		if(result!=null && result.size()>0){
			return result.get(0);
		}
		return null;
	}
}
