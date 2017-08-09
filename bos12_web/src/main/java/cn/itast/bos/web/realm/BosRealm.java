package cn.itast.bos.web.realm;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.bos.dao.IFunctionDao;
import cn.itcast.bos.dao.IUserDao;
import cn.itcast.bos.domain.Function;
import cn.itcast.bos.domain.User;
import cn.itcast.bos.utils.BosUtils;
/**
 * 自定义realm实现用户认证  授权
 * @author admin
 *
 */
public class BosRealm extends AuthorizingRealm {

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IFunctionDao functionDao;
	
	
	//授权
	/*@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取当前用户（因为我们根据不同的用户授予不同的权限）
		
		//根据用户id到权限表查询权限数据
		
		//实例化简单授权对象 
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//为用户进行授权
		info.addStringPermission("staff");
		info.addRole("staff2");
		return info;
	}
*/
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//先获取当前用户对象 
		Subject subject = SecurityUtils.getSubject();
		User user = (User)subject.getPrincipal();
		List<Function> listFunctions = null;
		//如果是管理员则查询所有权限数据
		if(user.getUsername().equals("admin")){
			//如果是管理员，查询所有权限
			listFunctions = functionDao.findAll();
		}
		else
		{
			//根据用户id查询权限表数据
			listFunctions =  functionDao.findFunctionByUserId(user.getId());
		}
		//授权
		//实例化简单授权对象 
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//循环遍历权限数据放入 授权对象中
		for (Function function : listFunctions) {
			//将关键字放入授权对象中
			info.addStringPermission(function.getCode());
		}
		return info;
	}
	
	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//principal这个对象 是在认证成功后 通过SimpleAuthenticationInfo 存入进去的
		//User  userToken = (User)token.getPrincipal();
		//前台action存放的使用UsernamePasswordToken 所以取出也使用UsernamePasswordToken
		UsernamePasswordToken userToken = (UsernamePasswordToken)token;
		//根据用户名查询用户对象
		User user = userDao.findUserByUsername(userToken.getUsername());
		//根据用户名判断用户是否存在
		if(user == null){
			//如果不存在 则直接返回null
			return null;
		}
		//如果已经存在 则调用认证方法将当前reaml对象、用户密码、登录成功后需要获取的对象传入
		SimpleAuthenticationInfo sa = new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getSimpleName());
		return sa;
	}

}
