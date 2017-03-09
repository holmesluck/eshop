/*该js是订单管理页面通用的 by 张羊左 */
$(document).ready(function () {
	var i;  
	var page1=1;
	  var page=0;
	  var userId=0;
	  var  orderId=0;
	    //state 为2,1,0等值
	 var  state=0;
       var  beginDate;
       beginDate="2000-01-01";
        var endDate;
        endDate="2050-01-01";
     /*
	  function getParameter()
	    {
	       var	  psName = arguments[0];
	     
	 	   var    url=window.location.href;	 
		   var    str=url.split("?")[1]; 
		 
		   if(str==undefined){str="userId";}
	 	   var    value=str.split("&");
	 	  
	 	   for(x in value){
	 		   var name=value[x].split("=")[0];
	 		   if(name == psName){
	 			   var result=value[x].split("=")[1];
	 			 
	 		   }
	 	   }
	 	   
	 	   return result;
	    }
	 */
       
      //如果状态变为已发货或是已确认收货要将当前的checkbox 变为disabled 属性 
        function disabledit() { 
        	$(".orderlistStatus1").each(function(){
     	   $(this).attr("disabled",true);
            });
            $(".orderlistStatus2").each(function(){
     	   $(this).attr("disabled",true);
           });
        }
      

        	/* var box = document.getElementsByName("ordersn");
        	
        	
        	 
    		for(var i=0;i<box.length;i++)
    			{
    			  if(box[i].attr("class")!=1||box[i].attr("id")!=0)
    				{ 
    				 alert(box[i].attr("id"));
    				   box[i].attr("disabled","disabled");			  
    			    }
    			}*/
   
	//点击查询按钮的事件操作
       
	 $("#zyz-ordersearch").bind("click",function(){
	     page1=1;
		 var obj = document.getElementById("zyz-orderstate");   
		 for (i=0;i<obj.children.length; i++){ 
			     if(obj.children[i].checked==true)
		          { state=obj.children[i].id; }
			 }
		 
		  $("#zyz-showorderlist").hide();
		  $("#zyz-button").hide();
		 
		  $("#zyz-showorderlist2").show();
		  $("#zyz-showorderlist2").html('');
		  $("#zyz-button1").removeClass("hide");
		  $("#zyz-more2").removeClass("hide");
		  
		
		 userId=$("#zyz-input-userid").val();
		 orderId=$("#zyz-input-ordernum").val();
	     beginDate=$("#zyz-start-time").val();
	     endDate=$("#zyz-end-time").val();
		 
		/* if($("#zyz-input-ordernum").val()==""||$("#zyz-input-ordernum").val()==""
				 ||$("#zyz-start-time").val()==""||$("#zyz-end-time").val()=="")
		  {
			 alert("填写的内容不能为空");
		  }*/
		 $.post(" adminShowOrderList.action",{
		     userId:userId,
			 orderId:orderId,
			 orderState:state,
	          beginDate:beginDate,
	          endDate:endDate,
	          page:0
	          },function(response){
	        	  var data=response;
	        	 $("#zyz-showorderlist").hide();
	        	 $("#showOrderList2").show();
	        	 
	        	  
	        	
	        	  for(x in data){
	        		  var insert = "";
	        		  if(data[x]["orderlistStatus"]==0)
	        			  {
	        			    state1="未发货"; 
	        			  }
	        		  else if(data[x]["orderlistStatus"]==1)
	        			  {
	        			    state1="已发货";
	        			  }
	        		  else if(data[x]["orderlistStatus"]==2)
	        			  {
	        			   state1="已确认收货";
	        			  }
	        		  insert+= "<tr>"+
                      "<td>"+"<input type='checkbox' name='ordersn' value='"+data[x]["orderlistId"]+"' class='zyz-select orderlistStatus"+data[x]["orderlistStatus"]+"'>"+"</td>"+
                      "<td>"+data[x]["orderlistId"]+"</td>"+
                      "<td>"+data[x]["userId"]+"</td>"+"<td>"+
                      data[x]["orderlistOrderdate"]+"</td>"+
                      "<td>"+state1+"</td>"+
                      "<td>"+"<a href="+"adminShowOrderDetail.action?orderlistId="+data[x]["orderlistId"]+">"+"<img src='resources/xq.gif' value='详情' class='picture'>"
                      +"</a>"+"</td>"+" </tr>";
	            	 

	            	  $("#zyz-showorderlist2").append(insert);

	            	  $("#showorderlist2").append(insert);

	        	  }

	        	  disabledit();
	        	
	          },"json");
		 
	    });
	//更多1按钮的事件
	
	 $("#zyz-more1").bind("click",function(){ 
		
		 var obj = document.getElementById("zyz-orderstate");   
		 for (var i=0;i<obj.children.length; i++){ 
			     if(obj.children[i].checked==true)
		          { state=obj.children[i].id; }
			 }
		 
		 page=page1;
		 userId=$("#zyz-input-userid").val();
		 orderId=$("#zyz-input-ordernum").val();
	     beginDate=$("#zyz-start-time").val();
	     endDate=$("#zyz-end-time").val();
		$.post("adminShowOrderList.action",{		
			     userId:userId,
				 orderId:orderId,
				 orderState:state,
		          beginDate:beginDate,
		          endDate:endDate,
		          page:page
	          },  function(response){
	        	  var data=response;
	        	
	        	  if(data.length<10)
        		  {$("#zyz-more1").addClass("hide");
        		  }
	        	  else {page1++;}
	        	
	        	  for(x in data){
	        		  var insert = "";
	        		  if(data[x]["orderlistStatus"]==0)
	        			  {
	        			    state1="未发货"; 
	        			  }
	        		  else if(data[x]["orderlistStatus"]==1)
	        			  {
	        			    state1="已发货";
	        			  }
	        		  else if(data[x]["orderlistStatus"]==2)
	        			  {
	        			   state1="已确认收货";
	        			  }
	            	  insert+= "<tr>"+
                      "<td>"+"<input type='checkbox' name='ordersn' value='"+data[x]["orderlistId"]+"' class='zyz-select orderlistStatus"+data[x]["orderlistStatus"]+"'>"+"</td>"+
                      "<td>"+data[x]["orderlistId"]+"</td>"+
                      "<td>"+data[x]["userId"]+"</td>"+"<td>"+
                      data[x]["orderlistOrderdate"]+"</td>"+
                      "<td>"+state1+"</td>"+
                      "<td>"+"<a href="+"adminShowOrderDetail.action?orderlistId="+data[x]["orderlistId"]+">"+"<img src='resources/xq.gif' value='详情' class='picture'>"
                      +"</a>"+"</td>"+" </tr>";
	            	 
	            	  $("#zyz-showorderlist").append(insert);
	            	  
	        	  }
	        	
	        	  disabledit();
	        	
	          },"json");
		
		 
	   }); 
	 //更多2按钮的事件
		
	 $("#zyz-more2").bind("click",function(){ 
		 
		 var obj = document.getElementById("zyz-orderstate");   
		 for (var i=0;i<obj.children.length; i++){ 
			     if(obj.children[i].checked==true)
		          { state=obj.children[i].id; }
			 }
		
		 page=page1;
		 userId=$("#zyz-input-userid").val();
		 orderId=$("#zyz-input-ordernum").val();
	     beginDate=$("#zyz-start-time").val();
	     endDate=$("#zyz-end-time").val();
		$.post("adminShowOrderList.action",{		
			     userId:userId,
				 orderId:orderId,
				 orderState:state,
		          beginDate:beginDate,
		          endDate:endDate,
		          page:page
	          },  function(response){
	        	  var data=response;
	        	  if(data.length<10)
        		  {$("#zyz-more2").addClass("hide");
        		  }
	        	  else { page1++;}
	        	
	        	  for(x in data){
	        		  var insert = "";
	        		  if(data[x]["orderlistStatus"]==0)
	        			  {
	        			    state1="未发货"; 
	        			  }
	        		  else if(data[x]["orderlistStatus"]==1)
	        			  {
	        			    state1="已发货";
	        			  }
	        		  else if(data[x]["orderlistStatus"]==2)
	        			  {
	        			   state1="已确认收货";
	        			  }
	            	  insert+= "<tr>"+
                      "<td>"+"<input type='checkbox' name='ordersn' value='"+data[x]["orderlistId"]+"' class='zyz-select orderlistStatus"+data[x]["orderlistStatus"]+"'>"+"</td>"+
                      "<td>"+data[x]["orderlistId"]+"</td>"+
                      "<td>"+data[x]["userId"]+"</td>"+"<td>"+
                      data[x]["orderlistOrderdate"]+"</td>"+
                      "<td>"+state1+"</td>"+
                      "<td>"+"<a href="+"adminShowOrderDetail.action?orderlistId="+data[x]["orderlistId"]+">"+"<img src='resources/xq.gif' value='详情' class='picture'>"
                      +"</a>"+"</td>"+" </tr>";
	            	  
	            	  $("#zyz-showorderlist2").append(insert);
	            	  
	        	  }
	        	  disabledit();
	          },"json");
		 
	   }); 
	 //审核按钮的点击事件	 
	 $(".zyz-verify").each(function(){
	 $(".zyz-verify").bind("click",function(){
		 
		 var box = document.getElementsByName("ordersn");
		 strOrderList='';
		for(var i=0;i<box.length;i++)
			{
			  if(box[i].checked==true)
				{ 
				 
				   strOrderList=strOrderList+box[i].value+",";
				}
			  
			}
		strOrderList = strOrderList.substring(0,strOrderList.length - 1); 
		 $(".zyz-verify").each(function(){
			 if(strOrderList=="")
			{
				 alert("请选择要审核的商品");
				 return false;
			}
			 else{ $(this).attr("href","adminConfirmOrderList.action?strOrderList="+strOrderList+"");}
			
			 });
	      
        return true;
	    });
	
	   });
	 disabledit();
});