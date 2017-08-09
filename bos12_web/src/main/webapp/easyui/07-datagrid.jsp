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
<title>数据表格钮</title>

</head>

<body>
	<!-- 方法一：实现datagrid,通过普通html渲染成datagrid -->
	<!-- <table class="easyui-datagrid" style="width:300px">
		表头
		<thead>
			<tr>
				field：字段名称  
				<th data-options="field:'code',width:90">编号</th>
				<th data-options="field:'name',width:90">姓名</th>
				<th data-options="field:'price',width:90">工资</th>
			</tr>
		</thead>
		表内容体
		<tbody>
			<tr>
				<td>001</td>
				<td>高峰</td>
				<td>2万</td>
			</tr>
			<tr>
				<td>002</td>
				<td>汪洋</td>
				<td>1万</td>
			</tr>
		</tbody>
	</table> -->

	<!-- 方法二 通过datagrid自带url属性异步请求datagrid_data.json，将数据展示到表格中 -->
	<%-- <table class="easyui-datagrid" style="width:300px"
		data-options="url:'${pageContext.request.contextPath }/json/datagrid_data.json',fitColumns:true,singleSelect:true">
		<!-- fitColumns:自动伸展或收缩列宽来保证表格正好充满屏幕  singleSelect：True允许选择只有一行。-->
		<thead>
			<tr>
				<th data-options="field:'code',width:100">Code</th>
				<th data-options="field:'name',width:100">Name</th>
				<th data-options="field:'price',width:100,align:'right'">Price</th>
			</tr>
		</thead>
	</table> --%>
	
	<!-- 方法三：定义table容器，通过js动态创建数据表格 -->
	<table id="dg" style="width:605px"></table>
	<script type="text/javascript">
		$('#dg').datagrid({
			url : '${pageContext.request.contextPath }/json/datagrid_data.json',//通过datagrid自带url属性异步请求datagrid_data.json
			//columns 列属性 可以配置多个列   code：第一行第一列 title：第一行一列标题
			pagination:true,//true显示分页栏datagrid底。
			pageList:[1,2,3,4],
			pageSize:1,//当设置分页属性,初始化页面大小。 pageList分页下拉改变，必须要结合pageSize一起使用，并且pageSize设置页面初始化的大小必须在pagelist中
			//total:总的记录数  rows：当前页面显示的数据，放入rows
			 toolbar: [{  		 //工具栏
			  iconCls: 'icon-edit', //图标
			  text : '查询',//按钮名称
			  handler: function(){
				  alert('edit')//执行具体的操作
			  }  	
			  },{  	//'-' 分割线	
			  iconCls: 'icon-help',  		
			  handler: function(){alert('help')}  	
			  }]  
			,
			columns : [ [
			{
				field : 'code',
				title : 'Code',
				width : 200
			},
			{
				field : 'name',
				title : 'Name',
				width : 200
			},

			{
				field : 'price',
				title : 'Price',
				width : 200,
				align : 'right'
			}
			] ]

		});
	</script>




</body>
</html>