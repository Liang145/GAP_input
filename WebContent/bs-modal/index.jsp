<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html >
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>微信多条图文回复界面</title>
<!-- dataGrid -->
<link rel="stylesheet"
	href="dataGrid/css-datagrid/jquery.dataTables.css" />
<link rel="stylesheet" href="dataGrid/css-datagrid/demo_table_jui.css" />
<link rel="stylesheet"
	href="dataGrid/css-datagrid/themes/bootstrap_new/jquery-ui-1.10.3.custom.css" />
<link rel="stylesheet" href="dataGrid/css-datagrid/myTables_light.css" />
<!-- jquery.1.9.1.min.js 必须在 jquery.dataTables.min.js 前面 -->
<script type="text/javascript" src="js/jquery.1.9.1.min.js"></script>
<script src="dataGrid/js-datagrid/jquery.dataTables.min.js"></script>
<script src="common/js/btn-datagrid.js"></script>
<!-- end dataGrid -->



<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/public.css" />
<link rel="stylesheet" href="css/components.css" />
<script src="js/bootstrap.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('#imageList').html(
								'<table class="display" id="example"></table>');

						$('#example')
								.dataTable(
										{
											"sDom" : 'rt<"clear">',
											"bProcessing" : true,
											"bServerSide" : true,
											"sAjaxSource" : "appv/media/getMediaList.action?type=image",
											"bJQueryUI" : true, // 使用自定义主题
											"aoColumns" : [ // 定义表头信息
													{
														"sTitle" : " ",
														"sClass" : "center",
														"mData" : function(
																source, type,
																val) {
															var checkBox = " ";
															return checkBox;
														}
													},
													{
														"sTitle" : "预览",
														//"sClass" : "center",
														"mData" : function(
																rowData, type,
																val) {
															var thumb = "<img height='100' width='100' src='appv/media/freeGetMedia.action?id="
																	+ rowData.id
																	+ "' />";
															return thumb;
														}
													},
													{
														"sTitle" : "id",
														"asSorting" : [ "asc" ],
														"mData" : "id",
														"bVisible" : false
													},
													{
														"sTitle" : "名称",
														"asSorting" : [ "asc" ],
														"mData" : "fileName",
														"bVisible" : true
													},
													{
														"sTitle" : "创建日期",
														"asSorting" : [ "desc",
																"asc" ],
														"mData" : "date"
													},
													{
														"sTitle" : "操作",
														//"sClass" : "center",
														"mData" : function(
																rowData, type,
																val) {

															var del = makeEditButton(
																	"删除",
																	"danger",
																	"appv/media/deleteImage.action",
																	"id",
																	rowData.id,
																	null);
															return del;
														}
													} ],

											"aaSorting" : [ [ 4, "desc" ] ], // 以第 5 列倒序排列
											"aoColumnDefs" : [ // 用于指定某一列的设置
											{
												"bSearchable" : false,
												"bVisible" : true,
												"aTargets" : [ 2 ]
											}, {
												"bVisible" : true,
												"aTargets" : [ 3 ]
											}
											// bSearchable：是否可搜索；bVisible：是否可见；aTargets：哪一列
											],
											"bPaginate" : false, // 翻页功能，默认为true
											"bSort" : false, // 排序功能，默认为true
											"bInfo" : false, // 页脚信息，默认为true
											"sPaginationType" : "full_numbers", // 分页样式，默认是两个箭头，full_numbers是数字显示
											"aLengthMenu" : [
													[ 10, 25, 50, -1 ],
													[ 10, 25, 50, "All" ] ], // 每页显示数量下拉框内容
											"oLanguage" : { // 修改语言
												"sLengthMenu" : "每页显示 _MENU_条",
												"sZeroRecords" : "没有找到符合条件的数据",
												"sProcessing" : "正在加载...",
												"sInfo" : "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
												"sInfoEmpty" : "木有记录",
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

						// 列宽控制函数定义(当第一列是checkBox是地使用，为第一列固定32px的宽度，其余列按百分比)  
						function widthControl(widthNum) {
							$("thead th:eq(0)").css('width', '32px');
							$("thead th:eq(0) .DataTables_sort_wrapper").css(
									'padding-right', '11px');
							$("thead th:eq(0) .myCheckbox_header").next(
									'.DataTables_sort_icon').css('display',
									'none');
							for ( var i = 1; i <= widthNum.length; i++) {
								$("thead th:eq(" + i + ")").css('width',
										widthNum[i - 1]);
							}
							;
						}
						;

						// 列宽控制函数引用
						widthControl([ '25%', '25%', '25%', '25%' ]);
						// 说明：当第一列是checkbox，我在上面设置了固定32px的宽度，其余剩下几列，这里就写几个参数。单位可以是百分比或是px等任何CSS可以识别的单位。 

						//modal点击打开后，不等CSS执行完成，窗口还没出现时就会触发
						$('#myModal').on('show.bs.modal', function(e) {
							alert("show");
						});
						//modal点击打开后，等CSS执行完成，窗口出现后触发
						$('#myModal').on('shown.bs.modal', function(e) {
							alert("shown");
						});
						//modal点击关闭后，不等CSS执行完成，触发后窗口才消失
						$('#myModal').on('hide.bs.modal', function(e) {
							alert("hide");
						});
						//modal点击关闭后，等CSS执行完成，窗口完全消失后触发
						$('#myModal').on('hidden.bs.modal', function(e) {
							$(this).removeData("bs.modal");//确保每次弹出窗口都是新加载的数据
						});

					});

	function showModal() {
		$('#myModal').modal('show');
	}
</script>
</head>
<body>
	<!-- Button trigger modal -->
	<a class="btn btn-primary btn-lg" data-toggle="modal"
		data-target="#myModal">Launch demo1 modal</a>

	<a class="btn btn-primary btn-lg" onclick="javascript:showModal();">Launch
		demo1 modal</a>

	<!-- Modal -->

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Modal title</h4>
				</div>
				<div class="modal-body" id="imageList"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

</body>
</html>