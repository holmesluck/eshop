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
    
    <link  rel="stylesheet" href="css/calender.css" >
</head>
</html>
<body>
<!-- 订单列表 
<div>
	<s:iterator value="orderList">
				<s:property value="orderlistId"/>
				<s:property value="TUser.userId"/>
				<s:property value="TAddress.addressAddress"/>
				<s:property value="orderlistOrderdate"/>
				<s:property value="orderlistStatus"/>
				<s:property value="orderlistTotalprice"/>
				<s:iterator value="TOrderlistcontents">
				<s:property value="TGoods.goodsId"/>
				<s:property value="orderlistcontentNum"/>
				</s:iterator>
			</s:iterator>
</div>
 -->
<%@ include file="../common/siteNav.jsp"%>
<div id="wrapper">
    <%@ include file="../common/header.jsp"%>
    <div id="main">
<!--         <div id="search2">
            <div id="searchleft">
                <img src="../image/电脑图片及信息/戴尔.jpg" id="ico_site"/>
                网站路径：订单管理
            </div>
        </div> -->
        <div id="sendnote">
            <div id="sendnotehead"><strong>订单查询</strong></div>
            <div id="sendnotecontent">
                <form action="" method="post" name="form1">
                    <table id="tradequery">
                        <tr>
                            <th>订&nbsp;&nbsp;单&nbsp;&nbsp;号：</th>
                            <td>
                            <input type="Text" class="inputtext" name="webUserEntity.order" id="zyz-input-ordernum"
                                       onFocus="nextfield='webUserEntity.order'" maxlength="25">
                           </td>
                        </tr>
                        <tr>
                            <th>客&nbsp;&nbsp;户&nbsp;&nbsp;号：</th>
                            <td><input type="Text" class="inputtext" name="webUserEntity.user" id="zyz-input-userid"
                                       onFocus="nextfield='webUserEntity.order'" maxlength="25"></td>
                        </tr>
                        <tr>
                            <th>订单状态：</th>
                            <td id="zyz-orderstate">
                                 <input type="radio" name="check" id="" class="zyz-state" checked>随意
                                <input type="radio" name="check" id="0" class="zyz-state"> 未发货
                                <input type="radio" name="check" id="1" class="zyz-state" > 已发货
                                <input type="radio" name="check" id="2" class="zyz-state" > 已确认收货
                            </td>
                        </tr>
                        <tr><th>起始时间：&nbsp;</th><td><input  id="zyz-start-time" value=""/></td></tr>
                        <tr><th>结束时间：&nbsp;</th><td><input  id="zyz-end-time" value=""/></td></tr>
                        <tr>
                            <th></th>
                            <td>
                               <a type="Button" name="button22" class="btn btn-primary" id="zyz-ordersearch">查询
                               </a>
                                
                            <td>
                        </tr>
                    </table>
                </form>
            </div>
             <div id="sendnotehead"><strong>查询结果</strong></div>
            <div id="sendnotecontent">
                <form action="" method="post" name="form1">
                    <table id="favorite">
                        <thead>
                        <tr>
                            <th></th>
                            <th>订单号</th>
                            <th>客户号</th>
                            <th>下单时间</th>
                            <th>订单状态</th>
                            <th>详情</th>
                        </tr>
                        </thead>
                       <tbody id="zyz-showorderlist">
                       <s:iterator value="orderList">
                        
                        <tr>
                            <td class="checkb"><input type="checkbox" name="ordersn" value=<s:property value="orderlistId"/> class="zyz-select orderlistStatus<s:property value='orderlistStatus'/>" id="zyz-select" ></td>
                            <td><s:property value="orderlistId"/></td>
                            <td><s:property value="TUser.userId"/></td>
                            <td ><s:property value="orderlistOrderdate"/></td>
                            <td class="zyz-checkthebox" name="zyz-checkthebox">             
                            <s:if test="orderlistStatus==0">
				                               未发货<br/>
				           </s:if>
				           <s:elseif test="orderlistStatus==1">
				                             已发货<br/>
				          </s:elseif>
				          <s:else>
					               已确认收货<br/>
				          </s:else>
                            
                            </td>                           
                            <td><a href="adminShowOrderDetail.action?orderlistId=<s:property value="orderlistId"/>"><img src="resources/xq.gif" value="详情" class="picture"></a></td>                  
                        </tr>
                       
                        </s:iterator>
                          </tbody>
                         <tbody id="zyz-button" >
                         <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><a class="btn zyz-more" id="zyz-more1">更多</a></td>
                            <td></td>
                            <td></td>
                         </tr>                
                         </tbody>
                          <tbody id="zyz-showorderlist2" class="hide">
                        </tbody>
                        <tbody id="zyz-button1" class="hide">
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><a class="btn zyz-more"   id="zyz-more2">更多</a></td>
                            <td></td>
                            <td></td>
                        </tr>
                        
                        </tbody>
                        <tbody id="zyz-button2">
                          <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><a class="btn zyz-verify" id="zyz-verify">审核</a></td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
        
       
        <script src="js/jquery-1.7.2.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/ordermanage.js"></script>
      
        <script type="text/javascript" src="js/calendar.js" ></script>  
	<script type="text/javascript" src="js/calendar-zh.js" ></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
</body>
</html>
