package com.gc.system.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gc.common.persistence.impl.BaseDaoImpl;
import com.gc.common.web.PageResult;
import com.gc.system.dao.ResourceListDao;
import com.gc.system.entity.ResourceList;
import com.gc.system.utils.DateUtil;

/**
 * @author zhuang
 */
@Repository("resourceListDao")
public class ResourceListDaoImpl extends BaseDaoImpl<ResourceList> implements
		ResourceListDao {

	@Override
	public PageResult<ResourceList> queryResourceListForpage(
			Integer currentPage, Integer pageSize, ResourceList resourceList) {

		Map<String, Object> map = new HashMap<String, Object>();
		String hql = " from ResourceList where classification like :classification and mainVarieties like :mainVarieties and mainSteelfactory like :mainSteelfactory and company like :company "
				+ " order by uploderTime desc ";
		String hqlCount = " select count(*) " + hql;
		map.put("classification",
				"%"
						+ (resourceList.getClassification() == null ? ""
								: resourceList.getClassification().trim()) + "%");
		map.put("mainVarieties",
				"%"
						+ (resourceList.getMainVarieties() == null ? ""
								: resourceList.getMainVarieties().trim()) + "%");
		map.put("mainSteelfactory", "%"
				+ (resourceList.getMainSteelfactory() == null ? ""
						: resourceList.getMainSteelfactory().trim()) + "%");
		map.put("company",
				"%"
						+ (resourceList.getCompany() == null ? ""
								: resourceList.getCompany().trim()) + "%");

		return query(hql, hqlCount, map, currentPage, pageSize);
	}

	@Override
	public ResourceList queryResourceListbyName(String ResourceListName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addResourceList(ResourceList ResourceList) {
		// TODO Auto-generated method stub

	}

	@Override
	public ResourceList findResourceListById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteResourceList(ResourceList ResourceList) {
		// TODO Auto-generated method stub

	}

	@Override
	public ResourceList findByStrId(String id) {
		String hql = " from  ResourceList where id=:id  ";
		Map<String, String> param = new HashMap<String, String>();
		param.put("id", id);
		List<ResourceList> list = super.query(hql, param);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public PageResult<ResourceList> queryResourceListForpageByUserId(
			Integer currentPage, Integer pageSize, ResourceList resourceList) {

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer hql =new StringBuffer();
		hql.append(" from ResourceList where classification like :classification and mainVarieties like :mainVarieties  and uploderUser=:uploderUser  ");
				
		if(resourceList.getUploderTimeStr()!=null && resourceList.getUploderTimeStr().length()==10){
			//正确输入查询日期才会查询
				hql.append(" and  substring(uploderTime,1,10) = :uploderTime  ");
				map.put("uploderTime",resourceList.getUploderTimeStr());
		}
		hql.append(" order by uploderTime desc ");
		String hqlCount = " select count(*) " + hql.toString();
		map.put("classification",
				"%"
						+ (resourceList.getClassification() == null ? ""
								: resourceList.getClassification().trim()) + "%");
		map.put("mainVarieties",
				"%"
						+ (resourceList.getMainVarieties() == null ? ""
								: resourceList.getMainVarieties().trim()) + "%");
		map.put("uploderUser",resourceList.getUploderUser());
		return query(hql.toString(), hqlCount, map, currentPage, pageSize);
	}
}
