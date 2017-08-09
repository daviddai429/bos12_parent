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
<title>数据表格-编辑</title>

</head>

<body>
	<table id="dg" style="width:605px"></table>
	<script type="text/javascript">
		//为了实现下面的这个功能 ，需要定义全局suoyin
		var index;
		$('#dg').datagrid({
			url : '${pageContext.request.contextPath }/json/datagrid_data.json',//通过datagrid自带url属性异步请求datagrid_data.json
			//columns 列属性 可以配置多个列   code：第一行第一列 title：第一行一列标题
			pagination:true,//true显示分页栏datagrid底。
			pageList:[1,2,3,4],
			onAfterEdit:function(rowIndex, rowData, changes){
				alert("1"+rowIndex);
				index = undefined;
			},
			pageSize:1,//当设置分页属性,初始化页面大小。 pageList分页下拉改变，必须要结合pageSize一起使用，并且pageSize设置页面初始化的大小必须在pagelist中
			//total:总的记录数  rows：当前页面显示的数据，放入rows
			 toolbar: [{  		 //工具栏
			  iconCls: 'icon-edit', //图标
			  text : '新增一行',//按钮名称
			  handler: function(){
				  
				  
				  
				  
				  if(index != undefined)
				  {
					  alert("3"+index);
					  $('#dg').datagrid('endEdit',index);
					  //必须要改成undefined ，目的 重新新增一行
					//  index = undefined;
				  }
				  
				  
				  if(index == undefined){
						 //如果索引是undefined的就说明第一行，则新增
						//插入一行
						  $('#dg').datagrid('insertRow',{
							  	index: 0,	// 索引从0开始
							  	row: {
							  		name: 'new name',
							  		age: 30,
							  		note: 'some messages'
							  	}
						   });  
						  //开始编辑某一行
						  $('#dg').datagrid('beginEdit',0);
						  index = 0;
						  alert("2"+index);
					  }
				  
				  //单击新增一行按钮之后 之前有没有行在编辑状态
				  
				  //如果没有在编辑状态则新增一行
				  
				  //如果已经有在编辑的行，则保持前面那行到数据库
			  }  	
			  }]  
			,
			columns : [ [
			{
				field : 'code',
				title : 'Code',
				width : 200,
				editor:{//列可编辑属性设置
					type: "numberbox",
					options:{"required":true}
				}
			},
			{
				field : 'name',
				title : 'Name',
				width : 200,
				editor:{//列可编辑属性设置
					type: "numberbox",
					options:{"required":true}
				}
			},

			{
				field : 'price',
				title : 'Price',
				width : 200,
				align : 'right',
				editor:{//列可编辑属性设置
					type: "numberbox",
					options:{"required":true}
				}
			}
			] ]

		});
	</script>
</body>
</html>