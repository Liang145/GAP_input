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
<title>欢迎 | 微信管理后台</title>
<link rel="stylesheet" href="bs/css/bootstrap.min.css" />
<style>
.jumbotron {
	margin-top: 180px;
	font-family: "微软雅黑", "宋体";
}

.centerWrap {
	width: 550px;
	margin: 0 auto;
}

.error-icon {
	display: block;
	float: left;
	height: 100%;
	font-size: 120px;
	line-height: 168px;
	color: #2697D2;
}

.error-info {
	float: left;
	padding-left: 60px;
}

h1 {
	font-family: "微软雅黑", "宋体";
	line-height:
}

.clear {
	clear: both;
}
</style>

<script type="text/javascript" src="jquery/jquery.1.9.1.min.js"></script>
<script src="bs/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function() {
		function jump(count) {
			window.setTimeout(function() {
				count--;
				if (count > 0) {
					$(".second").html(count);
					jump(count);
				} else {
					location.href = "#";
				}
			}, 1000);
		}
		jump(3);
	});
</script>

</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<div class="centerWrap">
				<span class="glyphicon glyphicon-exclamation-sign error-icon"></span>
				<div class="error-info">
					<h1>出错了！</h1>
					<p>
						您还没有登录，<span class="second">3</span>秒后跳转到登录界面。
					</p>
					<p>
						<a href="#">点击这里立即跳转。</a>
					</p>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>


</body>
</html>