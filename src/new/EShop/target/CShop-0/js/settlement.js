
$(document).ready(function () {
	
	//增加地址时对手机号进行的判断
	$("#hwd-addAddress-phone").bind("blur",function(){
        if($(this).val()==""){return false;}
        var val = $(this).val();
        var reg =  /^(\+86)?(1[0-9]{10})$/;
        if(reg.test(val)){
            $(this).next().removeClass("hwd-wrong").addClass("hwd-right");
        }else{
            $(this).next().removeClass("hwd-right").addClass("hwd-wrong");
        }
        var i = 0;
		$("#hwd-addAddress input").each(function(){
			if(!($(this).next().hasClass("hwd-right")))
				i++;
		});
		if(i==0){
			$("#hwd-addAddress-submit").removeClass("disabled");
		}else{
			$("#hwd-addAddress-submit").addClass("disabled");
			changeSuccess();
		}
    });
	//修改地址时对手机号进行判断
	$("#hwd-changeAddress-phone").bind("blur",function(){
        if($(this).val()==""){return false;}
        var val = $(this).val();
        var reg =  /^(\+86)?(1[0-9]{10})$/;
        if(reg.test(val)){
            $(this).next().removeClass("hwd-wrong").addClass("hwd-right");
        }else{
            $(this).next().removeClass("hwd-right").addClass("hwd-wrong");
        }
        var i = 0;
		$("#hwd-changeAddress input").each(function(){
			if(!($(this).next().hasClass("hwd-right")))
				i++;
		});
		if(i==0){
			$("#hwd-changeAddress-submit").removeClass("disabled");
		}else{
			$("#hwd-changeAddress-submit").addClass("disabled");
			changeSuccess();
		}
    });
	//增加地址时对邮编进行的判断
	$("#hwd-addAddress-zip").bind("blur",function(){
        if($(this).val()==""){return false;}
        var val = $(this).val();
        /*var reg = new RegExp("[1-9]\d{5}(?!\d)");*/
        var reg = /^[0-9]{6}$/;
        if(reg.test(val)){
            $(this).next().removeClass("hwd-wrong").addClass("hwd-right");
        }else{
            $(this).next().removeClass("hwd-right").addClass("hwd-wrong");
        }
        var i = 0;
		$("#hwd-addAddress input").each(function(){
			if(!($(this).next().hasClass("hwd-right")))
				i++;
		});
		if(i==0){
			$("#hwd-addAddress-submit").removeClass("disabled");
		}else{
			$("#hwd-addAddress-submit").addClass("disabled");
			changeSuccess();
		}
    });
	//修改地址时对邮编进行判断
	$("#hwd-changeAddress-zip").bind("blur",function(){
        if($(this).val()==""){return false;}
        var val = $(this).val();
      /*  var reg = new RegExp("[1-9]\d{5}(?!\d)");*/
        var reg = /^[0-9]{6}$/;
        if(reg.test(val)){
            $(this).next().removeClass("hwd-wrong").addClass("hwd-right");
        }else{
            $(this).next().removeClass("hwd-right").addClass("hwd-wrong");
        }
        var i = 0;
		$("#hwd-changeAddress input").each(function(){
			if(!($(this).next().hasClass("hwd-right")))
				i++;
		});
		if(i==0){
			$("#hwd-changeAddress-submit").removeClass("disabled");
		}else{
			$("#hwd-changeAddress-submit").addClass("disabled");
			changeSuccess();
		}
    });
	$("#hwd-addAddress-name").bind("blur",function(){
		if($(this).val()!="")
			$(this).next().removeClass("hwd-wrong").addClass("hwd-right");
		else
			$(this).next().removeClass("hwd-right").addClass("hwd-wrong");
		var i = 0;
		$("#hwd-addAddress input").each(function(){
			if(!($(this).next().hasClass("hwd-right")))
				i++;
		});
		if(i==0){
			$("#hwd-addAddress-submit").removeClass("disabled");
		}else{
			$("#hwd-addAddress-submit").addClass("disabled");
			changeSuccess();
		}
	});
	$("#hwd-addAddress-address").bind("blur",function(){
		if($(this).val()!="")
			$(this).next().removeClass("hwd-wrong").addClass("hwd-right");
		else
			$(this).next().removeClass("hwd-right").addClass("hwd-wrong");
		var i = 0;
		$("#hwd-addAddress input").each(function(){
			if(!($(this).next().hasClass("hwd-right")))
				i++;
		});
		if(i==0){
			$("#hwd-addAddress-submit").removeClass("disabled");
		}else{
			$("#hwd-addAddress-submit").addClass("disabled");
			changeSuccess();
		}
	});
	$("#hwd-changeAddress-name").bind("blur",function(){
		if($(this).val()!="")
			$(this).next().removeClass("hwd-wrong").addClass("hwd-right");
		else
			$(this).next().removeClass("hwd-right").addClass("hwd-wrong");
		var i = 0;
		$("#hwd-changeAddress input").each(function(){
			if(!($(this).next().hasClass("hwd-right")))
				i++;
		});
		if(i==0){
			$("#hwd-changeAddress-submit").removeClass("disabled");
		}else{
			$("#hwd-changeAddress-submit").addClass("disabled");
			changeSuccess();
		}
	});
	$("#hwd-changeAddress-address").bind("blur",function(){
		if($(this).val()!="")
			$(this).next().removeClass("hwd-wrong").addClass("hwd-right");
		else
			$(this).next().removeClass("hwd-right").addClass("hwd-wrong");
		var i = 0;
		$("#hwd-changeAddress input").each(function(){
			if(!($(this).next().hasClass("hwd-right")))
				i++;
		});
		if(i==0){
			$("#hwd-changeAddress-submit").removeClass("disabled");
		}else{
			$("#hwd-changeAddress-submit").addClass("disabled");
			changeSuccess();
		}
	});
	//显示不同的页面
	if($(".yxx-addressLinkman").text()==""){
		$("#yxx-settlementBlock1").hide();
		$("#yxx-settlementBlock2").show();
	}
	
	else{
		$("#yxx-settlementBlock1").show();
		$("#yxx-settlementBlock2").hide();
	}
	
	//修改地址
	$(".yxx-settlementButton").unbind("click");
	$(".yxx-settlementButton").bind("click",function(){
//		alert("1");	
//		$.get("userGetAddress.action",function(response){
//			
//			
//		},"json");
		$("#yxx-settlementBlock1").hide();
		$("#yxx-settlementBlock2").show();
	});
	
	//对地址进行修改
	$(".yxx-settlementModify").each(function(){
		$(this).unbind("click");
		$(this).bind("click",function(){
			var current = $(this);
			var man = $(this).parent().prev().prev().prev().prev("td").text();
			var address = $(this).parent().prev().prev().prev("td").text();
			var phone = $(this).parent().prev().prev("td").text();
			var postcode = $(this).parent().prev("td").text();
			var id=$(this).attr("id");
			$("#hwd-getId").text(id);
			$("#hwd-changeAddress-name").val(man);
			$("#hwd-changeAddress-address").val(address);
			$("#hwd-changeAddress-phone").val(phone);
			$("#hwd-changeAddress-zip").val(postcode);
			$("#hwd-addAddress").removeClass("show").addClass("hide");
			$("#hwd-changeAddress").removeClass("hide").addClass("show");
			//insert.insertBefore("$('#yxx-settlementQuxiao')");
			//$(insert).insertBefore($(this).parent().parent().parent().next().next().children('a'));
			
		});
	});
	function changeSuccess(){
	//完成修改
	$("#hwd-changeAddress-submit").unbind("click");
	$("#hwd-changeAddress-submit").bind("click",function(){
		if ($(this).hasClass("disabled")) {
	        return false;
	    };
		$.post("userModifyAddress.action",{
			addressId:$("#hwd-getId").text(),
			addressLinkman:$("#hwd-changeAddress-name").val(),
			addressAddress:$("#hwd-changeAddress-address").val(),
			addressPhone:$("#hwd-changeAddress-phone").val(),
			addressPostcode:$("#hwd-changeAddress-zip").val()
		},function(response){
			if(response=="success"){
		    	alert("你已修改添加地址");
		    	window.location.reload(true);
		    }
			window.location.reload(true);
		},"json");
	});
	//添加地址
    $("#hwd-addAddress-submit").unbind("click");
    $("#hwd-addAddress-submit").bind("click",function(){
    	if ($(this).hasClass("disabled")) {
	        return false;
	    };
    	var name=$("#hwd-addAddress-name").val();
    	var address=$("#hwd-addAddress-address").val();
    	var phone=$("#hwd-addAddress-phone").val();
    	var zip=$("#hwd-addAddress-zip").val();
    	
    	$.post("userAddAddress.action",{
    		addressLinkman:name,
    		addressAddress:address,
    		addressPhone:phone,
    		addressPostcode:zip
		},function(response){
			    if(response=="success"){
			    	alert("你已成功添加地址");
			    	window.location.reload(true);
			    }
			    window.location.reload(true);
		},"json");
	
    });
	}
	//取消修改
/*	$("#yxx-settlementQuxiao").unbind("click");
	$("#yxx-settlementQuxiao").bind("click",function(){
		$("#yxx-settlementBlock1").show();
		$("#yxx-settlementBlock2").hide();
	});*/
	
	//选择地址
	/*function choice(){
		$(".yxx-radio").each(function(){
			$(this).unbind("click");
			$(this).bind("click",function(){
				$("#yxx-settlementBlock1").show();
				$("#yxx-settlementBlock2").hide();
				$(".yxx-addressLinkman").text($(this).next().text());
				$(".yxx-addressPhone").text($(this).next().next().text()) ;
				$(".yxx-addressPostcode").text($(this).next().next().next().text());
				$(".yxx-addressAddress").text($(this).next().next().next().next().text());				
				var addressId = $(this).children("input").val();
				//alert(addressId);	
				$.get("userEnterPayment.action",{									
					selectedAddressId:addressId
				},function(response){
					//alert("1");
					$(".yxx-addressLinkman").text(response.defaultAddress.addressLinkman);
					$(".yxx-addressPhone").text(response.defaultAddress.addressPhone) ;
					$(".yxx-addressPostcode").text(response.defaultAddress.addressPostcode);
					$(".yxx-addressAddress").text(response.defaultAddress.addressAddress);
					
				},"json");
			});			
	    });
	}
	
	choice();*/
	
	//打开添加地址	
	$("#hwd-settlement-addAddress").unbind("click");
	$("#hwd-settlement-addAddress").bind("click",function(){
		$("#hwd-addAddress-name").val("");
		$("#hwd-addAddress-address").val("");
		$("#hwd-addAddress-phone").val("");
		$("#hwd-addAddress-zip").val("");
		$("#hwd-addAddress").removeClass("hide").addClass("show");
		$("#hwd-changeAddress").removeClass("show").addClass("hide");
		return false;
	});

	/*$("#yxx-settlementQueren").unbind("click");
	$("#yxx-settlementQueren").bind("click",function(){
		var insert=" ";
		//alert("1");
		var name = $("#yxx-settlementNewLinkman").val();
		var phone = $("#yxx-settlementNewPhone").val();
		var code = $("#yxx-settlementNewCode").val();
		var address = $("#yxx-settlementNewAddress").val();
		//alert("1");
		if(name == ""||phone == ""||code == ""||address == ""){
			alert("信息填写不完整");
		}
		else if(isNaN( phone )){
			alert("电话输入有误，请输入数值");
		}
		else if(isNaN( code )){
			alert("邮编输入有误，请输入数值");
		}
		else{
			insert+="<tr>"+
        	"<td class='yxx-radio'>"+"<input type='radio' name='address' value=''/>"+"</td>"+
        	"<td class='yxx-address'>"+name+"</td>"+
        	"<td class='yxx-address'>"+phone+"</td>"+
        	"<td class='yxx-address'>"+code+"</td>"+	            				
        	"<td class='yxx-address'>"+address+"</td>"+
        	"<td><a class='btn yxx-settlementModify'>"+"修改"+"</a></td>"+
        "</tr>";
			$("#yxx-settlementTable1").append(insert);
			choice();
			$.post("userAddBuyAddress.action",{
				addressLinkman:$("#yxx-settlementNewLinkman").val(),
				addressAddress:$("#yxx-settlementNewAddress").val(),
				addressPhone:$("#yxx-settlementNewPhone").val(),
				addressPostcode:$("#yxx-settlementNewCode").val()
			},"json");
		}					*/
		/*$(".yxx-addressLinkman").text($("#yxx-settlementNewLinkman").val());
		$(".yxx-addressPhone").text($("#yxx-settlementNewPhone").val()) ;
		$(".yxx-addressPostcode").text($("#yxx-settlementNewCode").val());
		$(".yxx-addressAddress").text($("#yxx-settlementNewAddress").val());*/
/*		$("#yxx-settlementBlock1").show();
		$("#yxx-settlementBlock2").hide();*/
		//
	// });
//隐藏已经失效的地址
	
		$("#hwd-address tr.addressIsdeltrue").each(function(){
	    	$(this).addClass("hide");
	    });
    
    //选择默认地址
    $("#hwd-address .defaultAddress").each(function(){
    	$(this).unbind("click");
    	$(this).bind("click",function(){
    		$.post("userModifyDefault.action",{
    			addressId:$(this).attr("value")
    		},function(response){
    			window.location.reload(true);
    		},"json");
    	});
    });
//标记上默认地址
    $("#hwd-address .defaultAddresstrue").each(function(){
    	$(this).attr("checked",true);
    });
    $("#hwd-settlementSubmit").unbind("click");
    $("#hwd-settlementSubmit").bind("click",function(){
    	if($(".defaultAddresstrue").length==0){
    		alert("请添加寄送地址");
    		return false;
    	}
    });
    changeSuccess();
});
