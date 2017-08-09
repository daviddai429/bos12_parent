package cn.itcast.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.Region;
import cn.itcast.bos.domain.Subarea;
import cn.itcast.bos.service.ISubareaService;
import cn.itcast.bos.utils.FileUtils;
import cn.itcast.bos.web.action.base.BaseAction;

/**
 * 分区模块 展示层
 * 
 * @author admin
 *
 */
@Controller // 注解作用：标识作用
@Scope("prototype") // 多例
public class SubareaAction extends BaseAction<Subarea>{
	//定区id
	private String did;
	public void setDid(String did) {
		this.did = did;
	}

	@Resource
	private ISubareaService subareaService;
	public String save() {
		subareaService.save(model);
		return LIST;
	}
	
	//分页查询
	public String pageQuery() throws IOException{
		
		/*addresskey	
		region.city	
		region.district	
		region.province	*/
		//关键字
		String addresskey = model.getAddresskey();
		//获取离线查询对象
		DetachedCriteria dc = pageBean.getDetachedCriteria();
		//判断关键是否为空  ，不为空则添加查询条件
		if(StringUtils.isNotBlank(addresskey)){
			dc.add(Restrictions.like("addresskey", "%"+addresskey+"%"));
		}
		
		//判断region是否为空  如果不为空则添加相应查询条件
		Region region = model.getRegion();
		if(region != null){
			//多表关联查询，需要创建别名 
			dc.createAlias("region", "r");
			String city  = region.getCity();
			String district =region.getDistrict();
			String province = region.getProvince();
			//判断值是否为空 不为空则添加条件
			if(StringUtils.isNotBlank(city)){
				dc.add(Restrictions.like("r.city", "%"+city+"%"));
			}
			if(StringUtils.isNotBlank(district)){
				dc.add(Restrictions.like("r.district", "%"+district+"%"));
			}
			if(StringUtils.isNotBlank(province)){
				dc.add(Restrictions.like("r.province", "%"+province+"%"));
			}
		}
		
		//将pageBean传给service层
		subareaService.pageQuery(pageBean);
		this.Object2JSON(new String[]{"currPage","pageSize","detachedCriteria","subareas","decidedzone"}, pageBean);
		return NONE;
	}
	//文件导出
	public String exportXls(){
		//空的excel对象
		HSSFWorkbook hb = new HSSFWorkbook();
		//创建一个sheet
		HSSFSheet createSheet = hb.createSheet("Sheet");
		//创建表头
		HSSFRow createRow = createSheet.createRow(0);
		createRow.createCell(0).setCellValue("编号");
		createRow.createCell(1).setCellValue("省市区");
		createRow.createCell(2).setCellValue("关键字");
		createRow.createCell(3).setCellValue("位置");
		//查询要导出的所有分区数据
		List<Subarea> list = subareaService.findAll();
		//再循环查询的数据，写入excel
		for (Subarea subarea : list) {
			//获取最后一行
			int rownum = createSheet.getLastRowNum();
			//创建内容行
			HSSFRow content = createSheet.createRow(rownum+1);
			content.createCell(0).setCellValue(subarea.getId());
			content.createCell(1).setCellValue(subarea.getRegion().getRegionName());
			content.createCell(2).setCellValue(subarea.getAddresskey());
			content.createCell(3).setCellValue(subarea.getPosition());
		}
		//通过输出流回写浏览器
		
		String filename = "分区数据.xls";
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		String filename2 = "";
		try {
			filename2 = FileUtils.encodeDownloadFilename(filename, agent);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String mimeType = ServletActionContext.getServletContext().getMimeType(filename);
		//一个流 两个头
		ServletActionContext.getResponse().setContentType(mimeType);
		//inline：在当前浏览器显示  attachment:附件
		ServletActionContext.getResponse().setHeader("content-disposition", "filename="+filename2);
		ServletOutputStream out;
		try {
			out = ServletActionContext.getResponse().getOutputStream();
			hb.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return NONE;
	}
	
	
	//查询分区数据
	public String listajax() throws IOException{
		List<Subarea> listSubarea = subareaService.listajax();
		this.Object2JSON(new String[]{"decidedzone","region"}, listSubarea);
		return NONE;
	}
	
	//根据定区id查询分区数据 
	public String findSubareaByDecidedzoneId() throws IOException{
		List<Subarea> listSuarea = subareaService.findSubareaByDecidedzoneId(did);
		this.Object2JSON(new String[]{"decidedzone","subareas"}, listSuarea);
		return NONE;
	}
}
