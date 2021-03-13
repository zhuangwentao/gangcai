package com.gc.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gc.system.dao.HotsalesGoodsDao;
import com.gc.system.entity.HotsalesGoods;
import com.gc.system.service.HotsaleService;

/**
 * 
 * @author zhuang_wt
 *
 */
@Service("hotsaleService")
public class HotsaleServiceImpl implements HotsaleService {

	@Autowired
	private HotsalesGoodsDao hotsalesGoodsDao;

	@Override
	public void addHotsaleList(List<HotsalesGoods> hotsalesGoodsList) {
		for (HotsalesGoods hs : hotsalesGoodsList) {
			hotsalesGoodsDao.save(hs);
		}
	}

	@Override
	public void deleteHotsalesGoods(Integer id) {
		HotsalesGoods hotsalesGoods = hotsalesGoodsDao.findById(id);
		if (hotsalesGoods != null) {
			hotsalesGoodsDao.remove(hotsalesGoods);
		}
	}

}
