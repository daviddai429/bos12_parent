package cn.itcast.bos.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.itcast.bos.domain.Region;
import cn.itcast.bos.service.IRegionService;
import cn.itcast.bos.utils.Config;
import cn.itcast.bos.utils.FileUtil;
import cn.itcast.bos.web.action.base.BaseAction;
/**
 * 区域模块  展示层
 * @author admin
 *
 */
@Controller //注解作用：标识作用
@Scope("prototype")//多例
public class RegionAction extends BaseAction<Region>{
	
	@Autowired
	private Config config;
	
	//区域模糊查询的参数
	private String q;
	
	public void setQ(String q) {
		this.q = q;
	}
	@Resource
	private IRegionService regionService;
	
	//页面导入的文件
	private File regionFile;
	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}


	//导入区域数据
	public String importXls(){
		regionService.importXls(regionFile);
		//System.out.println(regionFile);
		return NONE;
	}
	
	
	//分页查询
	public String pageQuery() throws IOException{
		//将pageBean传给service层
		regionService.pageQuery(pageBean);
		this.Object2JSON(new String[]{"currPage","pageSize","detachedCriteria","subareas"}, pageBean);
		return NONE;
	}
	//查询分区数据
	public String listajax() throws IOException{
		//查询所有区域数据
		List<Region> listRegion = null;
		//模糊查询
		if(StringUtils.isNotBlank(q)){
			listRegion = regionService.findByQ(q);
		}
		else
		{
			//查询所有区域数据
			listRegion = regionService.findAll();
		}
		
		this.Object2JSON(new String[]{"subareas"}, listRegion);
		return NONE;
	}
	//模板下载
	public String downTemplete(){
		
		String templatePath = ServletActionContext.getServletContext().getRealPath(
				"/")
				+ config.getTemplateFolder()
				+ File.separator
				+ "region_import_template.xls";
		HttpServletResponse response = ServletActionContext.getResponse();
		FileUtil.download(templatePath, response);
		return NONE;
	}
	
}
