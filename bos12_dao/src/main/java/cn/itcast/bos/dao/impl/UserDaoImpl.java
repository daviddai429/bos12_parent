package cn.itcast.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.bos.dao.IUserDao;
import cn.itcast.bos.dao.base.impl.BaseDaoImpl;
import cn.itcast.bos.domain.User;
/**
 * 用户模块  持久层
 * @author admin
 *
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	@Override
	public User findByUserNameAndPassword(User model) {
		String hql ="FROM User WHERE username = ? AND password = ?";
		List<User> listUser = (List<User>) this.getHibernateTemplate().find(hql, model.getUsername(),model.getPassword());
		if(listUser != null && listUser.size() > 0){
			return listUser.get(0);
		}
		return null;
	}
	
	
	@Override
	public User findUserByUsername(String username) {
		String hql ="FROM User WHERE username = ?";
		List<User> listUser = (List<User>) this.getHibernateTemplate().find(hql, username);
		if(listUser != null && listUser.size() > 0){
			return listUser.get(0);
		}
		return null;
	}

}
