<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>敏感词过滤</title>
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

<script type="text/javascript"
	src="uploadifyHtml5/jquery.uploadifive.min.js"></script>
<link rel="stylesheet" href="uploadifyHtml5/uploadifive.css" />

<script>
	var exampleTable;
	$(document).ready(function() {
		$('#demo').html('<table class="display" id="exampleTable"></table>');

		exampleTable = $('#exampleTable').dataTable({
			"herderstyle" : "center",
			"bProcessing" : true,
			"bServerSide" : true, //指定从服务器端获取数据，默认为false，在开发中都是设置为true，这样每次对table中行数据做修改后，调用datatable
			//的fnDraw()可以实现ajax动态刷新table数据
			//在这里不会有修改表单的情况，so，设置为false，因为设置为true，datatable的过滤功能将失效
			"sAjaxSource" : "appwebmin/sysconfig/admin/dataGrid",
			"bJQueryUI" : true, // 使用自定义主题
			"bLengthChange" : false,
			"bFilter" : false,
			"aoColumns" : [ // 定义表头信息
			 {
				"sTitle" : "敏感关键词",
				"sClass" : "center",
				"mData" : "keyword"
			},{
				"sTitle" : "操作",
				"sClass" : "center",
				"mData" : function(rowData, type, val) {
					var del = makeDeleteButton(
							"appwebmin/sysconfig/admin/delete",
							rowData.keyword, "删除成功");

					return  del;
				}

			}
			],

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
			"aLengthMenu" : [ [ 20, 50, 100, -1 ], [ 20, 50, 100, "All" ] ], // 每页显示数量下拉框内容
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
		widthControl([ '50%','50%']);
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
					<li>系统管理</li>
					<li>系统配置</li>
					<li class="active">敏感词设置</li>
				</ol>
			</div>
		</div>
		<div class="row">
			<!--  
			<div class="col-md-1">
				<a href="" class="btn btn-success" role="button">导入日志</a>
			</div>
			-->
			<div class="col-md-1">
				<a href="appwebmin/sysconfig/admin/add" class="btn btn-warning">新增</a>
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


