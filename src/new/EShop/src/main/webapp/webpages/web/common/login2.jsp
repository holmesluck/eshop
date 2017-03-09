<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>CShop</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/home.css" />
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
<div id="body-wrapper">
		<%@ include file="../common/header.jsp"%>
		<div id="main">
		</div>
		<%@ include file="../common/footer.jsp"%>
		</div>
	<%@ include file="../common/login.jsp"%>
	<%@ include file="../common/register.jsp"%>
	<script src="js/jquery-1.7.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/common.js"></script>
	<script>
		alert("您还未登录，请先登录");
		$("#login").modal("show");
	</script>
</body>
</html>