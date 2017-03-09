$(document).ready(function () {
    //点击转换昵称为可修改状态
    $("#hwd-personalInfo-change").unbind("click");
    $("#hwd-personalInfo-change").bind("click",function(){
    	$("#hwd-personalInfo-changeUserNickname").val($("#hwd-personalInfo-userNickname").text());
        $("#hwd-personalInfo-userNickname").addClass("hidden");
        $("#hwd-personalInfo-changeUserNickname").removeClass("hidden");
        $("#hwd-personalInfo-change").addClass("hidden");
        $("#hwd-personalInfo-submit").removeClass("hidden");
    });
    //修改昵称
    $("#hwd-personalInfo-submit").unbind("click");
    $("#hwd-personalInfo-submit").bind("click",function(){
    	$.get("userModifyUserNickName.action?userNickName="+$("#hwd-personalInfo-changeUserNickname").val(),function(response){
    		
    		if(response=="success"){
//    			$("#hwd-personalInfo-userNickname").text($('#hwd-personalInfo-changeUserNickname').val());
//    			$("#hwd-personalInfo-userNickname").removeClass("hidden");
//    	        $("#hwd-personalInfo-changeUserNickname").addClass("hidden");
//    			$("#hwd-personalInfo-change").removeClass("hidden");
//    	        $("#hwd-personalInfo-submit").addClass("hidden");
    	        window.location.reload(true);
    		}else{
    			alert("修改失败，请再试一次");
    		}
    	});
    });
    //检查原密码
    $("#hwd-changePassword-oldPassword").bind("blur",function(){
    	if($(this).val()==""){return false;}
    	$.post("userModifyCheckOldPassword.action",{
            password:$("#hwd-changePassword-oldPassword").val()
        }, function (response, status) {
            if(response.status=="right"){
                $("#hwd-changePassword-oldPassword").next().removeClass("hwd-wrong").addClass("hwd-right");
            }else{
                $("#hwd-changePassword-oldPassword").next().removeClass("hwd-right").addClass("hwd-wrong");
            }
        },"json");
    	var i = 0;
		$("#hwd-changePassword input").each(function(){
			if(!($(this).next().hasClass("hwd-right")))
				i++;
		});
		if(i==0){
			$("#hwd-changePassword-submit").removeClass("disabled");
		}
    });
  //验证新密码
    $("#hwd-changePassword-newPassword").bind("blur",function(){
    	if($(this).val()==""){return false;}
        var val = $(this).val();
        var reg = /^[a-zA-Z0-9_-]{6,16}$/;
        if(reg.test(val)){
            $(this).next().removeClass("hwd-wrong").addClass("hwd-right");
        }else{
            $(this).next().removeClass("hwd-right").addClass("hwd-wrong");
        }
        var i = 0;
		$("#hwd-changePassword input").each(function(){
			if(!($(this).next().hasClass("hwd-right")))
				i++;
		});
		if(i==0){
			$("#hwd-changePassword-submit").removeClass("disabled");
		}
    });
    // 验证再次输入密码
    $("#hwd-changePassword-newPasswordAgain").bind("blur",function(){
    	if($(this).val()==""){return false;}
        var val = $(this).val();
        var val2 = $("#hwd-changePassword-newPassword").val();
        if(val == val2){
            $(this).next().removeClass("hwd-wrong").addClass("hwd-right");
        }else{
            $(this).next().removeClass("hwd-right").addClass("hwd-wrong");
        }
        var i = 0;
		$("#hwd-changePassword input").each(function(){
			if(!($(this).next().hasClass("hwd-right")))
				i++;
		});
		if(i==0){
			$("#hwd-changePassword-submit").removeClass("disabled");
		}
    });

    //修改密码
    $("#hwd-changePassword-submit").bind("click",function(){
    	if ($(this).hasClass("disabled")) {
            return false;
        };
        $.post("userModifyPassword.action",{
            password:$("#hwd-changePassword-newPassword").val()
        }, function (response, status) {
        	if(response.status=="密码修改成功"){
        		alert(response.status);
        		window.location.reload(true);
        	}else{
        		alert(response.status);
        		$("#hwd-changePassword input").each(function(){
        			$(this).val("");
        		});
        	}
        },"json");
    });
    //添加地址
    $("#hwd-addAddress-submit").unbind("click");
    $("#hwd-addAddress-submit").bind("click",function(){
    	//验证是否都有填写
		if($("#hwd-addAddress-name").val()==""){alert("请填写完整");return false;}
		if($("#hwd-addAddress-address").val()==""){alert("请填写完整");return false;}
		if($("#hwd-addAddress-phone").val()==""){alert("请填写完整");return false;}
		if($("#hwd-addAddress-zip").val()==""){alert("请填写完整");return false;}
    	$.post("userAddAddress.action",{
    		addressLinkman:$("#hwd-addAddress-name").val(),
    		addressAddress:$("#hwd-addAddress-address").val(),
    		addressPhone:$("#hwd-addAddress-phone").val(),
    		addressPostcode:$("#hwd-addAddress-zip").val()
        }, function (response, status) {
        	if(response=="success"){
        		alert("添加成功");
        		window.location.reload(true);
        	}else{
        		alert("添加失败，请重新尝试");
        		$("#hwd-changePassword input").each(function(){
        			$(this).val("");
        		});
        	}
        },"json");
    	
    });
    //删除地址
    $(".hwd-deleteAddress").unbind("click");
    $(".hwd-deleteAddress").bind("click",function(){
    	var r = confirm("确认删除？");
    	if(r!=true){return false;}
    	$.post("userDeleteAddress.action",{
    		addressId:$(this).attr("id")
        }, function (response) {
        	if(response=="success"){
        		alert("删除成功");
        		window.location.reload(true);
        	}else{
        		alert("删除失败");
        	}
        },"json");
    });
    //选择默认地址
    $("#hwd-address .defaultAddress").each(function(){
    	$(this).unbind("click");
    	$(this).bind("click",function(){
    		$.post("userModifyDefault.action",{
    			addressId:$(this).attr("id")
    		},function(response){
    		},"json");
    	});
    });
    
    
    //隐藏已经失效的地址
    $("#hwd-address tr.addressIsdeltrue").each(function(){
    	$(this).addClass("hide");
    });
    //标记上默认地址
    $("#hwd-address .defaultAddresstrue").each(function(){
    	$(this).attr("checked",true);
    });
});