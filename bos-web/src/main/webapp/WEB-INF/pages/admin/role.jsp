<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<!-- 导入ztree类库 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css"
	type="text/css" />
<script
	src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		// 点击保存
		$('#edit').click(function() {
			var v = $('#roleForm').form('validate');
			if (v) {
				var treeObj = $.fn.zTree.getZTreeObj("functionTree");
				var nodes = treeObj.getCheckedNodes(true);
				var array = new Array();
				for (var i = 0; i < nodes.length; i++) {
					var id = nodes[i].id;
					array.push(id);
				}
				var ids = array.join(",");
				$('input[name=functionIds]').val(ids);
				$('#roleForm').submit();
			}
		});
		// 数据表格属性
		$("#grid")
				.datagrid(
						{
							toolbar : [ {
								id : 'add',
								text : '添加角色',
								iconCls : 'icon-add',
								handler : function() {
									location.href = '${pageContext.request.contextPath}/page_admin_role_add.action';
								}
							} ],
							url : 'RoleAction_pageQuery.action',
							pagination : true,
							columns : [ [ {
								field : 'id',
								title : '编号',
								width : 200
							}, {
								field : 'name',
								title : '名称',
								width : 200
							}, {
								field : 'description',
								title : '描述',
								width : 200
							} ] ],
							onDblClickRow : doDblClickRow
						});
		$('#editRoleWindow').window({
			title : '修改角色',
			width : 600,
			modal : true,
			shadow : true,
			closed : true,
			height : 600,
			resizable : false,
		});
		// 授权树初始化
		var setting = {
			data : {
				key : {
					title : "t"
				},
				simpleData : {
					enable : true
				}
			},
			check : {
				enable : true,
			}
		};

		$.ajax({
			url : 'FunctionAction_listajax.action',
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				//	var zNodes = eval("(" + data + ")");
				$.fn.zTree.init($("#functionTree"), setting, data);
			},
			error : function(msg) {
				alert('树加载异常!');
			}
		});
	});
	function doDblClickRow(rowIndex, rowData) {
		$('#editRoleWindow').window("open");
		$('#editRoleWindow').form("load", rowData);
		$.post("FunctionAction_findFunctionsByRoleId.action", {
			roleId : rowData.id
		}, function(data) {
			   //获取ztree对象
			   var treeObj = $.fn.zTree.getZTreeObj("functionTree");
			   treeObj.expandAll(true);
			  //遍历勾选角色关联的菜单数据
			  for (var i = 0; i < data.length; i++) {
			  //根据角色菜单节点数据的属性搜索，获取与完整菜单树完全匹配的节点JSON对象集合
		      var nodes = treeObj.getNodesByParam("id", data[i].id, null);
			          //勾选当前选中的节点
		       treeObj.checkNode(nodes[0],true,false);
			}
		}, "json");
	}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'">
		<table id="grid"></table>
	</div>

	<div class="easyui-window" id="editRoleWindow" collapsible="false"
		minimizable="false" maximizable="false"
		style="top: 200px; left: 200px">
		<div class="datagrid-toolbar">
			<a id="edit" icon="icon-edit" href="#" class="easyui-linkbutton"
				plain="true">修改</a>
		</div>
		<div style="overflow: auto; padding: 5px;" border="false">
			<form id="roleForm" method="post" action="RoleAction_edit.action">
				<table class="table-edit" width="100%" align="center">
					<tr class="title">
						<td colspan="2">角色信息</td>
						<input type="hidden" name="functionIds" />
						<input type="hidden" name="id" />
					</tr>

					<tr>
						<td>名称</td>
						<td><input type="text" name="name" class="easyui-validatebox"
							data-options="required:true" /></td>
					</tr>
					<tr>
						<td>描述</td>
						<td><textarea name="description" rows="4" cols="60"></textarea>
						</td>
					</tr>
					<tr>
						<td>授权</td>
						<td>
							<ul id="functionTree" class="ztree"></ul>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>

</body>
</html>