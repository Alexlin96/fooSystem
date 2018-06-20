package com.pure.common;

import java.util.ArrayList;
import java.util.List;
/**
 * 通用分页模型
 * @author 
 *
 */
public class PageInfo {
	/**
	 * 总条数
	 */
	private int total;
	/**
	 * 总页数
	 */
	private int pageTotal;
	/**
	 * 当前页
	 */
	private int currPageNo;
	/**
	 * 每页条数
	 */
	private int pageSize;
	/**
	 * 当前页详细数据
	 */
	private List rows = new ArrayList();

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public int getCurrPageNo() {
		return currPageNo;
	}

	public void setCurrPageNo(int currPageNo) {
		this.currPageNo = currPageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "PageInfo [total=" + total + ", pageTotal=" + pageTotal
				+ ", currPageNo=" + currPageNo + ", pageSize=" + pageSize
				+ ", rows=" + rows + "]";
	}

	
}
