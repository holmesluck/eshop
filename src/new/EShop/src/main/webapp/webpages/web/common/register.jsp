<!-- 这里是注册模块，他包含在所有界面中，初始为隐藏状态 
@author 黄伟铎
-->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div id="register" class="modal hide fade">
    <!-- 注册模块头部，用来提示所处位置 -->
    <div class="modal-header">
    </div>
    <!-- 注册模块的表单部分 -->
    <div class="modal-body">
        <form class="form-horizontal">
            <div class="control-group">
                <label class="control-label" for="hwd-register-userEmail"><em class="hwd-must">*</em>邮箱</label>

                <div class="controls">
                    <input type="text" name="hwd-register-userEmail" id="hwd-register-userEmail" placeholder="请输入您的邮箱">
                    <span class="hwd-test"></span>
                    <span></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="hwd-register-userNickname">昵称</label>

                <div class="controls">
                    <input type="text" name="hwd-register-userNickname" id="hwd-register-userNickname" placeholder="请输入您的昵称">
                    <span class="hwd-test hwd-right"></span>
                    <div style="font-size:11px;">请输入2-16位字符，支持中文、英文、数字、'-'和'_'</div>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="hwd-register-userPassword"><em class="hwd-must">*</em>密码</label>

                <div class="controls">
                    <input type="password" name="hwd-register-userPassword" id="hwd-register-userPassword" placeholder="请输入您的密码">
                    <span class="hwd-test"></span>
                    <div style="font-size:11px;">请输入6-16位数字、字母、'-'和'_'，注意区分字母大小写 </div>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="hwd-register-passwordAgain"><em class="hwd-must">*</em>确认密码</label>

                <div class="controls">
                    <input type="password" id="hwd-register-passwordAgain" placeholder="请再次输入您的密码">
                    <span class="hwd-test"></span>
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <button type="button" class="btn btn-primary disabled" id="hwd-register"></button>
                </div>
            </div>
        </form>
    </div>
</div>