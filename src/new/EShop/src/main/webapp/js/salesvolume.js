/*该js是销量查询管理页面通用的 by 张羊左 */
$(document).ready(function () {
	
	 $("#zyz-salessearch").bind("click",function(){
		     var now,y,m,d;
		     now=new Date(); 
		     y = now.getYear(); 
		     m = now.getMonth()+1;
		     d = now.getDate(); 
			 startDate=$("#zyz-start-time").val();			 
		     terminalDate=$("#zyz-end-time").val();
		      /*str= terminalDate.split("-");
		      str1= startDate.split("-");
		      alert(str[0]);
		      alert(str[1]);
		      alert(str[2]);
		      alert(str1[0]);
		      alert(str1[1]);
		      alert(str1[2]);
		    
		      //判断不能为空
		     if(startDate==null||terminalDate==null)
				 {
			        alert("不能为空请重新填写");
			     }
		  
	      else {
	    	   //判断结束时间不能超过系统当前时间
			     if(str[0]>y)
		    	 {
		    	   alert("结束时间超出系统时间默认结束时间为系统当前时间");
		    	   terminalDate=y+"-"+m+"-"+d;
		    	 }
		      else if(str[0]==y&&str[1]>m)
		    	  {
		    	  alert("结束时间超出系统时间默认结束时间为系统当前时间");
		    	     terminalDate=y+"-"+m+"-"+d;
		    	  }
		      else if(str[0]==y&&str[1]==m&&str[2]>d)
		    	  {
		    	  alert("结束时间超出系统时间默认结束时间为系统当前时间");
		    	     terminalDate=y+"-"+m+"-"+d;
		    	  }
		      else{
			     //判断起始时间不能超过当前系统时间
		       if(str1[0]>y)
		    	 {
		    	   alert("起始时间超出系统时间默认起始时间为系统当前时间");
		    	   startDate=y+"-"+m+"-"+d;
		    	 }
		      else if(str1[0]==y&&str1[1]>m)
		    	  {
		    	  alert("起始时间超出系统时间默认起始时间为系统当前时间");
		    	  startDate=y+"-"+m+"-"+d;
		    	  }
		      else if(str1[0]==y&&str1[1]==m&&str1[2]>d)
		    	  {
		    	  alert("起始时间超出系统时间默认起始时间为系统当前时间");
		    	  startDate=y+"-"+m+"-"+d;
		    	  }
	    	  alert("请查看搜索结果");
		      }
		      }*/
			 $("#zyz-salessearch").attr("href","adminSalesStatisticsadminSalesStatistics.action? startDate="+startDate+"&terminalDate="+terminalDate+"");
	        return true;
 });
	
});