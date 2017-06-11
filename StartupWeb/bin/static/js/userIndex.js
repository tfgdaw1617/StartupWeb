$(function() {
	var $ppc = $('.progress-pie-chart'), percent = parseInt($ppc
			.data('percent')), deg = 360 * percent / 100;
	if (percent > 50) {
		$ppc.addClass('gt-50');
	}
	$('.ppc-progress-fill').css('transform', 'rotate(' + deg + 'deg)');
	$('.ppc-percents span').html(percent + '%');
	
	$('.count').each(function() {
		$(this).prop('Counter', 0).animate({
			Counter : $(this).text()
		}, {
			duration : 20,
			easing : 'swing',
			step : function(now) {
				$(this).text(Math.ceil(now));
			}
		});
	});

	jQuery('.skillbar').each(function() {
		jQuery(this).find('.skillbar-bar').animate({
			width : jQuery(this).attr('data-percent')
		}, 2000);
	});

	$("#imgPerfil").on("mouseenter", function() {
		$("#popupEditaImagen").show();
	}).on("mouseleave", function() {
		$("#popupEditaImagen").hide();
	});
	$("#popupEditaImagen").on("mouseenter", function() {
		$("#popupEditaImagen").show();
	}).on("mouseleave", function() {
		$("#popupEditaImagen").hide();
	});

});
