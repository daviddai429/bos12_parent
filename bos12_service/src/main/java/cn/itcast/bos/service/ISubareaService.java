package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.domain.Subarea;
import cn.itcast.bos.utils.PageBean;

public interface ISubareaService {

	/**
	 * 保存分区数据
	 * @param model
	 */
	public void save(Subarea model);

	/**
	 * 分页分页查询
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);

	/**
	 * 查询所有的分区数据
	 * @return
	 */
	public List<Subarea> findAll();

	/**
	 * 查询未关联定区的数据
	 * @return
	 */
	public List<Subarea> listajax();

	/**
	 * 根据定区id查询分区数据
	 * @param did
	 * @return
	 */
	public List<Subarea> findSubareaByDecidedzoneId(String did);

}
