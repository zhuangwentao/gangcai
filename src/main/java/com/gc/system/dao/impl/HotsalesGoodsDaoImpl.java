package com.gc.system.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gc.common.persistence.impl.BaseDaoImpl;
import com.gc.common.web.PageResult;
import com.gc.system.dao.HotsalesGoodsDao;
import com.gc.system.entity.HotsalesGoods;
import com.gc.system.utils.DateUtil;

/**
 * @author zhuang
 */
@Repository("hotsalesGoodsDao")
public class HotsalesGoodsDaoImpl extends BaseDaoImpl<HotsalesGoods> implements
		HotsalesGoodsDao {

	@Override
	public PageResult<HotsalesGoods> findHsGoodsListForPage(
			Integer currentPage, Integer pageSize, HotsalesGoods hotsalesGoods) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select product_name,specifications,material,origin,area,num,wearhouse,company,price,description,effective_date,id");
		sql.append(" from hotsales_goods ");
		sql.append(" where product_name like :productName ");
		sql.append(" and specifications like :specifications ");
		sql.append(" and material like :material ");
		sql.append(" and origin like :origin ");
		sql.append(" and area like :area ");
		sql.append(" and wearhouse like :wearhouse ");
		sql.append(" and company like :company ");
		// sql.append(" and substring(effective_date,1,10) = substring(NOW(),1,10) ");

		if (hotsalesGoods.getPrice() != null
				&& Double.valueOf("0").compareTo(hotsalesGoods.getPrice()) < 0) {
			sql.append(" and price >= :price ");
			map.put("price", hotsalesGoods.getPrice());
		}
		if (hotsalesGoods.getPriceEnd() != null
				&& Double.valueOf("0").compareTo(hotsalesGoods.getPriceEnd()) < 0) {
			sql.append(" and price <= :priceEnd ");
			map.put("priceEnd", hotsalesGoods.getPriceEnd());
		}
		if (hotsalesGoods.getEffectiveDateStr() != null
				&& !"".equals(hotsalesGoods.getEffectiveDateStr().trim())) {
			sql.append(" and substring(effective_date,1,10) = :effectiveDateStr ");
			map.put("effectiveDateStr", hotsalesGoods.getEffectiveDateStr());
		}

		sql.append("order by effective_date desc ");
		String hqlCount = " select count(*) from ( " + sql.toString() + ")aa";

		map.put("productName", "%"
				+ (hotsalesGoods.getProductName() == null ? "" : hotsalesGoods
						.getProductName().trim()) + "%");
		map.put("specifications", "%"
				+ (hotsalesGoods.getSpecifications() == null ? ""
						: hotsalesGoods.getSpecifications().trim()) + "%");
		map.put("material", "%"
				+ (hotsalesGoods.getMaterial() == null ? "" : hotsalesGoods
						.getMaterial().trim()) + "%");
		map.put("origin", "%"
				+ (hotsalesGoods.getOrigin() == null ? "" : hotsalesGoods
						.getOrigin().trim()) + "%");
		map.put("area", "%"
				+ (hotsalesGoods.getArea() == null ? "" : hotsalesGoods
						.getArea().trim()) + "%");
		map.put("wearhouse", "%"
				+ (hotsalesGoods.getWearhouse() == null ? "" : hotsalesGoods
						.getWearhouse().trim()) + "%");
		map.put("company", "%"
				+ (hotsalesGoods.getCompany() == null ? "" : hotsalesGoods
						.getCompany().trim()) + "%");

		PageResult<Object[]> list = nativeQuerySQLObject(sql.toString(),
				hqlCount, map, currentPage, pageSize);

		PageResult<HotsalesGoods> result = new PageResult<HotsalesGoods>();
		result.setCurrentIndex(list.getCurrentIndex());
		result.setCurrentPage(list.getCurrentPage());
		result.setPageList(list.getPageList());
		result.setPageSize(list.getPageSize());
		result.setTotalCount(list.getTotalCount());
		result.setTotalPage(list.getTotalPage());

		List<HotsalesGoods> content = new ArrayList<HotsalesGoods>();
		HotsalesGoods entity = null;
		for (Object[] row : list.getContent()) {
			entity = new HotsalesGoods();
			entity.setProductName(row[0].toString());// product_name
			entity.setSpecifications(row[1].toString());// specifications
			entity.setMaterial(row[2].toString());// material
			entity.setOrigin(row[3].toString());// origin
			entity.setArea(row[4].toString());// area
			entity.setNum(Double.valueOf(row[5].toString()));// num
			entity.setWearhouse(row[6].toString());// wearhouse
			entity.setCompany(row[7].toString());// company
			entity.setPrice(Double.valueOf(row[8].toString()));// price
			entity.setDescription(row[9].toString());// description
			entity.setEffectiveDate(DateUtil.stringToDate(row[10].toString(),
					"yyyy-MM-dd"));// effective_date
			entity.setId(Integer.valueOf(row[11].toString()));// id
			content.add(entity);
		}
		result.setContent(content);
		return result;

	}

	@Override
	public PageResult<HotsalesGoods> findHsGoodsListForPageForClient(
			Integer currentPage, Integer pageSize, HotsalesGoods hotsalesGoods) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select product_name,specifications,material,origin,area,num,wearhouse,company,price,description,effective_date,id");
		sql.append(" from hotsales_goods ");
		sql.append(" where product_name like :productName ");
		sql.append(" and specifications like :specifications ");
		sql.append(" and material like :material ");
		sql.append(" and origin like :origin ");
		sql.append(" and area like :area ");
		sql.append(" and wearhouse like :wearhouse ");
		sql.append(" and company like :company ");
		sql.append(" and substring(effective_date,1,10) = substring(NOW(),1,10) ");

		if (hotsalesGoods.getPrice() != null
				&& Double.valueOf("0").compareTo(hotsalesGoods.getPrice()) < 0) {
			sql.append(" and price >= :price ");
			map.put("price", hotsalesGoods.getPrice());
		}
		if (hotsalesGoods.getPriceEnd() != null
				&& Double.valueOf("0").compareTo(hotsalesGoods.getPriceEnd()) < 0) {
			sql.append(" and price <= :priceEnd ");
			map.put("priceEnd", hotsalesGoods.getPriceEnd());
		}
		sql.append("order by effective_date desc ");
		String hqlCount = " select count(*) from ( " + sql.toString() + ")aa";

		map.put("productName", "%"
				+ (hotsalesGoods.getProductName() == null ? "" : hotsalesGoods
						.getProductName().trim()) + "%");
		map.put("specifications", "%"
				+ (hotsalesGoods.getSpecifications() == null ? ""
						: hotsalesGoods.getSpecifications().trim()) + "%");
		map.put("material", "%"
				+ (hotsalesGoods.getMaterial() == null ? "" : hotsalesGoods
						.getMaterial().trim()) + "%");
		map.put("origin", "%"
				+ (hotsalesGoods.getOrigin() == null ? "" : hotsalesGoods
						.getOrigin().trim()) + "%");
		map.put("area", "%"
				+ (hotsalesGoods.getArea() == null ? "" : hotsalesGoods
						.getArea().trim()) + "%");
		map.put("wearhouse", "%"
				+ (hotsalesGoods.getWearhouse() == null ? "" : hotsalesGoods
						.getWearhouse().trim()) + "%");
		map.put("company", "%"
				+ (hotsalesGoods.getCompany() == null ? "" : hotsalesGoods
						.getCompany().trim()) + "%");

		PageResult<Object[]> list = nativeQuerySQLObject(sql.toString(),
				hqlCount, map, currentPage, pageSize);

		PageResult<HotsalesGoods> result = new PageResult<HotsalesGoods>();
		result.setCurrentIndex(list.getCurrentIndex());
		result.setCurrentPage(list.getCurrentPage());
		result.setPageList(list.getPageList());
		result.setPageSize(list.getPageSize());
		result.setTotalCount(list.getTotalCount());
		result.setTotalPage(list.getTotalPage());

		List<HotsalesGoods> content = new ArrayList<HotsalesGoods>();
		HotsalesGoods entity = null;
		for (Object[] row : list.getContent()) {
			entity = new HotsalesGoods();
			entity.setProductName(row[0].toString());// product_name
			entity.setSpecifications(row[1].toString());// specifications
			entity.setMaterial(row[2].toString());// material
			entity.setOrigin(row[3].toString());// origin
			entity.setArea(row[4].toString());// area
			entity.setNum(Double.valueOf(row[5].toString()));// num
			entity.setWearhouse(row[6].toString());// wearhouse
			entity.setCompany(row[7].toString());// company
			entity.setPrice(Double.valueOf(row[8].toString()));// price
			entity.setDescription(row[9].toString());// description
			entity.setEffectiveDate(DateUtil.stringToDate(row[10].toString(),
					"yyyy-MM-dd"));// effective_date
			entity.setId(Integer.valueOf(row[11].toString()));// id
			content.add(entity);
		}
		result.setContent(content);
		return result;

	}
}
