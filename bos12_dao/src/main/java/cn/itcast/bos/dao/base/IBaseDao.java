package cn.itcast.bos.dao.base;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.bos.utils.PageBean;

/**
 * 持久层公共方法
 * @author admin
 *
 * @param <T>
 */
public interface IBaseDao<T> {
	 /**
	  * 增
	  * @param entity
	  */
     public void save(T entity);
     
     /**
	  * 增
	  * @param entity
	  */
     public void saveOrUpdate(T entity);
     
     /**
      * 删
      * @param entity
      */
	 public void delete(T entity);
	 /**
	  * 修
	  * @param entity
	  */
	 public void update(T entity);
	 
	 /**
	  * 根据id查询T
	  * @param id
	  * @return
	  */
	 public T findById(Serializable id);
	 /**
	  * 查询所有
	  * @return
	  */
	 public List<T> findAll();
	 
	 /**
	  * 分页查询
	  */
	 public void pageQuery(PageBean pageBean);
	 /**
	  * 命令查询语句
	  * @param queryName
	  * @param objects
	  */
	 public void executeQueryName(String queryName,Object ...objects)throws SQLException;
	 /**
	  * 根据离线查询对象查询list集合
	  * @param dc
	  * @return
	  */
	 public List<T> findByCriteria(DetachedCriteria dc);
}
