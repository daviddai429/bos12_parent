package cn.itcast.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.bos.dao.IFunctionDao;
import cn.itcast.bos.dao.base.impl.BaseDaoImpl;
import cn.itcast.bos.domain.Function;
/**
 * 权限模块  持久层
 * @author admin
 *
 */
@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {

	@Override
	public List<Function> findFunctionByUserId(String id) {
		//根据用户id查询权限表数据
		/**
		 * select f.* from auth_function f LEFT OUTER JOIN role_function rf 
			on f.id = rf.function_id  LEFT OUTER JOIN auth_role ar on 
			ar.id = rf.role_id LEFT OUTER JOIN user_role ur on ur.role_id = ar.id
			LEFT OUTER JOIN t_user u on u.id = ur.user_id
			where u.id = '4028aca25dc0e58d015dc0e69d530000'
		 */
		String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles r LEFT OUTER JOIN r.users u WHERE u.id = ?";
		return (List<Function>) this.getHibernateTemplate().find(hql, id);
	}

	@Override
	public List<Function> findMenuByUserId(String id) {
		String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles r LEFT OUTER JOIN r.users u WHERE u.id = ? "
				+ " AND f.generatemenu = '1' order by f.zindex asc ";
		return  (List<Function>) this.getHibernateTemplate().find(hql, id);
	}

}
