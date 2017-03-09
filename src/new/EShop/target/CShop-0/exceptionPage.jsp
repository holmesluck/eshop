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
	<link rel="stylesheet" type="text/css" href="css/exceptionpage.css" />
</head>
<body>
	<div id="body-wrapper">
		<%@ include file="webpages/web/common/header.jsp"%>
		<div id="main" class="row-fluid">
			<!-- 所需数据 
				1.错误信息
			-->
			<div id="yxx-exceptionPageContent">
				<div id="yxx-exceptionPageLeftcontent">
					我是错误信息				
				</div>
				<div id="yxx-exceptionPageRightcontent">
					从前有座山，山里有座庙，<br/>庙里有个页面找不到<br/>
					错误信息：${exception.message}<br/>
					
				</div>
			</div>
		</div>
		<%@ include file="webpages/web/common/footer.jsp"%>
	</div>
	<%@ include file="webpages/web/common//login.jsp"%>
	<%@ include file="webpages/web/common/register.jsp"%>
	<script src="js/jquery-1.7.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/common.js"></script>
</body>
</html>