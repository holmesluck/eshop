<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>电脑商城</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/admin-frame.css" rel="stylesheet">
    <link href="css/admin-home.css" type="text/css" rel="stylesheet">
</head>
</html>
<body>
	<!--所需数据
		1.管理员用户名
		2.密码
	-->
<div id="wrapper">
    <%@ include file="back/common/header.jsp"%>
    <div id="main">
    <br>
        <!-- <div id="search2">
            <div id="searchleft">
                <img src="../image/电脑图片及信息/戴尔.jpg" id="ico_site"/>
                网站路径：<a href="home.html">首页</a>&gt;&gt;<a href="">管理员登录</a>
            </div>
        </div> -->
        <div id="sendnote">
            <div id="sendnotehead"><strong> 异常页面</strong></div>
			<div style="height:200px; text-align:center; font-size:36px; color:red; padding-top:150px ">
				操作错误
			</div>
        </div>
        <br/>
        <div id="bottom">Copyright © HelloWorld</div>
    </div>
</div>
        <script src="js/jquery-1.7.2.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/adminLogin.js"></script>
</body>
</html>
