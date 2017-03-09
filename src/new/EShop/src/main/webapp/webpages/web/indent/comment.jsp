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
    <link rel="stylesheet" type="text/css" href="css/comment.css" />
</head>
<body>
	<div id="body-wrapper">
		1<%@ include file="../common/header.jsp"%>
		<div id="main" class="row-fluid">
			<!-- 所需数据 （商品为订单中购买的商品）
				1.商品名
				2.商品缩略图
			-->
			
			<div id="yxx-commentHead"><img src="resources/comment.jpg" /></div>
			<div id="yxx-commentContainer">
			<s:iterator value = "">
	        	<div class="yxx-commentContent">
	            	<div class="yxx-commentLeftcontent">
	                	<div class="yxx-commentGoodsName"><a href="#">${tGoods.goodsName}</a></div>
	                    <a href="#"><img src="resources${tGoods.goodsImage}" width="150" height="150"/></a>
	                </div>
	                <div class="yxx-commentRightcontent">
	                	<div id="star">                       
	                        <ul>
	                            <li><a href="javascript:;">1</a></li>
	                            <li><a href="javascript:;">2</a></li>
	                            <li><a href="javascript:;">3</a></li>
	                            <li><a href="javascript:;">4</a></li>
	                            <li><a href="javascript:;">5</a></li>
	                        </ul>
	                        <span style="line-height:28px"></span>
	                        <p></p>
	                    </div>
	                    <textarea class="yxx-commentTextarea"></textarea>
	                </div>
	            </div>
	        </s:iterator>
            <div class="clear"></div>
            <div class="yxx-commentSubmit"><input type="submit" value="提交评论" class="yxx-commentSubmitcontrol hwd-interceptor"/></div>
          </div>          	
		</div>
		<%@ include file="../common/footer.jsp"%>
	</div>
	<%@ include file="../common/login.jsp"%>
	<%@ include file="../common/register.jsp"%>
	<script src="js/jquery-1.7.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/comment.js"></script>
	<script src="js/common.js"></script>
</body>
</html>