package cn.itcast.bos.service;

import cn.itcast.bos.domain.NoticeBill;

public interface INoticeBillService {

	/**
	 * 保存业务受理单 并 尝试自动分单
	 * @param model
	 */
	public void save(NoticeBill model);

}
