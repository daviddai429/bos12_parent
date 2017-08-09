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
<!-- 引入ocupload js -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
<title>下拉列表框</title>

</head>

<body>
	<!-- <select id="cc" class="easyui-combobox" name="dept"
		style="width: 200px;">

		<option value="aa">aitem1</option>

		<option>bitem2</option>

		<option>bitem3</option>

		<option>ditem4</option>

		<option>eitem5</option>

	</select> -->
	<!-- valueField::底层数据值名称绑定到这个组合框，传到后台实际值。  textField:底层数据字段名称绑定到这个组合框，前台显示值。 url:一个URL加载列表数据从远程。-->
<!-- 	<input id="cc" class="easyui-combobox" name="dept" data-options="valueField:'id',textField:'text',url:'get_data.php'" />
 -->	
	
	<input id="cc" name="dept" value="请选择">
	<script type="text/javascript">
		$(function(){
			$('#cc').combobox({ 
				url:'${pageContext.request.contextPath}/json/combobox_data.json', 
				valueField:'id', 
				textField:'text' 
			});
		})
	</script>
	
		

	


</body>
</html>