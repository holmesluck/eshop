<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>CShop</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/home.css" />
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<div id="body-wrapper">
		<%@ include file="../common/header.jsp"%>
		<div id="main" class="row-fluid">
			<div id="hwd-sidebar">
				<h2 id="hwd-goodsDirectory">商品目录</h2>
				<div id="hwd-category">
					<s:iterator value="catalogList" status="st">
						<div>
							<h3 class="FiCatalogGoods"><a href="enterFiCatalogGoods.action?firstCatalogId=${firstcatalogId}&pageNum=0"> ${firstcatalogName}</a></h3>
							<ul class="hwd-SeCatalogGoods-list">
								<s:iterator value="TSecondcatalogs">	
									<li><a href="enterSeCatalogGoods.action?secondCatalogId=${secondcatalogId}&pageNum=0"> ${secondcatalogName}</a></li>
								</s:iterator>
							</ul>
						</div>
					</s:iterator>
		        </div>
			</div>
			<div id="hwd-content">
				<div id="hwd-slide">
					<div id="myCarousel" class="carousel slide">
		                <ol class="carousel-indicators">
		                  <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		                  <li data-target="#myCarousel" data-slide-to="1"></li>
		                  <li data-target="#myCarousel" data-slide-to="2"></li>
		                </ol>
		                <div class="carousel-inner">
		                  <div class="item active">
		                    <img src="resources/000.jpg" alt="">
		                  </div>
		                  <div class="item">
		                    <img src="resources/001.jpg" alt="">
		                  </div>
		                  <div class="item">
		                    <img src="resources/003.jpg" alt="">
		                  </div>
		                </div>
		                <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
		                <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
		              </div>
				</div>
				<div id="hwd-display" class="clearfix">
					<div id="hwd-display-header">推荐商品</div>
					<s:iterator value="goodsList" id="goodList" status="status">
						<div class="hwd-computerDisplay">
							<div class="hwd-computerDisplay-wrapper">
								<div class="hwd-computerDisplay-img">
									<a href="showAGoodsInfo.action?goodsId=${goodsId}"><img src="resources${goodsImage}"></a>
									<div class="hwd-computerDisplay-price">¥<s:property value="discountPriceList[#status.index]"/></div>
								</div>
								<div class="hwd-computerDisplay-header"><a href="showAGoodsInfo.action?goodsId=${goodsId}">${goodsName}</a></div>
								<!-- <div class="hwd-computerDisplay-footer">${goodsDescription}</div> -->
							</div>
						</div>
					</s:iterator>
				</div>
			</div>
		</div>
		<%@ include file="../common/footer.jsp"%>
	</div>
	<%@ include file="../common/login.jsp"%>
	<%@ include file="../common/register.jsp"%>
	<script src="js/jquery-1.7.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/common.js"></script>
	<script src="js/home.js"></script>
</body>
</html>