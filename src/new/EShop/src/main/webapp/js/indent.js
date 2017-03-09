$(document).ready(function () {
	//common();
	//alert("1");
	var page = 1;
	$(".yxx-indentMoreinfo").unbind("click");
	$(".yxx-indentMoreinfo").bind("click",function(){
		    page++;
		    //alert("1");
			$.get("userGetOrderlist.action",{
				pageNum:page
				},function(response){
					//alert("1");
					var data=response;
					   //alert("1");
					   //alert(data.length);
			     	  if(data.length < 10)
			     		  {$(".yxx-indentMoreinfo").addClass("hide");}
			     	      //alert("1");
			     	  for(x in data){
			     		 //alert("1");
			     		  var insert = "";
			         	  /*insert+="<div class='yxx-indentContent'>"+
		            	"<div class='yxx-indentLine1'>"+
		            		"<ul>"+
			                	"<li>"+ "订单编号:"+"<span>"+data[x]["orderlistId"]+"</span>"+"</li>"+
	                            "<li>"+ "成交时间:"+ data[x]["orderlistOrderDate"]+ "</li>";
			         	  if(data[x]["orderlistStatus"]==1){
			         		  insert+="<li>"+
		                    	"<a href='userConfirmReceiveGoods.action?orderlistId="+data[x]["orderlistId"]+"' class='btn yxx-indentQueren'>"+"确认"+"</a>"+		                    	
		                    	"</li>";
			         	  }
			         	 insert+="</ul>"+
		                "</div>"+
		                "<div class='yxx-indentLine1'>"+
		                	"<ul>"+
			                	"<li><div class='controlOverflow'>"+ "收货地址:"+ data[x]["address"]+ "</div>"+ "</li>"+
			                    "<li>"+ "电话号码:"+ data[x]["phoneNumber"]+ "</li>"+
		                    "</ul>"	+	                   
		                "</div>"+
		                "<div class='yxx-indentLine3'>"+
		                	"<div class='yxx-indentGoods'>"+
		                    	"<table cellpadding='0' cellspacing='0'>";
			         	 for(var i in data[x]['goodsName']){
			         		insert+="<tr>"+
                        	"<td class='yxx-indentName'><a href='showAGoodsInfo.action?goodsId="+data[x]["goodsId"][i]+ "'>"+data[x]["goodsName"][i]+"</a>"+ "</td>"+
                        	"<td class='yxx-indentPrice'>"+ data[x]["单价"][i]+ "</td>"+
                        	"<td class='yxx-indentNum'>"+ data[x]["amount"][i]+ "</td>"+
                            "</tr>";
			         	 }
			         	insert+="</table>"+
		                    "</div>"+
		                    "<div class='yxx-indentTotal'>"+
		                    	"<p>"+ "合计："+ "</p>"+
		                        "<p>"+ data[x]["orderlistTotalPrice"]+ "</p>"+
		                    "</div>"+
		                    "<div class='yxx-indentState'>";
			         	 if(data[x]["orderlistStatus"]==0){
			         		 insert+="未发货";
			         	 }else if(data[x]["orderlistStatus"]==1){
			         		insert+="已发货";
			         	 }
			         	insert+="</div>"+
		                "</div>" +                     
		         "</div>";*/
			         	
			     		 insert+="<tr style='height:90px; margin-top:10px; margin-bottom:10px;'>"+
		        			"<td style='width:50px;'><a href='userQueryTransactionInfo.action?orderlistId="+data[x]["orderlistId"]+"'>"+data[x]["orderlistId"]+"</a></td>"+ 
		        			"<td style='width:220px;'>";
			     		    //alert("yxx");
			     		      //alert(data[x]["goodsImage"].length);
			     		 		if(data[x]["goodsImage"].length > 4){			     		 		
		        				/*"<s:if test='goodsImage.size() > 4'>"+*/
			     		 			for(var i=0;i<=3;i++){ 			
		        					/*"<s:iterator value='goodsImage' id='id' status='status'  begin='0' end='3'>"+*/
		        						insert+="<a href='showAGoodsInfo.action?goodsId="+data[x]["goodsId"][i]+"'><img  src='resources"+data[x]["goodsImage"][i]+"' alt='商品图片'  style='width:40px; height:30px;margin-right:5px;'/></a>";				
		        					/*"</s:iterator>"+*/
			     		 			}
		        					insert+="<br/>"+
		        					"<a href='userQueryTransactionInfo.action?orderlistId="+data[x]["goodsId"]+"'>查看更多</a>";
			     	             }
		        				/*"</s:if>"+
		        				"<s:else>"+
		        					"<s:iterator value='goodsImage' id='id' status='status' >"+*/
		        				else{
		        						for(var i=0;i<data[x]["goodsId"].length;i++){ 
		        							insert+="<a href='showAGoodsInfo.action?goodsId="+data[x]["goodsId"][i]+"'><img src='resources"+data[x]["goodsImage"][i]+"' value='id' alt='商品图片'  style='width:40px; height:30px;margin-right:5px;'/></a>";
		        						}
		        				/*"</s:iterator>"+
		        				"</s:else>"+*/
		        					}
		        			insert+="</td>"+
			        			"<td style='width:100px;'>"+data[x]["linkMan"]+"</td>"+
			        			"<td style='width:100px;'>"+data[x]["orderlistTotalPrice"]+"</td>"+
			        			"<td style='width:100px;'>"+data[x]["orderlistOrderDate"]+"</td>"+
			        			"<td style='width:100px;'>";
			        			if(data[x]["orderlistStatus"]==0){
					         		 insert+="未发货<br/>";
					         	 }else if(data[x]["orderlistStatus"]==1){
					         		insert+="已发货<br/>"+
				     				 "<a href='userConfirmReceiveGoods.action?orderlistId=${orderlistId}' >确认收货</a>"+
				     				 "<br/>";
				         	     }
		        				/*"<s:if test='orderlistStatus==0'>"+
									"未发货<br/>"+
								"</s:if>"+
								"<s:elseif test='orderlistStatus==1'>"+
				     				 "已发货<br/>"+
				     				 "<a href='userConfirmReceiveGoods.action?orderlistId=${orderlistId}' >确认收货</a>"+
				     				 "<br/>"+
								"</s:elseif>"+*/
							insert+="<a href='userQueryTransactionInfo.action?orderlistId=${orderlistId}'>查看详情</a>"+
							"</td>"+
	        			 "</tr>";
			         	
			         	  $("tbody").append(insert);
			         	  };
			},"json");

		});
});