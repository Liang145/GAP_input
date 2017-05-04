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
<!-- 该页面中链接使用了相对路径，所以下面这句是无效的，可以去除 -->
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎 | 微信管理后台</title>

<script>
	//window.top.location.href使用绝对路径,否则无法兼容iframe里访问的和直接在浏览器访问的两种情况
	window.top.location.href = "<%=basePath%>"+"error/securityError.jsp";
</script>

</head>

</html>