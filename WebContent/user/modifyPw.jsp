<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.jet.vframe.sys.user.pojo.User"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	User webUser = (User) session.getAttribute("webUser");
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>user edit</title>




<link rel="stylesheet" href="bs/css/bootstrap.min.css" />

<script src="jquery/jquery-2.0.3.min.js"></script>
<script src="bs/js/bootstrap.min.js"></script>
<!-- bs-validator -->
<link rel="stylesheet"
	href="bs-validator/css/bootstrapValidator.min.css">
<script src="bs-validator/js/bootstrapValidator.min.js"></script>
<!--END bs-validator -->

<script>
	$(document).ready(function() {
		$('.form-test').bootstrapValidator({
			submitHandler : function(validator, form, submitButton) {
				// Use Ajax to submit form data
				alert("test");
			},
			submitButtons : 'button[type="submit"]',
			message : 'This value is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				username : {
					validators : {
						notEmpty : {
							message : '不可为空'
						},
						regexp : {
							regexp : /^[a-zA-Z0-9_]+$/,
							message : '只能接受 数字、字母和下划线'
						}
					}
				},
				password : {
					validators : {
						notEmpty : {
							message : '不可为空'
						},
						greaterThan : {
							message : '长度必须大于或等于6',
							value : 4,
							inclusive : true
						}

					}
				}
			}
		});
	});

	function submit() {
		$('.form-test').data('bootstrapValidator').validate();
		var res = $('.form-test').data('bootstrapValidator').isValid();

	}
</script>
</head>
<body>

	<div class="container">
		<h1>验证插件测试</h1>
		<form class="form-horizontal form-test" role="form">
			<div class="form-group">
				<label for="username" class="col-sm-2 control-label">用户名：</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="username"
						name="username" placeholder="请输入用户名">
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-2 control-label">密码：</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="password"
						name="password" placeholder="请输入6-18位密码">
				</div>
			</div>
			<div class="form-group">
				<label for="confirm-password" class="col-sm-2 control-label">确认密码：</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="confirm-password"
						name="confirm-password" placeholder="请输入与上方一致的密码">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<div class="checkbox">
						<label> <input type="checkbox"> 记住我
						</label>
					</div>
				</div>
			</div>

		</form>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="button" onclick="javascript:submit();"
					class="btn btn-default">注册</button>
			</div>
		</div>


	</div>
</body>
</html>