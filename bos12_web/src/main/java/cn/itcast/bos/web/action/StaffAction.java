package cn.itcast.bos.web.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.service.IStaffService;
import cn.itcast.bos.web.action.base.BaseAction;
/**
 * 取派员模块  展示层
 * @author admin
 *
 */
@Controller //注解作用：标识作用
@Scope("prototype")//多例
public class StaffAction extends BaseAction<Staff>{
	//日志对象 StaffAction.class 打印日志的时候 前面打印StaffAction.class
	private static final Logger logger = LoggerFactory.getLogger(StaffAction.class);
	
	//取派员ids
	private String ids;
	public void setIds(String ids) {
		this.ids = ids;
	}
	//page:当前页码 rows每页显示的条数
	/*private Integer page;
	private Integer rows;
	 
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}*/
	@Resource
	private IStaffService staffService;
	//保存取派员
	public String save(){
		//调用service保存取派员
		staffService.save(model);
		return LIST;
	}
	//分页查询
	/*public String pageQuery() throws IOException{
		//将传入的参数封装到pageBean
		PageBean pageBean = new PageBean();
		pageBean.setCurrPage(page);
		pageBean.setPageSize(rows);//这里是当前页码参数传入每页显示多少条
		//创建离线查询条件
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
		pageBean.setDetachedCriteria(detachedCriteria);
		//将pageBean传给service层
		staffService.pageQuery(pageBean);
		//排除不需要转json的数据
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"currPage","pageSize","detachedCriteria"});
		//将Object转json
		JSONObject jsonObject = JSONObject.fromObject(pageBean,jsonConfig);
		//将数据结果通过输出流返回页面
		//通过输出流写回页码
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(jsonObject.toString());
		return NONE;
	}*/
	public String pageQuery() throws IOException{
		//日志级别 error  info warn debug
		logger.info("当前页码："+pageBean.getCurrPage()+";每页显示的记录数"+pageBean.getPageSize());
		/*logger.debug("当前页码："+pageBean.getCurrPage()+";每页显示的记录数"+pageBean.getPageSize());
		logger.error("当前页码："+pageBean.getCurrPage()+";每页显示的记录数"+pageBean.getPageSize());
		logger.warn("当前页码："+pageBean.getCurrPage()+";每页显示的记录数"+pageBean.getPageSize());
		logger.trace("当前页码："+pageBean.getCurrPage()+";每页显示的记录数"+pageBean.getPageSize());*/
		staffService.pageQuery(pageBean);
		String json = this.Object2JSON(new String[]{"currPage","pageSize","detachedCriteria","decidedzones"}, pageBean);
		logger.info("返回结果："+json);
		return NONE;
	}
	
	//取派员删除
	
	public String delete() throws IOException{
		logger.info("需要删除的取派员ids："+ids);
		String rs = "0";
		try {
			throw new IOException();
			//staffService.delete(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("删除取派员失败",e);
			rs = "1";
		}
		logger.info("返回结果："+rs);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(rs);
		return NONE;
	}
	
	/**
	 * 修改取派员信息
	 */
	@RequiresPermissions("staff.delete")
	public String edit(){
		//先查询数据库原始数据
		String id = model.getId();
		Staff staff = staffService.findById(id);
		//根据页面提交的参数进行覆盖，参数已经封装到model对象
		staff.setHaspda(model.getHaspda());
		staff.setName(model.getName());
		staff.setStandard(model.getStandard());
		staff.setStation(model.getStation());
		staff.setTelephone(model.getTelephone());
		
		staffService.update(staff);//提交事务
		return LIST;
	}

	
	//查询取派员
	public String listajax() throws IOException{
		//查询未离职的取派员数据
		List<Staff> listStaff = staffService.listajax();
		this.Object2JSON(new String[]{"decidedzones"}, listStaff);
		return NONE;
	}
}
