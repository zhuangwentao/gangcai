package com.gc.system.service;

import java.util.List;

import com.gc.system.entity.HotsalesGoods;

/**
 */
public interface HotsaleService {

	public void addHotsaleList(List<HotsalesGoods> hotsalesGoodsList);

	public void deleteHotsalesGoods(Integer id);

}
