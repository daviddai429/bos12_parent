package cn.itcast.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.IRoleDao;
import cn.itcast.bos.domain.Function;
import cn.itcast.bos.domain.Role;
import cn.itcast.bos.service.IRoleService;
/**
 * 角色模块 业务层
 * 
 * @author admin
 *
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

	@Resource
	private IRoleDao roleDao;
	
	@Override
	public void save(Role role, String functionIds) {
		//保存角色
		roleDao.save(role);//持久对象
		//保存角色权限关系表
		//判断权限ids是否为空
		if(StringUtils.isNotBlank(functionIds)){
			//split functionIds
			String[] ids = functionIds.split(",");//所有的权限ids
			for (String id : ids) {
				//循环遍历权限id
				//角色关联权限id
				//创建一个权限托管对象 
				Function function = new Function();
				function.setId(id);
				//通过hibernate特性自动关联更新权限角色关系表
				role.getFunctions().add(function);
			}
		}
	}

	@Override
	public List<Role> listajax() {
		return roleDao.findAll();
	}

}
