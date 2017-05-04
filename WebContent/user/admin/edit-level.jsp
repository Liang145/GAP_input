<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑用户</title>

<link rel="stylesheet" href="bs/css/bootstrap.css" />
<link rel="stylesheet" href="public/css/components.css" />
<link rel="stylesheet" href="public/css/inframe.css" />

<script type="text/javascript" src="jquery/jquery.1.9.1.min.js"></script>
<script src="bs/js/bootstrap.js"></script>

</head>
<body class="inFrame">



	<div class="container-fliud welcomeWrap">
		<div class="row">
			<div class="col-md-12">
				<ol class="breadcrumb">
					<li>当前位置：</li>
					<li><a href="user/admin/list/level">等级管理</a></li>
					<li class="active">编辑等级</li>
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

		<form action="user/admin/update/level" method="post" id="form">
			<div class="row">
				<div class="col-md-12">
					<div class="form-horizontal user_form">
						<fieldset>
							<div class="form-group">
								<label class="col-sm-2 control-label">用户名</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" placeholder="用户名"
										name="form.user_name" value="${form.user_name}"
										readonly="readonly" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">真实姓名</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" placeholder="真实姓名"
										name="form.real_name" value="${form.real_name}"
										readonly="readonly" />
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
									<select class="form-control" name="form.level">
										<option value="0"
											<c:if test="${form.level==0}">selected="selected"</c:if>>默认</option>
										<option value="1"
											<c:if test="${form.level==1}">selected="selected"</c:if>>低级</option>
										<option value="2"
											<c:if test="${form.level==2}">selected="selected"</c:if>>中级</option>
										<option value="3"
											<c:if test="${form.level==3}">selected="selected"</c:if>>高级</option>
									</select>
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