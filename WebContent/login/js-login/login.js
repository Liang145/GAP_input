$(document).ready(function() {
	$(".menu-level3").slideUp(100);
	
	$(".dropdown-toggle").click(function() {

		$(".input-group-btn").toggleClass('open');

	});
	$(".background").click(function() {
			$(".input-group-btn").removeClass('open');
	});
	$(".forClick").click(function() {
			$(".input-group-btn").removeClass('open');
	});

	$(".user-group").click(function() {
		$(this).children('.menu-level3').slideToggle(200);
		$(this).toggleClass('group-open');
	});
	$(".menu-level3 li").click(function() {
		$(".username").val($(this).children('.userNameValue').text());

		userID0 = $(this).children(".userID").text();
		userGroupName0 = $(this).parent('.menu-level3').parent('.user-group').children('.userGroupName').text();
		userGroupID0 = $(this).parent('.menu-level3').parent('.user-group').children('.userGroupID').text();
	
		$(".userID0").val(userID0);
		$(".userGroupName0").val(userGroupName0);
		$(".userGroupID0").val(userGroupID0);

		$(".input-group-btn").toggleClass('open');
	});

	/**********

	// ҳ�������ɺ�ִ����䣬�������ش�����Ϣ��
	$('.errorInfo').hide();

	// ��Ҫ������ʾ��ʱ��ִ������Ĵ��룬.html()������ݾ�����ʾ�����ݿ��������ֻ�HTML��ǩ��
	$('.errorInfo').html(123).slideDown('100');

	// �Ƴ�������ʾ��ʱ��ִ������Ĵ���
	$('.errorInfo').slideUp('100');
	
	**********/


});

