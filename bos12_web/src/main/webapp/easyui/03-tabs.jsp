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
<title>tabs选项卡面板(动态添加选项卡)</title>
</head>

<!-- <body>
	<div id="tt" class="easyui-tabs" style="width: 500px; height: 250px;">
		<div title="取派员设置" style="padding: 20px; display: none;">tab1</div>
		<div title="管理分区" data-options="closable:true"
			style="overflow: auto; padding: 20px; display: none;">tab2</div>

		<div title="管理定区" data-options="iconCls:'icon-reload',closable:true"
			style="padding: 20px; display: none;">tab3</div>

	</div>




</body> -->
<body class="easyui-layout">

	<div data-options="region:'north'"
		style="height: 100px;"></div>

	<div data-options="region:'south'"
		style="height: 100px;"></div>

	<div data-options="region:'west',title:'菜单导航',split:true"
		style="width: 200px;">
		<!-- fit:自适应 -->
		<div id="aa" class="easyui-accordion" data-options="fit:true">
			<div title="基本功能" data-options="iconCls:'icon-mini-add',selected:true"
				style="overflow: auto;">
				<!-- 定一个按钮，为这个按钮绑定单击事件，动态打开一个选项卡 -->
				<input id="btn1" type="button" value="动态添加选项卡">
				<script type="text/javascript">
					  $(function(){
						  //位btn1添加单击事件
						  $("#btn1").click(function(){
							  //解决打开多个选项卡问题
							  //判断当前是否已经打开选项卡，如果已经打开选中，如果没有打开，动态创建
							  var flag = $('#mytabs').tabs('exists','取派员设置');
							  if(flag){
								  //已经存在 则选中
								  $('#mytabs').tabs('select','取派员设置');
							  }
							  else{
								  //不存在 则添加选项卡
								  //为div id为mytabs 的div动态添加选项卡
								  $('#mytabs').tabs('add',
									        {
									          title: '取派员设置',
									          selected: true,
									          closable:true,
									          content:"<iframe frameborder=\"0\" height=\"100%\" width=\"100%\" src=\"http://www.itcast.cn/\"></iframe>"
								  });
							  }
							  
						  })
					  })
				</script>
			</div>
			<div title="系统管理" data-options="iconCls:'icon-mini-add'"
				style="padding: 10px;">content2</div>
		</div>
				
	</div>

	<div id="mytabs" class="easyui-tabs" data-options="region:'center'"></div>

</body>
</html>