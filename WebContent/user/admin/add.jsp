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
<title>添加用户</title>

<link rel="stylesheet" href="bs/css/bootstrap.css" />
<link rel="stylesheet" href="public/css/components.css" />
<link rel="stylesheet" href="public/css/inframe.css" />
<link rel="stylesheet"
	href="bs-validator/css/bootstrapValidator.min.css" />

<script type="text/javascript" src="jquery/jquery.1.9.1.min.js"></script>
<script src="bs/js/bootstrap.js"></script>
<script src="bs-validator/js/bootstrapValidator.min.js"></script>
<script>
	$(document).ready(function() {
		$('#form').bootstrapValidator({
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				'form.user_name' : {
					validators : {
						notEmpty : {
							message : '要求不能为空'
						},
						regexp : {
							regexp : /^[a-zA-Z0-9]+$/,
							message : '要求数字或字母'
						},
						stringLength : {
							min : 6,
							max : 18,
							message : '要求长度为6-18'
						}
					}
				},
				'form.real_name' : {
					validators : {
						notEmpty : {
							message : '要求不能为空'
						},
						regexp : {
							regexp : /^[a-zA-Z\u4e00-\u9fa5]+$/,
							message : '要求字母或汉字'
						},
						stringLength : {
							max : 18,
							message : '要求长度不超过18'
						}
					}
				},
				'form.password' : {
					validators : {
						notEmpty : {
							message : '要求不能为空'
						},
						regexp : {
							regexp : /[A-Za-z].*[0-9]|[0-9].*[A-Za-z]/,
							message : '要求数字和字母组合'
						},
						stringLength : {
							min : 6,
							max : 18,
							message : '要求长度为6-18'
						}
					}
				},
				'form.filter_ip' : {
					validators : {
						ip : {
							ipv4 : true,
							ipv6 : false,
							message : '要求ipv4格式'
						}
					}
				}
			},
			submitHandler : function(validator, form, submitButton) {
				validator.defaultSubmit();
			}
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
					<li><a href="user/admin/list/info">用户管理</a></li>
					<li class="active">添加用户</li>
				</ol>
			</div>
		</div>
		<form action="user/admin/save" method="post" id="form">
			<div class="row">
				<div class="col-md-12">
					<div class="form-horizontal user_form">
						<fieldset>
							<div class="form-group">
								<label class="col-sm-2 control-label">用户名</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" placeholder="用户名"
										name="form.user_name" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">真实姓名</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" placeholder="真实姓名"
										name="form.real_name" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">密码</label>
								<div class="col-sm-4">
									<input type="password" class="form-control" placeholder="请输入密码"
										name="form.password" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">角色：</label>
								<div class="col-sm-4">
									<select class="form-control" disabled name="form.role">
										<option value="0">系统管理员</option>
										<option value="1">系统审计员</option>
										<option value="2">系统安全员</option>
										<option selected="selected" value="3">业务员</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">用户等级：</label>
								<div class="col-sm-4">
									<select class="form-control" disabled name="form.level">
										<option selected="selected" value="0">默认</option>
										<option value="1">低级</option>
										<option value="2">中级</option>
										<option value="3">高级</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">限制IP</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" placeholder="请输入ip地址"
										name="form.filter_ip" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">是否开启IP限制</label>
								<div class="col-sm-4">
									<label class="radio-inline"> <input type="radio"
										name="form.is_filter" value="true" />是
									</label> <label class="radio-inline"> <input type="radio"
										name="form.is_filter" value="false" checked /> 否
									</label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">状态</label>
								<div class="col-sm-4">
									<label class="radio-inline"> <input type="radio"
										name="form.enable" value="true" checked />正常
									</label> <label class="radio-inline"> <input type="radio"
										name="form.enable" value="false" /> 锁定
									</label>
								</div>
							</div>

						</fieldset>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<!-- <a class="btn btn-success">保存修改</a> -->
								<button type="submit" class="btn btn-success">保存</button>

							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>

</body>
</html>