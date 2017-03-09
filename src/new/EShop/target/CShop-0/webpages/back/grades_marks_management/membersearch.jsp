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
<!-- 所需数据：
       1、会员信息（包括：会员号、会员级别、积分、注册时间）
	   2、会员订单信息
-->
<!-- <s:property value="tUser.userId"/>
<s:property value="tUser.userEmail"/>
<s:property value="tUser.TUserlevel.userlevelName"/>
<s:property value="tUser.userRewardpoints"/>
 -->
<%@ include file="../common/siteNav.jsp"%>
<div id="wrapper">
    <%@ include file="../common/header.jsp"%>
    <div id="main">
<!--         <div id="search2">
            <div id="searchleft">
                <img src="../image/电脑图片及信息/戴尔.jpg" id="ico_site"/>
                网站路径：<a href="user_admin.html">会员管理</a>&gt;&gt;会员查询结果
            </div>
        </div> -->
        <div id="sendnote">
            <div id="sendnotehead"><strong>查询结果</strong></div>
            <div id="sendnotecontent">
                    <table id="favorite">
                        <thead>
                        <tr>
                            <th>会员号</th>
                            <th>会员邮箱</th>
                            <th>会员级别</th>
                            <th>积分</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:if test="tUser==null">
                        	<tr><td><span style="color:red;font-size:18px;">该用户不存在</span></td></tr>
                        </s:if>
                        <s:else>
                        
                        <tr id="isDel${request.user.userIsdel}">
                        	<td><s:property value="tUser.userId"/></td>
                            <td><s:property value="tUser.userEmail"/></td>
                            <td><s:property value="tUser.TUserlevel.userlevelName"/></td>
                            <td><s:property value="tUser.userRewardpoints"/></td>
                            <td><a class="btn btn-danger" href="adminDeleteUser.action?userId=<s:property value='tUser.userId'/>">删除</a></td>
                        </tr>
                        </s:else>
                        </tbody>
                    </table>
            </div>
        </div>
        </div>
        <script src="js/jquery-1.7.2.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/membersearch.js"></script>
        </div>
</body>
</html>
