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
	      1、品牌、系列
		  2、系列id
	-->
	<%-- <s:iterator value="catalogList">	
		<a href="xx.action?firstcatalogId=${firstcatalogId}">${firstcatalogName}</a>		
		<s:iterator value="TSecondcatalogs">	
			<a href="xx.action?firstcatalogId=${secondcatalogId}">${secondcatalogName}</a>
		</s:iterator>	
	</s:iterator> --%>	
	<%@ include file="../common/siteNav.jsp"%>
<div id="wrapper">
    <%@ include file="../common/header.jsp"%>
	<div id="main">
<!--     <ul class="breadcrumb">
        <li><a href="#">首页</a> <span class="divider">/</span></li>
        <li class="active">商品管理</li>
    </ul> -->
<div id="sendnote">
    <div id="sendnotehead"><strong>现有目录列表</strong></div>
    <div id="sendnotecontent" class="clearfix">
    <s:iterator value="catalogList">
    	<div class="contents">
	        <ul>
	            <h4>${firstcatalogName}</h4>
	            <s:iterator value="TSecondcatalogs">
	            	<li><a href="showAllGoods.action?secondcatalogId=${secondcatalogId}&pageNum=0">${secondcatalogName}</a><a class="hwd-deleteCategory" href="adminDeleteSeGoodsCatalog.action?seCatalogId=${secondcatalogId}"><img src="resources/delete.jpg" id="ico_site"/></a><a href="adminEnterSeCatalogModify.action?secondCatalogId=${secondcatalogId}"><img src="resources/modify.jpg" id="ico_site"/></a></li>
	            </s:iterator>	
	        </ul>
    	</div>	
	</s:iterator>
</div>
</div>
<div id="double2">
      <div id="doublehead1"><strong>添加目录</strong></div>
    <div id="doublecontent2">
        <form method="post" action="adminAddGoodsSeCatalog.action" class="form-horizontal" id='hwd-addCatalog'>
          <div class="control-group">
            <label class="control-label" for="catalogName"><em class="must">*</em>目录名</label>
            <div class="controls">
              <input type="text" id="catalogName" name="catalogName" placeholder="目录名">
            </div>
          </div>
          <div class="control-group">
            <label class="control-label" for="firstCatalogId"><em class="must">*</em>父目录</label>
            <div class="controls">
                <select id="firstCatalogId" name="firstCatalogId">
                    <option value="0">请选择</option>
                    <option value="1">联想</option>
                    <option value="2">宏基</option>
                    <option value="3">戴尔</option>
                    <option value="4">华硕</option>
                </select>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label" for="catalogDescription">目录描述</label>
            <div class="controls">
              <textarea  id="catalogDescription" name="catalogDescription"></textarea>
            </div>
          </div>
          <div class="control-group">
            <div class="controls">
              <button type="submit" id="hwd-addCategory-submit" class="btn">添加</button>
              <button type="reset" class="btn">重置</button>
            </div>
          </div>
        </form>
    </div>
    </div><!-- 
	<div id="bottom">Copyright © HelloWorld</div> -->
	<script src="js/jquery-1.7.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/add_deletecategory.js"></script>	
</body>
</html>
