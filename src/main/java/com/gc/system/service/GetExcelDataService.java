package com.gc.system.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gc.system.entity.HotsalesGoods;
import com.gc.system.entity.StockGoods;
import com.gc.system.exceptions.BussinessException;

/**
 */
public interface GetExcelDataService {

	public List<StockGoods> getStockGoodsFromImportExcel(MultipartFile mpf)
			throws BussinessException;

	public List<HotsalesGoods> getHotsalesGoodsFromImportExcel(MultipartFile mpf);

}
