package cn.itcast.bos.web.action;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.crm12.client.CrmUtils;
import cn.itcast.bos.crm12.client.Customer;
import cn.itcast.bos.crm12.client.ICustomerService;
import cn.itcast.bos.domain.NoticeBill;
import cn.itcast.bos.service.INoticeBillService;
import cn.itcast.bos.web.action.base.BaseAction;
/**
 * 业务通知单模块  展示层
 * @author admin
 *
 */
@Controller //注解作用：标识作用
@Scope("prototype")//多例
public class NoticeBillAction extends BaseAction<NoticeBill> {
	@Resource
	private INoticeBillService noticeBillService;
	
	//根据手机号码差客户信息
	public String findCustomerByPhone() throws IOException{
		ICustomerService customerService = CrmUtils.getICustomerService();
		Customer customer = customerService.findCustomerByPhone(model.getTelephone());
		this.Object2JSON(new String[]{}, customer);
		return NONE;
	}
	
	//保存业务受理数据并 尝试自动分单
	public String save(){
		noticeBillService.save(model);
		return LIST;
	}
}
