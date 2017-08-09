package cn.itcast.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.crm12.client.CrmUtils;
import cn.itcast.bos.crm12.client.Customer;
import cn.itcast.bos.crm12.client.CustomerServiceImplService;
import cn.itcast.bos.crm12.client.ICustomerService;
import cn.itcast.bos.domain.Decidedzone;
import cn.itcast.bos.service.IDecidedzoneService;
import cn.itcast.bos.web.action.base.BaseAction;
/**
 * 定区模块  展示层
 * @author admin
 *
 */
@Controller //注解作用：标识作用
@Scope("prototype")//多例
public class DecidedzoneAction extends BaseAction<Decidedzone> {
	
	//通过属性驱动接收页面上客户ids
	private List<Integer> customerIds;
	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}

	//定一个数组属性驱动接收分区ids
	private String[] subareaids;
	public void setSubareaids(String[] subareaids) {
		this.subareaids = subareaids;
	}

	//
	@Resource
	private IDecidedzoneService decidedzoneService;
	
	public String save(){
		decidedzoneService.save(model,subareaids);
		return LIST;
	}
	
	//分页查询
	public String pageQuery() throws IOException{
		//将客户端打成jar上传maven仓库 调用测试
		/*ICustomerService iCustomerService = CrmUtils.getICustomerService();
		List<Customer> findAll = iCustomerService.findByHasRelation("8a7e86d65d92ed8f015d92ee4d3b0000");
		for (Customer customer : findAll) {
			System.out.println(customer);
		}*/
		decidedzoneService.pageQuery(pageBean);
		this.Object2JSON(new String[]{"subareas","decidedzones"}, pageBean);
		return NONE;
	}
	
	//查询未关联定区的客户数据
	public String findByNoRelation() throws IOException{
		ICustomerService iCustomerService = CrmUtils.getICustomerService();
		List<Customer> findByNoRelation = iCustomerService.findByNoRelation();
		this.Object2JSON(new String[]{}, findByNoRelation);
		return NONE;
	}
	
	//查询未关联定区的客户数据
	public String findByHasRelation() throws IOException{
		ICustomerService iCustomerService = CrmUtils.getICustomerService();
		 List<Customer> findByHasRelation = iCustomerService.findByHasRelation(model.getId());
		this.Object2JSON(new String[]{}, findByHasRelation);
		return NONE;
	}
	//定区关联客户
	public String assigncustomerstodecidedzone(){
		ICustomerService iCustomerService = CrmUtils.getICustomerService();
		//第一个参数：客户ids  第二个参数 定区id
		iCustomerService.assigncustomerstodecidedzone(customerIds, model.getId());
		return LIST; 
	}
}
