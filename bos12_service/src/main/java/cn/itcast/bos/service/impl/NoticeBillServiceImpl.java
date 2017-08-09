package cn.itcast.bos.service.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.crm12.client.CrmUtils;
import cn.itcast.bos.crm12.client.ICustomerService;
import cn.itcast.bos.dao.IDecidedzoneDao;
import cn.itcast.bos.dao.INoticeBillDao;
import cn.itcast.bos.dao.IWorkBillDao;
import cn.itcast.bos.domain.Decidedzone;
import cn.itcast.bos.domain.NoticeBill;
import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.domain.User;
import cn.itcast.bos.domain.WorkBill;
import cn.itcast.bos.service.INoticeBillService;
import cn.itcast.bos.utils.BosUtils;
/**
 * 业务通知单模块 业务层
 * 
 * @author admin
 *
 */
@Service
@Transactional
public class NoticeBillServiceImpl implements INoticeBillService {

	@Resource
	private INoticeBillDao noticeBillDao;
	
	@Resource
	private IDecidedzoneDao decidedzoneDao;
	
	@Resource
	private IWorkBillDao workBillDao;
	
	@Override
	public void save(NoticeBill noticeBill) {
		//获取session中user对象 ，在关联业务受理对象
		User user = BosUtils.getUser();
		noticeBill.setUser(user);
		//保存业务受理单表数据
		noticeBillDao.save(noticeBill);
		
		//根据页面取件地址 查询定区id
		ICustomerService customerService = CrmUtils.getICustomerService();
		String decidedzoneId = customerService.findDecidedzoneIdByAddress(noticeBill.getPickaddress());
		//根据定区id判断是否为空
		if(StringUtils.isNotBlank(decidedzoneId)){
			//如果定区id不为空，自动分单
			//自动分单：根据定区id到定区表查询取派员id
			Decidedzone decidedzone = decidedzoneDao.findById(decidedzoneId);
			Staff staff = decidedzone.getStaff();
			//生成工单
			WorkBill workBill = new WorkBill();
			workBill.setAttachbilltimes(1);
			workBill.setBuildtime(new Timestamp(System.currentTimeMillis()));
			workBill.setNoticeBill(noticeBill);
			workBill.setPickstate(WorkBill.PICKSTATE_1);
			workBill.setRemark(noticeBill.getRemark());
			workBill.setStaff(staff);
			workBill.setType(WorkBill.TYPE_1);
			workBillDao.save(workBill);
			
			//通过业务受理表持久状态的实体类 关联更新取派员
			noticeBill.setStaff(staff);
			noticeBill.setOrdertype(NoticeBill.ORDERTYPE_AUTO);
			//通过工单表持久状态的实体类 关联更新取派员
			//发短信
			//.................
		}
		else
		{
			//如果定区id为空,说明只能手动分单
			noticeBill.setOrdertype(NoticeBill.ORDERTYPE_MAN);
		}
	}

}
