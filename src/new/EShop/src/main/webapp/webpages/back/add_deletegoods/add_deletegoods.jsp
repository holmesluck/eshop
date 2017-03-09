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
	<!--添加商品所需数据
		1.(必填项）商品名、父目录（下拉列表，内容含父目录和系列子目录）
		2.商品描述
		3.(必填项）商品照片（选择文件和上传按钮）
                4.品牌（下拉列表）
		5.型号
		6.操作系统
		7.处理器
		8.内存
		9.硬盘容量
		10.屏幕尺寸
		11.分辨率
		12.(必填项）市场价
		13.(必填项）库存量
		14.(必填项）折扣h
	-->
	<!-- 
	数据：<br/>
	<s:iterator value="goods" id="id" status="status">
	商品Id:${goodsId}<br/>
	图片:${goodsImage}<br/>
	商品名:${goodsName}<br/>
	市场价:${goodsPrice}<br/>
	折后价:<s:property value="discountPrice[#status.index]" /><br/>
	</s:iterator>
	  -->
<%@ include file="../common/siteNav.jsp"%>
<div id="wrapper">
    <%@ include file="../common/header.jsp"%>
<div id="main">
<!-- <div id="search2">
    <div id="searchleft">
        <img src="../image/电脑图片及信息/戴尔.jpg" id="ico_site"/>
        网站路径：<a href="category_list.html">商品管理</a>&gt;&gt;<a href="#"> 商品列表</a>
    </div>
</div> -->
<div id="double1">
    <div id="doublehead1"><strong>商品列表</strong></div>
    <div id="doublecontent1">
            <a href="#double2" class="btn btn-primary" id="addCategory">添加商品</a>
            <table id="xialabiao">
            <s:iterator value="goods" id="id" status="status">
            <tbody class="isdel${goodsIsdel}">
                <tr>
                    <td width="7%" rowspan="4" align="center"><span class="red hidden">${goodsId}</span></td>
                    <td width="23%" rowspan="4" align="right" class="inputHeader"><a href="adminGetAGoodsInfo.action?goodsId=${goodsId}"><img
                            src="resources${goodsImage}" width="100" height="100" border="0"></a></td>
                    <td width="70%" align="left" class="inputHeader"><span
                            class="red">${goodsName}</span></td>
                </tr>
                <tr>
                    <td align="left" class="inputHeader">市场价:${goodsPrice}</td>
                </tr>
                <tr>
                    <td align="left" class="inputHeader">折后价:<s:property value="discountPrice[#status.index]" /></td>
                </tr>
                <tr>
                    <td align="left" class="inputHeader"><a href="adminGetGoodsInfo.action?goodsId=${goodsId}" class="btn">修改</a>&nbsp;&nbsp;
                        <a href=""  class="hwd-deleteGoods btn" id="${goodsId}">删除</a></td>
                </tr>
                </tbody>
                </s:iterator>
            </table>
    </div>
</div>
<div id="double2">
    <div id="doublehead2"><strong>添加商品</strong></div>
    <div id="doublecontent2">
    
        <s:form action="adminAddGoods.action" method="post" enctype="multipart/form-data" name="form1">
        <table id="itemsearch">
            <tr>
                <th width="181"><em class="must">*</em>商品名称</th>
                <td width="390">
                    <input type="Text" class="inputttextlarge" id="goodsName" name="goodsName" onFocus="nextfield='name'" value=""
                           maxlength="25"></td>
            </tr>
            <tr>
                <th width="181"><em class="must">*</em>一级目录</th>             
                <td width="390">
                    <select id="goodsFirstCatalogId" name="goodsFirstCatalogId">
                        <option value="0">请选择</option>
	                    <option value="1">联想</option>
	                    <option value="2">宏基</option>
	                    <option value="3">戴尔</option>
	                    <option value="4">华硕</option>
                    </select>
                </td>  
            </tr>
            <tr>
                <th width="181">二级目录</th>             
                <td width="390">
                    <select id="goodsSecondCatalogId" name="goodsSecondCatalogId">
                    </select>
                </td>  
            </tr>
            <tr>
                <th>商品描述</th>
                <td><textarea id="goodsDescription" name="goodsDescription" class="textAreaStyle"></textarea></td>
                <td></td>
            </tr>
            <tr>
                 <!--  <th><span class="inputHeader"><em class="must">*</em>商品图片</span></th>
                <td>
                      <input type="file" id="goodsImage">
                  		</td> -->
                  		<th><span class="inputHeader"><em class="must">*</em>商品图片</span></th>
                  		<td></td>
      <td><s:file name="myFile"></s:file></td>
                <td></td>
            </tr>
            <tr>
                <th><em class="must">*</em>型号</th>
                <td><input type="text" id="goodsModel" name="goodsModel" class="inputttextlarge" value=""></td>
            </tr>
            <tr>
                <th>操作系统</th>
                <td><input type="text" id="goodsOperationSystem" name="goodsOperationSystem" class="inputttextlarge" value=""></td>
            </tr>
            <tr>
                <th><em class="must">*</em>处理器</th>
                <td><input type="text" id="goodsProcesser" name="goodsProcesser" class="inputttextlarge" value=""></td>
            </tr>
            <tr>
                <th><em class="must">*</em>内存</th>
                <td><input type="text" id="goodsMemory" name="goodsMemory" class="inputttextlarge" value=""></td>
            </tr>
            <tr>
                <th><em class="must">*</em>硬盘容量</th>
                <td><input type="text" id="goodsHarddiskCapacity" name="goodsHarddiskCapacity" class="inputttextlarge" value=""></td>
            </tr>
            <tr>
                <th>屏幕尺寸</th>
                <td><input type="text" id="goodsScreenSize" name="goodsScreenSize" class="inputttextlarge" value=""></td>
            </tr>
            <tr>
                <th>分辨率</th>
                <td><input type="text" id="goodsResolutionDefinition" name="goodsResolutionDefinition" class="inputttextlarge" value=""></td>
            </tr>
            <tr>
                <th><em class="must">*</em>市场价</th>
                <td><input type="text" id="goodsPrice" name="goodsPrice" class="inputttextlarge" value=""></td>
            </tr>
            <tr>
                <th><em class="must">*</em>库存量</th>
                <td>
                    <input type="text" id="goodsStock" name="goodsStock" class="inputttextlarge" value=""></td>
            <tr>
                <th><em class="must">*</em>折扣</th>
                <td>
                    <input type="text" id="goodsDiscount" name="goodsDiscount" class="inputttextlarge" value=""><span >折扣输入为0-1数值(不包含0)</span></td>
                    
            </tr>
            <tr>
                <th></th>
                <td>
                 <!--    <input type="button" id="hwd-addSubmit" name="button2" class="btn" value="添加">
                    <input type="Reset" name="button1" class="btn" value="重置"> -->
                   <td><s:submit  cssClass="btn btn-primary struts2submit" value="提交"></s:submit></td>
                </td>
                <td></td>
            </tr>
        </table>
        </s:form>
    </div>
</div>
<script src="js/jquery-1.7.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/add_deletegoods.js"></script>
</body>
</html>
