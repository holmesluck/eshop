$(document).ready(function () {
	//没有输入成功情况下禁止点击事件
	$("#submit").bind("click",function(){
		if ($(this).hasClass("disabled")) {
	        return false;
	    };
	    $.post("userModifyPassword.action",{
	    	password:$("#hwd-newPassword").val()
	    },function(response){
	    	if(response.status=="密码修改成功"){
	    		alert(response.status);
	    		$("#hwd-home").click();
	    		}else{
	    			alert(response.status);
	    		}
	    },"json");
	});
	 //验证密码
    $("#hwd-newPassword").bind("blur",function(){
        if($(this).val()==""){return false;}
        var val = $(this).val();
        var reg = /^[a-zA-Z0-9_-]{6,16}$/;
        if(reg.test(val)){
            $(this).next().removeClass("hwd-wrong").addClass("hwd-right");
        }else{
            $(this).next().removeClass("hwd-right").addClass("hwd-wrong");
        }
        if($("#hwd-newPassword").next().hasClass("hwd-right")&&$("#hwd-newPasswordAgin").next().hasClass("hwd-right"))
			$("#submit").removeClass("disabled");
    });
    // 验证再次输入密码
    $("#hwd-newPasswordAgin").bind("blur",function(){
        if($(this).val()==""){return false;}
        var val = $(this).val();
        var val2 = $("#hwd-newPassword").val();
        if(val == val2){
            $(this).next().removeClass("hwd-wrong").addClass("hwd-right");
        }else{
            $(this).next().removeClass("hwd-right").addClass("hwd-wrong");
        }
        if($("#hwd-newPassword").next().hasClass("hwd-right")&&$("#hwd-newPasswordAgin").next().hasClass("hwd-right"))
			$("#submit").removeClass("disabled");
    });
});