package com.gc.system.dao;

import com.gc.common.persistence.BaseDao;
import com.gc.common.web.PageResult;
import com.gc.system.entity.StockGoods;

/**
 * @author zhuang
 */
public interface StockGoodsDao extends BaseDao<StockGoods> {

	/**
	 * 分页查询
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param stockGoods
	 * @return
	 */
	public PageResult<StockGoods> findGoodsListForPage(Integer currentPage,
			Integer pageSize, StockGoods stockGoods);

	public void delteGoodsByRlId(String id);
}
