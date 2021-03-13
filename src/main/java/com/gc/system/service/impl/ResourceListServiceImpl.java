package com.gc.system.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gc.common.mapper.CommonConstant;
import com.gc.common.web.PageResult;
import com.gc.system.dao.HotsalesGoodsDao;
import com.gc.system.dao.ResourceListDao;
import com.gc.system.dao.StockGoodsDao;
import com.gc.system.dao.UserDao;
import com.gc.system.entity.HotsalesGoods;
import com.gc.system.entity.ResourceList;
import com.gc.system.entity.StockGoods;
import com.gc.system.entity.User;
import com.gc.system.service.ResourceListService;

/**
 * @author zhuang
 */
@Service("resourceListService")
public class ResourceListServiceImpl implements ResourceListService {

	@Autowired
	private ResourceListDao resourceListDao;
	@Autowired
	private StockGoodsDao stockGoodsDao;
	@Autowired
	private UserDao userDao;

	@Autowired
	private HotsalesGoodsDao hotsalesGoodsDao;

	@Override
	public PageResult<ResourceList> findResourceListListForpage(
			Integer currentPage, Integer pageSize, ResourceList resourceList)
			throws Exception {
		PageResult<ResourceList> result = resourceListDao
				.queryResourceListForpage(currentPage, pageSize, resourceList);
		// shiro来得到用户 id
		Subject subject = SecurityUtils.getSubject();
		User curUser = (User) subject.getSession().getAttribute(
				CommonConstant.USER_SESSION);
		String opeRole = "";
		if (curUser != null) {
			// 登录的情况
			curUser = userDao.findById(curUser.getId());
			opeRole = curUser.getRole();
		}
		for (ResourceList entity : result.getContent()) {
			entity.setOpeRole(opeRole);
			User user = userDao.findById(entity.getUploderUser());
			entity.setUser(user);
		}
		return result;
	}

	@Override
	public ResourceList queryResourceListbyName(String resourceListName)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addResourceList(ResourceList resourceList) throws Exception {
		resourceListDao.save(resourceList);
	}

	@Override
	public ResourceList findResourceListById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addStockGoods(ResourceList resourceList,
			List<StockGoods> goodslist) throws Exception {
		for (StockGoods stockGoods : goodslist) {
			stockGoods.setResourceListId(resourceList.getId());
			stockGoodsDao.save(stockGoods);
		}

	}

	@Override
	public PageResult<StockGoods> findGoodsListForPage(Integer currentPage,
			Integer pageSize, StockGoods stockGoods) {
		return stockGoodsDao.findGoodsListForPage(currentPage, pageSize,
				stockGoods);
	}

	@Override
	public void deleteResourceList(String url, String id) throws Exception {
		// TODO
		// 删除url对应的文件
		ResourceList resourceList = resourceListDao.findByStrId(id);
		if (resourceList != null) {
			resourceListDao.remove(resourceList);
		}
		stockGoodsDao.delteGoodsByRlId(id);

	}

	@Override
	public PageResult<ResourceList> findResourceListListForpageByUser(
			Integer currentPage, Integer pageSize, ResourceList resourceList) {
		PageResult<ResourceList> result = resourceListDao
				.queryResourceListForpageByUserId(currentPage, pageSize,
						resourceList);
		return result;
	}

	@Override
	public PageResult<HotsalesGoods> findHsGoodsListForPage(
			Integer currentPage, Integer pageSize, HotsalesGoods hotsalesGoods) {
		return hotsalesGoodsDao.findHsGoodsListForPage(currentPage, pageSize,
				hotsalesGoods);
	}

	@Override
	public PageResult<HotsalesGoods> queryHsGoodsPageForClient(
			Integer currentPage, Integer pageSize, HotsalesGoods hotsalesGoods) {
		return hotsalesGoodsDao.findHsGoodsListForPageForClient(currentPage,
				pageSize, hotsalesGoods);
	}

}
