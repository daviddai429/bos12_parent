package cn.itcast.bos.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.itcast.bos.domain.User;
import cn.itcast.bos.utils.BosUtils;
/**
 * 自定义方法拦截器
 * @author admin
 *
 */
public class LoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//获取session中的用户对象
		User user = BosUtils.getUser();
		if(user == null){
			//不存在
			return "login";
		}
		//存在 则放行
		return invocation.invoke();
	}

}
