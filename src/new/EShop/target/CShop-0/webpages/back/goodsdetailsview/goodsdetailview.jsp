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
</head>
</html>
<body>
	<!--查看商品详情所显示的数据
		1.市场价
		2.折扣价
		3.库存量
		4.型号
		5.处理器型号
		6.屏幕尺寸
		7.硬盘容量
		8.内存
		9.用户评分
	-->
<%@ include file="../common/siteNav.jsp"%>
<div id="wrapper">
    <%@ include file="../common/header.jsp"%>
    <div id="main">
<!--         <div id="search2">
            <div id="searchleft">
                网站路径：<a href="home.html">首页</a>&gt;&gt;<a href="#">商品详细信息</a>
            </div>
        </div> -->
        <div id="sendnote">
            <div id="sendnotehead"><strong>商品信息</strong></div>
            <div id="sendnotecontent">
                <table id="creditquery">
                    <caption>
                      ${aGoodsInfo.goodsName}
                    </caption>
                    <tbody>
                    <tr>
                        <td class="span2"><img src="resources${aGoodsInfo.goodsImage}">	</td>
                        <td colspan="4" class="tdleftalign">
                            ${aGoodsInfo.goodsDescription}
                        </td>
                    </tr>
                    
                <tr>
                    <td>市场价：￥${aGoodsInfo.goodsPrice}</td>
                    <td>折扣：${aGoodsInfo.goodsDiscount}</td>
                    <td>库存量：${aGoodsInfo.goodsStock}</td>
                    <td>型号：${aGoodsInfo.goodsModel}</td>
                    <td>处理器型号：${aGoodsInfo.goodsProcessor}</td>
                </tr>
                <tr>
                    <td>屏幕尺寸：${aGoodsInfo.goodsScreenSize}</td>
                    <td>硬盘容量：${aGoodsInfo.goodsHarddiskCapacity}</td>
                    <td>内存：${aGoodsInfo.goodsMemory}</td>              
                    <td>用户评分：${aGoodsInfo.goodsAveragemark}</td>
                </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div id="sendnote">
            <div id="sendnotehead"><strong>查看评论</strong></div>
            <div id="sendnotecontent">
              
              <s:iterator value="aGoodsInfo.tComments">
                <table id="creditquery">
                    <tr>
                        <td class="commentr1color"> 会员：</td>
                        <td class="commentr1color">  ${tUser.userEmail}</td>
                        <td class="commentr1color"> 发表时间：</td>
                        <td class="commentr1color">  ${commentDate}</td>
                        <td class="commentr1color"> 打分：</td>
                        <td class="commentr1color">${commentMark}</td>
                    </tr>
                    <tr>
                        <td class="commentr2color">评论：</td>
                        <td colspan="5" class="commentr2color"><span class="red">${commentContent}</span></td>
                        <td><a class="btn" href="adminDeleteGoodsComment.action?commentId=${commentId}&goodsId=${aGoodsInfo.goodsId}">删除</a> </td>
                    </tr>
                  
                </table>
                     </s:iterator>
            </div>
        </div>
        
        <script src="js/jquery-1.7.2.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/goodsdetailsview.js"></script>
</body>
</html>
