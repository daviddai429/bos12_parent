package cn.itcast.bos.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.IRegionDao;
import cn.itcast.bos.domain.Region;
import cn.itcast.bos.service.IRegionService;
import cn.itcast.bos.service.IStaffService;
import cn.itcast.bos.utils.PageBean;
import cn.itcast.bos.utils.PinYin4jUtils;

/**
 * 取派员模块 业务层
 * 
 * @author admin
 *
 */
@Service
@Transactional
public class RegionServiceImpl implements IRegionService {

	@Resource
	private IRegionDao regionDao;
	
	@Override
	public void importXls(File regionFile) {
		// 2007 XSSFWorkBook //2003 HSSFWorkBook
		try {
			// 创建一个excel对象 将excel通过FileInputStream传给HSSFWorkbook
			HSSFWorkbook hb = new HSSFWorkbook(new FileInputStream(regionFile));
			HSSFSheet sheetAt = hb.getSheetAt(0);// 获取第一sheet页
			// int lastRowNum = sheetAt.getLastRowNum();
			// 遍历每一行数据
			for (Row row : sheetAt) {
				//
				int rowNum = row.getRowNum();
				if (rowNum == 0) {
					continue;
				}
				// 遍历每一列单元格数据
				/*for (Cell cell : row) {
					System.out.println(cell.getStringCellValue());
				}*/
				
				String id = row.getCell(0).getStringCellValue();//区域编号
				String province = row.getCell(1).getStringCellValue();//省份
				String city = row.getCell(2).getStringCellValue();//城市
				String district = row.getCell(3).getStringCellValue();//区域
				String postcode = row.getCell(4).getStringCellValue();//邮编
				
				Region region = new Region(id, province, city, district, postcode,null, null, null);
				
				province = province.substring(0,province.length()-1);//截取省
				city = city.substring(0,city.length()-1);//截取市
				district = district.substring(0,district.length()-1);//截取区
				String[] headByString = PinYin4jUtils.getHeadByString(province+city+district);//将省市区 拼接转每个汉字首字母数组，将数组通过join转字符串
				String citycode = PinYin4jUtils.hanziToPinyin(city,"");//将汉字转拼音
				region.setShortcode(StringUtils.join(headByString, ""));
				region.setCitycode(citycode);
				regionDao.saveOrUpdate(region);
			}
			hb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void pageQuery(PageBean pageBean) {
		regionDao.pageQuery(pageBean);
	}

	@Override
	public List<Region> findAll() {
		return regionDao.findAll();
	}

	@Override
	public List<Region> findByQ(String q) {
		//hql String hql ="FROM Region r WHERE r.shortcode LIKE ? or r.citycode LIKE ?";
		
		//离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Region.class);
		//模糊添加条件
		dc.add(Restrictions.or(Restrictions.like("citycode", "%"+q+"%"),Restrictions.like("shortcode", "%"+q+"%")));
		return regionDao.findByCriteria(dc);
	}

}
