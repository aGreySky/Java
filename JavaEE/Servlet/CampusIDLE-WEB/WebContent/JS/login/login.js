$(".signin").click(function(){
    this.className="signin focus";
    $(".signup")[0].className="signup";
    $(".input_signin")[0].className="input_signin active";
    $(".input_signup")[0].className="input_signup";
});
$(".signup").click(function(){
    this.className="signup focus";
    $(".signin")[0].className="signin";
    $(".input_signup")[0].className="input_signup active";
    $(".input_signin")[0].className="input_signin";
});

//登录框居中
$(window).resize(function(){ 
		$(".login_cont").css({ 
			position: "absolute", 
			left: ($(window).width() - $(".login_cont").outerWidth())/2, 
			top: ($(window).height() - $(".login_cont").outerHeight())/2 
		});        
	}); 
		$(function(){ 
		$(window).resize(); 
	});
	
//登录图片居中
$(window).resize(function(){ 
		$(".login_pic").css({ 
			position: "absolute", 
			left: ($(window).width() - $(".login_pic").outerWidth())/2, 
			top: ($(window).height() - $(".login_pic").outerHeight())/2 
		});        
	}); 
		$(function(){ 
		$(window).resize(); 
	});

//登录图片与表单的切换
$(document).ready(function(){
	$("#login_pic").click(function() {
		$(this).hide();
		$(".login_cont").show();
	});
});