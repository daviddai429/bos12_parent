package cn.itcast.bos.utils;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import cn.itcast.bos.domain.User;

/**
 * bos工具类
 * @author admin
 *
 */
public class BosUtils {
	/**
	 * 获取httpSession
	 * @return
	 */
	public static HttpSession getSession(){
		return ServletActionContext.getRequest().getSession();
	}
	
	/**
	 * 获取session中的用户对象
	 * @return
	 */
	public static User getUser(){
		//根据用户查询用户对象，放入session   用户名不存在，则返回空用户对象
		return (User)getSession().getAttribute("user");
	}
	/*public static User getUser(){
		//根据用户查询用户对象，放入session   用户名不存在，则返回空用户对象
		String username = (String)getSession().getAttribute("username");
		if(StringUtils.isNotBlank(username)){
			//查询数据库 将用户对象放入session
			User user = new User();
			//判断session中用户对象是否为空
			if(user != null){
				return user;
			}
			else{
				//查询数据库
			}
			return user;
		}
		else
		{
			return null;
		}
	}*/
}
