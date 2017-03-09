$(document).ready(function () {
	//alert("1");
	//加入购物车
	var page = 0;
	function addtofavorite(){
		$(".yxx-favoriteAdd").each(function(){
		    $(this).unbind("click");
			$(this).bind("click",function(){
				//alert("1");
			    var goodsId=$(this).attr("name");
			    var goodsStock = $(this).parent("p").attr("name");
			    //alert(goodsId);
			    $.post("addToCart.action",{
					   goodsId:goodsId,
					   goodsStock:goodsStock
					   
			   	}, function (response) {
			   		
			   		//alert(response.isAddSuccessJson);
			   		//alert(response.isAddSuccessJson);
			   		if(response == 1){	   				   		
			   			alert("成功加入购物车");
			   			//alert(response.isAddSuccessJson);
			           }
			   	   else if(response == 2) {
			               alert("商品已下架");
			           }			   		
			   	else if(response == 3){	   				   		
		   			       alert("无库存");
		           }
			   	else if(response == 4){	   				   		
	   			       alert("亲，库存不够了");
	           }
			       },"json");
		});
	});}
	addtofavorite();
	
	//从收藏夹删除商品
	function deletefromfavorite(){$(".yxx-favoriteDelete").each(function(){
		$(this).unbind("click");
		$(this).bind("click",function(){
			//alert("1");
		    var goodsId=$(this).attr("name");
		    //alert(goodsId);
		    $(".yxx-favoriteDelete").attr("href","userDeleteFromFavority.action?goodsId="+goodsId);		    
		});
	});}
	
	deletefromfavorite();
	
	//收藏夹页面的更多事件按钮
	$(".yxx-favoriteMoreInfo").unbind("click");
	$(".yxx-favoriteMoreInfo").bind("click",function(){
		   /*var result1=getParameter("searchConditions"); 
		    page++;*/
		page++;
		$.post("userEnterFavority.action",{		
		          page:page
	       },  function(response){
	     	  var data=response.goodsJsonList;
	     	  if(data.length<12)
	     		  {$(".yxx-favoriteMoreInfo").addClass("hidden");}
	     	  for(x in data){
	     		  var insert = "";
	         	  insert+="<div class= 'yxx-favoriteContainer' >"+
		                	"<div class='yxx-favoriteLeftcontent'>"+              
		                        "<a href='showAGoodsInfo.action?goodsId="+data[x]["goodsId"]+"'>"+
		                            "<img src='resources"+data[x]["goodsImage"]+"' title='computer' width='280' height='210'/>"
		                      +"</a>"+
		                    "</div>"+
		                    "<div class='yxx-favoriteRightcontent'>"+
		                    	"<p class='yxx-favoriteComputername'>"+		            
		                    		"<a href='showAGoodsInfo.action?goodsId="+data[x]["goodsId"]+"'>"+data[x]["goodsModel"]+"</a>"+
		                    	"</p>"+
		                    	"<p>"+
		                    		"<span class='textposition'>"+data[x]["goodsProcessor"]+"</span>"+
		                    		"<span class='textposition'>"+data[x]["goodsMemory"]+"</span>"+
		                    		"<span class='textposition'>"+data[x]["goodsHarddiskCapacity"]+"</span>"+
		                   			"<span class='textposition'>"+data[x]["goodsScreenSize"]+"</span>"+
		                        "</p>"+
		                        "<p class='hwd-goodsAveragemark'>";
							  		if(data[x]["goodsAveragemark"]<= 1.4)
							  			insert+="<div class='star1'></div>";
							  		else if(data[x]["goodsAveragemark"] > 1.4&& data[x]["goodsAveragemark"] <= 2.4)
							  			insert+="<div class='star2'></div>";
							  		else if(data[x]["goodsAveragemark"] > 2.4&& data[x]["goodsAveragemark"] <=3.4)					  
							  			insert+="<div class='star3'></div>";
							  		else if(data[x]["goodsAveragemark"] > 3.4&& data[x]["goodsAveragemark"]<=4.4)							  		
							  			insert+="<div class='star4'></div>";
							  		else if(data[x]["goodsAveragemark"] > 4.4)							  		
							  			insert+="<div class='star5'></div>";
							  		
						       insert+="</p>";
						       
						       //zyz 判断商品的折扣是否为1如果为1则只显示现价，否则划掉现价显示折扣价
		      	                  if(data[x]['goodsDiscount']==1)      	                	  
		      	                  {
		      	                	  insert+='<span class="hwd-price" style="color:red;font-size:16px;">价格：￥'+data[x]['goodsPrice']+'</span>'+'</p>'+'<p class="hwd-goodsDiscount">';
		      	                	  
		      	                  }
		      	                  else{
		            	          insert+='<span class="hwd-price" style="text-decoration:line-through;margin-right:10px;">价格：￥'
		            	        	  +data[x]['goodsPrice']+'</span>'+'<span class="hwd-discountPrice" style="color:red;font-size:16px;"> 现价：'
		            	        	  +data[x]['goodsDiscount']*data[x]['goodsPrice']+'</span>'+'</p>'+'<p class="hwd-goodsDiscount">';
		            	          }
						       
		                        
		                       insert+= "<p>"+
		                        "<p name='"+data[x]["goodsStock"]+"'>";
		                        if( data[x]["goodsStock"] <= 0)
                 	            	insert+="<span>已售完</span>";
	         	  				else                 	    
		                        	insert+="<a >"+"<input type='button'  class='btn yxx-favoriteAdd' name='"+data[x]["goodsId"]+"'/>"+"</a>";		                       
		                        insert+="<a class='yxx-favoriteDelete btn' name='"+data[x]["goodsId"]+"'></a>"+
		                        "</p>"+
		                    "</div>"+
		                 "</div>";
	         	  $("#yxx-favoriteContent").append(insert);
	         	 addtofavorite();
	         	 deletefromfavorite();
	     	  }
	     	  
	       },"json"); 
	}); 
});


