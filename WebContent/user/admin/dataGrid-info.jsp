<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<link rel="stylesheet" href="bs/css/bootstrap.css" />
<link rel="stylesheet"
	href="dataGrid/css-datagrid/jquery.dataTables.css" />
<link rel="stylesheet" href="dataGrid/css-datagrid/demo_table_jui.css" />
<link rel="stylesheet"
	href="dataGrid/css-datagrid/themes/bootstrap_new/jquery-ui-1.10.3.custom.css" />
<link rel="stylesheet" href="dataGrid/css-datagrid/myTables_light.css" />
<link rel="stylesheet" href="public/css/components.css" />
<link rel="stylesheet" href="public/css/inframe.css" />


<!-- jquery.1.9.1.min.js 必须在 jquery.dataTables.min.js 前面 -->
<script type="text/javascript" src="jquery/jquery.1.9.1.min.js"></script>
<script src="dataGrid/js-datagrid/jquery.dataTables.min.js"></script>
<script src="bs/js/bootstrap.js"></script>
<script src="common/js/btn-datagrid.js"></script>

<script>
	var exampleTable;
	$(document).ready(
			function() {
				$('#demo').html(
						'<table class="display" id="exampleTable"></table>');

				exampleTable = $('#exampleTable').dataTable(
						{
							"herderstyle" : "center",
							"bProcessing" : true,
							"bServerSide" : true, //指定从服务器端获取数据，默认为false，在开发中都是设置为true，这样每次对table中行数据做修改后，调用datatable
							//的fnDraw()可以实现ajax动态刷新table数据
							//在这里不会有修改表单的情况，so，设置为false，因为设置为true，datatable的过滤功能将失效
							"sAjaxSource" : "user/admin/dataGrid",
							"bJQueryUI" : true, // 使用自定义主题
							"bLengthChange" : false,
							"bFilter" : false,
							"aoColumns" : [ // 定义表头信息
									{
										"sTitle" : "用户名",
										"sClass" : "center",
										"mData" : "user_name"
									},
									{
										"sTitle" : "真实姓名",
										"sClass" : "center",
										"mData" : "real_name"
									},

									{
										"sTitle" : "角色",
										"sClass" : "center",
										"mData" : function(rowData, type, val) {
											switch (rowData.role) {
											case 0:
												return "<p>系统管理员</p>";
											case 1:
												return "<p>系统审计员</p>";
											case 2:
												return "<p>系统安全员</p>";
											case 3:
												return "<p>业务员</p>";
											}
										}
									},
									{
										"sTitle" : "优先级",
										"sClass" : "center",
										"mData" : function(rowData, type, val) {
											switch (rowData.level) {
											case 0:
												return "<p>默认</p>";
											case 1:
												return "<p>低级</p>";
											case 2:
												return "<p>中级</p>";
											case 3:
												return "<p>高级</p>";
											}
										}
									},
									{
										"sTitle" : "是否限制IP",
										"sClass" : "center",
										"mData" : function(rowData, type, val) {
											if (rowData.is_filter)
												return "<p>是</p>";
											else
												return "<p>否</p>";

										}

									},
									{
										"sTitle" : "状态",
										"sClass" : "center",
										"mData" : function(rowData, type, val) {
											if (rowData.enable)
												return "<p>正常</p>";
											else
												return "<p>锁定</p>";

										}

									},
									{
										"sTitle" : "创建时间",
										"sClass" : "center",
										"mData" : "create_date"

									},

									{
										"sTitle" : "操作",
										"sClass" : "center",
										"mData" : function(rowData, type, val) {
											var edit = makeEditButton("编辑",
													"warning",
													"user/admin/edit/info",
													rowData.user_name);
											var del = makeDeleteButton(
													"user/admin/delete",
													rowData.user_name, "删除成功");

											return edit + " " + del;
										}

									} ],

							"aaSorting" : [ [ 7, "desc" ] ], // 从0开始
							"aoColumnDefs" : [ //bSort打开后有效; bSortable:是否排序;bSearchable：是否可搜索；bVisible：是否可见；aTargets：哪一列
							{
								"bSortable" : false,
								"aTargets" : [ 0 ]
							}, {
								"bSortable" : false,
								"aTargets" : [ 1 ]
							}, {
								"bSortable" : false,
								"aTargets" : [ 2 ]
							}

							],
							"iDisplayLength" : 20,
							"bPaginate" : true, // 翻页功能，默认为true
							"bSort" : false, // 排序功能，默认为true
							"bInfo" : true, // 页脚信息，默认为true
							"sPaginationType" : "full_numbers", // 分页样式，默认是两个箭头，full_numbers是数字显示
							"aLengthMenu" : [ [ 20, 50, 100, -1 ],
									[ 20, 50, 100, "All" ] ], // 每页显示数量下拉框内容
							"oLanguage" : { // 修改语言
								"sLengthMenu" : "每页显示 _MENU_条",
								"sZeroRecords" : "没有找到符合条件的数据",
								"sProcessing" : "正在加载...",
								"sInfo" : "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
								"sInfoEmpty" : "没有记录",
								"sInfoFiltered" : "(从 _MAX_ 条记录中过滤)",
								"sSearch" : "搜索：",
								"oPaginate" : {
									"sFirst" : "首页",
									"sPrevious" : "前一页",
									"sNext" : "后一页",
									"sLast" : "尾页"
								}
							}

						});
				// table width
				$('.dataTable').css("width", "100%");
				// 列宽控制函数定义
				function widthControl(widthNum) {
					//$("thead th:eq(0)").css('width', '32px');
					//$("thead th:eq(0) .DataTables_sort_wrapper").css(
					//		'padding-right', '11px');
					//$("thead th:eq(0) .myCheckbox_header").next(
					//		'.DataTables_sort_icon').css('display',
					//		'none');
					for (var i = 0; i < widthNum.length; i++) {
						$("thead th:eq(" + i + ")").css('width', widthNum[i]);
					}
					;
				}
				;

				// 列宽控制函数引用
				widthControl([ '10%', '10%', '15%', '10%', '10%', '10%', '20%',
						'15%' ]);
				// 说明：第一列是checkbox，我在上面设置了固定32px的宽度，其余剩下几列，这里就写几个参数。单位可以是百分比或是px等任何CSS可以识别的单位。						
			});
</script>
</head>
<body class="inFrame">

	<div class="container-fliud welcomeWrap">
		<div class="row">
			<div class="col-md-12">
				<ol class="breadcrumb">
					<li>当前位置：</li>
					<li class="active">用户管理</li>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<a class="btn btn-wx add_user" role="button" href="user/admin/add">添加用户</a>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div id="demo"></div>
			</div>
		</div>
	</div>


</body>
</html>


