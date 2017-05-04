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
<title>Insert title here</title>
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
				'form.ip' : {
					validators : {
						notEmpty : {
							message : '要求不能为空'
						},
						ip : {
							ipv4 : true,
							ipv6 : false,
							message : '要求ipv4格式'
						}
					}
				},
				'form.gateway' : {
					validators : {
						notEmpty : {
							message : '要求不能为空'
						},
						ip : {
							ipv4 : true,
							ipv6 : false,
							message : '要求ipv4格式'
						}
					}
				},
				'form.netmask' : {
					validators : {
						notEmpty : {
							message : '要求不能为空'
						},
						ip : {
							ipv4 : true,
							ipv6 : false,
							message : '要求ipv4格式'
						}
					}
				},
				'form.dns1' : {
					validators : {
						ip : {
							ipv4 : true,
							ipv6 : false,
							message : '要求ipv4格式'
						}
					}
				},
				'form.dns2' : {
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
					<li >系统管理</li>
					<li >网络配置</li>
					<li class="active">数据端</li>
				</ol>
			</div>
		</div>
		<c:if test="${!empty tip}">
			<div class="alert alert-info alert-dismissible fade in" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<strong>${tip}</strong>

			</div>
		</c:if>
		<form action="appwebmin/network/admin/save/data" method="post"
			id="form">
			<div class="row">
				<div class="col-md-12">
					<div class="form-horizontal user_form">
						<fieldset>
							<div class="form-group">
								<label class="col-sm-2 control-label">IP</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" placeholder="请输入ip"
										name="form.ip" value="${form.ip}" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">网关</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" placeholder="请输入网关"
										name="form.gateway" value="${form.gateway}" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">子网掩码</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" placeholder="请输入掩码"
										name="form.netmask" value="${form.netmask}" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label">DNS1</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" placeholder="请输入dns"
										name="form.dns1" value="${form.dns1}" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">DNS2</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" placeholder="请输入dns"
										name="form.dns2" value="${form.dns2}" />
								</div>
							</div>
						</fieldset>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
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