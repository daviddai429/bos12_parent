package cn.itcast.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.Function;
import cn.itcast.bos.service.IFunctionService;
import cn.itcast.bos.web.action.base.BaseAction;
/**
 * 权限模块  展示层
 * @author admin
 *
 */
@Controller //注解作用：标识作用
@Scope("prototype")//多例
public class FunctionAction extends BaseAction<Function> {
	
	//注入service
	@Resource
	private IFunctionService functionService;
	
	//保存权限功能
	public String save(){
		functionService.save(model);
		return LIST;
	}
	
	//查询权限功能
	public String listajax() throws IOException{
		List<Function> listFunction = functionService.findAll();
		this.Object2JSON(new String[]{"parentFunction","childrenFunctions","roles"}, listFunction);
		return NONE;
	}
	
	//分页查询
	public String pageQuery() throws IOException{
		//将客户端打成jar上传maven仓库 调用测试
		pageBean.setCurrPage(Integer.parseInt(model.getPage()));
		functionService.pageQuery(pageBean);
		this.Object2JSON(new String[]{"parentFunction","childrenFunctions","roles"}, pageBean);
		return NONE;
	}
	
	//根据用户id查询菜单数据
	public String findMenuByUserId() throws IOException{
		List<Function> listFunction = functionService.findMenuByUserId();
		this.Object2JSON(new String[]{"parentFunction","childrenFunctions","roles"}, listFunction);
		return NONE;
	}
}
