package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.domain.Function;
import cn.itcast.bos.utils.PageBean;

public interface IFunctionService {

	/**
	 * 查询所有的权限菜单
	 * @return
	 */
	public List<Function> findAll();

	/**
	 * 保存权限数据
	 * @param model
	 */
	public void save(Function model);

	/**
	 * 分页查询
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);

	/**
	 * 根据用户id查询菜单数据
	 * @return
	 */
	public List<Function> findMenuByUserId();

}
