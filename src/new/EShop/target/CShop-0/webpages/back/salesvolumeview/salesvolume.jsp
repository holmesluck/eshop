<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>电脑商城</title>
    <link href="css/admin-frame.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/admin-home.css" type="text/css" rel="stylesheet">
     <link href="css/tcal.css" rel="stylesheet">
     <link  rel="stylesheet" href="css/calender.css" >
</head>
</html>
<body>
<!-- 所需数据：
       1、商品卖出时间、销售数量、销售单价
	   2、一级目录（品牌）销售情况，包括销售数量和销售总价
	   3、二级目录（系列）销售情况，包括销售数量和销售总价
	   4、一年内所有商品销售情况，包括销售数量和销售总价
-->
<!--<table style="cellspacing:5px; border:1px solid; margin:5px; text-align:center; width:600px; height:auto;">
<tr>
	<th>品牌</th>
	<th>销量</th>
	<th>销售额</th>
</tr>
<s:iterator value="catalogList"  id="id"  status="status">
     <tr>
     <td>
     	<s:property value='id'/>
     </td>
     <td>
     	<s:property value='salesAmount[#status.index]'/>
     </td>
     <td>
     	<s:property value='sales[#status.index]'/>
     </td>
     </tr>
</s:iterator>
<tr>
<td>总销售量：${salesTotalAmount};</td>
<td>总销售额：${totalSales};</td>
</tr>
</table>
-->
<%@ include file="../common/siteNav.jsp"%>
<div id="wrapper">
    <%@ include file="../common/header.jsp"%>
    <div id="main">
<!--         <div id="search2">
            <div id="searchleft">
                <img src="../image/电脑图片及信息/戴尔.jpg" id="ico_site"/>
                网站路径：<a href="">销售量统计</a>
            </div>
        </div> -->
        <div id="sendnote">
            <div id="sendnotehead"><strong>销售量查询</strong></div>
            <div id="sendnotecontent">
                <form action="adminSalesStatistics.action" method="post" name="form1">
                    <table id="tradequery">
                        <tr>
                           <tr><th>起始时间：&nbsp;</th><td><input name="startDate" id="zyz-start-time"/></td></tr>
                           <tr><th>结束时间：&nbsp;</th><td><input name="terminalDate" id="zyz-end-time"/></td></tr>
                        </tr>
                        <tr>
                            <th></th>
                            <td><button type="submit" class="bt2 btn btn-primary" id="zyz-salessearch">查询</button>
                            <td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div id="double2">
    <div id="doublehead2"><strong>销售量统计</strong></div>
    <div id="zyz-sendnotecontent">
                <form action="" method="post"  name="form1">
                    <table id="tradequery" class="table table-striped">
                       <tr >
	                       <th style="text-align:left;">品牌/系列</th>
	                       <th style="text-align:left;">销量</th>
	                       <th style="text-align:left;">销售额</th>
                       </tr>
                      <s:iterator value="catalogList"  id="id"  status="status">
                       <tr>
                        <td>
     	                  <s:property value='id'/>
                       </td>
                        <td>
     	                <s:property value='salesAmount[#status.index]'/>
                        </td>
                         <td>
     	                 <s:property value='sales[#status.index]'/>
                        </td>
                        </tr>
                       </s:iterator>
                       <tr>
                      <td><b>总销售量：${salesTotalAmount};</b></td>
                       <td><b>总销售额：${totalSales};</b></td>
                       </tr>
                    </table>
                </form>
            </div>
</div>
        <script src="js/jquery-1.7.2.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/tcal.js"></script>
        <script src="js/salesvolume.js"></script>
        
	<script type="text/javascript" src="js/calendar.js" ></script>  
	<script type="text/javascript" src="js/calendar-zh.js" ></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
</body>
</html>
