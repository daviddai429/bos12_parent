package cn.itcast.bos.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.apache.bcel.classfile.Constant;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import cn.itcast.bos.dao.IStaffDao;
import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.service.IStaffService;
import cn.itcast.bos.utils.Contants;
import cn.itcast.bos.utils.PageBean;
/**
 * 取派员模块  业务层
 * @author admin
 *
 */
@Service
@Transactional 
public class StaffServiceImpl implements IStaffService {

	@Resource
	private IStaffDao staffDao;
	@Override
	public void save(Staff model) {
		staffDao.save(model);
	}
	@Override
	public void pageQuery(PageBean pageBean) {
		staffDao.pageQuery(pageBean);
	}
	@Override
	public void delete(String ids) throws SQLException {
		//ids多个取派员
		if(StringUtils.isNotBlank(ids)){
			String[] staffIds = ids.split(",");
			//循环取派员编号
			for (String staffId : staffIds) {
				//通过命名查询条件执行删除 
				staffDao.executeQueryName("staff.delete",staffId);
			}
		}
	} 
	
	@Override
	public void update(Staff staff) {
		staffDao.update(staff);
	}
	@Override
	public Staff findById(String id) {
		return staffDao.findById(id);
	}
	@Override
	public List<Staff> listajax() {
		//离线查询对象
		DetachedCriteria dc =DetachedCriteria.forClass(Staff.class);
		//添加未离职的查询条件
		dc.add(Restrictions.eq("deltag", Contants.ZERO));
		//查询
		return staffDao.findByCriteria(dc);
	}

}
