<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<title>通道管理</title>

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
				'form.name' : {
					validators : {
						notEmpty : {
							message : '要求不能为空'
						},
						between : {
							min : 1,
							max : 99,
							message : '要求大小为1-99'
						},
						integer : {
							message : '要求为整数'
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
				},
				'form.mark' : {
					validators : {
						stringLength : {
							max : 20,
							message : '最大长度20'
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
					<li><a href="appwebmin/channel/admin/list">通道管理</a></li>
					<li class="active">编辑通道</li>
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
		<form action="appwebmin/channel/admin/save" method="post" id="form">
			<div class="row">
				<div class="col-md-12">
					<div class="form-horizontal user_form">
						<fieldset>
							<input type="hidden" class="form-control" id="id" name="form.id"
								value="${form.id}" readonly="readonly" />
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">通道名称</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="name"
										placeholder="请输入通道名" name="form.name" value="${form.name}" />
								</div>
							</div>
							<div class="form-group">
								<label for="password" class="col-sm-2 control-label">密码</label>
								<div class="col-sm-4">
									<input type="password" class="form-control" id="password"
										placeholder="请输入密码" name="form.password"
										value="${form.password}" />
								</div>
							</div>
							<div class="form-group">
								<label for="level" class="col-sm-2 control-label">通道等级：</label>
								<div class="col-sm-4">
									<select class="form-control" id="level" name="form.level">
										<c:forEach var="i" begin="1" end="${sessionScope.webUser.level}" step="1">
											<c:choose>
												<c:when test="${i==1}">
													<option
														<c:if test="${form.level==1}">selected="selected"</c:if>
														value="1">低级</option>
												</c:when>
												<c:when test="${i==2}">
													<option
														<c:if test="${form.level==2}">selected="selected"</c:if>
														value="2">中级</option>
												</c:when>
												<c:when test="${i==3}">
													<option
														<c:if test="${form.level==3}">selected="selected"</c:if>
														value="3">高级</option>
												</c:when>
											</c:choose>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="filter_ip" class="col-sm-2 control-label">IP</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="filter_ip"
										placeholder="请输入ip地址" name="form.filter_ip"
										value="${form.filter_ip}" />
								</div>
							</div>
							<div class="form-group">
								<label for="is_filter" class="col-sm-2 control-label">是否开启IP过滤</label>
								<div class="col-sm-4" id="is_filter">
									<label class="radio-inline"> <input type="radio"
										name="form.is_filter" value="true"
										<c:if test="${form.is_filter==true}">checked</c:if> />是
									</label> <label class="radio-inline"> <input type="radio"
										name="form.is_filter" value="false"
										<c:if test="${form.is_filter!=true}">checked</c:if> /> 否
									</label>
								</div>
							</div>
							<div class="form-group">
								<label for="mark" class="col-sm-2 control-label">备注</label>
								<div class="col-sm-4">
									<textarea id="mark" placeholder="备注" rows="2"
										class="form-control" name="form.mark">${form.mark}</textarea>
								</div>
							</div>

							<div class="form-group">
								<label for="mark" class="col-sm-2 control-label">创建时间</label>
								<div class="col-sm-4">
									<input id="mark" placeholder="创建时间" class="form-control"
										name="form.create_date"
										value="<fmt:formatDate value="${form.create_date}" pattern="yyyy-MM-dd HH:mm:ss"/>"
										readonly="readonly" />

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