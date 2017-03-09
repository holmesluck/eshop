/*这是页面公共模块的JS代码
包括注册和登录模块
@author 黄伟铎
*/
$(document).ready(function () {
	if($("#hwd-getSeddion").text()!=""){
		login = true;
	}else{
		login = false;
	}
     //进入登录模块之前先清除该模块内的数据
     $('.hwd-loginButton').bind("click",function(){
         $("#hwd-login-userEmail").val("");
         $("#hwd-login-userPassword").val("");
         $("#hwd-login-remember").removeAttr("checked");
     });
	// 登录提交事件
	$("#hwd-login-submit").unbind('click');
    $("#hwd-login-submit").bind('click',function(){
    	if($("#hwd-login-remember").attr("checked")){
			var remember = "checked";
		}else{
			remember = "unchecked";
		}
    	$.post("userLogin.action",{
    		userEmail:$("#hwd-login-userEmail").val(),
    		userPassword:$("#hwd-login-userPassword").val(),
    		remember: remember
    	}, function (response, status) {
    		if(response.status=="成功"){
                $("#login").modal('hide');
                window.location.reload(true);
            }else{
                alert(response.status);
            }
        },"json");
    });
    // 在登录模块上点击注册按钮会先隐藏掉登录页面，再进入显示注册模块
    $("#hwd-registerButton").unbind("click");
    $("#hwd-registerButton").bind("click",function(){
    	$("#login").modal('hide');
    });
    $(".hwd-registerButton").unbind("click");
    $(".hwd-registerButton").bind("click",function(){
        $("#register input").each(function(){
            $(this).val("");
        });
    });
    /*
     *对注册表单的每一个输入进行判断
     */
    // 判断邮箱
    $("#hwd-register-userEmail").bind("blur",function(){
        if($(this).val()==""){return false;}
        var val = $(this).val();
        $("#hwd-register-userEmail").next().next().text("");
        var reg = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/;
        if(reg.test(val)){
            $(this).next().removeClass("hwd-wrong").addClass("hwd-right");
    		$.post("userRegistCheckEmail.action",{
    			userEmail:$("#hwd-register-userEmail").val()
            }, function (response) {
                if(response.registed==false){
                    $("#hwd-register-userEmail").next().removeClass("hwd-wrong").addClass("hwd-right");
                }else{
                    $("#hwd-register-userEmail").next().removeClass("hwd-right").addClass("hwd-wrong");
                    $("#hwd-register-userEmail").next().next().text("邮箱已经被注册");
                }
            },"json");
        }else{
            $(this).next().removeClass("hwd-right").addClass("hwd-wrong");
        }
        var i = 0;
		$("#register input").each(function(){
			if(!($(this).next().hasClass("hwd-right")))
				i++;
		});
		if(i==0){
			$("#hwd-register").removeClass("disabled");
		}else{
			$("#hwd-register").addClass("disabled");
			bindRegister();
		}
    });
    // 验证昵称
    $("#hwd-register-userNickname").bind("blur",function(){
        var val = $(this).val();
        var reg =  /[\w\u4e00-\u9fa5_-]{2,16}/;                                   // /^[a-zA-Z0-9_-]{4,16}$/;
        if(reg.test(val)||$(this).val()==""){
            $(this).next().removeClass("hwd-wrong").addClass("hwd-right");
        }else{
            $(this).next().removeClass("hwd-right").addClass("hwd-wrong");
        }
        var i = 0;
		$("#register input").each(function(){
			if(!($(this).next().hasClass("hwd-right")))
				i++;
		});
		if(i==0){
			$("#hwd-register").removeClass("disabled");
		}else{
			$("#hwd-register").addClass("disabled");
			bindRegister();
		}
    });
    //验证密码
    $("#hwd-register-userPassword").bind("blur",function(){
        if($(this).val()==""){return false;}
        var val = $(this).val();
        var reg = /^[a-zA-Z0-9_-]{6,16}$/;
        if(reg.test(val)){
            $(this).next().removeClass("hwd-wrong").addClass("hwd-right");
        }else{
            $(this).next().removeClass("hwd-right").addClass("hwd-wrong");
        }
        var i = 0;
		$("#register input").each(function(){
			if(!($(this).next().hasClass("hwd-right")))
				i++;
		});
		if(i==0){
			$("#hwd-register").removeClass("disabled");
		}else{
			$("#hwd-register").addClass("disabled");
			bindRegister();
		}
    });
    // 验证再次输入密码
    $("#hwd-register-passwordAgain").bind("blur",function(){
        if($(this).val()==""){return false;}
        var val = $(this).val();
        var val2 = $("#hwd-register-userPassword").val();
        if(val == val2){
            $(this).next().removeClass("hwd-wrong").addClass("hwd-right");
        }else{
            $(this).next().removeClass("hwd-right").addClass("hwd-wrong");
        }
        var i = 0;
		$("#register input").each(function(){
			if(!($(this).next().hasClass("hwd-right")))
				i++;
		});
		if(i==0){
			$("#hwd-register").removeClass("disabled");
		}else{
			$("#hwd-register").addClass("disabled");
			bindRegister();
		}
    });
        
    // 注册事件
    function bindRegister(){
    $("#hwd-register").unbind("click");
    $("#hwd-register").bind("click",function(){
        if ($(this).hasClass("disabled")) {
            return false;
        };
        $.post("userRegistEmailSend.action",{
            userEmail:$("#hwd-register-userEmail").val(),
            userPassword:$("#hwd-register-userPassword").val(),
            userNickname:$("#hwd-register-userNickname").val()
        }, function (response, status) {
            if(response==true){
                alert("注册信息已发送到您的邮件，请注意查收");
                $("#register").modal('hide');
                // window.location.reload(true);
            }else{
                alert("注册失败");
            }
        },"json");
    });
    }
    
    // 退出登录
    $("#logout").unbind("click");
    $("#logout").bind("click",function(){
        $.post("userLogout.action", function (response, status) {
                alert("退出成功");
                window.location.reload(true);
        },"json");
        return false;
    });
    //拦截器
     /*$(".hwd-interceptor2").each(function(){
     	$(this).unbind("click");
     	$(this).bind("click",function(){
     		if(login==false){
     			alert("您还未登录，请先登录");
                 $("#login").modal('show');
                 return false;
     		}
     		return true;
     	});
     });*/
    $("#hwd-login-findPass").unbind("click");
    $("#hwd-login-findPass").bind("click",function(){
    	if($("#hwd-login-userEmail").val()==""){alert("请输入您的注册邮箱");return false;}
    	$.post("findPasswordSendMail.action",{
    		userEmail:$("#hwd-login-userEmail").val()
    	},function(response){
    		if(response.status=="success"){
    			alert("关于找回密码的邮件已经发到您的邮箱，请注意查收");
    		}else{
    			alert("此邮箱未注册，请重新输入！");
    		}
    	},"json");
    	return false;
    });
    //返回顶部
    (function() {
    var $backToTopTxt = "返回顶部", $backToTopEle = $('<div class="backToTop"></div>').appendTo($("body"))
        .text($backToTopTxt).attr("title", $backToTopTxt).click(function() {
            $("html, body").animate({ scrollTop: 0 }, 120);
    }), $backToTopFun = function() {
        var st = $(document).scrollTop(), winh = $(window).height();
        (st > 0)? $backToTopEle.show(): $backToTopEle.hide();    
        //IE6下的定位
        if (!window.XMLHttpRequest) {
            $backToTopEle.css("top", st + winh - 166);    
        }
    };
    $(window).bind("scroll", $backToTopFun);
    $(function() { $backToTopFun(); });
})();
    bindRegister();
});