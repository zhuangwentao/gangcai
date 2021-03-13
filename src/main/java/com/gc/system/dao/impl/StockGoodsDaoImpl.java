package com.gc.system.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gc.common.persistence.impl.BaseDaoImpl;
import com.gc.common.web.PageResult;
import com.gc.system.dao.StockGoodsDao;
import com.gc.system.entity.ResourceList;
import com.gc.system.entity.StockGoods;

/**
 * @author zhuang
 */
@Repository("stockGoodsDao")
public class StockGoodsDaoImpl extends BaseDaoImpl<StockGoods> implements
		StockGoodsDao {

	@Override
	public PageResult<StockGoods> findGoodsListForPage(Integer currentPage,
			Integer pageSize, StockGoods stockGoods) {

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select rl.company,rl.area, ");
		sql.append(" sg.product_name,sg.material,sg.specifications,sg.origin,sg.price,sg.wearhouse ");
		sql.append(" from stock_goods sg inner join resource_list rl on (sg.resource_list_id=rl.ID) ");
		sql.append(" where rl.area like :area ");
		sql.append(" and sg.specifications like :specifications ");
		sql.append(" and sg.material like :material ");
		sql.append(" and sg.origin like :origin ");
		sql.append(" and sg.product_name like :productName ");
		sql.append(" and sg.wearhouse like :wearhouse ");
		sql.append(" and rl.company like :company ");
		

		if (stockGoods.getPrice() != null
				&& Double.valueOf("0").compareTo(stockGoods.getPrice()) < 0) {
			sql.append(" and sg.price >= :price ");
			map.put("price", stockGoods.getPrice());
		}
		if (stockGoods.getPriceEnd() != null
				&& Double.valueOf("0").compareTo(stockGoods.getPriceEnd()) < 0) {
			sql.append(" and sg.price <= :priceEnd ");
			map.put("priceEnd", stockGoods.getPriceEnd());
		}

		String hqlCount = " select count(*) from ( " + sql.toString() + ")aa";
		map.put("company",
				"%"
						+ (stockGoods.getCompany() == null ? "" : stockGoods
								.getCompany().trim()) + "%");
		map.put("area",
				"%"
						+ (stockGoods.getArea() == null ? "" : stockGoods
								.getArea().trim()) + "%");
		map.put("specifications",
				"%"
						+ (stockGoods.getSpecifications() == null ? ""
								: stockGoods.getSpecifications().trim()) + "%");
		map.put("material",
				"%"
						+ (stockGoods.getMaterial() == null ? "" : stockGoods
								.getMaterial().trim()) + "%");
		map.put("origin",
				"%"
						+ (stockGoods.getOrigin() == null ? "" : stockGoods
								.getOrigin().trim()) + "%");
		map.put("productName",
				"%"
						+ (stockGoods.getProductName() == null ? ""
								: stockGoods.getProductName().trim()) + "%");
		map.put("wearhouse",
				"%"
						+ (stockGoods.getWearhouse() == null ? "" : stockGoods
								.getWearhouse().trim()) + "%");
		PageResult<StockGoods> result = new PageResult<StockGoods>();
		PageResult<Object[]> list = nativeQuerySQLObject(sql.toString(),
				hqlCount, map, currentPage, pageSize);
		result.setCurrentIndex(list.getCurrentIndex());
		result.setCurrentPage(list.getCurrentPage());
		result.setPageList(list.getPageList());
		result.setPageSize(list.getPageSize());
		result.setTotalCount(list.getTotalCount());
		result.setTotalPage(list.getTotalPage());

		List<StockGoods> content = new ArrayList<StockGoods>();
		StockGoods entity = null;
		ResourceList resourceList = null;
		for (Object[] row : list.getContent()) {
			entity = new StockGoods();
			resourceList = new ResourceList();
			resourceList.setCompany(row[0].toString());// company
			resourceList.setArea(row[1].toString());// area
			entity.setResourceList(resourceList);
			entity.setProductName(row[2].toString());// product_name
			entity.setMaterial(row[3].toString());// material
			entity.setSpecifications(row[4].toString());// specifications
			entity.setOrigin(row[5].toString());// origin
			entity.setPrice(Double.valueOf(row[6].toString()));// price
			entity.setWearhouse(row[7].toString());// wearhouse
			content.add(entity);
		}
		result.setContent(content);
		return result;
	}

	@Override
	public void delteGoodsByRlId(String id) {
		String hql = " delete from StockGoods where  resourceListId=:resourceListId ";
		Map<String, String> param = new HashMap<String, String>();
		param.put("resourceListId", id);
		super.updateHQL(hql, param);
	}
}
