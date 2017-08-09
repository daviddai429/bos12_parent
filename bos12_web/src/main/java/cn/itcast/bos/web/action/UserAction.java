package cn.itcast.bos.web.action;

import java.io.IOException;

import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.itcast.bos.domain.User;
import cn.itcast.bos.service.IUserService;
import cn.itcast.bos.utils.MD5Utils;
import cn.itcast.bos.web.action.base.BaseAction;
/**
 * 用户模块  展示层
 * @author admin
 *
 */
@Controller //注解作用：标识作用
@Scope("prototype")//多例
public class UserAction extends BaseAction<User> {
	
	//角色ids
	private String[] roleIds;
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	//通过属性驱动获取验证码
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	@Resource
	private IUserService userService;
	
	//用户登录
	/*public String login(){
		//用户名 密码 验证码
		//判断用户的输入的验证码 是否与 session中的验证码一致
		String key = (String)ServletActionContext.getRequest().getSession().getAttribute("key");
		if(StringUtils.isNotBlank(checkcode) && checkcode.equals(key)){
			//如果验证码一致 则调用service查询用户是否存在
			User user = userService.findByUserNameAndPassword(model);
			if(user != null){
				//如果用户存在则跳转成功页面
				//跳转成功页面
				ServletActionContext.getRequest().getSession().setAttribute("user", user);
				return HOME;
			}
			else
			{
				this.addActionError("用户名或密码错误");
				//如果用户不存在则跳转登录页面 并给出错误提示
				return LOGIN;
			}
		}
		else
		{
			this.addActionError("验证码错误");
			//如果验证码不一致 跳转登录页面给出错误提示
			return LOGIN;
		}
		
	}*/
	
	
	public String login(){
		//用户名 密码 验证码
		//判断用户的输入的验证码 是否与 session中的验证码一致
		String key = (String)ServletActionContext.getRequest().getSession().getAttribute("key");
		if(StringUtils.isNotBlank(checkcode) && checkcode.equals(key)){
			  //获取当前用户对象
			 Subject subject = SecurityUtils.getSubject();
			 //获取令牌
			 AuthenticationToken token = new UsernamePasswordToken(model.getUsername(), MD5Utils.md5(model.getPassword()));
			 try {
				 //使用shiro自带登录功能区认证
				 subject.login(token);
				 //获取当前用户的对象
				 /*String username = (String)subject.getPrincipal();
				 User user = new User();
				 user.setUsername(username);
				 ServletActionContext.getRequest().getSession().setAttribute("username", username);*/
				 User user = (User)subject.getPrincipal();
				 ServletActionContext.getRequest().getSession().setAttribute("user", user);
				 return HOME;
			} catch (Exception e) {
				e.printStackTrace();
				this.addActionError("用户名或密码错误");
				return LOGIN;
			}
		}
		else
		{
			this.addActionError("验证码错误");
			//如果验证码不一致 跳转登录页面给出错误提示
			return LOGIN;
		}
		
	}
	//登出
	public String logout(){
		//销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		return LOGIN;
	}
	
	//修改密码
	public String editPassword() throws IOException{
		String rs = "1";//1：成功   0：失败
		//调用service修改用户密码
		try {
			userService.editPassword(model.getPassword());
		} catch (Exception e) {
			rs = "0";
		}
		//通过输出流写回页码
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(rs);
		return NONE;
	}
	
	//新增用户
	public String save(){
		userService.save(roleIds,model);
		return LIST;
	}
	
	public String pageQuery() throws IOException{
		userService.pageQuery(pageBean);
		this.Object2JSON(new String[]{"noticeBills","users","functions","roles"}, pageBean);
		return NONE;
	}
}
