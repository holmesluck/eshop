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
				3.总价格
				4.收发货状态（未发货/已发货）
                5.订单地址（地址，电话号码）
                6.订单中的商品信息（商品名、 数量）	
			-->

            <!-- 取数据
            <s:property value="orderlistDisplay.size()"/><br/>
            <s:if test="orderlistDisplay.size() == 10">  
            1<br/>
            </s:if>
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
				订单地址:${address}<br/>
				电话号码：${phoneNumber}<br/>
				商品详情:<br/>
				<s:iterator value="goodsName"  id="id"  status="status">
                <s:property value='id'/> 
                <s:property value='amount[#status.index]'/>
                <br/>
                </s:iterator> 
				<br/>
		</s:iterator>
		 -->
		 <div id="hwd-index-wrapper">
			<div id="yxx-indentHead"><!-- <img src="resources/indent.jpg" /> --></div>
	        <div id="yxx-indentContainer">
	        <s:if test="orderlistDisplay.size() > 0">        
	        	<s:iterator value="orderlistDisplay">	        		        	
		        <div class="yxx-indentContent">
		            	<div class="yxx-indentLine1">
		            		<ul>
			                	<li>订单编号:<span>${orderlistId}</span></li>
	                            <li>成交时间:${orderlistOrderDate}</li>	                                                       		                    
		                   <s:if test="orderlistStatus==1">
		                    <li>
		                    	<a href="userConfirmReceiveGoods.action?orderlistId=${orderlistId}" class="btn yxx-indentQueren"/>确认收货</a>
		                    </li>
		                   </s:if>
		                    </ul>
		                </div>
		                <div class="yxx-indentLine1">
		                	<ul>
			                	<li><div class="controlOverflow">收货地址:${address}</div></li>
			                    <li>电话号码:${phoneNumber}</li>
		                    </ul>		                   
		                </div>
		                <div class="yxx-indentLine3">
		                	<div class="yxx-indentGoods">
		                    	<table cellpadding="0" cellspacing="0">
		                    	    <tr>
		                            	<td class="yxx-indentName">商品名</td>
		                                <!-- <td class="yxx-indentPrice">价格</td> -->
		                                <td class="yxx-indentNum">数量</td>
		                            </tr>
		                        	<s:iterator value="goodsName"  id="id"  status="status">
		                        	<tr>
		                            	<td class="yxx-indentName"><a href="showAGoodsInfo.action?goodsId=<s:property value='goodsId[#status.index]'/>"><s:property value='id'/></a></td>
		                                <!-- <td class="yxx-indentPrice"></td> -->
		                                <td class="yxx-indentNum"><s:property value='amount[#status.index]'/></td>
		                            </tr>
		                            </s:iterator>                                           
		                        </table>
		                    </div>
		                    <div class="yxx-indentTotal">
		                    	<p>合计：</p>
		                        <p>${orderlistTotalPrice}</p>
		                    </div>
		                    <div class="yxx-indentState">
		                    	<s:if test="orderlistStatus==0">未发货</s:if>
								<s:elseif test="orderlistStatus==1">已发货</s:elseif>
							</div>
		                </div>                        
		         </div>
		         </s:iterator>
		       </s:if>
		       
				<s:if test="orderlistDisplay.size() == O">
		       	    <div id="yxx-indentNogoods">
		        		您还没有订单，快去购物吧
		        	</div>
				</s:if>
            
	        </div>
	        <s:if test="orderlistDisplay.size() == 10">
	            <div class="yxx-indentMoreinfo"><a class="yxx-indentMoreinfo btn-primary btn"></a></div>
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