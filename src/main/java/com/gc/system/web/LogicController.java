package com.gc.system.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.CacheManager;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gc.common.mapper.CommonConstant;
import com.gc.common.utils.StringUtils;
import com.gc.common.web.BaseController;
import com.gc.common.web.PageResult;
import com.gc.system.entity.HotsalesGoods;
import com.gc.system.entity.ResourceList;
import com.gc.system.entity.StockGoods;
import com.gc.system.entity.User;
import com.gc.system.exceptions.BussinessException;
import com.gc.system.service.GetExcelDataService;
import com.gc.system.service.HotsaleService;
import com.gc.system.service.ResourceListService;
import com.gc.system.utils.DateUtil;
import com.gc.system.utils.PropertyReadUtil;
import com.gc.system.vo.DeleteRlVO;

/**
 * @author zhuang <br>
 *         业务逻辑的controller
 */
@Controller
@RequestMapping("/logic")
public class LogicController extends BaseController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(LogicController.class);

	@Autowired
	private ResourceListService resourceListService;
	@Autowired
	private GetExcelDataService getExcelDataService;
	@Autowired
	private CacheManager cacheManager;
	@Autowired
	private HotsaleService hotsaleService;

	/**
	 * 资源单页面查询资源单<br>
	 * 分页查询
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param resourceList
	 * @return
	 */
	@RequestMapping(value = "resourceListPage", method = RequestMethod.GET)
	@ResponseBody
	public PageResult<ResourceList> queryResourceListListForPage(
			Integer currentPage, Integer pageSize, ResourceList resourceList) {

		currentPage = (currentPage - 1) * pageSize;// 分页查询是按起始序号

		PageResult<ResourceList> result = null;
		try {
			result = this.resourceListService.findResourceListListForpage(
					currentPage, pageSize, resourceList);
		} catch (Exception e) {
			LOGGER.error("资源单页面查询资源单失败", e);
			return result;
		}
		return result;
	}

	/**
	 * 搜现货页面查询<br>
	 * 分页查询
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param resourceList
	 * @return
	 */
	@RequestMapping(value = "goodsPage", method = RequestMethod.GET)
	@ResponseBody
	public PageResult<StockGoods> queryGoodsListForPage(Integer currentPage,
			Integer pageSize, StockGoods stockGoods) {

		currentPage = (currentPage - 1) * pageSize;// 分页查询是按起始序号

		PageResult<StockGoods> result = null;
		try {
			result = this.resourceListService.findGoodsListForPage(currentPage,
					pageSize, stockGoods);
		} catch (Exception e) {
			LOGGER.error("资源单页面查询资源单失败", e);
			return result;
		}
		return result;
	}

	/**
	 * 上传资源单。
	 * 
	 */
	@RequiresRoles("user")
	@RequestMapping(value = "uploadRl", method = RequestMethod.POST)
	@ResponseBody
	public String uploadResourceList(MultipartHttpServletRequest request,
			HttpServletResponse response) {

		String result = "添加成功";
		// 得到页面参数
		ResourceList resourceList = new ResourceList();
		resourceList.setCompany(request.getParameter("company"));
		String discription = request.getParameter("description");
		discription = StringUtils.convertHtmlToDb(discription);
		resourceList.setDescription(discription);
		resourceList.setClassification(request.getParameter("classification"));
		resourceList.setMainVarieties(request.getParameter("mainVarieties"));
		resourceList.setMainSteelfactory(request
				.getParameter("mainSteelfactory"));
		resourceList.setArea(request.getParameter("area"));
		resourceList.setUploderTime(new Date());
		// shiro来得到用户 id
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(
				CommonConstant.USER_SESSION);

		resourceList.setId(user.getId()
				+ String.valueOf(resourceList.getUploderTime().getTime()));
		resourceList.setUploderUser(user.getId());// 存储用户id

		// 得到资源单文件内的内容
		List<StockGoods> goodslist = new ArrayList<StockGoods>();
		try {
			Iterator<String> itr = request.getFileNames();
			if (itr != null && itr.hasNext()) {
				MultipartFile mpf = request.getFile(itr.next());

				if (mpf != null) {
					// 读取excel数据到list
					goodslist = getExcelDataService
							.getStockGoodsFromImportExcel(mpf);

					// 写入数据库地址格式
					// 生成存放路径，约定放在rootpath()\\resource\\uploadFiles\\
					// 命名格式为:用户id_日期时间戳.xlsx
					// String path = "\\resource\\uploadFiles\\resourceList\\";
					String fileName = user.getId().toString()
							+ "_"
							+ DateUtil.dateToString(
									resourceList.getUploderTime(),
									"yyyyMMddHHmmss") + ".xls";// 没加扩展后缀
					String fileName2 = user.getId().toString()
							+ "_"
							+ DateUtil.dateToString(
									resourceList.getUploderTime(),
									"yyyyMMddHHmmss");// 没加扩展后缀

					String realPath = PropertyReadUtil.getPropertiesByKeyName(
							"application.properties", "uploadRlfolder");
					File pathFile = new File(realPath);
					if (!pathFile.exists()) {
						// 文件夹不存 创建文件
						pathFile.mkdirs();
					}

					String url = realPath + "/" + fileName;
					resourceList.setUrl(fileName2);// 数据库中只保存文件名
					// 将文件保存到服务器指定地址
					File dest = new File(url);
					mpf.transferTo(dest);
				}
			}
		} catch (BussinessException e1) {
			result = e1.getMessage();
			return result;
		} catch (Exception e) {
			LOGGER.info("读取excel出错", e);
		}
		// 将读取得到的数据存入数据库
		try {
			resourceListService.addResourceList(resourceList);
		} catch (Exception e1) {
			LOGGER.info("插入资源单失败", e1);
			result = "资源单数据插入失败";
		}
		try {
			resourceListService.addStockGoods(resourceList, goodslist);
		} catch (Exception e) {
			LOGGER.info("现货单失败", e);
		}

		return result;
	}

	/**
	 * 上传今日特卖单。
	 * 
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = "uploadHs", method = RequestMethod.POST)
	@ResponseBody
	public String uploadHotsale(MultipartHttpServletRequest request,
			HttpServletResponse response) {
		String result = "添加成功";
		// 得到资源单文件内的内容
		List<HotsalesGoods> hotsalesGoodsList = new ArrayList<HotsalesGoods>();
		try {
			Iterator<String> itr = request.getFileNames();
			MultipartFile mpf = request.getFile(itr.next());
			if (mpf != null) {
				// 读取excel数据到list
				hotsalesGoodsList = getExcelDataService
						.getHotsalesGoodsFromImportExcel(mpf);
			}
		} catch (BussinessException e1) {
			result = e1.getMessage();
			return result;
		} catch (Exception e) {
			LOGGER.info("读取excel出错", e);
		}
		// 将读取得到的数据存入数据库
		try {
			hotsaleService.addHotsaleList(hotsalesGoodsList);
		} catch (Exception e1) {
			LOGGER.info("插入热卖单失败", e1);
			result = "热卖单数据插入失败";
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "deleteRl", method = RequestMethod.DELETE)
	public String deleteRl(HttpServletRequest request, DeleteRlVO deleteRlVO) {
		try {
			resourceListService.deleteResourceList(deleteRlVO.getUrl(),
					deleteRlVO.getId());

			String realPath = PropertyReadUtil.getPropertiesByKeyName(
					"application.properties", "uploadRlfolder");
			String fileName = deleteRlVO.getUrl() + ".xls";
			// 获取下载文件路径
			String deletePath = realPath + "/" + fileName;

			try {
				File file = new File(deletePath);
				// 路径为文件且不为空则进行删除
				if (file.isFile() && file.exists()) {
					file.delete();
				}
			} catch (Exception e) {
				// 文件删除的异常捕获，不做处理
				LOGGER.error("删除资源单文件失败:" + deletePath, e);
			}

		} catch (Exception e) {
			LOGGER.error("删除资源单信息失败", e);
			return "fail";
		}
		return "success";
	}

	/**
	 * 今日热卖查询<br>
	 * 分页查询
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param resourceList
	 * @return
	 */
	@RequestMapping(value = "hsGoodsPage", method = RequestMethod.GET)
	@ResponseBody
	public PageResult<HotsalesGoods> queryHsGoodsListForPage(
			Integer currentPage, Integer pageSize, HotsalesGoods hotsalesGoods) {

		currentPage = (currentPage - 1) * pageSize;// 分页查询是按起始序号

		PageResult<HotsalesGoods> result = null;
		try {
			result = this.resourceListService.findHsGoodsListForPage(
					currentPage, pageSize, hotsalesGoods);
		} catch (Exception e) {
			LOGGER.error("资源单页面查询资源单失败", e);
			return result;
		}
		return result;
	}

	@RequestMapping(value = "hsGoodsPageForClient", method = RequestMethod.GET)
	@ResponseBody
	public PageResult<HotsalesGoods> queryHsGoodsPageForClient(
			Integer currentPage, Integer pageSize, HotsalesGoods hotsalesGoods) {

		currentPage = (currentPage - 1) * pageSize;// 分页查询是按起始序号

		PageResult<HotsalesGoods> result = null;
		try {
			result = this.resourceListService.queryHsGoodsPageForClient(
					currentPage, pageSize, hotsalesGoods);
		} catch (Exception e) {
			LOGGER.error("资源单页面查询资源单失败", e);
			return result;
		}
		return result;
	}

	@RequiresRoles("admin")
	@ResponseBody
	@RequestMapping(value = "deleteHs", method = RequestMethod.DELETE)
	public String deleteHs(HotsalesGoods hotsalesGoods) {
		try {
			hotsaleService.deleteHotsalesGoods(hotsalesGoods.getId());
		} catch (Exception e) {
			LOGGER.error("删除hotsale信息失败", e);
			return "fail";
		}
		return "success";
	}

	/**
	 * 资源单下载功能
	 */
	@RequestMapping(value = "downloadRl/{fileName}")
	public String downloadRl(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("fileName") String fileName) throws Exception {
		String contentType = "application/octet-stream";
		try {
			return this.download(request, response, fileName, contentType);
		} catch (Exception e) {
			LOGGER.info("下载失败", e);
		}
		return null;
	}

	// 文件下载 主要方法
	public String download(HttpServletRequest request,
			HttpServletResponse response, String fileName, String contentType)
			throws Exception {
		fileName = fileName + ".xls";
		String realPath = PropertyReadUtil.getPropertiesByKeyName(
				"application.properties", "uploadRlfolder");
		// 获取下载文件路径
		String downLoadPath = realPath + "/" + fileName;
		long fileLength = new File(downLoadPath).length();
		// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
		response.setContentType("application/x-xls;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
		response.setHeader("Content-Length", String.valueOf(fileLength));
		ServletOutputStream out = response.getOutputStream();
		// 通过文件路径获得File对象(假如此路径中有一个download.pdf文件)
		File file = new File(downLoadPath);
		if (file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				out = response.getOutputStream();
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = inputStream
						.read(buff, 0, buff.length))) {
					out.write(buff, 0, bytesRead);
				}
				inputStream.close();
				out.close();
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			return "redirect:../../system/404"; 
		}
		return null;
	}
}
