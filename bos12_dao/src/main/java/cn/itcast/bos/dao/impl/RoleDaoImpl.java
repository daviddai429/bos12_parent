package cn.itcast.bos.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.bos.dao.IRoleDao;
import cn.itcast.bos.dao.base.impl.BaseDaoImpl;
import cn.itcast.bos.domain.Role;
/**
 * 角色模块  持久层
 * @author admin
 *
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements IRoleDao {

}
