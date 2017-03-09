<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div id="header" class="clearfix">
	<h1 id="hwd-logo">CShop电脑商城</h1>
	<div id="hwd-pageHeader">
		<div id="hwd-userHolder">
			<div id="hwd-userMenu">
				<ul id="hwd-topMenu">
					<s:if test="#session.userId != null">
						<li><a href="userGetPersonalInfo.action"><s:property value="#session.userNickname"/>(<s:property value="#session.userLevel"/>)</a></li>
						<li><a href="#register" data-toggle="modal">注册</a></li>
						<li><a href="" id="logout">退出</a></li>
					</s:if>
					<s:else>
						<li><a class="hwd-loginButton" href="#login" data-toggle="modal">登录</a></li>
						<li><a class="hwd-registerButton" href="#register" data-toggle="modal">注册</a></li>
					</s:else>
						<li><a id="hwd-cartCount" class="shoppingCart" href="enterCart.action" ><s:property value="#session.amountInCart"/></a></li>
				</ul>
			</div>
		</div>
		<div id="hwd-goodsHolder">
			<div id="hwd-goodsHolder-wrapper">
				<form id="hwd-goodsSearch" action="quickSearchGoods.action" method="get">
					<div class="input-append">
					    <input type="text" name="searchWords" class="" id="hwd-quickSearch" value="<s:property value="#session.searchWords"/>"/>
					    <button type="submit" class="" id="hwd-quickSearch-submit"></button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<div id="hwd-navbar">
    <div id="hwd-navbar-wrapper">
        <ul id="hwd-navbar-list">
            <li><a id="hwd-home" href="showHomePage.action">首页</a></li>
			<li><a id="hwd-goodsSearch" href="advancedSearchGoods.action">商品搜索</a></li>
            <li><a class="hwd-interceptor" id="hwd-Favorite" href="userEnterFavority.action">收藏夹</a></li>
            <li><a class="hwd-interceptor" id="hwd-transaction" href="userQueryTransaction.action?pageNum=1">交易查询</a></li>
            <li><a class="hwd-interceptor" id="hwd-order" href="userGetOrderlist.action?pageNum=1">我的订单</a></li>
        </ul>
    </div>
</div>