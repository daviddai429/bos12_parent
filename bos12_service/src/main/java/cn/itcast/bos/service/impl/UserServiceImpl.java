package cn.itcast.bos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.IUserDao;
import cn.itcast.bos.domain.Role;
import cn.itcast.bos.domain.User;
import cn.itcast.bos.service.IUserService;
import cn.itcast.bos.utils.BosUtils;
import cn.itcast.bos.utils.MD5Utils;
import cn.itcast.bos.utils.PageBean;
/**
 * 用户模块  业务层
 * @author admin
 *
 */
@Service
@Transactional 
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userDao;
	
	@Override
	public User findByUserNameAndPassword(User model) {
		model.setPassword(MD5Utils.md5(model.getPassword()));
		return userDao.findByUserNameAndPassword(model);
	}

	@Override
	public void editPassword(String password) {
		User user = BosUtils.getUser();
		user.setPassword(MD5Utils.md5(password));
		userDao.update(user);
	}

	@Override
	public void save(String[] roleIds, User user) {
		//保存用户表
		//密码加密
		user.setPassword(MD5Utils.md5(user.getPassword()));
		userDao.save(user);
		//通过用户对象关联角色
		if(roleIds != null && roleIds.length > 0){
			//循环遍历角色id
			for (String roleId : roleIds) {
				//创建一个role托管对象
				Role role = new Role();
				role.setId(roleId);
				//通过用户关联更新用户角色关系
				user.getRoles().add(role);
			}
		}
		
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		userDao.pageQuery(pageBean);
	}
}
