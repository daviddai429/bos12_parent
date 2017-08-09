<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入easyui的css js -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<!-- 引入ztree 样式 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css"
	type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 使用min目的：压缩js，提高页面速度   压缩更安全-->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<!-- 引入ztree js-->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<!-- 解决提示信息中文 引入中文语言js包 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<title>菜单按钮</title>
<script type="text/javascript">

	function editUser(){
		$.messager.confirm('确认提示框', '确认退出系统?', function(r){
			alert(r);
			if (r){ 
			// exit action;
			} 
		});   
	}
</script>
</head>

<body>
	<a href="javascript:void(0)" id="mb" class="easyui-menubutton"
		data-options="menu:'#mm',iconCls:'icon-help'">控制面板</a>

	<div id="mm" style="width: 150px;">

		<div>修改密码</div>

		<div onclick="editUser()">联系管理员</div>
		<!-- 分割线 -->
		<hr/>
<!-- 		<div class="menu-sep"></div>
 -->		
 		<div onclick="editUser()">退出系统</div>
	</div>


</body>
</html>