package cn.itcast.bos.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.bos.dao.base.IBaseDao;
import cn.itcast.bos.utils.PageBean;
/**
 * 持久层公共方法实现类
 * @author admin
 *
 * @param <T>
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	//注入sessionFactory
	@Resource //j2ee 默认可以按照id注入  如果找不到id就按照类型注入
	//@Autowired //默认按照类型注入 如果要按照id注入需要结合 @Qualifier 一起使用
	//@Qualifier(value="id")
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	//实体对象类型
	public Class<T> entityClass;
	
	//通过构造函数 获取实体对象对象
	public BaseDaoImpl(){
		//Type：获取父类的类型  ParameterizedType:获取子类接口
		ParameterizedType genericSuperclass = (ParameterizedType)this.getClass().getGenericSuperclass();
		//获取父类类型上的数组
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		//获取子类对象类型
		entityClass = (Class<T>) actualTypeArguments[0];
	}
	
	@Override
	public void save(T entity) {
		//直接插入数据库
		this.getHibernateTemplate().save(entity);
	}
	
	@Override
	public void saveOrUpdate(T entity) {
		//1.查询数据库
		//2.对比数据是否有改变
		//3.如果没有改变，不做任何操作
		//4.如果有改变，update
		//5.如果查询的时候没有数据，则直接插入数据库
		this.getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public T findById(Serializable id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public List<T> findAll() {
		String hql = "FROM "+entityClass.getSimpleName();
		return (List<T>) this.getHibernateTemplate().find(hql);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		//分页分析：
		//
		//pageSize
		// -
		//查询总的记录数
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		//改变hibernate发出sql查询方式 select count(*) from table
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
		//不需要判断是否为空 如果没有记录放回0
		//将总的记录数放入pageBean
		pageBean.setTotal(list.get(0).intValue());
		//查询当前页需要显示的数据
		//从第几条记录开始查询
		int firstResult = (pageBean.getCurrPage()-1)*pageBean.getPageSize();
		// 每页最多查询记录数
		int maxResults = pageBean.getPageSize();
		//将projection去除
		detachedCriteria.setProjection(null);
		//改变hibernate最终封装数据的结果，将hibernate封装一个对象，封装成entityClass（BaseAction构造方法中设置的）
		detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		List rows = this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
		//将rows放入pageBean
		pageBean.setRows(rows);
	}

	/**
	 * 命名查询语句
	 * @param queryName
	 * @param objects
	 * @throws SQLException 
	 */
	public void executeQueryName(String queryName,Object ...objects) throws SQLException{
		//获取query对象 getNamedQuery 异常IllegalArgumentException: node to traverse cannot be null!
		Query query = this.getSessionFactory().getCurrentSession().getNamedQuery(queryName);
		//循环遍历参数放入参数上
		int i = 0;
		for (Object object : objects) {
			query.setParameter(i++, object);
		}
		//Execute the update or delete statement. 
		int rs = query.executeUpdate();
		if(rs == 0){
			//抛异常的目的：让service可以回滚事务
			throw new SQLException();
		}
	}
	
	public List<T> findByCriteria(DetachedCriteria dc){
		return (List<T>)this.getHibernateTemplate().findByCriteria(dc);
	}
}
