<!-- 这里是登录模块，他包含在所有界面中，初始为隐藏状态 
@author 黄伟铎 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div id="login" class="modal hide fade">
    <!-- 登录模块的头部，用来指示这里是登录界面 -->
    <div class="modal-header">
    </div>
    <!-- 登录模块的表单部分 -->
    <div class="modal-body">
        <form class="form-horizontal" action="userLogin.action" method="post">
            <div class="control-group">
                <label class="control-label" for="hwd-login-userEmail">用户名</label>

                <div class="controls">
                    <input type="text" name="hwd-login-userEmail" id="hwd-login-userEmail" placeholder="请输入您的邮箱">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="hwd-login-userPassword">密码</label>

                <div class="controls">
                    <input type="password" name="hwd-login-userPassword" id="hwd-login-userPassword" placeholder="请输入您的密码">
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <label class="checkbox">
                      <input type="checkbox" name="hwd-login-remember" id="hwd-login-remember"> 记住我7天
                    </label>
                    <button type="button" id="hwd-login-submit" class="btn"></button>
                    <!-- 点击这里会隐藏登录模块后显示注册模块 -->
                    <a href="#register" class="btn" data-toggle="modal" id="hwd-registerButton"></a>
                    <a href="" id="hwd-login-findPass">找回密码</a>
                </div>
            </div>
        </form>
    </div>
</div>