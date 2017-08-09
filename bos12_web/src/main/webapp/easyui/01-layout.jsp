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
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 使用min目的：压缩js，提高页面速度   压缩更安全-->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<title>layout页面布局</title>
</head>
<body class="easyui-layout">

	<div data-options="region:'north'"
		style="height: 100px;"></div>

	<div data-options="region:'south'"
		style="height: 100px;"></div>

	<div data-options="region:'west',title:'菜单导航',split:true"
		style="width: 200px;"></div>

	<div data-options="region:'center',title:'消息中心'"
		style="padding: 5px;"></div>

</body>

</html>