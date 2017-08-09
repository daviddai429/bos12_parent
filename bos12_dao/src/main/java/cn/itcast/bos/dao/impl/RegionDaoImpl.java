package cn.itcast.bos.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.bos.dao.IRegionDao;
import cn.itcast.bos.dao.base.impl.BaseDaoImpl;
import cn.itcast.bos.domain.Region;
/**
 * 区域模块  持久层
 * @author admin
 *
 */
@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao{

}
