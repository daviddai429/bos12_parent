package cn.itcast.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.ISubareaDao;
import cn.itcast.bos.domain.Subarea;
import cn.itcast.bos.service.ISubareaService;
import cn.itcast.bos.utils.PageBean;
/**
 * 分区模块  业务层
 * @author admin
 *
 */
@Service
@Transactional 
public class SubareaServiceImpl implements ISubareaService {
	@Resource
	private ISubareaDao subareaDao;
	@Override
	public void save(Subarea subarea) {
		subareaDao.save(subarea);
	}
	@Override
	public void pageQuery(PageBean pageBean) {
		subareaDao.pageQuery(pageBean);
	}
	@Override
	public List<Subarea> findAll() {
		return subareaDao.findAll();
	}
	@Override
	public List<Subarea> listajax() {
		//离线对象
		DetachedCriteria dc = DetachedCriteria.forClass(Subarea.class);
		dc.add(Restrictions.isNull("decidedzone"));
		return subareaDao.findByCriteria(dc);
	}
	@Override
	public List<Subarea> findSubareaByDecidedzoneId(String did) {
		DetachedCriteria dc = DetachedCriteria.forClass(Subarea.class);
		dc.add(Restrictions.eq("decidedzone.id", did));
		return subareaDao.findByCriteria(dc);
	}

}
