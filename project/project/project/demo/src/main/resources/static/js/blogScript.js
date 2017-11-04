$(document).ready(function(){
	$("#messages li.info").addClass("alert alert-info");
	$("#messages li.error").addClass("alert alert-danger");
	
	$("#messages li").click(function(){
		$(this).fadeOut();
	});
	setTimeout(function(){
		$("#messages li.info").fadeOut();
	},3000);
});