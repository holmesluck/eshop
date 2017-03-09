/*seriersgoodslist 用于 seriersgoodslist 页面的事件，
 * 该页面主要是通过主页点击目录列表进入的页面
 * by张羊左
 */ 
$(document).ready(function () {
    var c=0;
   
    c=$('div').filter('.zyz-statistics').length;
    if(c<10)
    {$("#zyz-more2").addClass("hide");}
    var state = getParameter("soldSort");
    var state1 = getParameter("priceSort");
    if(state==undefined)
	   {state="no";}
if(state1==undefined)
	   {
	    state1="no";
	   }
    var pageNum=getParameter("pageNum");
    

    
    var image=new Array(5);
    //数组保存图片路径 0，1 为销量按钮 2，3，4为价格按钮
    image[0]="resources/advancedSearch/SalesVolume1.png";  //no
    image[1]="resources/advancedSearch/SalesVolume2.png";  //desc
    image[2]="resources/advancedSearch/price1.png";    //no
    image[3]="resources/advancedSearch/price3.png";   //desc
    image[4]="resources/advancedSearch/price2.png";    //asc
   //改变按钮状态
    if(state==""||state=="no") {$("#sales").css("background-image","url("+image[0]+")");}
    if(state=="desc"){$("#sales").css("background-image","url("+image[1]+")");}
    if(state1==""||state1=="no"){$("#price").css("background-image","url("+image[2]+")");}
    if(state1=="desc"){$("#price").css("background-image","url("+image[3]+")");}
    if(state1=="asc"){$("#price").css("background-image","url("+image[4]+")");}
    function getParameter()
    {
       var	  psName = arguments[0];
     
 	   var    url=window.location.href;	 
 	   
	   var    str=url.split("?")[1]; 
	  
 	   var    value=str.split("&");
 	  
 	   for(x in value){
 		   var name=value[x].split("=")[0];
 		   if(name == psName){
 			   var result=value[x].split("=")[1];
 			 
 		   }
 	   }
 	   
 	   return result;
    }


 //按销量排序的按钮的事件操作
   $("#sales").bind("click",function(){
	      
	     
	       var result1=getParameter("secondCatalogId"); 
	  
	       if(state!="no"){
	    	   state="no";
	       }
	       else{
	    	   state="desc";
	       } 
	       $("#sales").attr("href","enterSeCatalogGoods.action?secondCatalogId="+result1+"&priceSort=no&pageNum=0&soldSort="+state);
	        return true;
   }); 
//价格排序的按钮的时间操作
   $("#price").bind("click",function(){
	   var result1=getParameter("secondCatalogId"); 
	   if(state1!="no"&&state1!="asc")     //不是默认排序和升序排序
	   {  
    	  state1="no";
	   }
	   else if(state1!="asc"&&state1!="desc") //是升序排序
       {
    		state1=  "asc";  
       }
	   else if(state1!="desc"&&state1!="no") //降序排序
	   {
		   state1="desc";
	   }
	   $("#price").attr("href","enterSeCatalogGoods.action?secondCatalogId="+result1+"&priceSort="+state1+"&pageNum=0&soldSort=no");
       return true;
   });
//快速搜索页面的更多事件按钮
   $("#zyz-more2").bind("click",function(){
	   var result1=getParameter("secondCatalogId"); 
	   var priceSort=getParameter("priceSort"); 
	   var soldSort=getParameter("soldSort"); 
	   if(priceSort==undefined)
	   {
	    priceSort="no";		   
	   }
       if(soldSort==undefined)
	   {
	    soldSort="no";
	   }
	   if(page==undefined)
		{
		   pageNum=0;   
		   
		}
	    pageNum++;
	$.post("enterSeCatalogGoods.action",{		
		secondCatalogId:result1,
	          priceSort:priceSort,
	          soldSort:soldSort,
	          pageNum:pageNum
          },  function(response){
        	  var data=response.goodsJsonList;
        	 if(data.length<10)
        		  {$("#zyz-more2").addClass("hide");}
        	  for(x in data){
        		  var insert = "";
            	  insert+='<div class="goods zyz-statistics"> '+
            	          '<div class="photo" >'+
            	          '<a href="showAGoodsInfo.action?goodsId='+data[x]['goodsId']+'">'+
        		          '<img src="resources'+data[x]['goodsImage']+'"  >'+'</a>'+
        		          '</div>'+'<div class="goodsdata">'+'<p class="goodsname">'
      		              +'<a href="showAGoodsInfo.action?goodsId='+data[x]['goodsId']+'">'+data[x]['goodsName']+
      		              '</a>'+'</p>'+'<p class="hwd-goods-info clearfix">'+'<span class="textposition">'
      		              +data[x]['goodsProcessor']+'</span>'+'<span class="textposition">'+data[x]['goodsMemory']+
      		              '</span>'+'<span class="textposition">'+data[x]['goodsHarddiskCapacity']+'</span>'
      		              +' <span class="textposition ">'+data[x]['goodsScreenSize']+'</span>'+'</p>'
      	                  +'<p class="hwd-goodsPrice">';
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
            	          //判断是否有货
            	  			if(data[x]['goodsStock']==0){
            	  				insert+='<span class="red">没货</span>';
            	  			}
            	  			insert+='<span>销量：'+data[x]['goodsSales']+'</span>'+'</p>'+
      		                '<p class="hwd-goodsAveragemark">';
            	  			//判断评分
      		                if(data[x]['goodsAveragemark']<=1.4)
      	                    {
      		                	insert+='<div class="star1">'+'</div>';
      	                    }
      		                if(data[x]['goodsAveragemark']>1.4&&data[x]['goodsAveragemark']<=2.4)
    	                    {
    		                	insert+='<div class="star1">'+'</div>';
    	                    }
      		                if(data[x]['goodsAveragemark']>1.4&&data[x]['goodsAveragemark']<=2.4)
  	                        {
  		                	    insert+='<div class="star2">'+'</div>';
  	                        }
      	                    if(data[x]['goodsAveragemark']>2.4&&data[x]['goodsAveragemark']<=3.4)
      		               {
      	                    	insert+='<div class="star3">'+'</div>';
      		               }
      		               if(data[x]['goodsAveragemark']>3.4&&data[x]['goodsAveragemark']<=4.4)
      		               {
     	                    	insert+='<div class="star4">'+'</div>';
     		               }
      		               if(data[x]['goodsAveragemark']>4.4)
    		               {
   	                    	     insert+='<div class="star5">'+'</div>';
   		                   }
      			           insert+='</p>'+'</div>'+'<div>'+'<div>'+'<a class="btn addshoppingcart" id="'+data[x]['goodsId']+'" >'
      			            +'</a>'+'<a  class="btn addcollection hwd-interceptor" id="${goodsId}">'+'</a>'+'</div>'
      			  		    +'</div>'+'</div>';                                                                         	                                                         	    
            	  $("#zyz-searchresult").append(insert);
            	  addcollectionbind();
            	    addshoppingbind();
        	  }
        	  
          },"json"); 
   }); 
  
   //加入购物车的事件操作
    function addshoppingbind(){
	   $(".addshoppingcart").each(function(){
		    $(this).unbind("click");
			$(this).bind("click",function(){
				var addshoppingcart =$(this);
			    var goodsId=$(this).attr("id");
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
			});
   };
   
 
   //点击收藏的操作
   function addcollectionbind(){
	   $(".addcollection").each(function(){
		    $(this).unbind("click");
			$(this).bind("click",function(){
			  /*  var c=$(this).attr("value");
			    if(c==0)
			    {
			    	$(this).addClass("disabled");
			    	alert("当前库存为0不能收藏");
			    }*/
			    var goodsId=$(this).attr("id");
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
   }
   addcollectionbind();
    addshoppingbind();
});