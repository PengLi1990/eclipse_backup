// JavaScript Document
function check(u,p,c,form){
	if($(u).val()==""){
		//alert("-����Ա��¼������Ϊ��");
		$("#error_name").html("����Ա�û�������Ϊ��");
		$(u).focus();
		return false;
	}
	if($(p).val()==""){
		$("#error_pass").html("����Ա���벻��Ϊ��");
		$(p).focus();
		return false;
	}
	if($(c).val()==""){
		$("#error_verify").html("��֤�벻��Ϊ��");
		$(c).focus();
		return false;
	}
	$(form).submit();
}
function reflashCode(){
	document.getElementById("imgCode").src="verifycode.action?"+Math.random();
}

$(document).ready(function(){				   
//	$("#verifyCode").keyup(function(){
//		$("#verifyCode").val($("#verifyCode").val().toUpperCase());
//	});	
	$("#enter").click(function(){
		check('#username','#password','#verifyCode','#form_login');
	});

	$("#username").hover(function(){
		$("#username").addClass("inputBgin");
	},function(){
		$("#username").removeClass("inputBgin");
	});
	$("#password").hover(function(){
		$("#password").addClass("inputBgin");
	},function(){
		$("#password").removeClass("inputBgin");
	});
	$("#verifyCode").hover(function(){
		$("#verifyCode").addClass("inputBgin_verify");
	},function(){
		$("#verifyCode").removeClass("inputBgin_verify");
	});
});

