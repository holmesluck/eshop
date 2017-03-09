$(document).ready(function () {
	var oldsrc=$(".picture1").attr("src");  //获取当前页面的图片路径
	function addCatalog(){
		var firstCatalog = {};
		firstCatalog["1"] = "联想";
		firstCatalog["2"] = "宏碁";
		firstCatalog["3"] = "戴尔";
		firstCatalog["4"] = "华硕";
		var goodsFirstCatalogId = $("#goodsFirstCatalogId").val();
		for(var i in firstCatalog){
			if(i!=goodsFirstCatalogId){
				$("#goodsFirstCatalogId").append("<option value='"+i+"'>"+firstCatalog[i]+"</option>");
			}
		}
		$.post("adminGetSeCatalogByFiC.action",{firstCatalogId:goodsFirstCatalogId},function(response){
			var data = response.secondCatalogList;
			for(var x in data){
				if(data[x]["secondcatalogId"]!=$("#goodsSecondCatalogId").val())
				$("#goodsSecondCatalogId").append("<option value='"+data[x]["secondcatalogId"]+"'>"+data[x]["secondcatalogName"]+"</option>");
			}
		},"json");
	}
	$("#goodsFirstCatalogId").bind("change",function(){
		var goodsFirstCatalogId=$("#goodsFirstCatalogId").val();
		$("#goodsSecondCatalogId").html("");
		$.post("adminGetSeCatalogByFiC.action",{firstCatalogId:goodsFirstCatalogId},function(response){
			var data = response.secondCatalogList;
			for(var x in data){
				$("#goodsSecondCatalogId").append("<option value='"+data[x]["secondcatalogId"]+"'>"+data[x]["secondcatalogName"]+"</option>");
			}
		},"json");
	});
	
	$("#adminModifyGoods_0").bind("click",function(){
		if($("#goodsName").val()==""){
			alert("请填写商品名称");
			return false;
		}
		if($("#goodsFirstCatalogId").val()==0){
			alert("请选择一级目录");
			return false;
		}
		if($("#adminAddGoods_myFile").val()==""){
			alert("请上传商品图片");
			return false;
		}
		if($("#goodsModel").val()==""){
			alert("请填写商品型号");
			return false;
		}
		if($("#goodsProcesser").val()==""){
			alert("请填写商品处理器");
			return false;
		}
		if($("#goodsMemory").val()==""){
			alert("请填写商品内存");
			return false;
		}
		if($("#goodsHarddiskCapacity").val()==""){
			alert("请填写商品硬盘容量");
			return false;
		}
		if($("#goodsPrice").val()==""){
			alert("请填写商品价格");
			return false;
		}
		if($("#goodsStock").val()==""){
			alert("请填写商品库存");
			return false;
		}
		if($("#goodsDiscount").val()==""){
			alert("请填写商品折扣");
			return false;
		}
		//判断商品价格为浮点正数
		var price = $("#goodsPrice").val();
		var reg = new RegExp("^\\d+(\\.\\d+)?$"); 
		if(!reg.test(price)){
			alert("商品价格应为非负浮点数");
			return false;
		}
		//判断商品库存为正整数
		var stock = $("#goodsStock").val();
		var reg = new RegExp("^[0-9]*[1-9][0-9]*$"); 
		if(!reg.test(stock)){
			alert("库存数应为非负整数");
			return false;
		}
		//判断商品折扣为1-100
		var discount = $("#goodsDiscount").val();
		var reg = new RegExp("^\\d+(\\.\\d+)?$"); 
		if(!reg.test(discount)){
			alert("折扣应为0-1的整数");
			return false;
		}
		if(discount<=0||discount>1){
			alert("折扣应为0-1的数值");
			return false;
		}
	});
	
	//点击提交按钮进行的判断
	$(".struts2submit").bind("click",function(){
		
	$(".struts2submit").attr("href","adminModifyGoods.action?oldGoodsImage="+oldsrc);
	});
	addCatalog();
});