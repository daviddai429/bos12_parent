package cn.itcast.bos.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.utils.PageBean;
/**
 * 取派员模块  业务层
 * @author admin
 *
 */
public interface IStaffService {

	/**
	 * 保存取派员
	 * @param model
	 */
	public void save(Staff model);

	/**
	 * 分页查询
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);

	/**
	 * 批量删除取派员
	 * @param ids
	 */
	public void delete(String ids)throws SQLException;

	/**
	 * 修改取派员
	 * @param staff
	 */
	public void update(Staff staff);

	/**
	 * 根据id查询staff
	 * @param id
	 * @return
	 */
	public Staff findById(String id);

	/**
	 * 查询取派员未离职的数据
	 * @return
	 */
	public List<Staff> listajax();

}
