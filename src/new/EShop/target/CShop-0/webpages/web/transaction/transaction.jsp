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
	<link  rel="stylesheet" href="css/transaction.css" >
</head>
<body>
	<div id="body-wrapper">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<div id="main" class="row-fluid">
	        <!-- <ul class="breadcrumb">
	            <li><a href="#">首页</a> <span class="divider">/</span></li>
	            <li class="active">交易查询</li>
	        </ul> -->
	        <form action="userQueryTransaction.action?pageNum=1" method="post">
	        <div class="sendnote">
	            <div id="sendnotehead"></div>
	            <div id="sendnoteBegintime"> 
	              	选择起始时间：<input type="text" id="EntBegintime" name="startDate" />
	              	选择结束时间：<input type="text" id="EntEndtime" name="terminalDate" />	   
	            	<input type="submit" class="btn hwd-transaction-search" value="">                   	              
	            </div>
	        </div>
	        </form>
        <!-- <s:iterator value="orderlistDisplay">
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
		</s:iterator> -->
	        <div class="sendnote">
	            <div id="sendnotehead2"></div>
	            <div >
	                    <table id="yxx-transactionContent">
	                        <thead>
	                        <tr>
	                            <th>订单号</th>
	                            <th>交易完成时间</th>
	                            <th>详情</th>
	                        </tr>
	                        </thead>
	                        <tbody>
	                        <s:iterator value="orderlistDisplay">
	                        <tr>
	                            <td>${orderlistId}</td>
	                            <td>${orderlistOrderDate}</td>
	                            <td><a id="hwd-transaction-search" href="userQueryTransactionInfo.action?orderlistId=${orderlistId}" class="btn"></a></td>
	                        </tr>
	                        </s:iterator>
	                        </tbody>
	                    </table>
	                    <s:if test="orderlistDisplay.size() == 10">
	                       <!--  <div id="submitall"> -->
						    <div id="yxx-tranctionMoreinfo"><a><div id="yxx-transactionMore" class=" btn-primary btn"></div></a></div>
							<!-- </div> -->
						</s:if>
	            </div>
	        </div>		    
        </div>   	    
	    <%@ include file="../common/footer.jsp"%>
    </div>
	<script src="js/jquery-1.7.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/calendar.js" ></script>  
	<script type="text/javascript" src="js/calendar-zh.js" ></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="js/transaction.js"></script>
	<script src="js/common.js"></script>
</body>
</html>