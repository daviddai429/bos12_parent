package cn.itcast.bos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.IWorkOrderManageDao;
import cn.itcast.bos.domain.WorkOrderManage;
import cn.itcast.bos.service.IWorkOrderManageService;
/**
 * 工作单快速录入模块 业务层
 * 
 * @author admin
 *
 */
@Service
@Transactional
public class WorkOrderManageServiceImpl implements IWorkOrderManageService {

	@Resource
	private IWorkOrderManageDao workOrderManageDao;
	
	@Override
	public void save(WorkOrderManage model) {
		workOrderManageDao.save(model);
	}

}
