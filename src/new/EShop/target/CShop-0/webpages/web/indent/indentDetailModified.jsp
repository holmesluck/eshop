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
	<link rel="stylesheet" type="text/css" href="css/settle.css" />
	<link rel="stylesheet" type="text/css" href="css/frame.css" />
</head>
<body>
	<div id="body-wrapper">
		<%@ include file="../common/header.jsp"%>
		<div id="main" class="row-fluid">
			<!-- 所需数据 
				订单号		
				客户号	
				下单时间	
				订单金额	
				送货方式	
				收货人	
				收货人地址		
				订单状态
				邮编	
   				收货人手机
   				商品编号	
   				商品名称	
   				原价	
   				交易价	
   				数量	
   				总价								
			-->
			<!--  
			<s:iterator value="orderlistDisplay">
			    客户号:${userName}<br/>
			    收货人:${address.addressLinkman}<br/>
				收货人地址:${address.addressAddress}<br/>	
				邮编:${address.addressPostcode}<br/>
   				收货人手机:${address.addressPhone}<br/>
				订单号:${orderlistId}<br/>
				下单时间:${orderlistOrderDate}<br/>
				订单金额:${orderlistTotalPrice}<br/>
				订单状态：已确认收货<br/>
				商品详情:<br/>
				<s:iterator value="goodsName"  id="id"  status="status">
                <s:property value='id'/>&nbsp;&nbsp;&nbsp;&nbsp;
                <s:property value='amount[#status.index]'/>&nbsp;&nbsp;&nbsp;&nbsp;
                <s:property value="goodsOriginalPrice[#status.index]"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <s:property value="goodsPrice[#status.index]"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <s:property value="goodsTotalPrice[#status.index]"/>
                <br/>
                </s:iterator> 
				<br/>
		       </s:iterator>
		-->	
			<ul class="breadcrumb">
	            <li><a href="#">首页</a> <span class="divider">/</span></li>
	            <li class="active">订单详情</li>
	        </ul>
	        <s:iterator value="orderlistDisplay">
	        <div>
		    <div id="double1">
		        <div id="doublehead1"><strong>配送信息</strong></div>
		        <div id="doublecontent1">
		            <form>
		                <table id="doublecontenttable3">
		                    <tr>
		                        <td>订单号</td>
		                        <td>${orderlistId}</td>
		                        <td>客户号</td>
		                        <td>${userName}</td>
		                        <td>下单时间</td>
		                        <td>${orderlistOrderDate}</td>
		                    </tr>
		                    <tr>
		                        <td>订单金额</td>
		                        <td>￥30.18</td>
		                        <td>送货方式</td>
		                        <td>送货上门</td>
		                        <td>收货人</td>
		                        <td>${address.addressLinkman}</td>
		                    </tr>
		                    <tr>
		                        <td>收货人地址</td>
		                        <td>${address.addressAddress}</td>
		                        <td>订单状态</td>
		                        <td>已确认收货</td>
		                        <td>邮编</td>
		                        <td>${address.addressPostcode}</td>
		                    </tr>
		                    <tr>
		                        <td>收货人手机</td>
		                        <td>${address.addressPhone}</td>
		                        <td></td>
		                        <td></td>
		                        <td></td>
		                        <td></td>
		                    </tr>
		                    </from>
		                </table>
		        </div>
		    </div>
		    <div id="double2">
		        <div id="doublehead2"><strong>商品信息</strong></div>
		        <div id="doublecontent2">
		            <form action="" method="post" enctype="multipart/form-data" name="form1">
		                <table id="favorite">
		                    <thead>
		                    <tr>
		                        <th>商品编号</th>
		                        <th>商品名称</th>
		                        <th>原价</th>
		                        <th>交易价</th>
		                        <th>数量</th>
		                        <th>总价</th>
		                    </tr>
		                    </thead>
		                    <tbody>
		                    <s:iterator value="goodsName"  id="id"  status="status">
		                    <tr>
		                        <td><span><s:property value="#status.index+1"/></span></td>
		                        <td><a href="showAGoodsInfo.action?goodsId=<s:property value="#request.goodsId[#status.index]"/>"><s:property value='id'/></a></td>
		                        <td><s:property value="goodsOriginalPrice[#status.index]"/></td>
		                        <td> 
               				 		<s:property value="goodsPrice[#status.index]"/></td>
		                        <td>
               					    <s:property value='amount[#status.index]'/>
                                </td>
		                        <td>
                					<s:property value="goodsTotalPrice[#status.index]"/>
                				</td>
		                    </tr>
		                    </s:iterator> 
		                    </tbody>
		                </table>
		            </form>
		        </div>
		    </div>
		    </div>
		     </s:iterator> 
		</div>
		<div class="clear"></div>
		<%@ include file="../common/footer.jsp"%>
	</div>
	<%@ include file="../common/login.jsp"%>
	<%@ include file="../common/register.jsp"%>
	<script src="js/jquery-1.7.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/common.js"></script>
</body>
</html>