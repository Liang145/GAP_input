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
<title>test for btn</title>
<link rel="stylesheet" href="bs/css/bootstrap.min.css" />
<script src="jquery/jquery-2.1.0.min.js"></script>
<script src="jquery/jquery.form.js"></script>
<script src="bs/js/bootstrap.min.js"></script>
<script>
	var timer = null;
	function getProgress(){
		var now = new Date();
		$.ajax({
			type:'get',
			//dataType:'json',
			url:'appwebmin/common/admin/progress?time='+now.getTime(),
			success:function(data){
				$('#progress').html('正在升级('+data+'%)');
				$('#progress').css('width',data+'%');
			},
			error:function(){
				alert('get progress error');
			}
		});
	}
	function start(){
		$('#progress').html('正在升级(0%)');
		$('#progress').css('width','0%');
		$('#myModal').modal({backdrop:'static',keyboard:false,show:true});
		timer = setInterval('getProgress()',500);
		$('#upload-form').ajaxSubmit({
			url:'appwebmin/common/admin/update',
			type:'post',
			success: function(data){
				window.clearInterval(timer);
				$('#myModal').modal('hide');
				if(data==0){
					alert('升级完成');
						
				}else{
					alert('升级失败(errcode:'+data+')');
				}				
			},
			error:function(){
				window.clearInterval(timer);
				$('#myModal').modal('hide');
				alert('升级失败');
				
			}
			
		});
	}
</script>
</head>
<body>
	<div class="text-center">
		<h4>当前版本信息</h4>
		<h4>输入端:${v_gapreceiver}</h4>
		<h4>编码端:${v_gapencode}</h4>
		<h4>输出端:${v_gapdisplay}</h4>
	</div>
	<form class=" col-md-offset-3 col-md-6" id="upload-form"
		action="appwebmin/common/admin/update" method="post"
		enctype="multipart/form-data">
		<div class="form-group ">
			<label for="update_key" class="col-md-6 control-label">升級密钥</label>
			<div class="col-md-6">
				<input type="text" class="form-control" name="update_key"
					id="update_key" placeholder="升級密钥">
			</div>
		</div>
		<div class="form-group">
			<label for="update_pkg" class="col-md-6 control-label">升級包</label>
			<div class="col-md-6">
				<input type="file" class="form-control" name="update_pkg"
					id="update_pkg">
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-offset-3 col-md-6">
				<button type="button" class="btn btn-warning"
					onclick="javascript:start();">开始升级</button>
			</div>
		</div>
	</form>


	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">系统升级</h4>
				</div>
				<div class="modal-body">
					<div class="progress">
						<div class="progress-bar progress-bar-striped active"
							id="progress" role="progressbar" aria-valuenow="90"
							aria-valuemin="0" aria-valuemax="100" style="width: 90%;">
							60%</div>
					</div>
				</div>

			</div>
		</div>
	</div>

</body>
</html>