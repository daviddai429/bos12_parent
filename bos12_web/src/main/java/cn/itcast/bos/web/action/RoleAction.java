package cn.itcast.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.Role;
import cn.itcast.bos.service.IRoleService;
import cn.itcast.bos.web.action.base.BaseAction;
/**
 * 角色模块  展示层
 * @author admin
 *
 */
@Controller //注解作用：标识作用
@Scope("prototype")//多例
public class RoleAction extends BaseAction<Role> {

	//新增一个属性驱动获取权限ids
	private String functionIds;
	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}

	//注入service
	@Resource
	private IRoleService roleService;

	//新增角色
	public String save(){
		roleService.save(model,functionIds);
		return LIST;
	}
	
	//查询角色数据
	public String listajax() throws IOException{
		//查询系统中的所有角色数据
		List<Role> listRole = roleService.listajax();
		this.Object2JSON(new String[]{"users","functions"},listRole);
		return NONE;
	}
}
