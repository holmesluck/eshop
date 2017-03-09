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
       1、会员级别设置信息（会员级别、积分下限、折扣比例）
-->
<%-- 
<s:iterator value = "userLevelList">
	<s:property value = "userlevelName"/>
	<s:property value = "userlevelLimits"/>
	<s:property value = "userlevelDiscount"/>
</s:iterator>
--%>
<%@ include file="../common/siteNav.jsp"%>
<div id="wrapper">
    <%@ include file="../common/header.jsp"%>
<div id="main">
<!-- <div id="search2">
    <div id="searchleft">
        <img src="../image/电脑图片及信息/戴尔.jpg" id="ico_site"/>
        网站路径：<a href=""> 会员管理</a>
    </div>
</div> -->
<div id="double1">
    <div id="doublehead1"><strong>会员级别设置</strong></div>
    <div id="doublecontent1">
            <table id="xialabiao">
                <tr>
                    <th class="inputHeader">&nbsp;</th>
                    <th align="center" class="inputHeader">会员级别&nbsp;&nbsp;</th>
                    <th align="center" class="inputContent ">积分下限</th>
                    <th width="18%" align="center" class="inputContent ">折扣比例</th>
                </tr>
	            <s:iterator value = "userLevelList">
	            	<tr>
	                    <td class="inputHeader">&nbsp;</td>
	                    <td align="center" class="inputHeader"><s:property value = "userlevelName"/></td>
	                    <td align="center" class="inputContent "><s:property value = "userlevelLimits"/></td>
	                    <td width="18%" align="center" class="inputContent "><s:property value = "userlevelDiscount"/></td>
	                </tr>
				</s:iterator>
                <tr>
                    <td colspan="6" class="titlegrey">&nbsp;</td>
                </tr>
                </table>
                <form method="post" action="adminModifyUserLevel.action">
                <table>
	                <tr>
	                    <td width="4%" class="inputHeader">&nbsp;</td>
	                    <td width="12%" align="center" class="inputHeader">会员级别：</td>
	                    <td width="25%" align="left" class="inputContent">
	                        <select name="userLevelId">
	                            <option value="1">普通会员</option>
	                            <option value="2">银卡</option>
	                            <option value="3">金卡</option>
	                            <option value="4">白金卡</option>
	                        </select></td>
	                    <td colspan="2" align="center" class="inputContent"><font color="#CC0000">&nbsp;&nbsp;&nbsp;&nbsp;
	                        &nbsp;</font></td>
	                    <td width="8%" align="center" class="inputContent">&nbsp;</td>
	                </tr>
	                <tr>
	                    <td class="inputHeader">&nbsp;</td>
	                    <td align="center" class="inputHeader">折扣比例：</td>
	                    <td align="left" class="inputContent"><input id="hwd-discount" type="text" size="6" name="discount"  maxlength="6">%</td>
	                    <td colspan="2" align="left" class="inputContent"><font color="#CC0000">会员购买物品时获得的折扣比例</font>
	                    </td>
	                    <td align="center" class="inputContent">&nbsp;</td>
	                </tr>
	                <tr>
                    <td colspan="2" class="inputHeader">&nbsp;</td>
                    <td colspan="3" align="left" class="inputContent">
                        <input type="submit" id="hwd-discount-submit" class="bt2 btn btn-primary" value="提交" >
                        <input type="reset" class="bt2 btn" value="重填"">
                    </td>
                    <td align="center" class="inputContent">&nbsp;</td>
                </tr>
            </table>
            </form>
    </div>
</div>
<div id="double2">
    <div id="doublehead2"><strong>会员查询</strong></div>
    <div id="doublecontent2">
    <form method="post" action="adminSearchUser.action">
        <table width="100%">
            <tr>
                <td width="3%" class="inputHeader">&nbsp;</td>
                <td width="16%" class="inputHeader">会员号：</td>
                <td width="39%" class="inputContent"><input type="text"  name="userId" value="" maxlength="15" size="15"></td>
                <td width="42%" class="inputContent">&nbsp;</td>
            </tr>
            <tr>
                <td width="4%" class="inputContent">&nbsp;</td>
                <td width="17%" class="inputHeader">&nbsp;</td>
                <td width="39%" class="inputContent">
                    <strong> <input type="submit"  class="bt2 btn btn-primary" value="查询" ></strong>&nbsp;&nbsp;
                    <input type="reset" class="bt2 btn" value="重填">
                    &nbsp; </td>
                <td width="40%" class="inputContent">&nbsp;</td>
            </tr>
        </table>
        </form>
    </div>
</div>
</div>
</div>
<script src="js/jquery-1.7.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/membermanagement.js"></script>
</body>
</html>
