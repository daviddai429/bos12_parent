package cn.itcast.bos.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.IDecidedzoneDao;
import cn.itcast.bos.dao.ISubareaDao;
import cn.itcast.bos.domain.Decidedzone;
import cn.itcast.bos.domain.Subarea;
import cn.itcast.bos.service.IDecidedzoneService;
import cn.itcast.bos.utils.PageBean;
/**
 * 定区模块 业务层
 * 
 * @author admin
 *
 */
@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedzoneService {

	@Resource
	private IDecidedzoneDao decidedzoneDao;
	
	@Resource
	private ISubareaDao subareaDao;
	
	@Override
	public void save(Decidedzone decidedzone, String[] subareaids) {
		//保存定区表
		decidedzoneDao.save(decidedzone);//decidedzone持久对象
		//关联分区表
		if(subareaids != null && subareaids.length > 0){
			//循环遍历分区id
			for (String subareaid : subareaids) {
				//更新分区表 定区id字段
				Subarea subarea =  subareaDao.findById(subareaid);
				subarea.setDecidedzone(decidedzone);
			}
		}
		
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		decidedzoneDao.pageQuery(pageBean);
	}

}
