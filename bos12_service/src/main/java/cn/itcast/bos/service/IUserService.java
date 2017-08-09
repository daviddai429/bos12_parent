package cn.itcast.bos.service;

import cn.itcast.bos.domain.User;
import cn.itcast.bos.utils.PageBean;
/**
 * 用户模块  业务层
 * @author admin
 *
 */
public interface IUserService {

	public User findByUserNameAndPassword(User model);

	/**
	 * 修改用户密码
	 * @param password
	 */
	public void editPassword(String password);

	/**
	 * 保存用户
	 * @param roleIds 角色ids
	 * @param model 用户对象
	 */
	public void save(String[] roleIds, User model);

	/**
	 * 用户分页查询
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);

}
