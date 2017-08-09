package cn.itcast.bos.service;

import java.io.File;
import java.util.List;

import cn.itcast.bos.domain.Region;
import cn.itcast.bos.utils.PageBean;

public interface IRegionService {

	/**
	 * 区域导入
	 * @param regionFile
	 */
	public void importXls(File regionFile);

	/**
	 * 分页查询
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);

	/**
	 * 查询所有区域数据
	 * @return
	 */
	public List<Region> findAll();

	/**
	 * 根据q查询区域数据
	 * @param q
	 * @return
	 */
	public List<Region> findByQ(String q);

}
