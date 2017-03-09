$(document).ready(function () {
$("#yxx-categoryAddbuttom").bind("click",function(){
	$("#yxx-categoryAdd").show();
});
$("#hwd-addCategory-submit").bind("click",function(){
	if($("#catalogName").val()==""){
		alert("请填好目录名");
		return false;
	}
	if($("#firstCatalogId").val()==0){
		alert("请选择父目录");
		return false;
	}
});
$(".hwd-deleteCategory").each(function(){
	$(this).unbind("click");
	$(this).bind("click",function(){
		var r = confirm("确认删除？");
        if(r!=true){return false;}
	});
});
$("#catalogName").unbind("blur");
$("#catalogName").bind("blur",function(){
	if($(this).val()==""){return false;}
	if($("#firstCatalogId").val()==0){return false;}
	
	$.post("checkSecondCatalogName.action",{
		fiCatalogId:$("#firstCatalogId").val(),
		catalogName:$("#catalogName").val()
	},function(response){
		if(response=="重名"){
			alert("该系列名已经存在此父目录下!");
			$("#hwd-addCategory-submit").addClass("disabled");
			
		}
		else {
			$("#hwd-addCategory-submit").removeClass("disabled");
		}
	},"json");
});
$("#firstCatalogId").unbind("blur");
$("#firstCatalogId").bind("blur",function(){
	if($(this).val()==0){return false;}
	if($("#catalogName").val()==""){return false;}
	
	$.post("checkSecondCatalogName.action",{
		fiCatalogId:$("#firstCatalogId").val(),
		catalogName:$("#catalogName").val()
	},function(response){
		if(response=="重名"){
			alert("该系列名已经存在此父目录下!");
			$("#hwd-addCategory-submit").addClass("disabled");
			
		}
		else {
			$("#hwd-addCategory-submit").removeClass("disabled");
		}
	},"json");
});
$("#hwd-addCategory-submit").bind("click",function(){
	if ($(this).hasClass("disabled")) {
        return false;
    };
});

});