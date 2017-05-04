<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
<title>Insert title here</title>
<link rel="stylesheet" href="bs/css/bootstrap.css" />
<link rel="stylesheet" href="public/css/components.css" />
<link rel="stylesheet" href="appwebmin/css/inframe.css" />
<script type="text/javascript" src="jquery/jquery.1.9.1.min.js"></script>
<script src="bs/js/bootstrap.js"></script>
<script>
	function shutdown() {
		$.post("appwebmin/power/admin/shutdown");
		$('#shutdownModal').modal('hide');
	}
	function reboot() {
		$.post("appwebmin/power/admin/reboot");
		$('#rebootModal').modal('hide');
	}
</script>
</head>
<body class="inFrame">
	<div class="container-fliud welcomeWrap">
		<div class="row">
			<div class="col-md-12">
				<ol class="breadcrumb">
					<li>当前位置：</li>
					<li>系统管理</li>
					<li class="active">电源管理</li>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<a href="javascript:void(0);"
					onclick="javascript:$('#shutdownModal').modal('show');return false;"
					class="btn btn-success" role="button">&nbsp;&nbsp;&nbsp;关机&nbsp;&nbsp;&nbsp; </a>
			</div>
			<div class="col-md-4">
				<a href="javascript:void(0);"
					onclick="javascript:$('#rebootModal').modal('show');return false;"
					class="btn btn-warning" role="button">&nbsp;&nbsp;&nbsp;重启&nbsp;&nbsp;&nbsp;</a>
			</div>
		</div>
	</div>



	<!-- Modal -->
	<div class="modal fade" id="shutdownModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">关机</h4>
				</div>
				<div class="modal-body">
					<p>确认要关机？</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary"
						onclick="javascript:shutdown();">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- Modal -->
	<div class="modal fade" id="rebootModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">重启</h4>
				</div>
				<div class="modal-body">
					<p>确认要重启？</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary"
						onclick="javascript:reboot();">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</body>
</html>