package cn.itcast.bos.dao.impl;
import org.springframework.stereotype.Repository;

import cn.itcast.bos.dao.IWorkBillDao;
import cn.itcast.bos.dao.base.impl.BaseDaoImpl;
import cn.itcast.bos.domain.WorkBill;
/**
 * 工单模块  持久层
 * @author admin
 *
 */
@Repository
public class WorkBillDaoImpl extends BaseDaoImpl<WorkBill> implements IWorkBillDao {

}
