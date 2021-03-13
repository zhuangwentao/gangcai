package com.gc.system.service;

import java.util.List;

import com.gc.common.web.PageResult;
import com.gc.system.entity.HotsalesGoods;
import com.gc.system.entity.ResourceList;
import com.gc.system.entity.StockGoods;

/**
 * @author zhuang
 */
public interface ResourceListService {

	/**
	 * 分页查询资源单信息
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param dto
	 * @return PageResult<ResourceList> 资源单信息
	 */
	public PageResult<ResourceList> findResourceListListForpage(
			Integer currentPage, Integer pageSize, ResourceList resourceList)
			throws Exception;

	/**
	 * 发布资源单，导入数据库数据
	 * 
	 * @param resourceList
	 * @param stockGoods
	 * @throws Exception
	 */
	public void addStockGoods(ResourceList resourceList,
			List<StockGoods> goodslist) throws Exception;

	/**
	 * 根据资源单名称返回一条资源单信息。
	 *
	 * @param ResourceListName
	 *            资源单名称
	 * @return ResourceList 一条资源单记录
	 */
	public ResourceList queryResourceListbyName(String resourceListName)
			throws Exception;

	/**
	 * 添加一条资源单记录。
	 *
	 * @param ResourceList
	 */
	public void addResourceList(ResourceList resourceList) throws Exception;

	/**
	 * 根据资源单id返回一条资源单信息。
	 *
	 * @param id
	 *            资源单名称
	 * @return ResourceList 一条资源单记录
	 */
	public ResourceList findResourceListById(Integer id) throws Exception;

	/**
	 * 分页查询现货信息
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param stockGoods
	 * @return
	 */
	public PageResult<StockGoods> findGoodsListForPage(Integer currentPage,
			Integer pageSize, StockGoods stockGoods);

	public void deleteResourceList(String url, String id) throws Exception;

	public PageResult<ResourceList> findResourceListListForpageByUser(
			Integer currentPage, Integer pageSize, ResourceList resourceList);

	public PageResult<HotsalesGoods> findHsGoodsListForPage(
			Integer currentPage, Integer pageSize, HotsalesGoods hotsalesGoods);

	public PageResult<HotsalesGoods> queryHsGoodsPageForClient(
			Integer currentPage, Integer pageSize, HotsalesGoods hotsalesGoods);

}
