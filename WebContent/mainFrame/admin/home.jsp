<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.jet.vframe.sys.user.pojo.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<!--  
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	-->
<title>光码设备 | 管理</title>
<link rel="stylesheet" href="public/css/components.css" />
<link rel="stylesheet" href="bs/css/bootstrap.min.css" />
<link rel="stylesheet" href="public/css/public.css" />
<link rel="stylesheet" href="mainFrame/css-mainFrame/mainFrame.css" />
<script src="jquery/jquery-2.0.3.min.js"></script>
<script src="bs/js/bootstrap.min.js"></script>

<!-- bs-validator -->
<link rel="stylesheet"
	href="bs-validator/css/bootstrapValidator.min.css">
<script src="bs-validator/js/bootstrapValidator.min.js"></script>
<!--END bs-validator -->
<script src="json/json2.js"></script>
<script>
	function showModal() {
		$('#myModal').modal('show');
	}
	function updatePassword() {
		var res = $('#form').data('bootstrapValidator').isValid();
		if (res) {
			var modifyPw = {
				'password' : $('#password').val(),
				'password1' : $('#password1').val(),
				'userName' : $('#userName').val()
			};
			$.ajax({
				type : 'POST',
				data : JSON.stringify(modifyPw),
				url : 'user/admin/modifypw',
				contentType : 'application/json',
				success : function(data) {
					alert(data);
				},
				error : function() {
					alert('保存失败');
				}
			});
			$('#myModal').modal('hide');
		}

	}

	$(document).ready(function($) {
		fineHeight = $(window).height() - 111 + 31;
		$(".mainRow, .mainWrap, iframe").height(fineHeight);
		$(".panel-body ul li").click(function() {
			$(this).parents(".panel-group").find('li').removeClass('active');
			$(this).addClass('active');
		});
	});

	function frameJumpTo(frameId, url) {
		$("#" + frameId).attr("src", url);
	}
	$(document).ready(function() {
		$('#form').bootstrapValidator({
			fields : {
				'password' : {
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
				'password1' : {
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
						},
						different : {
							field : 'password',
							message : '要求不能和旧密码相同'
						},
						identical : {
							field : 'password2',
							message : '要求两次密码一致'
						}
					}
				},
				'password2' : {
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
						},
						identical : {
							field : 'password1',
							message : '要求两次密码一致'
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
<body>
	<div class="container-fliud AllWrap">
		<!-- header开始-->
		<div class="row header">
			<div class="col-md-9">
				<h1 class="col-md-4">
					<span class="brand">光码</span><br />非接触式读码交换机(V3.2.0)
				</h1>
				<ul class="nav nav-tabs">
					<c:forEach items="${mainMenuList }" var="mm1">
						<c:if test="${mm1.isDefault == true }">
							<li id="top-menu-${mm1.id }" class="active"><a
								href="#tab-pane-${mm1.id }" data-toggle="tab"> ${mm1.name }</a>
							</li>
						</c:if>
						<c:if test="${mm1.isDefault == false }">
							<li id="top-menu-${mm1.id }"><a href="#tab-pane-${mm1.id }"
								data-toggle="tab">${mm1.name }</a></li>
						</c:if>
					</c:forEach>
				</ul>

			</div>
			<div class="col-md-3">
				<div class="user-info pull-right">
					<img src="mainFrame/images-mainFrame/user.png" alt="" />

					<div class="user-Info-text">
						<p class="welcome">欢迎您，${sessionScope.webUser.real_name}</p>
						<p class="account">
							<a href="javascript:void(0);" onclick="showModal()">修改密码</a> | <a
								href="login/logout">退出</a>
						</p>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<!-- header结束 -->

		<div class="row mainRowWrap">
			<!-- .row里是下方的主体部分。 -->
			<div class="mainRow">
				<div class="tab-content">
					<c:forEach items="${mainMenuList }" var="mm1">
						<c:if test="${mm1.isDefault == true }">
							<!-- 第N个主标签页面页的内容 -->
							<!-- 这里记住，没div没有结束标签是对的，因为这是在一个循环标签里面 -->
							<div class="tab-pane fade in active" id="tab-pane-${mm1.id }">
						</c:if>
						<c:if test="${mm1.isDefault == false }">
							<div class="tab-pane fade" id="tab-pane-${mm1.id }">
						</c:if>

						<!-- 无左侧菜单的页面内容 -->
						<c:if test="${mm1.isUrl == true }">
							<div class="mainWrap">
								<!-- jsp:include 的page属性只能接收字符串，不能接收变量 ，所以使用s:include，在value中加"/"表示是绝对路径，这样"%{#mm1.src}"值就可以写相对basepath的相对路径，从而保持所有路径一致性 -->
								<iframe id="iframe-${mm1.id }" name="iframe" src="${mm1.src }"
									frameborder="0"></iframe>
							</div>
						</c:if>
						<!--end 无左侧菜单的页面内容 -->

						<!-- 有左侧菜单的页面内容(左侧菜单+右侧iframe) -->
						<c:if test="${mm1.isUrl == false }">
							<!-- 左侧菜单 -->
							<div class="panel-group col-md-2 sidebar"
								id="sidebar-nav-${mm1.id }">
								<c:forEach items="${mm1.children }" var="mm2" varStatus="st2">
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a href="#collapse-${mm2.id }" data-toggle="collapse"
													data-parent="#sidebar-nav-${mm2.parentId }"> <span
													class="list-icon">&nbsp;</span> ${mm2.name }
												</a>
											</h4>
										</div>
										<c:if test="${st2.index == 0}">
											<div id="collapse-${mm2.id }"
												class="panel-collapse collapse in">
										</c:if>
										<c:if test="${st2.index != 0}">
											<div id="collapse-${mm2.id }" class="panel-collapse collapse">
										</c:if>
										<div class="panel-body">
											<ul>
												<c:forEach items="${mm2.children }" var="mm3">
													<li><a href="javascript:void(0);"
														onclick="javascript:frameJumpTo('iframe-${mm2.parentId }','${mm3.src }');return false;">${mm3.name }</a></li>
												</c:forEach>
											</ul>
										</div>
										<!-- panel-body结束 -->
									</div>
							</div>
					</c:forEach>
				</div>
				<!--end 左侧菜单 -->

				<!-- 右侧iframe -->
				<div class="col-md-10 mainWrap">
					<iframe id="iframe-${mm1.id }" name="iframe" src="${mm1.src }"
						frameborder="0"></iframe>
				</div>
				<!--end 右侧iframe -->
				</c:if>
				<!--end 有左侧菜单的页面内容(左侧菜单+右侧iframe) -->

			</div>
			<!--end 第N个主标签页面页的内容 -->
			</c:forEach>
			<!--end 第N个主标签页面页的内容 -->
		</div>
		<!-- tab-content结束 -->
	</div>
	<!-- mainRow结束 -->



	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改密码</h4>
				</div>
				<div class="modal-body">

					<div class="container-fliud welcomeWrap">

						<form id="form">

							<div class="row">
								<div class="col-md-12">
									<div class="form-horizontal user_form">
										<fieldset>
											<div class="form-group">
												<label for="username" class="col-sm-3 control-label">用户名</label>
												<div class="col-sm-9">
													<input type="text" name="userName" class="form-control"
														id="userName" readonly="readonly"
														value="${sessionScope.webUser.user_name}" />
												</div>
											</div>
											<div class="form-group">
												<label for="password" class="col-sm-3 control-label">当前密码</label>
												<div class="col-sm-9">
													<input type="password" name="password" class="form-control"
														id="password" name="password_confirm" />
												</div>
											</div>
											<div class="form-group">
												<label for="password1" class="col-sm-3 control-label">新密码</label>
												<div class="col-sm-9">
													<input type="password" name="password1"
														class="form-control" id="password1" name="password1" />
												</div>
											</div>
											<div class="form-group">
												<label for="password2" class="col-sm-3 control-label">确认新密码</label>
												<div class="col-sm-9">
													<input type="password" class="form-control" id="password2"
														name="password2" />
												</div>
											</div>
										</fieldset>
										<div class="form-group">
											<div class="col-sm-offset-3 col-sm-9">
												<button type="button" class="btn btn-success"
													onclick="javascript:updatePassword();">保存</button>
												<button type="button" class="btn btn-danger"
													data-dismiss="modal">返回</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>

			</div>
		</div>
	</div>



	<div class="clear"></div>

	</div>
	</div>
</body>
</html>