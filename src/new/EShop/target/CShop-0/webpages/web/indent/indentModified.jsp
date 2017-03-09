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
	<link rel="stylesheet" type="text/css" href="css/indent.css" />
</head>
<body>
	<div id="body-wrapper">
		<%@ include file="../common/header.jsp"%>
		<div id="main" class="row-fluid">
			<!-- 所需数据 （一次性显示10条订单）
				1.订单号
				2.下单时间
				3.订单金额
				4.收发货状态（未发货/已发货）
                5.订单中的商品
			-->

            <!-- 取数据 
			<s:iterator value="orderlistDisplay">
				订单号:${orderlistId}<br/>
				下单时间:${orderlistOrderDate}<br/>
				总价格:${orderlistTotalPrice}<br/>
				收发货状态:
				<s:if test="orderlistStatus==0">
					未发货<br/>
				</s:if>
				<s:elseif test="orderlistStatus==1">
				      已发货<br/>
				</s:elseif>
				订单商品:<br/>
				<s:iterator value="goodsImage" id="id" status="status">
                	<s:property value="goodsId[#status.index]" /><br/>
                	<s:property value="id" /><br/>
                </s:iterator> 
				<br/>
		</s:iterator>
		 -->
		 <div id="hwd-indent-wrapper">
			<div id="yxx-indentHead"></div>
	        <div id="yxx-indentContainer">       		        	
		        <div class="yxx-indentContent">
		        	<table style="width:900px; height:auto; border:1px; cellspacing:2px; text-align:center;">
		        		<tr>
		        			<td>订单号</td>
		        			<td>订单商品</td>
		        			<td>收货人</td>
		        			<td>订单金额</td>
		        			<td>下单时间</td>
		        			<td>订单状态</td>
		        		</tr>
		        		<s:if test="orderlistDisplay.size() > 0">        
	        			<s:iterator value="orderlistDisplay">
		        		<tr style="height:90px; margin-top:10px; margin-bottom:10px;">
		        			<td style="width:50px;"><a href="userQueryTransactionInfo.action?orderlistId=${orderlistId}">${orderlistId}</a></td> 
		        			<td style="width:220px;">
		        				<s:if test="goodsImage.size() > 4">
		        					<s:iterator value="goodsImage" id="id" status="status"  begin="0" end="3">
		        						<a href="showAGoodsInfo.action?goodsId=<s:property value="goodsId[#status.index]" />"><img src="resources<s:property value="id" />"  alt="商品图片"  style="width:40px; height:30px;margin-right:5px;"/></a>
		        					</s:iterator>
		        					<br/>
		        					<a href="userQueryTransactionInfo.action?orderlistId=${orderlistId}">查看更多</a>
		        				</s:if>
		        				<s:else>
		        					<s:iterator value="goodsImage" id="id" status="status" >
		        						<a href="showAGoodsInfo.action?goodsId=<s:property value="goodsId[#status.index]" />"><img src="resources<s:property value="id" />"  alt="商品图片"  style="width:40px; height:30px;margin-right:5px;"/></a>
		        					</s:iterator>
		        				</s:else>
		        			</td>
		        			<td style="width:100px;">${linkMan}</td>
		        			<td style="width:100px;">${orderlistTotalPrice}</td>
		        			<td style="width:100px;">${orderlistOrderDate}</td>
		        			<td style="width:100px;">
		        				<s:if test="orderlistStatus==0">
									未发货<br/>
								</s:if>
								<s:elseif test="orderlistStatus==1">
				     				 已发货<br/>
				     				 <a href="userConfirmReceiveGoods.action?orderlistId=${orderlistId}" >确认收货</a>
				     				 <br/>
								</s:elseif>
								<a href="userQueryTransactionInfo.action?orderlistId=${orderlistId}">查看详情</a>
							</td>
	        			 </tr>
	        			 </s:iterator>
		       		</s:if>
		        	</table>		            	
		       </div>
		       
				<s:if test="orderlistDisplay.size() == O">
		       	    <div id="yxx-indentNogoods">
		        		您还没有订单，快去购物吧
		        	</div>
				</s:if>
            
	        </div>
	        <s:if test="orderlistDisplay.size() == 10">
	            	<a class="yxx-indentMoreinfo btn-primary btn"></a>
	       </s:if>	
		</div>
		</div>
		<%@ include file="../common/footer.jsp"%>
	</div>
	<%@ include file="../common/login.jsp"%>
	<%@ include file="../common/register.jsp"%>
	<script src="js/jquery-1.7.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>	
	<script src="js/common.js"></script>
	<script src="js/indent.js"></script>
</body>
</html>