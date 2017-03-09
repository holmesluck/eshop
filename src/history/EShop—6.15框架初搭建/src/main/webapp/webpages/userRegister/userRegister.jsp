<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<center>
		<form action="/EShop/userRegister.action" method="post">
			<table width="671" height="302" border="1" cellpadding="1"
				cellspacing="1">
				<tr>
					<th height="40" colspan="2" scope="row"><span class="STYLE3">用户注册</span>
					</th>
				</tr>
				<tr>
					<th width="104" height="40" scope="row"><span class="STYLE2">用户名</span>
					</th>
					<td width="554"><input name="userName" type="text"
						class="STYLE2" size="46" maxlength="30" />
					</td>
				</tr>

				<tr>
					<th height="46" scope="row"><span class="STYLE2">密码</span>
					</th>
					<td><input name="password" type="password" class="STYLE2"
						size="46" maxlength="30" />
					</td>
				</tr>
				<tr>
					<th height="46" scope="row"><span class="STYLE2">描述</span>
					</th>
					<td><input name="description" type="text" class="STYLE2"
						size="46" maxlength="30" />
					</td>
				</tr>
				

				<tr>
					<th height="37" colspan="2" scope="row">
					<input name="Submit" type="submit" value="提交"/>	
						&nbsp;&nbsp; 
					<input name="Submit2" type="reset" value="重置" />
					</th>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>
