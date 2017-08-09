package cn.itcast.bos.web.action.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.utils.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
	//page:当前页码 rows每页显示的条数
	//private Integer page;
	//private Integer rows;
	
	//将传入的参数封装到pageBean
	public PageBean pageBean = new PageBean();
	public void setPage(Integer page) {
		pageBean.setCurrPage(page);
	}
	public void setRows(Integer rows) {
		pageBean.setPageSize(rows);//这里是当前页码参数传入每页显示多少条
	}
	
	
    public static final String HOME = "home";

    public static final String LIST = "list";
	
	//声明一个模型对象
	public T model;
	
	//通过构造函数 获取实体对象类型， 并且通过反射获取实体对象
	public BaseAction() {
		//Type：获取父类的类型  ParameterizedType:获取子类接口
		ParameterizedType genericSuperclass = (ParameterizedType)this.getClass().getGenericSuperclass();
		//获取父类类型上的数组
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		//获取子类对象类型
		Class<T> entityClass = (Class<T>) actualTypeArguments[0];
		try {
			//创建离线查询条件
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
			pageBean.setDetachedCriteria(detachedCriteria);
			//通过反射创建实体对象
			model = entityClass.newInstance();
			
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public T getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	/**
	 * 将对象传入 通过输出流返回页面
	 * @param excludes
	 * @param object
	 * @throws IOException
	 */
	public String Object2JSON(String[] excludes,Object object) throws IOException{
		//排除不需要转json的数据
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		String json = null;
		//判断object到时pageBean object对象还是list集合
		if(object instanceof List || object instanceof Object[]){
			json = JSONArray.fromObject(object,jsonConfig).toString();
		}
		else
		{
			//将Object转json
			json = JSONObject.fromObject(object,jsonConfig).toString();
		}
		//将数据结果通过输出流返回页面
		//通过输出流写回页码
		
		//方法三：
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
		return json;
	}
}
