// JavaScript Document

//购物车数量减少
$(document).ready(function () {
    $(".yxx-shoppingcartreduce").each(
    		function(){
    			$(this).unbind("click");
    			$(this).bind("click",function(){
    				var cnt = $(this).next("input").val();
    				//alert (cnt);
    				if (cnt<=1){
    		    			alert ("数量不能小于1！");
    		    		}
    		    	else{
	    		    		var temp = --cnt;
	    		    		$(this).next("input").attr("value",temp);
	    		    		//alert (cnt);
	    		    		var ACC=$(this).parent().prev("td").text();
	    		    		//alert (cnt);
	    		    		var account = parseFloat(ACC);
	    		    		//alert (account);
	    		    		var t = temp*account;
	    		    		//alert (t);
	    		    		$(this).parent().next("td").text(t);
    		    		}
    			    var goodsId = $(this).parent().next().next().attr("name");
    			    $.post("modifyCart.action",{
    					   goodsId:goodsId,amount:cnt
    					   
    			   	}, function (response) {
    			       },"json");
    			});
    		}); 
    
    //购物车数量增加
    $(".yxx-shoppingcartadd").each(
    		function(){
    			$(this).unbind("click");
    			$(this).bind("click",function(){
    				var cnt = $(this).prev("input").val();
    				//alert (cnt);
    				var maxNum = $(this).parent().prev().prev("td").text();
    				var max = parseFloat(maxNum);
    				//alert(max);
    				if (cnt>=max){
    		    		alert ("数量不能大于库存！");
    		    		}
    		    	else{
	    		    		var temp = ++cnt;
	    		    		$(this).prev("input").attr("value",temp);
	    		    		//alert (cnt);
	    		    		var ACC = $(this).parent().prev("td").text();
	    		    		//alert (cnt);
	    		    		var account = parseFloat(ACC);
	    		    		//alert (account);
	    		    		var t = temp*account;
	    		    		//alert (t);
	    		    		$(this).parent().next("td").text(t);
    		    		}
    				var goodsId = $(this).parent().next().next().attr("name");
     			    $.post("modifyCart.action",{
     			    	
     					   goodsId:goodsId,amount:cnt
     					   
     			   	}, function (response) {
     			       },"json");
    			});    	    		
    });
    
    //购物车数量失去焦点
    $(".yxx-shoppingcartNumber").each(
    		function(){
    			$(this).unbind("blur");
    			$(this).bind("blur",function(){
    				var maxNum = $(this).parent().prev().prev().text();
    				var max = parseFloat(maxNum);
    				var goodsId = $(this).parent().next().next().attr("name");
    				//alert(max);
    				var cnt = $(this).val();
    				if (cnt>max){
    		    		alert ("数量不能大于库存！");
    		    		$(this).attr("value",max);
    		    		var ACC=$(this).parent().prev("td").text();
    		    		//alert (ACC);
    		    		var account = parseFloat(ACC);
    		    		//alert (account);
    		    		var t = max*account;
    		    		//alert (t);
    		    		$(this).parent().next("td").text(t);
    		    		$.post("modifyCart.action",{
       					   goodsId:goodsId,amount:max
       					   
       			   	}, function (response) {
       			       },"json");
    		    		}
    				if (cnt<1){
    		    		alert ("数量不能小于1！");
    		    		$(this).attr("value","1");
    		    		var ACC=$(this).parent().prev("td").text();
    		    		//alert (ACC);
    		    		var account = parseFloat(ACC);
    		    		//alert (account);
    		    		var t = account;
    		    		//alert (t);
    		    		$(this).parent().next("td").text(t);
    		    		$.post("modifyCart.action",{
       					   goodsId:goodsId,amount:"1"
       					   
       			   	}, function (response) {
       			       },"json");
    		    		}
    				else if((cnt-parseInt(cnt))!=0){
    					var temp = parseInt(cnt);
    					alert ("数量要为整数");
    					$(this).attr("value",temp);
    		    		var ACC=$(this).parent().prev("td").text();
    		    		//alert (ACC);
    		    		var account = parseFloat(ACC);
    		    		//alert (account);
    		    		var t = temp * account;
    		    		//alert (t);
    		    		$(this).parent().next("td").text(t);
    		    		$.post("modifyCart.action",{
       					   goodsId:goodsId,amount:temp
       					   
       			   	}, function (response) {
       			       },"json");
    				}
    				else{
	    				var cnt = $(this).val();
	    				var ACC = $(this).parent().prev("td").text();
	    				var temp = ACC*cnt;
	    				$(this).parent().next("td").text(temp);
	    				$.post("modifyCart.action",{
	     					   goodsId:goodsId,amount:cnt
	     					   
	     			   	}, function (response) {
	     			       },"json");
    				}     			    
    			});   			
    		});
    
    //从购物车中删除商品
    $(".yxx-shoppingcartDelete").each(function(){
/*    	var temp = $(this);*/
	    $(this).unbind("click");
		$(this).bind("click",function(){
			//alert("1");
		    var goodsId=$(this).attr("name");
		    //alert(goodsId);
		    $(".yxx-shoppingcartDelete").attr("href","deleteFromCart.action?goodsId="+goodsId);		   
		});
	});
    
    //移入收藏夹
    $(".yxx-shoppingcartRemove").each(function(){
	    $(this).unbind("click");
		$(this).bind("click",function(){
			//alert("1");
		    var goodsId=$(this).attr("name");
		    //alert(goodsId);
		    $.post("userAddToFavority.action",{
				   goodsId:goodsId
				   
		   	}, function (response) {
		   			if(response==1){	   				   		
		   				alert("成功加入收藏夹");
		            }
		   	        else {alert("已经在收藏夹中");}
		            }
		       ,"json");
		});
	});
    
    //选择商品
    $(".yxx-shoppingcartCheckbox").each(function(){
    	$(this).unbind("click");
    	var state = 0;
    	var goodsId = $(this).val();
    	$(this).bind("click",function(){
    		//alert("1");
    		if ($(this).attr("checked")== "checked"){
    			state = 1;
    		}
    		//alert(state);
    		//alert(goodsId);
    		$.post("modifyCheckStateInCart.action",{
    			checkState:state,
        		goodsId:goodsId
    		},function(response){
    			//alert(response);
    		}
    			,"json");
    	});
    	
    });
    
    //进入结算中心
    
    $(".yxx-shoppingcartSubmit").unbind("click");    
    $(".yxx-shoppingcartSubmit").bind("click",function(){
    	var checked = $("input:checked");
        var idGoods = "";
        var l = checked.length;
        for(var i = 0; i<l; i++){
        	//alert(checked.length);
        	if(i!=l-1)
        		idGoods += checked[i].value+",";
        	else
        		idGoods += checked[i].value;
        }       
    	//alert("1");
    	 if(l==0){
    		 alert("无商品被选择");
    	 }
    	 else
    		 {
    		 $(".yxx-shoppingcartSubmit").attr("href","userEnterPayment.action?selectedId="+idGoods);
    		 }
    	 
    	 
    	 //alert("userEnterPayment.action?selectedId="+idGoods);
    	/*$.post("userEnterPayment.action",{
    		selectedId:idGoods
    	},"json");*/
    });
    $("#hwd-shoppingCart-checkAll").bind("click",function(){
        if($(this).attr("checked")=="checked"){
            $("#hwd-shoppingCart-wrapper input").each(function(){
                $(this).attr("checked", "checked");
            });
        }else{
            $("#yxx-shoppingcartContainer input").each(function(){
                $(this).attr("checked", false);
            });
        }
    });
 });