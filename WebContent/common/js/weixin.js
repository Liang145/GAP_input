$(document).ready(function($) {
	var get_width = $('.tile').css('width');
	$('.tile').css('height', get_width);
	$('.tile_header').css('height', get_width);
	var get_margin = $('.tile').css('margin-right');
	$('.tile').css('margin-bottom', get_margin);
	$('.tile_header').css('margin-bottom', get_margin);

	// $(".to_top").click(function(){
	// 	$('body,html').animate({scrollTop:0},400);
	// 	return false;
	// });
});