$(document).ready(function () {
	//隐藏掉已经下架的商品
	$('.isdeltrue').each(function(){
		$(this).addClass("hidden");
	});
	//动态加载二级目录
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
	//添加商品
    $("#hwd-addSubmit").bind("click",function(){
    	$.post("adminAddGoods.action",{
    		goodsName :$("#goodsName").val(),
    		goodsFirstCatalogId :$("#goodsFirstCatalogId").val(),
    		goodsSecondCatalogId :$("#goodsSecondCatalogId").val(),
    		goodsDescription :$("#goodsDescription").val(),
    		goodsModel :$("#goodsModel").val(),
    		goodsOperationSystem :$("#goodsOperationSystem").val(),
    		goodsProcesser :$("#goodsProcesser").val(),
    		goodsMemory :$("#goodsMemory").val(),
    		goodsHarddiskCapacity :$("#goodsHarddiskCapacity").val(),
    		goodsScreenSize :$("#goodsScreenSize").val(),
    		goodsResolutionDefinition :$("#goodsResolutionDefinition").val(),
    		goodsPrice :$("#goodsPrice").val(),
    		goodsStock :$("#goodsStock").val(),
    		goodsDiscount :$("#goodsDiscount").val()
    	},function(response){
    		if(response=="success"){
    			alert("添加成功");
        		window.location.reload(true);
    		}else{
    			alert("添加失败，请检查后重试");
    		}
    		
    	},"json");
    });
    //删除商品
    $(".hwd-deleteGoods").each(function(){
    	$(this).unbind("click");
    	$(this).bind("click",function(){
    		var r = confirm("确认删除？");
        	if(r!=true){return false;}
    		$.post("adminDeleteGoods.action",{
    			goodsId:$(this).attr("id")
    		},function(response){
    			if(response=="success"){
    				alert("删除成功");
    				window.location.reload(true);
    			}
    		},"json");
        	return false;
    	});
    });
    //添加商品
    $("#adminAddGoods_0").bind("click",function(){
    	if($("#goodsName").val()==""){
			alert("请填写商品名称");
			return false;
		}
		if($("#goodsFirstCatalogId").val()==0){
			alert("请选择一级目录");
			return false;
		}
		if($("#adminAddGoods_myFile").val()==""){
			alert("请上传商品图片 ");
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
			alert("折扣应为0-1的数值");
			return false;
		}
		if(discount<=0||discount>1){
			alert("折扣应为0-1的数值");
			return false;
		}
    });
});