<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div id="site-nav">
    <div id='site-nav-wrap'>
        <ul>
            <li><a href="adminLogout.action" id="logout">退出</a></li>
            <li><a href="adminLogout.action"><s:property value="#session.admin.adminName" /></a></li>
            <div style="clear:both;"></div>
        </ul>
    </div>
</div>