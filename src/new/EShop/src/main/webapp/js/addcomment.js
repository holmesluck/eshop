/*这是添加评论页面的js，作者：张羊左*/
$(document).ready(function () {
    $(".check").each(function(){
    	$(this).bind("click",function() {     
        // 获取当前页面点击的checkbox的id
    	var cb = $(this).attr("id");
        var obj =$(this).parent();     
   
        ///判斷obj中的子元素i是否為cb，若否則表示未被點選     
          for(i in obj.children())
            {if(obj.children()[i]["id"]!=cb)
            	{
            	     obj.children()[i].checked = false;                    
            	}
            else {obj.children()[i].checked = true;      }
            }
        });
    });
   $("#zyz-submit").bind("click",function(){
	   alert("shit");
	   $.post("userSubmitComment.action",{},function(response){
		   alert(response);
		   if(response=="成功")
		   {
			   window.location.href='userGetOrderlist.action?pageNum=1';
		   }
		   else alert("ERROR"); 
	   },"json");
	   return false;
   });
});

  
