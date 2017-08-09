package cn.itcast.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.IFunctionDao;
import cn.itcast.bos.domain.Function;
import cn.itcast.bos.domain.User;
import cn.itcast.bos.service.IFunctionService;
import cn.itcast.bos.utils.BosUtils;
import cn.itcast.bos.utils.PageBean;
/**
 * 权限模块 业务层
 * 
 * @author admin
 *
 */
@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {

	@Resource
	private IFunctionDao functionDao;
	@Override
	public List<Function> findAll() {
		DetachedCriteria dc = DetachedCriteria.forClass(Function.class);
		dc.add(Restrictions.eq("generatemenu", "1"));
		return functionDao.findByCriteria(dc);
	}
	@Override
	public void save(Function function) {
		if(function.getParentFunction() != null && function.getParentFunction().getId().equals(""))
		{
			function.setParentFunction(null);
		}
		functionDao.save(function);
	}
	@Override
	public void pageQuery(PageBean pageBean) {
		functionDao.pageQuery(pageBean);
	}
	@Override
	public List<Function> findMenuByUserId() {
		//先获取用户
		User user = BosUtils.getUser();
		List<Function> listFunctions  = null;
		//判断当前用户是否是admin
		if(user.getUsername().equals("admin")){
			//如果是管理员查询所有菜单
			DetachedCriteria dc = DetachedCriteria.forClass(Function.class);
			//添加查询条件  查询菜单数据条件
			dc.add(Restrictions.eq("generatemenu", "1"));
			//添加排序条件
			dc.addOrder(Order.asc("zindex"));
			listFunctions = functionDao.findByCriteria(dc);
		}
		else
		{
			//如果是普通用户根据id查询菜单数据
			listFunctions = functionDao.findMenuByUserId(user.getId());
		}
		return listFunctions;
	}

}
