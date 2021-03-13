package com.gc.system.dao;

import com.gc.common.persistence.BaseDao;
import com.gc.common.web.PageResult;
import com.gc.system.entity.ResourceList;

/**
 * @author zhuang
 */
public interface ResourceListDao extends BaseDao<ResourceList> {

	/**
	 * 查詢信息
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param resourceList
	 * @return
	 */
	public PageResult<ResourceList> queryResourceListForpage(
			Integer currentPage, Integer pageSize, ResourceList resourceList);

	/**
	 * 根据资源单名称返回一条资源单信息。
	 *
	 * @param ResourceListName
	 *            资源单名称
	 * @return ResourceList 一条资源单记录
	 */
	public ResourceList queryResourceListbyName(String ResourceListName);

	/**
	 * 添加一条资源单记录。
	 *
	 * @param ResourceList
	 */
	public void addResourceList(ResourceList ResourceList);

	/**
	 * 根据资源单id返回一条资源单信息。
	 *
	 * @param id
	 *            资源单名称
	 * @return ResourceList 一条资源单记录
	 */
	public ResourceList findResourceListById(Integer id);

	/**
	 * 删除一条资源单记录。
	 *
	 * @param ResourceList
	 */
	public void deleteResourceList(ResourceList ResourceList);

	public ResourceList findByStrId(String id);

	public PageResult<ResourceList> queryResourceListForpageByUserId(
			Integer currentPage, Integer pageSize, ResourceList resourceList);

}
