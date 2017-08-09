package cn.itcast.bos.dao;

import cn.itcast.bos.dao.base.IBaseDao;
import cn.itcast.bos.domain.User;
/**
 * 用户模块  持久层
 * @author admin
 *
 */
public interface IUserDao extends IBaseDao<User> {

	/**
	 * 查询用户
	 * @param model 用户实体对象
	 * @return 用户
	 */
	public User findByUserNameAndPassword(User model);

	/**
	 * 根据用户名查询用户对象
	 * @param username
	 * @return
	 */
	public User findUserByUsername(String username);

}
