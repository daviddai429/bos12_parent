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
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 使用min目的：压缩js，提高页面速度   压缩更安全-->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<!-- 引入ztree js-->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<title>ztree树形菜单</title>
</head>

<body class="easyui-layout">

	<div data-options="region:'north'"
		style="height: 100px;"></div>

	<div data-options="region:'south'"
		style="height: 100px;"></div>

	<div data-options="region:'west',title:'菜单导航',split:true"
		style="width: 200px;">
		<!-- fit:自适应 -->
		<div id="aa" class="easyui-accordion" data-options="fit:true">
			<div  id="mytree" class="ztree" title="基本功能" data-options="iconCls:'icon-mini-add',selected:true"
				style="overflow: auto;">
				<script type="text/javascript">
					$(function(){
						//页面加载完成后，执行下面的脚本
						//setting 配置详解 配置树形菜单的属性  默认setting属性所有的默认值
						var setting = {
								data: {
									//启用简单数据模式
									simpleData: {
										enable: true
									}
								},
								//回调绑定单击事件
								callback: {
									onClick: function(event, treeId, treeNode){//匿名函数
										//解决打开多个选项卡问题
										if(treeNode.page != undefined)
										{
											  //判断当前是否已经打开选项卡，如果已经打开选中，如果没有打开，动态创建
											  var flag = $('#mytabs').tabs('exists',treeNode.name);
											  if(flag){
												  //已经存在 则选中
												  $('#mytabs').tabs('select',treeNode.name);
											  }
											  else{
												  //不存在 则添加选项卡
												  //为div id为mytabs 的div动态添加选项卡
												  $('#mytabs').tabs('add',
													        {
													          title: treeNode.name,
													          selected: true,
													          closable:true,
													          content:"<iframe frameborder=\"0\" height=\"100%\" width=\"100%\" src=\""+treeNode.page+"\"></iframe>"
												  });
											  }
										}
									}
								}
						};
					//节点数据  标准json构造ztree树形菜单
					/* var zTreeNodes = [ {
							"name" : "基础数据",
							open : true,
							children : [ {
								"name" : "取派员设置",
								"url" : "http://g.cn",
								children : [ {
									"name" : "取派员设置1",
									"url" : "http://g.cn"
								}, {
									"name" : "取派员设置2",
									"url" : "http://baidu.com"
								}, {
									"name" : "取派员设置3",
									"url" : "http://www.sina.com.cn"
								} ]
							}, {
								"name" : "区域设置",
								"url" : "http://baidu.com"
							}, {
								"name" : "管理分区",
								"url" : "http://www.sina.com.cn"
							} ]
						} ]; */

						//简单的json构造ztree树形菜单
						/* var zTreeNodes = [
						    //<!--id一定要唯一  pId是父节点id 如果为0表示顶级菜单-->
						    {"id":1, "pId":0, "name":"基础数据"},
						    {"id":11, "pId":1, "name":"取派员设置"},
						    {"id":12, "pId":1, "name":"区域设置"},
						    {"id":111, "pId":11, "name":"取派员设置1"},
						    {"id":112, "pId":11, "name":"取派员设置2"}
						]; */
						
						//通过post请求获取menu.json构造ztree
						var url="${pageContext.request.contextPath}/json/menu.json";//请求menu路径
						$.post(url,{},function(data){
							//autoSearchTime=1000 火车票刷新
							//将json对象转成string弹出 alert(JSON.stringify(data));
							//打印 console.log(JSON.stringify(data));
							
							$.fn.zTree.init($("#mytree"), setting, data);
						})

						//

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