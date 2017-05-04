$(document).ready(function($) {
	// 打开保存成功的提示框
	$(".open-alert-success").click(function(event) {
		$(".my-alert.alert-success").slideDown('400');
	});
	// 打开保存失败的提示框
	$(".open-alert-fail").click(function(event) {
		$(".my-alert.alert-danger").slideDown('400');
	});
	// 关闭提示框，点击提示框右侧的X即可关闭，这句代码是为了给X按钮添加关闭事件。
	$(".close-alert").click(function(event) {
		$(".my-alert").slideUp('400');
	});

	// 以下用于控制菜单切换
	$(".switch-menu").click(function() {
		// $(".tab-pane").fadeOut('100'); // 这一行只是切换动画效果，由于fadeOut会给元素添加display属性，所以暂时不使用；
		$("#tab-pane-2").addClass('active in').siblings('.tab-pane').removeClass('active in');
		// 此处控制下方主体内容的切换，#tab-pane-2换成需要跳转到的id；
		$("#top-menu-2").addClass('active').siblings('li').removeClass('active');
		// 此处控制顶部菜单的切换，#top-menu-2换成需要跳转到的id；
	});
});