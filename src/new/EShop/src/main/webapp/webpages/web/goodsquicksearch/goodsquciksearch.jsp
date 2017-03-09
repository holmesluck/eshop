<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>CShop</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="css/style.css" />
    <link  rel="stylesheet" type="text/css"  href="css/goodssearch.css" />
</head>
<body>
	<div id="body-wrapper">
		<%@ include file="../common/header.jsp"%>
		<div id="main" class="row-fluid">  
		
			
   <div id="searchinfo">
	<div id="zyz-orderbtn">
        <div id="zyz-orderbtn-wrapper">
          <div id="zyz-orderbtn-buttonGroup">
             <a id="sales" class="btn"></a>
            <a id="price" class="btn"></a>
          </div>
       </div>      
    </div>
	   <div class="clearfix" id="zyz-searchresult">
	    <s:iterator value="goodsList" id="goodsList" status="status">
	    <div class="goods zyz-statistics"> 
		  <div class="photo" >
		    <a href="showAGoodsInfo.action?goodsId=${goodsId}">
		   <img src="resources${goodsImage}"  >	
		   </a>
		  </div>  
	       <div class="goodsdata">
		     <p class="goodsname">
		       <a href="showAGoodsInfo.action?goodsId=${goodsId}">${goodsName}</a>
		     </p>
	         <p class="hwd-goods-info clearfix">
	              <span class="textposition">${goodsProcessor}</span>
	             <span class="textposition">${goodsMemory}</span>
		         <span class="textposition">${goodsHarddiskCapacity}</span>
		         <span class="textposition ">${goodsScreenSize}</span>
			  </p>
			  <p class="hwd-goodsPrice">
			  <s:if test="goodsDiscount==1">
			  	<span class="hwd-price" style="color:red;font-size:16px;">价格：￥${goodsPrice}</span>
			  </s:if>
			  <s:else>
			  	<span class="hwd-price" style="text-decoration:line-through;margin-right:10px;">价格：￥${goodsPrice}</span><span class="hwd-discountPrice" style="color:red;font-size:16px;"> 现价：<s:property value="discountPriceList[#status.index]"/></span>
			  </s:else>
			  </p>
			  <p class="hwd-goodsDiscount">
			  	<span>
			  		<s:if test="goodsDiscount > 0">
			  			<!-- <span>有货</span> -->
			  		</s:if>
			  		<s:else>
						<span class="red">没货</span>
					</s:else>
				</span>
				<span>销量：${goodsSales}</span>
			  </p>
			  <p class="hwd-goodsAveragemark">
			  		<s:if test="goodsAveragemark <= 1.4">
			  			<div class="star1"></div>
			  		</s:if>
			  		<s:if test="goodsAveragemark > 1.4&& goodsAveragemark <= 2.4">
			  			<div class="star2"></div>
			  		</s:if>
			  		<s:if test="goodsAveragemark > 2.4&& goodsAveragemark <=3.4">
			  			<div class="star3"></div>
			  		</s:if>
			  		<s:if test="goodsAveragemark > 3.4&& goodsAveragemark <= 4.4">
			  			<div class="star4"></div>
			  		</s:if>
			  		<s:if test="goodsAveragemark > 4.4">
			  			<div class="star5"></div>
			  		</s:if>
			  </p>
		   </div> 
		     <div>
                 <div>
                    <a class="btn addshoppingcart" id="${goodsId}" ></a>
                    <a  class="btn addcollection hwd-interceptor" id="${goodsId}"></a>		 
                </div>
            </div>           
	    </div>
	     <!-- <div class="goods zyz-statistics"> 
	     <div class="goodsname">
	       <a href="showAGoodsInfo.action?goodsId=${goodsId}">${goodsName}</a>
	     </div>
		  <div class="photo" >
		    <a href="showAGoodsInfo.action?goodsId=${goodsId}">
		   <img src="resources${goodsImage}"  >	
		   </a>
		  </div>  
	       <div class="goodsdata">
	         <ul>
	              <li class="textposition" id="xiaoliang">商品ID:${goodsId}</li>
	              <li class="textposition" id="xinghao">型号：${goodsModel}</li>
		          <li class="textposition" id="yuanjiage">原价格：${goodsPrice}</li>
		          <li class="textposition" id="kucunshu">库存数：${goodsStock}</li>
			      <li class="textposition" id="zhekou">折扣：<s:property value="goodsPrice * 3"/></li>  
			      <li class="textposition" id="xiaoliang">总销量：${goodsSales}</li>
			  </ul>
		   </div> 
		     <div class="btn-group btngroup">
                 <div align="left">
                    <a class="btn addshoppingcart" id="${goodsId}" ></a>
                    <a  class="btn addcollection hwd-interceptor2" id="${goodsId}" ></a>		 
                </div>
            </div>           
	    </div> -->
	    </s:iterator>
	    </div>
		
	
		
	    <div id="submitall">
				<a class="btn" id="zyz-more2"></a>
		</div>
		</div>
		<%@ include file="../common/footer.jsp"%>
		
	    
	</div>
	<%@ include file="../common/login.jsp"%>
	<%@ include file="../common/register.jsp"%>
	<script src="js/jquery-1.7.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/common.js"></script>
	
	<script src="js/quicksearch.js"></script>
</body>
</html>