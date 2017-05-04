<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>投票管理</title>
<link rel="stylesheet" href="bs/css/bootstrap.min.css">
<link rel="stylesheet"
	href="bs-validator/css/bootstrapValidator.min.css" />
<link rel="stylesheet"
	href="bs-datetimepicker/css/bootstrap-datetimepicker.min.css">


<script src="jquery/jquery-2.1.0.min.js"></script>
<script src="bs/js/bootstrap.min.js"></script>
<script src="bs-validator/js/bootstrapValidator.min.js"></script>
<script src="bs-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script
	src="bs-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"
	charset="UTF-8"></script>
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
									min : 19,
									max : 20,
									message : '要求为日期格式YYYY-MM-DD HH:mm:ss'
								}
							}
						}
					},
					submitHandler : function(validator, form, submitButton) {
						validator.defaultSubmit();
					}
				});
				$('#vote-start').datetimepicker({
					language : 'zh-CN',
					format : 'yyyy-mm-dd hh:ii:ss',
					weekStart : 1,
					todayBtn : 1,
					autoclose : 1,
					todayHighlight : 1,
					startView : 2,
					minView : 2
				});
				$('#vote-start').on(
						'hide',
						function(e) {
							$('#form').data('bootstrapValidator').updateStatus(
									'start_date', 'NOT_VALIDATED', null)
									.validateField('start_date');
						});
				
			});
</script>
</head>
<body>
	<c:if test="${!empty tip}">
		<div class="alert alert-info alert-dismissible fade in" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>${tip}</strong>
		</div>
	</c:if>
	
	<div class="setting-basic">
		<form class="form-horizontal" action="appwebmin/sysconfig/admin/time/save"
			method="post" id="form">
			
			
			<div class="form-group">
			<br/>
				<label for="vote-start" class="col-xs-2 control-label">系统时间</label>
				<div class="col-xs-10">

					<input id="vote-start" class="form-control" type="text"
						name="start_date"
						value="${start_date}"
						style="width:200px">
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-success">保存设置</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>