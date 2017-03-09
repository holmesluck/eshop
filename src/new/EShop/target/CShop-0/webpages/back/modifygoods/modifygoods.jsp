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
		14.(必填项）折扣
	
	 商品名  ${goodsInfo.goodsName}
	<s:iterator value = "firstCatalogList">
	  一级目录id ${firstcatalogId} 
	   一级目录名 ${firstcatalogName} 
	   <s:iterator value = "TSecondcatalogs">
		二级目录id	${secondcatalogId}
		二级目录名	${secondcatalogName} 
	   </s:iterator>
	</s:iterator>
	 ${goodsInfo.goodsDescription}
	 ${goodsInfo.goodsImage}
	 ${goodsInfo.goodsModel}
	 ${goodsInfo.goodsOperationSystem}
	 ${goodsInfo.goodsProcessor}
	 ${goodsInfo.goodsMemory}
	 ${goodsInfo.goodsHarddiskCapacity}
	 ${goodsInfo.goodsScreenSize}
	 ${goodsInfo.goodsResolutionDefinition}
	 ${goodsInfo.goodsPrice}
	 ${goodsInfo.goodsStock}
	 ${goodsInfo.goodsDiscount}
	     -->               

 <%@ include file="../common/siteNav.jsp"%>
	<div id="wrapper">
    <%@ include file="../common/header.jsp"%>
    <div id="main">
<!--         <div id="search2">
            <div id="searchleft">
                <img src="../image/电脑图片及信息/戴尔.jpg" id="ico_site"/>
                网站路径：<a href="category_list.html">商品管理</a>&gt;&gt;<a href="item_list.html">商品列表</a>&gt;&gt;<a href="">商品编辑</a>
            </div>
        </div> -->
        <div id="sendnote">
            <div id="sendnotehead"><strong>编辑商品</strong></div>
            <div id="sendnotecontent">
                <s:form action="adminModifyGoods.action" method="post" enctype="multipart/form-data" name="form1">
                    <table id="itemsearch">
                    	<tr><td></td><td><input type="text" class="inputttextlarge hidden" id="goodsId" name="goodsId"
                                       value="${goodsInfo.goodsId}" maxlength="25"></td></tr>
                        <tr>
                            <th width="100px"><em class="must">*</em>商品名称</th>
                            <td width="360px">
                                <input type="text" class="inputttextlarge" id="goodsName" name="goodsName" value="${goodsInfo.goodsName}" maxlength="25"></td>
                        </tr>
                        <tr><td id="">${firstcatalogId}</td></tr>
                        <tr>
                            <th width=""><em class="must">*</em>一级目录</th>             
                            <td width="390">
                                <select name="goodsFirstCatalogId" id="goodsFirstCatalogId">
                                	<option value="${goodsInfo.TFirstcatalog.firstcatalogId}">${goodsInfo.TFirstcatalog.firstcatalogName}</option>
                                </select>
                            </td>  
                        </tr>
                        <tr>
                            <th width="">二级目录</th>             
                            <td width="390">
                                <select name="goodsSecondCatalogId" id="goodsSecondCatalogId">
                                	<option value="${goodsInfo.TSecondcatalog.secondcatalogId}">${goodsInfo.TSecondcatalog.secondcatalogName}</option>
                                </select>
                            </td>  
                        </tr>
                        <tr>
                            <th>商品描述</th>
                            <td><textarea id="goodsDescription" name="goodsDescription" class="textAreaStyle">${goodsInfo.goodsDescription}</textarea></td>
                            <td></td>
                        </tr>
                        <tr>
                            <th><span class="inputHeader"><em class="must">*</em>商品图片</span></th>
                            <td><img src="resources${goodsInfo.goodsImage}" class="picture1"><br>
                                </td>
                            <td><s:file name="myFile"></s:file></td>
                        </tr>
                        <tr>
                            <th><em class="must">*</em>型号</th>
                            <td><input type="text" id="goodsModel" name="goodsModel" class="inputttextlarge" value="${goodsInfo.goodsModel}"></td>
                        </tr>
                        <tr>
                            <th><em class="must">*</em>操作系统</th>
                            <td><input type="text" id="goodsOperationSystem" name="goodsOperationSystem" class="inputttextlarge" value="${goodsInfo.goodsOperationSystem}"></td>
                        </tr>
                        <tr>
                            <th><em class="must">*</em>处理器</th>
                            <td><input type="text" id="goodsProcesse" name="goodsProcesser" class="inputttextlarge" value="${goodsInfo.goodsProcessor}"></td>
                        </tr>
                        <tr>
                            <th><em class="must">*</em>内存</th>
                            <td><input type="text" id="goodsMemory" name="goodsMemory" class="inputttextlarge" value="${goodsInfo.goodsMemory}"></td>
                        </tr>
                        <tr>
                            <th><em class="must">*</em>硬盘容量</th>
                            <td><input type="text" id="goodsHarddiskCapacity" name="goodsHarddiskCapacity" class="inputttextlarge" value="${goodsInfo.goodsHarddiskCapacity}"></td>
                        </tr>
                        <tr>
                            <th>屏幕尺寸</th>
                            <td><input type="text" id="goodsScreenSize" name="goodsScreenSize" class="inputttextlarge" value="${goodsInfo.goodsScreenSize}"></td>
                        </tr>
                        <tr>
                            <th>分辨率</th>
                            <td><input type="text" id="goodsResolutionDefinition" name="goodsResolutionDefinition" class="inputttextlarge" value="${goodsInfo.goodsResolutionDefinition}"></td>
                        </tr>
                        <tr>
                            <th><em class="must">*</em>市场价</th>
                            <td><input type="text" id="goodsPrice" name="goodsPrice" class="inputttextlarge" value="${goodsInfo.goodsPrice}"></td>
                        </tr>
                        <tr>
                            <th><em class="must">*</em>库存量</th>
                            <td>
                                <input type="text" id="goodsStock" name="goodsStock" class="inputttextlarge" value="${goodsInfo.goodsStock}"></td>
                        <tr>
                            <th><em class="must">*</em>折扣</th>
                            <td>
                                <input type="text" id="goodsDiscount" name="goodsDiscount" class="inputttextlarge" value="${goodsInfo.goodsDiscount}"><span >折扣输入为0-1的数值(不包含0)</span></td>
                                
                        </tr>
                        <tr>
                            <th></th>
                      
                            <td>                              
                             
                                <s:submit cssClass="btn btn-primary struts2submit" value="提交"></s:submit>
                           </td>
                            <td></td>
                        </tr>
                    </table>
                </s:form>
            </div>
        </div>
    </div>
 
        <script src="js/jquery-1.7.2.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/modifygoods.js"></script>
</body>
</html>
