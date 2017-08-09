package cn.itcast.bos.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.bos.dao.IStaffDao;
import cn.itcast.bos.dao.base.impl.BaseDaoImpl;
import cn.itcast.bos.domain.Staff;
/**
 * 取派员模块  持久层
 * @author admin
 *
 */
@Repository
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements IStaffDao {

}
