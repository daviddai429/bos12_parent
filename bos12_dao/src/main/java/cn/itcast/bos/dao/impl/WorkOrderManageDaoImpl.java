package cn.itcast.bos.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import cn.itcast.bos.dao.IWorkOrderManageDao;
import cn.itcast.bos.dao.base.impl.BaseDaoImpl;
import cn.itcast.bos.domain.WorkOrderManage;
import cn.itcast.bos.utils.PageBean;
/**
 * 业务受理单模块  持久层
 * @author admin
 *
 */
@Repository
public class WorkOrderManageDaoImpl extends BaseDaoImpl<WorkOrderManage> implements IWorkOrderManageDao {

}
