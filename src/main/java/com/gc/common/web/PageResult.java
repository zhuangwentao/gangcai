/**
 * Project Name:***
 * File Name:PageResult.java
 * Package Name:com.wenxing.constants
 * Date:Jun 24, 20153:36:30 PM
*/

package com.gc.common.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:PageResult <br/>
 * @author   zhuangwentao
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class PageResult<T> implements Serializable{

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = -7304354915781923765L;

	
	/**当前页索引  默认值为:0*/
	private int currentIndex = 0;
	/**总记录数 默认值为：0*/
	private int totalCount = 0;
	/**当前页  默认值为：1*/
	private int currentPage = 1;
	/**总页数   默认值为：0*/
	private int totalPage = 0;
	/**每页记录数 默认值为；10条*/
	private int pageSize = 10;
	/**当前页记录的集合对象*/
	private List<T> content = new ArrayList<T>(0);
	/**pageList  [10,15,20,30,40,50,100]*/
	private String pageList="[10,15,20,30,40,50,100]";

	
	/**
	 * 创建一个新的实例 PageResult.
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */

	public PageResult() {
		super();
	}


	/**
	 * @return the currentIndex
	 */
	public int getCurrentIndex() {
		return currentIndex;
	}


	/**
	 * @param currentIndex the currentIndex to set
	 */
	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}


	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}


	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}


	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}


	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}


	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	/**
	 * @return the content
	 */
	public List<T> getContent() {
		return content;
	}


	/**
	 * @param content the content to set
	 */
	public void setContent(List<T> content) {
		this.content = content;
	}


	/**
	 * @return the pageList
	 */
	public String getPageList() {
		return pageList;
	}


	/**
	 * @param pageList the pageList to set
	 */
	public void setPageList(String pageList) {
		this.pageList = pageList;
	}
}

