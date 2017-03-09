
$(document).ready(function(){
	//alert("1");
	var pageNum = 1;	
	$("#yxx-tranctionMoreinfo").unbind("click");
	$("#yxx-tranctionMoreinfo").bind("click",function(){
		//alert("1");
		pageNum++;
		//alert(pageNum);
		$.post("userQueryTransaction.action",{
			pageNum:pageNum
		},function(response){
			var data=response;
			//alert("1");
	     	if(data.length < 10)
	     		  {$("#yxx-tranctionMoreinfo").addClass("hide");}
	     	for(x in data){
	     		var insert = "";
	     		insert+="<tr>"+
                "<td>"+data[x]["orderlistId"]+"</td>"+
                "<td>"+data[x]["orderlistOrderDate"]+"</td>"+           
                "<td><a id='hwd-transaction-search' class='btn' href='userQueryTransactionInfo.action?orderlistId="+data[x]["orderlistId"]+"'>" +
                		"</a></td>"+
            "</tr>";
	     		$("tbody").append(insert);	
	     	}
		},"json");
	});
});
