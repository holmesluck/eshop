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
    <%@ include file="../common/header.jsp"%>
    <div id="main">
    <br>
        <!-- <div id="search2">
            <div id="searchleft">
                <img src="../image/电脑图片及信息/戴尔.jpg" id="ico_site"/>
                网站路径：<a href="home.html">首页</a>&gt;&gt;<a href="">管理员登录</a>
            </div>
        </div> -->
        <div id="sendnote">
            <div id="sendnotehead"><strong> 管理员登录</strong></div>
            <div id="sendnotecontent">
                <form name="form1">
                    <table id="itemsearch">
                    	<tr>&nbsp;</tr>
                    	<tr>&nbsp;</tr>
                    	<tr>&nbsp;</tr>
                        <tr>
                            <th class="itemsearchth">用户名：</th>
                            <td class="itemsearchtd1">
                                <input type="Text" class="inputtext" id="adminName" value=""
                                       maxlength="25">
                            </td>
                            <td class="itemsearchtd2">
                            </td>
                        </tr>
                        <tr>
                            <th class="itemsearchth">密&nbsp;&nbsp;&nbsp;码：</th>
                            <td class="itemsearchtd1">
                                <input type="password" class="inputtext" id="adminPassword" value="" maxlength="25">
                            </td>
                            <td class="itemsearchtd2">&nbsp;</td>
                        </tr>
                        <tr>
                            <th></th>
                            <td><button type="button" class="btn btn-primary" id="hwd-admin-login">登录</button>
                                &nbsp;<input class="btn" type="reset" name="button1" value="重填">
                                &nbsp;
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <br/>
    </div>
</div>
        <script src="js/jquery-1.7.2.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/adminLogin.js"></script>
</body>
</html>
