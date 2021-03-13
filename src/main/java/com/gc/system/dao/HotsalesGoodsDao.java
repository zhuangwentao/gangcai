package com.gc.system.dao;

import com.gc.common.persistence.BaseDao;
import com.gc.common.web.PageResult;
import com.gc.system.entity.HotsalesGoods;

/**
 * @author zhuang
 */
public interface HotsalesGoodsDao extends BaseDao<HotsalesGoods> {

	PageResult<HotsalesGoods> findHsGoodsListForPage(Integer currentPage,
			Integer pageSize, HotsalesGoods hotsalesGoods);

	PageResult<HotsalesGoods> findHsGoodsListForPageForClient(
			Integer currentPage, Integer pageSize, HotsalesGoods hotsalesGoods);
}
