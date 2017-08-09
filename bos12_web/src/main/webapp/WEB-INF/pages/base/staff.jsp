<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags"  prefix="shiro"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	function doAdd(){
		//alert("增加...");
		$('#addStaffWindow').window("open");
	}
	
	function doView(){
		alert("查看...");
	}
	
	function doDelete(){
		//返回所有选定的行,当没有记录被选中,我将返回空数组。
		var rows = $("#mygrid").datagrid("getSelections");
		if(rows.length > 0){
			//删除
			//获取rows中id传入后台
			//创建array存放
			var array = new Array();
			for (var i = 0; i < rows.length; i++) {
				//将id放入数组里
				array.push(rows[i].id)
			}
			var url = "${pageContext.request.contextPath }/staffAction_delete.action?ids="+array;
			$.post(url,{},function(data){
				if(data == 1){
					$.messager.alert('提示信息','删除失败!','error');
				}
				else
				{
					//成功则跳转刷新页面
					location.href="${pageContext.request.contextPath }/page_base_staff.action";
				}
			})
			//location.href="${pageContext.request.contextPath }/staffAction_delete.action?ids="+array;
		}
		else
		{
			//提示告诉用户没有选中删除的取派员
			$.messager.alert('提示信息','请选择取派员进行删除!','warning');
		}
	}
	
	function doRestore(){
		alert("将取派员还原...");
	}
	//工具栏
	var toolbar = [ {
		id : 'button-view',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, 
	{
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	},
	{
		id : 'button-save',
		text : '还原',
		iconCls : 'icon-save',
		handler : doRestore
	}];
	// 定义列 ctrl+K
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	},{
		field : 'name',
		title : '姓名',
		width : 120,
		align : 'center'
	}, {
		field : 'telephone',
		title : '手机号',
		width : 120,
		align : 'center'
	}, {
		field : 'haspda',
		title : '是否有PDA',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="1"){
				return "有";
			}else{
				return "无";
			}
		}
	}, {
		field : 'deltag',
		title : '是否作废',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="0"){
				return "正常使用"
			}else{
				return "已作废";
			}
		}
	}, {
		field : 'standard',
		title : '取派标准',
		width : 120,
		align : 'center'
	}, {
		field : 'station',
		title : '所谓单位',
		width : 200,
		align : 'center'
	} ] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 取派员信息表格
		$('#mygrid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,//自适应
			border : false,//没有边框
			rownumbers : true,//编号
			striped : true,//True有条纹的行。
			pagination : true,//启用分页
			toolbar : toolbar,//工具栏
			url : "${pageContext.request.contextPath }/staffAction_pageQuery.action",
			idField : 'id',//指明哪个字段是一个标识字段 主键id可以作为标识字段
			columns : columns,
			onDblClickRow : doDblClickRow//双击行事件
		});
		
		// 添加取派员窗口
		$('#addStaffWindow').window({
	        title: '添加取派员',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		// 修改取派员窗口
		$('#editStaffWindow').window({
	        title: '修改取派员',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
	});

	function doDblClickRow(rowIndex, rowData){
		//先打开修改取派员窗口，再将rowData回显数据到表单
		$('#editStaffWindow').window("open");
		// 当用户双击一行,参数包含: rowIndex: 点击的行索引,从0 rowData: 点击对应的记录行
		//alert(rowIndex+"==="+ JSON.stringify(rowData));
		//将双击事件获取的数据 放入form表单中的load  rowData传入
		$('#editStaffForm').form('load',rowData);  
		//0==={"decidedzones":[],"deltag":"1","haspda":"1","id":"8a7e86b75d7e52dc015d7e542fe30000",
				//"name":"高峰","standard":"200公斤","station":"黑马","telephone":"13838383838"}
	}
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	
	<div region="center" border="false">
	<!-- shiro:hasPermission:判断当前用户是否有单个权限   shiro:hasRole：判断当前用户是否有这个角色权限，这个角色包含多个权限  
	-->
	<shiro:hasRole name="staff">shiro:hasRole</shiro:hasRole>
	<s:debug></s:debug>
    	<table id="mygrid"></table>
	</div>
	<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="addStaffForm" action="${pageContext.request.contextPath }/staffAction_save.action" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<!-- <tr>
						<td>取派员编号</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr> -->
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<td><input type="text" name="telephone" data-options="validType:'myphone'" class="easyui-validatebox" required="true"/></td>
						
						<!-- 到自定义验证规则,覆盖$.fn.validatebox.defaults.rules。 -->
						<script type="text/javascript">
							$(function(){
								var reg = /^1[3|4|5|7|8][0-9]{9}$/;
								//页码加载成功之后
								//自定义手机号码验证码规则
								$.extend($.fn.validatebox.defaults.rules, { 

									myphone: { //自定义验证规则名称

										validator: function(value, param){ 
	
											return reg.test(value); 
	
										}, 
										message: '请输入正确的手机号码.' 
										} 
								}); 
									
								//为保存按钮绑定单击事件
								$("#save").click(function(){
									var v = $("#addStaffForm").form("validate");
									if(v){
										//提交表单
										$("#addStaffForm").submit();
									}
								})

							})
						</script>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="checkbox" name="haspda" value="1" />
						是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							<input type="text" name="standard" class="easyui-validatebox" required="true"/>  
						</td>
					</tr>
					</table>
			</form>
		</div>
	</div>
	
	
	<div class="easyui-window" title="对收派员修改" id="editStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="edit" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >修改</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="editStaffForm" action="${pageContext.request.contextPath }/staffAction_edit.action" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<!-- <tr>
						<td>取派员编号</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr> -->
					<!-- 隐藏 当前取派员id -->
					<input name="id" type="hidden"/>
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<td><input type="text" name="telephone" data-options="validType:'myphone'" class="easyui-validatebox" required="true"/></td>
						
						<!-- 到自定义验证规则,覆盖$.fn.validatebox.defaults.rules。 -->
						<script type="text/javascript">
							$(function(){
								var reg = /^1[3|4|5|7|8][0-9]{9}$/;
								//页码加载成功之后
								//自定义手机号码验证码规则
								$.extend($.fn.validatebox.defaults.rules, { 

									myphone: { //自定义验证规则名称

										validator: function(value, param){ 
	
											return reg.test(value); 
	
										}, 
										message: '请输入正确的手机号码.' 
										} 
								}); 
									
								//为保存按钮绑定单击事件
								$("#edit").click(function(){
									var v = $("#editStaffForm").form("validate");
									if(v){
										//提交表单
										$("#editStaffForm").submit();
									}
								})

							})
						</script>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="checkbox" name="haspda" value="1" />
						是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							<input type="text" name="standard" class="easyui-validatebox" required="true"/>  
						</td>
					</tr>
					</table>
			</form>
		</div>
	</div>
</body>
</html>	