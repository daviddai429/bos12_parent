package cn.itcast.bos.service;

import cn.itcast.bos.domain.Decidedzone;
import cn.itcast.bos.utils.PageBean;

public interface IDecidedzoneService {

	/**
	 * 保存定区数据 
	 * @param model 定区数据
	 * @param subareaids 分区ids
	 */
	public void save(Decidedzone model, String[] subareaids);

	/**
	 * 分页查询
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);

}
