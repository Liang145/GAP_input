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
<title>数据日志列表</title>
<link rel="stylesheet" href="bs/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="bs-validator/css/bootstrapValidator.min.css" />
<link rel="stylesheet"
	href="bs-datetimepicker/css/bootstrap-datetimepicker.min.css">
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
<script src="bs-validator/js/bootstrapValidator.min.js"></script>
<script src="bs-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script
	src="bs-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"
	charset="UTF-8"></script>
<script src="common/js/btn-datagrid.js"></script>

<script>
	function showContent(content) {
		$('#content').html(content);
		$('#contentModal').modal('show');
	}

	var exampleTable;
	function deleteDataLog() {
		$('#deleteModal').modal('hide');
		$.ajaxSetup({
			async : false
		});
		$.post("appwebmin/datalog/admin/delete");
		exampleTable.fnDraw();// 重载dataTable
	}

	$(document)
			.ready(
					function() {
						$('#demo')
								.html(
										'<table class="display" id="exampleTable"></table>');

						exampleTable = $('#exampleTable')
								.dataTable(
										{
											"herderstyle" : "center",
											"bProcessing" : true,
											"bServerSide" : true, //指定从服务器端获取数据，默认为false，在开发中都是设置为true，这样每次对table中行数据做修改后，调用datatable
											//的fnDraw()可以实现ajax动态刷新table数据
											//在这里不会有修改表单的情况，so，设置为false，因为设置为true，datatable的过滤功能将失效
											"sAjaxSource" : "appwebmin/datalog/admin/dataGrid",
											"bJQueryUI" : true, // 使用自定义主题
											"bLengthChange" : false,
											"bFilter" : false,
											"aoColumns" : [ // 定义表头信息
													{
														"sTitle" : "id",
														"sClass" : "center",
														"mData" : "id",
														"bVisible" : false
													},
													{
														"sTitle" : "通道名称",
														"sClass" : "center",
														"mData" : "channel_name"
													},
													{
														"sTitle" : "数据类型",
														"sClass" : "center",
														"mData" : function(
																rowData, type,
																val) {
															switch (rowData.content_type) {
															case 0:
																return "文本";
															case 1:
																return "文件";
															}
														}
													},
													{
														"sTitle" : "状态",
														"sClass" : "center",
														"mData" : function(
																rowData, type,
																val) {
															switch (rowData.status) {
															case 0:
																return "接收";
															case 1:
																return "传输";
															case 2:
																return "完成";
															}
														}
													},
													{
														"sTitle" : "操作时间",
														"sClass" : "center",
														"mData" : "create_date"
													},
													{
														"sTitle" : "操作",
														"sClass" : "center",
														"mData" : function(
																rowData, type,
																val) {
															var edit = "<a onclick=\"javascript:showContent(\'"
																	+ rowData.content
																	+ "\');return false;\" class=\"btn btn-warning\">查看内容</a>";
															return edit;
														}

													} ],

											"aaSorting" : [ [ 0, "desc" ] ], // 从0开始
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
											"aLengthMenu" : [
													[ 20, 50, 100, -1 ],
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
								$("thead th:eq(" + i + ")").css('width',
										widthNum[i]);
							}
							;
						}
						;

						// 列宽控制函数引用
						widthControl([ '20%', '20%', '20%', '20%', '20%' ]);
						// 说明：第一列是checkbox，我在上面设置了固定32px的宽度，其余剩下几列，这里就写几个参数。单位可以是百分比或是px等任何CSS可以识别的单位。						
					});
</script>
<script>
	$(document).ready(
			function() {
				$('#form').bootstrapValidator({
					feedbackIcons : {
						valid : 'glyphicon glyphicon-ok',
						invalid : 'glyphicon glyphicon-remove',
						validating : 'glyphicon glyphicon-refresh'
					},
					fields : {
						'start_date' : {
							validators : {
								notEmpty : {
									message : '要求不能为空'
								},
								stringLength : {
									min : 10,
									max : 10,
									message : '要求为日期格式YYYY-MM-DD'
								}
							}
						},
						'end_date' : {
							validators : {
								notEmpty : {
									message : '要求不能为空'
								},
								stringLength : {
									min : 10,
									max : 10,
									message : '要求为日期格式YYYY-MM-DD'
								}
							}
						}
					},
					submitHandler : function(validator, form, submitButton) {
						validator.defaultSubmit();
					}
				});
				$('#start_date').datetimepicker({
					language : 'zh-CN',
					format : 'yyyy-mm-dd',
					weekStart : 1,
					todayBtn : 1,
					autoclose : 1,
					todayHighlight : 1,
					startView : 2,
					minView : 2
				});
				$('#end_date').datetimepicker({
					language : 'zh-CN',
					format : 'yyyy-mm-dd',
					weekStart : 1,
					todayBtn : 1,
					autoclose : 1,
					todayHighlight : 1,
					startView : 2,
					minView : 2
				});
				$('#start_date').on(
						'hide',
						function(e) {
							$('#form').data('bootstrapValidator').updateStatus(
									'start_date', 'NOT_VALIDATED', null)
									.validateField('start_date');
						});
				$('#end_date').on(
						'hide',
						function(e) {
							$('#form').data('bootstrapValidator').updateStatus(
									'end_date', 'NOT_VALIDATED', null)
									.validateField('end_date');
						});

			});
</script>
</head>
<body class="inFrame">

	<div class="container-fliud welcomeWrap">
		<div class="row">
			<div class="col-md-12">
				<ol class="breadcrumb">
					<li>当前位置：</li>
					<li>日志管理</li>
					<li class="active">业务日志</li>
				</ol>
			</div>
		</div>
		
				<form class="form-inline col-md-12" action="appwebmin/datalog/admin/export" method="post"
					id="form">
					<div class="form-group">
					<label for='start_date'>开始时间</label>
					<input id="start_date" class="form-control" type="text" name="start_date"
						value="${start_date}">
					</div>
					<div class="form-group">
					<label for='end_date'>开始时间</label>
					<input id="end_date" class="form-control" type="text" name="end_date"
						value="${end_date}">
					</div>
					<button type="submit" class="btn btn-warning">导出日志</button>
					<a href="javascript:void(0);"
					onclick="javascript:$('#deleteModal').modal('show');return false;"
					class="btn btn-danger">清空日志</a>
				</form>
		<div class="row">
			<div class="col-md-12">
				<div id="demo"></div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="contentModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">内容</h4>
				</div>
				<div class="modal-body">
					<p id="content"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- Modal -->
	<div class="modal fade" id="deleteModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">清空日志</h4>
				</div>
				<div class="modal-body">
					<p>确定要清空当前用户所有通道的日志？</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary"
						onclick="javascript:deleteDataLog();">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

</body>
</html>


