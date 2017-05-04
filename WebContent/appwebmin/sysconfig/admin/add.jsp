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
<title>新增敏感词</title>

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
				'form.keyword' : {
					validators : {
						notEmpty : {
							message : '要求不能为空'
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
					<li>系统管理：</li>
					<li><a href="appwebmin/sysconfig/admin/list">敏感词管理</a></li>
					<li class="active">添加敏感词</li>
				</ol>
			</div>
		</div>
		<form action="appwebmin/sysconfig/admin/save" method="post" id="form">
			<div class="row">
				<div class="col-md-12">
					<div class="form-horizontal user_form">
						<fieldset>
							<div class="form-group">
								<label class="col-sm-2 control-label">敏感词</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" placeholder="敏感词"
										name="form.keyword" />
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