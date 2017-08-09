package cn.itcast.bos.dao;

import java.util.List;

import cn.itcast.bos.dao.base.IBaseDao;
import cn.itcast.bos.domain.Function;

public interface IFunctionDao extends IBaseDao<Function> {

	/**
	 * 根据用户查询权限数据
	 * @param id
	 * @return
	 */
	public List<Function> findFunctionByUserId(String id);

	/**
	 * 根据用户id查询菜单数据
	 * @param id
	 * @return
	 */
	public List<Function> findMenuByUserId(String id);

}
