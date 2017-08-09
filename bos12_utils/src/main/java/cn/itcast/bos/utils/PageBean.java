package cn.itcast.bos.utils;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 分页工具类
 * 
 * @author admin
 *
 */
public class PageBean {
	// 传入参数：当前页码
	private Integer currPage;
	// 传入参数：当前每页显示的记录数
	private Integer pageSize;
	// 回传前台：总的记录数
	private Integer total;
	// 回传前台：总记录数据
	private List rows;
	// 查询对象
	private DetachedCriteria detachedCriteria;

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}

	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}

}
