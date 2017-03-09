<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>CShop</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<link rel="stylesheet" type="text/css" href="css/settle.css" />
	<link rel="stylesheet" type="text/css" href="css/retrievepassword.css" />
</head>
<body>
	<div id="body-wrapper">
		<%@ include file="../common/header.jsp"%>
		<div id="main" class="row-fluid">
			<!-- 所需数据 
				1.用户名  
			-->
			
			<div id="yxx-retrievePasswordContainer">
				<div id="yxx-retrievePasswordHead"></div>
				<div id="yxx-retrievePasswordContent">
					<form action="userModifyPassword.action" method="post"> 
					<table width="300px" class="table">
						<tr>
							<td class="first">邮箱:</td>
							<td><s:property value="tUser.userEmail"/></td>
						</tr> 
						<tr>
							<td class="first">新密码:</td>
							<td><input type="password" name="password" id="hwd-newPassword" />
							<span class="hwd-test"></span>
							</td>
						</tr>
						<tr>
							<td class="first">再次输入新密码:</td>
							<td><input type="password" id="hwd-newPasswordAgin"/>
							<span class="hwd-test"></span>
							</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="button" id="submit" class="btn disabled" value="修改"></td>
						</tr>
					</table>
					</form>
				</div>
			</div>
		</div>
		<%@ include file="../common/footer.jsp"%>
	</div>
	<%@ include file="../common/login.jsp"%>
	<%@ include file="../common/register.jsp"%>
	<script src="js/jquery-1.7.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/common.js"></script>
	<script src="js/retrievepassword.js"></script>
</body>
</html>