$(document).ready(function () {
	
	/* Animations on scroll */
	
	
	$('.js--wp-1').waypoint(function() {
		$('.js--wp-1').addClass('animated bounceInLeft');
	},  {
        offset: '50%;'
    });
	
	$('.js--wp-2').waypoint(function() {
		$('.js--wp-2').addClass('animated bounceInRight');
	},  {
        offset: '50%;'
    });
	
});