package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.domain.Role;

public interface IRoleService {

	/**
	 * 新增角色
	 * @param model
	 * @param functionIds
	 */
	public void save(Role role, String functionIds);

	/**
	 * 查询所有角色数据
	 * @return
	 */
	public List<Role> listajax();

}
