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
    <link rel="stylesheet" type="text/css" href="css/settlement.css" />
</head>
<body>
	<div id="body-wrapper">
		<%@ include file="../common/header.jsp"%>
		<div id="main" class="row-fluid">
			<!-- 所需数据 （商品为正要购买的商品）
				1.编号
				2.商品图片
				3.商品名
				4.商品价格
				5.商品折后价格
				6.数量
			    7.订单总价格
				8.产生总积分
				9.默认送货地址信息(姓名，地址，电话号码，)	
			-->
			
				<!-- <s:iterator value = "buyList">
					商品图片: ${tGoods.goodsImage} <br/>
					商品名   :  ${tGoods.goodsName} <br/>
					商品价格:  ${tGoods.goodsPrice}<br/>
					商品折后价格:${price }<br/>
					数量:    ${amount} <br/>
					商品总价:  ${totalPrice} <br/>				
					订单总价格： ${totalPrice} <br/>
					产生总积分：       <br/>
					地址信息： ${defaultAddress.addressAddress }<br/>
					邮编：${defaultAddress.addressPostcode }<br/>
					联系电话：${defaultAddress.addressPhone }<br/>
					联系人：${defaultAddress.addressLinkman }<br/> 
				</s:iterator> -->
				<div id="hwd-settlementAddress-wrapper">
            	<div id="yxx-settlementAddressHead">
            		<a class="btn" id="hwd-settlement-addAddress"></a>
            	</div>
                <!-- <div id="yxx-settlementAddressHead">
                                                      送货地址
                    <input type="button" value="更换地址" class="yxx-settlementButton btn" />
                </div> -->
						<div id="hwd-address">
			            	<table id="hwd-address-table" class="table">
			            		<thead>
			            			<tr>
			            				<th>默认地址</th>
			            				<th>收货人</th>
			            				<th>详细地址</th>
			            				<th>手机号码</th>
			            				<th>邮政编码</th>
			            				<th>操作</th>
			            			</tr>
			            		</thead>
			            		<tbody>
									<s:iterator value="addressJsonViewList">
				            			<tr>
				            				<td ><input type="radio" name="address" value="<s:property value='addressId'></s:property>" class="defaultAddress defaultAddress<s:property value='addressIsdefault'/> "/></td>
				            				<td class="yxx-address"><s:property value="addressLinkman"></s:property></td>
				            				<td class="yxx-address"><s:property value="addressAddress"></s:property></td>
				            				<td class="yxx-address"><s:property value="addressPhone"></s:property></td> 
				            				<td class="yxx-address"><s:property value="addressPostcode"></s:property></td>     
				            				<td><a class="btn yxx-settlementModify" id="<s:property value='addressId' />"></a></td>            			
				            			</tr>
				            		</s:iterator>
			            		</tbody>
			            	</table>
			            	<div id="hwd-addAddress" class="hide">
				            	<form class="form-horizontal">
								  	<div class="control-group">
								    	<label class="control-label" for="hwd-addAddress-name">姓名:</label>
								    	<div class="controls">
								      		<input type="text" id="hwd-addAddress-name" placeholder="请输入您的姓名">
								      		<span class="hwd-test"></span>
								    	</div>
								  	</div>
								  	<div class="control-group">
								    	<label class="control-label" for="hwd-addAddress-address">通讯地址:</label>
								    	<div class="controls">
								      		<input type="text" id="hwd-addAddress-address" placeholder="请输入您的详细地址">
								      		<span class="hwd-test"></span>
								    	</div>
								  	</div>
								  	<div class="control-group">
								    	<label class="control-label" for="hwd-addAddress-phone">手机号码:</label>
								    	<div class="controls">
								      		<input type="text" id="hwd-addAddress-phone" placeholder="请输入您的手机号码">
								      		<span class="hwd-test"></span>
								    	</div>
								  	</div>
								  	<div class="control-group">
								    	<label class="control-label" for="hwd-addAddress-zip">邮政编码:</label>
								    	<div class="controls">
								      		<input type="text" id="hwd-addAddress-zip" placeholder="请输入您的邮编">
								    	<span class="hwd-test"></span>
								    	</div>
								  	</div>
								  	<div class="control-group">
									    <div class="controls">
									      	<button type="button" class="btn hwd-interceptor disabled" id="hwd-addAddress-submit"></button>
									    </div>
									</div>
								</form>
			            	</div>
			            	<div id="hwd-changeAddress" class="hide">
				            	<form class="form-horizontal">
				            	<div id="hwd-getId" style="display:none;"></div>
								  	<div class="control-group">
								    	<label class="control-label" for="hwd-changeAddress-name">姓名:</label>
								    	<div class="controls">
								      		<input type="text" id="hwd-changeAddress-name" placeholder="请输入您的姓名">
								    	<span class="hwd-test hwd-right"></span>
								    	</div>
								  	</div>
								  	<div class="control-group">
								    	<label class="control-label" for="hwd-changeAddress-address">通讯地址:</label>
								    	<div class="controls">
								      		<input type="text" id="hwd-changeAddress-address" placeholder="请输入您的详细地址">
								    	<span class="hwd-test hwd-right"></span>
								    	</div>
								  	</div>
								  	<div class="control-group">
								    	<label class="control-label" for="hwd-changeAddress-phone">手机号码:</label>
								    	<div class="controls">
								      		<input type="text" id="hwd-changeAddress-phone" placeholder="请输入您的手机号码">
								    	<span class="hwd-test hwd-right"></span>
								    	</div>
								  	</div>
								  	<div class="control-group">
								    	<label class="control-label" for="hwd-changeAddress-zip">邮政编码:</label>
								    	<div class="controls">
								      		<input type="text" id="hwd-changeAddress-zip" placeholder="请输入您的邮编">
								    	<span class="hwd-test hwd-right"></span>
								    	</div>
								  	</div>
								  	<div class="control-group">
									    <div class="controls">
									      	<button type="button" class="btn hwd-interceptor" id="hwd-changeAddress-submit"></button>
									    </div>
									</div>
								</form>
			            	</div>
			            </div>
	                <!-- <div id="yxx-settlementSubmit">
	                	<a href="userSubmitPayment.action"><input type="submit" value="提交订单" class="yxx-settlementSubmit btn hwd-interceptor" /></a>
	                </div> -->
	            </div>
				   <div id="hwd-settlement-swapper">
			<div id="yxx-settlementHead"></div>			   
			<div id="yxx-settlementContainer">
                <div id="yxx-settlementGoodsHead"></div>
            	<div id="yxx-settlementGoods">                
                        <table width="900" border="0" cellpadding="0" cellspacing="0" >
                          <tr>
                          	<th style="width:50px;">编号</th>
                            <th class="yxx-shoppingcartGoodsInfo">商品</th>
                            <th>价钱</th>
                            <th>数量</th>
                            <th>小计</th>
                          </tr>
                          <% int i = 0; %>
                          <s:iterator value = "buyList">
                          <% i+=1; %>
                          <tr>                           
                            <td style="width:50px">
                            	<%=i %> 
                            </td>
                            <td class="yxx-shoppingcartGoodsInfo">
                                <div class="yxx-shoppingcartGoodsInfo">
                                    <a href="showAGoodsInfo.action?goodsId=${TGoods.goodsId}">
                                        <img src="resources${tGoods.goodsImage}" width="80px" height="80px"/>
                                    </a>
                                    <span >
                                        <a href="showAGoodsInfo.action?goodsId=${TGoods.goodsId}">
                                            ${tGoods.goodsName}
                                        </a>
                                    </span>
                                </div>
                            </td>
                            <!-- <td>${tGoods.goodsPrice}</td> -->
                            <td>${price }</td>
                            <td>${amount}</td>
                            <td>${countPrice}</td>
                             <!-- 折前价格：totalPrice  -->
                          </tr>
                          </s:iterator>
                        </table>
                        <div id="yxx-settlementGoodsTotal" class="clearfix">
                        	<p id="yxx-settlementGoodsAccount">总计：￥${countPrice}
                        		<%-- 会员折后价${countPrice} --%>
                        		<%-- 会员没有打折价${totalPrice} --%>
                        	</p>
                        	<p id="yxx-settlementGoodsIntegral">本单产生积分：${score}</p>
                        </div>
                        <div id="yxx-settlementSubmit" class="">
                        	<a href="userSubmitPayment.action" id="hwd-settlementSubmit" class="yxx-settlementSubmit btn hwd-interceptor"></a>
                            <a class="btn yxx-settlementBack" href="enterCart.action"></a>
                        </div>
                </div>
                <div class="clear"></div>
            </div>
           	</div>
            </div>	
            
		</div>
		<%@ include file="../common/footer.jsp"%>
	</div>
	<%@ include file="../common/login.jsp"%>
	<%@ include file="../common/register.jsp"%>
	<script src="js/jquery-1.7.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/settlement.js"></script>
	<script src="js/common.js"></script>
</body>
</html>