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
<link rel="stylesheet" type="text/css" href="css/addcomment1.css" />
</head>
<body>
	<div id="body-wrapper">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<div id="main" class="row-fluid">
			<div id="commentframe">

				<div id="commentarea">
					<form class="form-horizontal" action="userSubmitComment.action"
						method="post">
						<div style="margin:10px;">
							<strong id="zyz-orderlistId"> 订单编号:${orderlistId} </strong>
						</div>
						<s:iterator value="goods">
							<div id="goods">
							<div id="photo">
								<img src="resources${goodsImage}">
								<div id="shangpinming">${goodsName}</div>
							</div>

							<div id="commentposition">
								<div id="marks">
									<span style="margin-right:10px;">评分</span>
									<input class="radio inline check" type="radio" id="${goodsId}1"
										name="${goodsId}1" value="option1" >
									1 <input class="radio inline check" type="radio"
										id="${goodsId}2" name="${goodsId}2" value="option2"
										> 2 <input
										class="radio inline check" type="radio" id="${goodsId}3"
										name="${goodsId}3" value="option3" >
									3 <input class="radio inline check" type="radio"
										id="${goodsId}4" name="${goodsId}4" value="option4"
										> 4 <input
										class="radio inline check" type="radio" id="${goodsId}5"
										name="${goodsId}5" value="option5" 
										checked> 5

								</div>
								<div id="comment">
									<span style="margin-right:10px;">评论</span>
									<textarea rows="3" id="${goodsId}comment"
										name="${goodsId}comment"></textarea>
								</div>
							</div>
						</div>
						</s:iterator>
						<div id="submitall">
							<button class="btn  btn-primary hwd-interceptor"  type="submit">提交</button>
						</div>
					</form>
					<div style="clear:both;"></div>
					</div>
				</div>
			</div>

		</div>
		<%@ include file="../common/footer.jsp"%>
	<%@ include file="../common/login.jsp"%>
	<%@ include file="../common/register.jsp"%>
	<script src="js/jquery-1.7.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/common.js"></script>
	<script src="js/addcomment.js"></script>
</body>
</html>