$(document).ready(function () {
	$("#hwd-discount-submit").unbind("click");
	$("#hwd-discount-submit").bind("click",function(){
		var discount = $("#hwd-discount").val();
		var reg = new RegExp("^-?\\d+$"); 
		if(!reg.test(discount)){
			alert("请输入整数");
			return false;
		}
		if(discount<0||discount>100){
			alert("请输入1-100的整数");
			return false;
		}
	});
});