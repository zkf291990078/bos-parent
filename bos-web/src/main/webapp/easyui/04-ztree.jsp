<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ztree</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css"
	type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.all-3.5.js"></script>

</head>
<body class="easyui-layout">
	<!-- 使用div元素描述每个区域 -->
	<div title="XXX管理系统" style="height: 100px"
		data-options="region:'north'">北部区域</div>
	<div title="系统菜单" style="width: 200px" data-options="region:'west'">
		<!-- 制作accordion折叠面板 
			fit:true----自适应(填充父容器)
		-->
		<div class="easyui-accordion" data-options="fit:true">
			<!-- 使用子div表示每个面板 -->
			<div data-options="iconCls:'icon-cut'" title="面板一">1111</div>
			<div title="面板二">
				2222
				<!-- 展示ztree效果 :使用标准json数据构造ztree-->
				<ul id="ztree1" class="ztree"></ul>
				<SCRIPT LANGUAGE="JavaScript">
					$(function() {
						//页面加载完成后，执行这段代码----动态创建ztree
						var setting = {};
						//构造节点数据
						var zNodes = [ {
							"name" : "节点一",
							"children" : [ {
								"name" : "节点一_1"
							}, {
								"name" : "节点一_2"
							} ]
						},//每个json对象表示一个节点数据
						{
							"name" : "节点二"
						}, {
							"name" : "节点三"
						} ];
						//调用API初始化ztree
						$.fn.zTree.init($("#ztree1"), setting, zNodes);
					});
				</SCRIPT>
			</div>

			<div title="面板三">
				3333
				<!-- 展示ztree效果 :使用简单json数据构造ztree-->
				<ul id="ztree2" class="ztree"></ul>
				<SCRIPT LANGUAGE="JavaScript">
					$(function() {
						//页面加载完成后，执行这段代码----动态创建ztree
						var setting2 = {
							data : {
								simpleData : {
									enable : true
								//使用简单json数据构造ztree节点
								}
							}
						};
						//构造节点数据
						var zNodes2 = [ {
							"id" : "1",
							"pId" : "0",
							"name" : "节点一"
						}, {
							"id" : "2",
							"pId" : "1",
							"name" : "节点二"
						}, {
							"id" : "3",
							"pId" : "0",
							"name" : "节点2"
						} ];
						//调用API初始化ztree
						$.fn.zTree.init($("#ztree2"), setting2, zNodes2);
					});
				</SCRIPT>
			</div>
			<div title="面板四">
				<ul id="ztree3" class="ztree"></ul>
				<SCRIPT LANGUAGE="JavaScript">
					$(function() {
						var setting3 = {
								data: {
									simpleData: {
										enable: true//使用简单json数据构造ztree节点
									}
								},
								callback:{
									onClick:function(event, treeId, treeNode){
										if(treeNode.page!=undefined){
										 var e=	$("#mytabs").tabs("exists",treeNode.name);
										 if(e){
											 $("#mytabs").tabs("select",treeNode.name);
										 }else{
											 $("#mytabs").tabs("add",{
												 title:treeNode.name,
													closable:true,
													content:'<iframe frameborder="0" height="100%" width="100%" src="'+treeNode.page+'"></iframe>' 
											 });
										 }
										}
									}
								}
						};

						var url="${pageContext.request.contextPath}/json/menu.json";
						$.post(url,{},function(data){
							$.fn.zTree.init($("#ztree3"), setting3, data);
						},"json");
						
					});
				</SCRIPT>
			</div>
		</div>
	</div>
	<div data-options="region:'center'">中心区域
	<div id="mytabs" class="easyui-tabs" data-options="fit:true">
	</div>
	</div>
	<div style="width: 100px" data-options="region:'east'">东部区域</div>
	<div style="height: 50px" data-options="region:'south'">南部区域</div>
</body>
</html>