<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>CShop</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">	
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<link rel="stylesheet" type="text/css" href="css/favorite.css" />
</head>
<body>
	<div id="body-wrapper">
		<%@ include file="../common/header.jsp"%>
		<div id="main" class="row-fluid">
			<!-- 所需数据 （商品为用户收藏的商品，一次性显示9件商品）
				1.序号（1,2,3……） 
                2.商品小图片
                3.商品名
                4.商品价格
                5.商品折后价
                6.商品Id:${TGoods.goodsId}
                7.商品库存：${TGoods.goodsStock}
			-->	
			<div id="yxx-favoriteHead">
				<div id="hwd-favoriteHead-header"></div>
			<!-- <img src="resources/computer.gif"/></div> -->
			<div id="yxx-favoriteContent" class="clearfix">
				<s:if test="listFavority.size()>O">
				    <s:iterator value = "listFavority">
				    <div class="yxx-favoriteContainer">
		                	<div class="yxx-favoriteLeftcontent">                 
		                        <a href="showAGoodsInfo.action?goodsId=${TGoods.goodsId}">
		                        	<img src="resources${TGoods.goodsImage}" title="computer" width="280" height="210"/>
		                        </a>
		                    </div>
		                    <div class="yxx-favoriteRightcontent">
		                    	<p class="yxx-favoriteComputername"><a href="showAGoodsInfo.action?goodsId=${TGoods.goodsId}">${TGoods.goodsName}</a></p>
		                        <p>	<span class="textposition">${TGoods.goodsProcessor}</span>
						             <span class="textposition">${TGoods.goodsMemory}</span>
							         <span class="textposition">${TGoods.goodsHarddiskCapacity}</span>
							         <span class="textposition ">${TGoods.goodsScreenSize}</span>
		                        </p>
		                        <%-- //${TGoods.goodsAveragemark} --%>
		                        <p class="hwd-goodsAveragemark">
							  		<s:if test="TGoods.goodsAveragemark <= 1.4">
							  			<div class="star1"></div>
							  		</s:if>
							  		<s:if test="TGoods.goodsAveragemark > 1.4&& TGoods.goodsAveragemark <= 2.4">
							  			<div class="star2"></div>
							  		</s:if>
							  		<s:if test="TGoods.goodsAveragemark > 2.4&& TGoods.goodsAveragemark <=3.4">
							  			<div class="star3"></div>
							  		</s:if>
							  		<s:if test="TGoods.goodsAveragemark > 3.4&& TGoods.goodsAveragemark <= 4.4">
							  			<div class="star4"></div>
							  		</s:if>
							  		<s:if test="TGoods.goodsAveragemark > 4.4">
							  			<div class="star5"></div>
							  		</s:if>
									   </p>
				                        <p class="hwd-goodsPrice">
					                    <s:if test="TGoods.goodsDiscount==1">
					  	                <span class="hwd-price" style="color:red;font-size:16px;">价格：￥${TGoods.goodsPrice }</span>
					                    </s:if>
					                    <s:else>
					  	                 <span class="hwd-price" style="text-decoration:line-through;margin-right:10px;">价格￥${TGoods.goodsPrice }</span>
					  	                 <span class="hwd-discountPrice" style="color:red;font-size:16px;"> 现价：<s:property value="TGoods.goodsPrice*TGoods.goodsDiscount"/></span>
					                   </s:else>
					                   </p>
		                        <p name="${TGoods.goodsStock}">	
                 	            <s:if test="TGoods.goodsStock <= 0">
                 	            	<span class="">已售完</span>&nbsp;
                 	            </s:if>
                 	            <s:else>
	                 	            <a class="btn yxx-favoriteAdd" name="${TGoods.goodsId}">
	                 	            </a>
                 	            </s:else>     		                         
		                        <a class="yxx-favoriteDelete btn" name="${TGoods.goodsId}"></a>
		                        </p>
		                    </div>	            
		            </div>
		            </s:iterator>
	            </s:if>	            
	            <s:if test="listFavority.size()<=O">				    
				    <div class="yxx-favoriteContent">
		                	<div id="yxx-favoriteNonecontent">                 
		                       	收藏夹没有商品哦，快去商城逛逛吧！
		                    </div>	            
		            </div>
	            </s:if> 
	             
	        </div>
	        <div  class="clear"></div> 
	        <s:if test="listFavority.size()>10">
	            	<a class="yxx-favoriteMoreInfo btn"></a>
	        </s:if>	                              	
			
		</div>
		<%@ include file="../common/footer.jsp"%>
	</div>
	<%@ include file="../common/login.jsp"%>
	<%@ include file="../common/register.jsp"%>
	<script src="js/jquery-1.7.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/common.js"></script>
	<script src="js/favorite.js"></script>
</body>
</html>