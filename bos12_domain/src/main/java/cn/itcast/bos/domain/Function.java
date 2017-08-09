package cn.itcast.bos.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 权限实体类
 */

public class Function implements java.io.Serializable {

	// Fields

	private String id;
	private Function parentFunction;//父类
	private String name;//权限名称
	private String code;//关键字
	private String description;
	private String page;
	private String generatemenu="0";//是否生成菜单 1：生成   0:不生成
	private Integer zindex;//排序
	private Set childrenFunctions = new HashSet(0);
	private Set roles = new HashSet(0);

	//为了显示顶级菜单 添加pId
	public String getpId() {
		if(parentFunction == null){
			return "0";
		}
		return parentFunction.getId();
	}

	/** default constructor */
	public Function() {
	}

	public Function(String id, Function parentFunction, String name,
			String code, String description, String page, String generatemenu,
			Integer zindex, Set childrenFunctions, Set roles) {
		super();
		this.id = id;
		this.parentFunction = parentFunction;
		this.name = name;
		this.code = code;
		this.description = description;
		this.page = page;
		this.generatemenu = generatemenu;
		this.zindex = zindex;
		this.childrenFunctions = childrenFunctions;
		this.roles = roles;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Function getParentFunction() {
		return parentFunction;
	}

	public void setParentFunction(Function parentFunction) {
		this.parentFunction = parentFunction;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getGeneratemenu() {
		return generatemenu;
	}

	public void setGeneratemenu(String generatemenu) {
		this.generatemenu = generatemenu;
	}

	public Integer getZindex() {
		return zindex;
	}

	public void setZindex(Integer zindex) {
		this.zindex = zindex;
	}

	public Set getChildrenFunctions() {
		return childrenFunctions;
	}

	public void setChildrenFunctions(Set childrenFunctions) {
		this.childrenFunctions = childrenFunctions;
	}

	public Set getRoles() {
		return roles;
	}

	public void setRoles(Set roles) {
		this.roles = roles;
	}
}