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
	<link rel="stylesheet" type="text/css" href="css/shoppingcart.css" />
</head>
<body>
	<div id="body-wrapper">
		<%@ include file="../common/header.jsp"%>
		<div id="main" class="row-fluid">
			<!-- 所需数据 （商品为用户购物车中的商品，一次性显示10条订单）
				1.编号（1,2,3...）
				2.商品图片
				3.商品名
				4.商品价格
				5.商品折后价格
				6.数量
			-->
          
			<%--<s:iterator value = "cartListToShow">
			商品图片: ${tGoods.goodsImage} <br/>
			商品名   :  ${tGoods.goodsName} <br/>
			商品价格:  ${tGoods.goodsPrice}<br/>
			商品折后价格:${price }<br/>
			数量:    ${amount} <br/>
			庫存：        ${stock}  <br/>
			商品总价:  ${totalPrice} <br/>
			选择：	${boolSelected}<br/>
			</s:iterator>  --%>
	
			<div id="hwd-shoppingCart-wrapper">
			<div id="yxx-shoppingcartHead"></div>			
			<s:if test="cartListToShow != null && cartListToShow.size() > 0">
				<div id="yxx-shoppingcartContainer" class="clearfix">
	            	<div class="yxx-shoppingcartContent">
	                    <table border="0" cellpadding="0" cellspacing="0" >
	                      <tr class="yxx-shoppingcartTablehead">
	                        <th><input type="checkbox" id="hwd-shoppingCart-checkAll"/>全选</th>
	                        <th class="yxx-shoppingcartGoodsInfo">商品</th>
	                        <th>库存</th>
	                        <th>价格</th>
	                        <!-- <th>积分</th> -->
	                        <th>数量</th>
	                        <th>小计</th>
	                        <th>操作</th>
	                      </tr>
	                      <s:iterator value = "cartListToShow">
	                      <tr >
	                        <s:if test="boolSelected == 1">
	                        	<td><input type="checkbox" checked="checked" class="yxx-shoppingcartCheckbox" value="${TGoods.goodsId}" /></td>
	                        </s:if>
	                        <s:if test="boolSelected == 0">
	                        	<td><input type="checkbox" class="yxx-shoppingcartCheckbox" value="${TGoods.goodsId}" /></td>
	                        </s:if>
	                        <td class="yxx-shoppingcartGoodsInfo">
	                            <div class="yxx-shoppingcartGoodsInfo">
	                            	<a href="showAGoodsInfo.action?goodsId=${TGoods.goodsId}">
	                                	<img src="resources/${tGoods.goodsImage} " width="80px" height="80px"/>
	                            	</a>
	                            	<span >
	                                	<a href="showAGoodsInfo.action?goodsId=${TGoods.goodsId}">
	                                    	${tGoods.goodsName}
	                                    </a>
	                                </span>
	                            </div>
	                        </td>
	                        <td id="yxx-shoppingcartKucun">${stock}</td>
	                        <td>${price }<!-- ￥${tGoods.goodsPrice} --></td>                        
	                        <!-- <td class="yxx-shoppingcartAcc">￥${price }</td>                         -->
	                        <td style="width:150px" id="yxx-shoppingcartNumcontrol">
	                        	<a class="yxx-shoppingcartreduce">
	                            	<img src="resources/cart_btn_reduce.gif" />
	                            </a>
								<input name="Gqty" type="text" class="yxx-shoppingcartNumber" value="${amount}" />							
								<a class="yxx-shoppingcartadd" >
	                            	<img src="resources/cart_btn_add.gif" />
	                            </a>
	                        </td>
	                        <td class="yxx-shoppingcartAccount">${totalPrice}</td>
	                        <td name="${TGoods.goodsId}">
	                            <p><a class="yxx-shoppingcartRemove" name="${TGoods.goodsId}">移入收藏夹</a></p>
	                            <p><a class="yxx-shoppingcartDelete" name="${TGoods.goodsId}">删除</a></p>
	                        </td>
	                      </tr>
	                      </s:iterator>
	                    </table>
	                    <div id="yxx-shoppingcartTotalaccount"><%-- 总计：${totalPrice} --%></div>
	            	</div>
	                <a class="btn yxx-shoppingcartSubmit"></a>
				</div>
			</s:if>
			
			<s:else>
				<div id="yxx-shoppingcartNogoods">
		        		这里没有一丝商品的痕迹。。。
		        </div>
		    </s:else>
		</div>
		</div>
		<%@ include file="../common/footer.jsp"%>
	</div>
	<%@ include file="../common/login.jsp"%>
	<%@ include file="../common/register.jsp"%>
	<script src="js/jquery-1.7.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/common.js"></script>
	<script src="js/shoppingcart.js"></script>	
</body>
</html>