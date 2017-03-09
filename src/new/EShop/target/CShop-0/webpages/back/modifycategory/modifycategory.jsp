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
       1、目录名（系列）、父目录（品牌）、目录描述
-->
<div id="hwd-firstcatalogId" style="display:none;">${ASecondCatalogInfo.TFirstcatalog.firstcatalogId}</div>
<!-- ${ASecondCatalogInfo.secondcatalogName}
${ASecondCatalogInfo.TFirstcatalog.firstcatalogName}
${ASecondCatalogInfo.secondcatalogDescription} -->
<%@ include file="../common/siteNav.jsp"%>
<div id="wrapper">
    <%@ include file="../common/header.jsp"%>
    <div id="main">
<!--         <div id="search2">
            <div id="searchleft">
                <img src="../image/电脑图片及信息/戴尔.jpg" id="ico_site"/>
                网站路径：<a href="home.html">首页</a>&gt;&gt;<a href="category_list.html">商品目录</a>&gt;&gt;<a
                    href="">商品目录编辑</a>
            </div>
        </div> -->
        <div id="sendnote">
            <div id="sendnotehead"><strong>编辑商品目录</strong></div>
            <div id="sendnotecontent">
                <form action="adminModifyGoodsSeCatalog.action" method="post" name="form1">
                    <table id="itemsearch">
                    <tr><td><input type="text" name="seCatalogId" class="inputtext hidden" value="${ASecondCatalogInfo.secondcatalogId}"></td></tr>
                        <tr>
                            <th width="180"><em class="must">*</em>目录名称</th>
                            <td width="360">
                                <input type="text" name="catalogName" class="inputtext" onFocus="nextfield='password'"
                                       value="${ASecondCatalogInfo.secondcatalogName}" maxlength="25"></td>
                        </tr>
                        <tr>
                            <th><em class="must">*</em>父目录</th>
                            <td>
                                <select id="firstCatalogId" name="firstCatalogId">
                                    <option value="1">联想</option>
                                    <option value="2">宏碁</option>
                                    <option value="3">戴尔</option>
                                    <option value="4">华硕</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>目录描述</th>
                            <td>
                                <textarea name="catalogDescription" class="textAreaStyle">${ASecondCatalogInfo.secondcatalogDescription}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <button type="submit" class="btn">提交</button>
                                <button type="Reset" class="btn">重置</button>
                                <a class="btn" href="adminEnterGoodsManage.action">返回</a>
                            </td>
                        </tr>
                    </table>
                </form>

            </div>
        </div>

        <script src="js/jquery-1.7.2.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/modifycategory.js"></script>
        <script type="text/javascript">
        $("#firstCatalogId").val($("#hwd-firstcatalogId").text());
        </script>
</body>
</html>
