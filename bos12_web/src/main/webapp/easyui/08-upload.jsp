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
<title>文件上传</title>

</head>

<body>
	<!-- 上文件 不刷新页面 ajax不支持文件上传 -->
	<!-- 默认：application/x-www-form-urlencoded 文件上传：multipart/form-data -->
	<!-- <form target="abc" action="xxx.action" method="post" enctype="multipart/form-data">
		<input type="file">
		<input type="submit" value="文件上传">
	</form>
	
	<iframe style="display: none" name="abc"></iframe> -->
	
	<!-- 	OCUpload一键上传插件使用 -->
	<input id="but1" type="button" value="文件上传">
	<script type="text/javascript">
		$(function(){
			//页面加载完成后 为but1绑定文件上传的事件
			$("#but1").upload({
				name: 'regionFile',
				action: 'xxx.action'
			})
		})
	</script>
</body>
</html>