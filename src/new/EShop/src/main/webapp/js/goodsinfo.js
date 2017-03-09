$(document).ready(function () {
	

//加入购物车的事件操作
	 $(".addshoppingcart1").bind("click",function(){
 
	
	    var goodsId=$(this).attr("id");
	    var addshoppingcart =$(this);
	    $.post("addToCart.action",{
			   goodsId:goodsId
			
	   	}, function (response) {
	   		
	   		if(response==1){	   				   		
	   			
	   			alert("加入购物车成功");
	   			window.location.reload(true);
	           }
	   	   else if(response==2) {
	               alert("商品已下架");
	           }
	   	else if(response==3) {
	   		addshoppingcart.addClass("disabled");
            alert("商品无库存");
        }
	else if(response==4) {
		addshoppingcart.addClass("disabled");
            alert("商品库存不足");
            }
	       },"json");

	});
 
   //点击收藏的操作
   $(".addcollection1").bind("click",function(){
	   
	
		    var goodsId=$(this).attr("id");
		  /*  var c=$(this).attr("value");
		    if(c==0)
		    {
		    	$(this).addClass("disabled");
		    	alert("当前库存为0不能收藏");
		    }*/
		    $.post("userAddToFavority.action",{
				   goodsId:goodsId
				   
		   	}, function (response) {
		   		if(response==1){	   				   		
		   			
		   			alert("成功加入收藏夹");
		           }
		   	   else {alert("已经在收藏夹中");}
		       },"json");
		
	});
});