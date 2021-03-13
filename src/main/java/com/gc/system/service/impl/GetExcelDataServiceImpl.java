package com.gc.system.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gc.common.utils.StringUtils;
import com.gc.system.entity.HotsalesGoods;
import com.gc.system.entity.StockGoods;
import com.gc.system.exceptions.BussinessException;
import com.gc.system.service.GetExcelDataService;

@Service("getExcelDataService")
public class GetExcelDataServiceImpl implements GetExcelDataService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(GetExcelDataServiceImpl.class);

	// 将有可能出现.0的情况删除。
	public String rTrimZero(String src) {

		if (src == null) {
			return src;
		}
		if (src.endsWith(".0")) {
			return src.substring(0, src.length() - 2);
		}
		return src;
	}

	@Override
	public List<StockGoods> getStockGoodsFromImportExcel(MultipartFile mpf)
			throws BussinessException {
		List<StockGoods> result = new ArrayList<StockGoods>();
		try {
			XSSFWorkbook wb = new XSSFWorkbook(mpf.getInputStream());
			// 提取工作表、工作表的行和列
			// 第一个工作表
			XSSFSheet sheet = wb.getSheetAt(0);
			// 每一行循环
			Iterator<Row> rows = sheet.rowIterator();
			// 判断是不是第一行，如果是的话就跳过
			boolean firstRow = true;
			// 每次读取行的索引，抛出错误使用。
			int rowNum = 0;
			while (rows.hasNext()) {
				rowNum++;
				XSSFRow row = (XSSFRow) rows.next();
				// 该行开始没有数据的话直接退出解析
				if (StringUtils.isEmpty(row.getCell(0).getStringCellValue()
						.toString())) {
					break;
				}
				// 标题行跳过处理
				if (firstRow) {
					firstRow = false;
					continue;
				}
				// 取得每一行数据
				StockGoods stockGoods = new StockGoods();
				// System.out.println("Row #" + row.getRowNum());
				// Iterate over each cell in the row and print out the cell"s
				Iterator<Cell> cells = row.cellIterator();
				// 判断列数匹配数据库字段
				int idx = 0;
				try {
					while (cells.hasNext()) {
						idx++;
						XSSFCell cell = (XSSFCell) cells.next();

						Object cellDate = null;
						switch (cell.getCellType()) {
						case XSSFCell.CELL_TYPE_NUMERIC:
							cellDate = cell.getNumericCellValue();
							break;
						case XSSFCell.CELL_TYPE_STRING:
							cellDate = cell.getStringCellValue();
							break;
						case XSSFCell.CELL_TYPE_BOOLEAN:
							cellDate = cell.getBooleanCellValue();
							break;
						case XSSFCell.CELL_TYPE_FORMULA:
							cellDate = cell.getCellFormula();
							break;
						default:
							cellDate = "";
							break;
						}
						if (idx == 1) {
							// 品名
							stockGoods.setProductName(rTrimZero(cellDate
									.toString().trim()));
						} else if (idx == 2) {
							// 材质
							stockGoods.setMaterial(rTrimZero(cellDate
									.toString().trim()));
						} else if (idx == 3) {
							// 规格
							stockGoods.setSpecifications(rTrimZero(cellDate
									.toString().trim()));
						} else if (idx == 4) {
							// 产地
							stockGoods.setOrigin(rTrimZero(cellDate.toString()
									.trim()));
						} else if (idx == 5) {
							// 仓库
							stockGoods.setWearhouse(rTrimZero(cellDate
									.toString().trim()));
						} else if (idx == 6) {
							// 价格
							stockGoods.setPrice(Double
									.valueOf(rTrimZero(cellDate.toString()
											.trim())));
						}
					}
				} catch (Exception e) {
					LOGGER.info("读取资源单内部数据出错：错误信息第" + rowNum + "行。请修改后重试。", e);
					throw new BussinessException("读取资源单内部数据出错：错误信息第" + rowNum
							+ "行。请修改后重试。");
				}
				result.add(stockGoods);
			}
		} catch (IOException e) {
			LOGGER.info("读取资源单文件出错", e);
		} finally {
			try {
				if (mpf != null && mpf.getInputStream() != null) {
					mpf.getInputStream().close();
				}
			} catch (IOException e) {
				LOGGER.info("关闭资源单文件输入流出错", e);
			}
		}
		return result;
	}

	@Override
	public List<HotsalesGoods> getHotsalesGoodsFromImportExcel(MultipartFile mpf) {
		List<HotsalesGoods> result = new ArrayList<HotsalesGoods>();
		try {
			XSSFWorkbook wb = new XSSFWorkbook(mpf.getInputStream());
			// 提取工作表、工作表的行和列
			// 第一个工作表
			XSSFSheet sheet = wb.getSheetAt(0);
			// 每一行循环
			Iterator<Row> rows = sheet.rowIterator();
			// 判断是不是第一行，如果是的话就跳过
			boolean firstRow = true;
			// 每次读取行的索引，抛出错误使用。
			int rowNum = 0;
			while (rows.hasNext()) {
				rowNum++;
				XSSFRow row = (XSSFRow) rows.next();
				// 该行开始没有数据的话直接退出解析
				if (StringUtils.isEmpty(row.getCell(0).getStringCellValue()
						.toString())) {
					break;
				}
				// 标题行跳过处理
				if (firstRow) {
					firstRow = false;
					continue;
				}
				// 取得每一行数据
				HotsalesGoods hotsalesGoods = new HotsalesGoods();
				// System.out.println("Row #" + row.getRowNum());
				// Iterate over each cell in the row and print out the cell"s
				Iterator<Cell> cells = row.cellIterator();
				// 判断列数匹配数据库字段
				int idx = 0;
				try {
					while (cells.hasNext()) {
						idx++;
						XSSFCell cell = (XSSFCell) cells.next();

						Object cellDate = null;
						switch (cell.getCellType()) {
						case XSSFCell.CELL_TYPE_NUMERIC:
							cellDate = cell.getNumericCellValue();
							break;
						case XSSFCell.CELL_TYPE_STRING:
							cellDate = cell.getStringCellValue();
							break;
						case XSSFCell.CELL_TYPE_BOOLEAN:
							cellDate = cell.getBooleanCellValue();
							break;
						case XSSFCell.CELL_TYPE_FORMULA:
							cellDate = cell.getCellFormula();
							break;
						default:
							cellDate = "";
							break;
						}
						if (idx == 1) {
							// 品名 productName
							hotsalesGoods.setProductName(rTrimZero(cellDate
									.toString().trim()));
						} else if (idx == 2) {
							// 规格 specifications
							hotsalesGoods.setSpecifications(rTrimZero(cellDate
									.toString().trim()));
						} else if (idx == 3) {
							// 材质 material
							hotsalesGoods.setMaterial(rTrimZero(cellDate
									.toString().trim()));
						} else if (idx == 4) {
							// 产地 origin
							hotsalesGoods.setOrigin(rTrimZero(cellDate
									.toString().trim()));
						} else if (idx == 5) {
							// 地区 area
							hotsalesGoods.setArea(rTrimZero(cellDate.toString()
									.trim()));
						} else if (idx == 6) {
							// 数量
							hotsalesGoods.setNum(Double
									.valueOf(rTrimZero(cellDate.toString()
											.trim())));
						} else if (idx == 7) {
							// 仓库
							hotsalesGoods.setWearhouse(rTrimZero(cellDate
									.toString().trim()));
						} else if (idx == 8) {
							// 公司
							hotsalesGoods.setCompany(rTrimZero(cellDate
									.toString().trim()));
						} else if (idx == 9) {
							// 价格 price
							hotsalesGoods.setPrice(Double
									.valueOf(rTrimZero(cellDate.toString()
											.trim())));
						} else if (idx == 10) {
							// effectiveDate
							hotsalesGoods.setEffectiveDate(cell
									.getDateCellValue());
						} else if (idx == 11) {
							// description
							String description = rTrimZero(cellDate.toString()
									.trim());
							description = StringUtils
									.convertHtmlToDb(description);
							hotsalesGoods.setDescription(description);
						}
					}
				} catch (Exception e) {
					LOGGER.info("读取热卖单内部数据出错：错误信息第" + rowNum + "行。请修改后重试。", e);
					throw new BussinessException("读取热卖单内部数据出错：错误信息第" + rowNum
							+ "行。请修改后重试。");
				}
				result.add(hotsalesGoods);
			}
		} catch (IOException e) {
			LOGGER.info("读取资源单文件出错", e);
		} finally {
			try {
				if (mpf != null && mpf.getInputStream() != null) {
					mpf.getInputStream().close();
				}
			} catch (IOException e) {
				LOGGER.info("关闭热卖单文件输入流出错", e);
			}
		}
		return result;
	}
}
