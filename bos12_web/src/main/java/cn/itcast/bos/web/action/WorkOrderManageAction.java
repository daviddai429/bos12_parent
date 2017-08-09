package cn.itcast.bos.web.action;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.WorkOrderManage;
import cn.itcast.bos.service.IWorkOrderManageService;
import cn.itcast.bos.web.action.base.BaseAction;
/**
 * 工作单快速录入模块  展示层
 * @author admin
 *
 */
@Controller //注解作用：标识作用
@Scope("prototype")//多例
public class WorkOrderManageAction extends BaseAction<WorkOrderManage> {
	
	@Resource
	private IWorkOrderManageService workOrderManageService;
	public String save() throws IOException{
		String rs = "1";
		try {
			workOrderManageService.save(model);

		} catch (Exception e) {
			rs = "0";
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(rs);
		return NONE;
	}
}
