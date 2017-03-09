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
<link rel="stylesheet" type="text/css" href="css/personalInfo.css" />
<link rel="stylesheet" type="text/css" href="css/settlement.css" />
</head>
<body>
	<div id="body-wrapper">
		<%@ include file="../common/header.jsp"%>
		<div id="main" class="row-fluid">
			<!-- 所需数据 
				1、个人信息所有数据
				2、该用户地址簿所有数据
				3、积分数据
			-->
<!-- 			Address的显示 -->
			<!-- <s:property value="tUser.userEmail" />
			<s:property value="tUser.userNickname" />
			<s:iterator value="tUser.TAddresses">
				<s:property value="addressId"/>
				<s:property value="addressAddress"/>
				<s:property value="addressPostcode"/>
				<s:property value="addressPhone"/>
				<s:property value="addressLinkman"/>
			</s:iterator>
			<s:property value="tUser.userRewardpoints" />
			
			<s:property value="status"/>
			<s:iterator value="addressList">
				<s:property value="addressAddress"/>
				<s:property value="addressPostcode"/>
				<s:property value="addressPhone"/>
				<s:property value="addressLinkman"/>
				<s:property value="addressIsdel"/>
				<s:property value="addressIsdefault"/>
			</s:iterator> -->
			<div id="hwd-personalInfo-wrapper">
				<div id="hwd-personalInfo-header"></div>
			    <div class="tabbable" id="hwd-personalInfo-content">
			        <ul class="nav nav-tabs">
			            <li class="active"><a href="#hwd-personalInfo" data-toggle="tab">个人基本信息</a></li>
			            <li><a href="#hwd-address" data-toggle="tab">地址薄信息</a></li>
			            <li><a href="#hwd-changePassword" data-toggle="tab">修改密码</a></li>
			            <li><a href="#hwd-score" data-toggle="tab">积分说明</a></li>
			        </ul>
			        <div class="tab-content">
			            <div class="tab-pane fade active in" id="hwd-personalInfo">
			            	<form class="form-horizontal">
							  	<div class="control-group">
							    	<label class="control-label">邮箱:</label>
							    	<div class="controls">
							    		<div class="hwd-personalInfo-show">
							      			<s:property value='tUser.userEmail'/>
							      		</div>
							    	</div>
							  	</div>
							  	<div class="control-group">
							    	<label class="control-label" for="inputPassword">昵称:</label>
							    	<div class="controls">
							    		<div class="hwd-personalInfo-show">
							    			<span id="hwd-personalInfo-userNickname"><s:property value='tUser.userNickname'/></span>
							      			<input type="text" class="hidden" id="hwd-personalInfo-changeUserNickname" placeholder="请输入您的昵称" value="<s:property value='tUser.userNickname'/>" />
							      			<a type="button" class="btn btn-primary hwd-interceptor" id="hwd-personalInfo-change"></a>
								      	<a type="button" class="btn btn-primary hidden hwd-interceptor" id="hwd-personalInfo-submit"></a>
							      		</div>
							    	</div>
							  	</div>
							  	
							  	<!-- <div class="control-group">
								    <div class="controls">
								      	<a type="button" class="btn btn-primary hwd-interceptor" id="hwd-personalInfo-change">修改</a>
								      	<a type="button" class="btn btn-primary hidden hwd-interceptor" id="hwd-personalInfo-submit">完成</a>
								    </div>
								</div> -->
							</form>
			            </div>
			            <div class="tab-pane fade" id="hwd-address">
			            	<table id="hwd-address-table" class="table">
			            		<thead>
			            			<tr>
			            				<th>默认地址</th>
			            				<th>收货人</th>
			            				<th>详细地址</th>
			            				<th>手机号码</th>
			            				<th>邮政编码</th>
			            				<th>操作</th>
			            			</tr>
			            		</thead>
			            		<tbody>
									<s:iterator value="tUser.TAddresses">
				            			<tr class="addressIsdel<s:property value='addressIsdel'/>">
				            				<td ><input type="radio" name="address" value="<s:property value='addressId'></s:property>" class="defaultAddress defaultAddress<s:property value='addressIsdefault'/> "/></td>
				            				<td class="yxx-address"><s:property value="addressLinkman"></s:property></td>
				            				<td class="yxx-address"><s:property value="addressAddress"></s:property></td>
				            				<td class="yxx-address"><s:property value="addressPhone"></s:property></td> 
				            				<td class="yxx-address"><s:property value="addressPostcode"></s:property></td>     
				            				<td>
				            					<a class="btn yxx-settlementModify" id="<s:property value='addressId' />"></a>
				            					<a class="btn hwd-deleteAddress" id="<s:property value='addressId' />"></a>
				            				</td>            			
				            			</tr>
				            		</s:iterator>
			            		</tbody>
			            	</table>
			            	<div class="clearfix">
			            	<a class="btn" id="hwd-settlement-addAddress"></a>
			            	</div>
			            	<div id="hwd-addAddress" class="hide">
				            	<form class="form-horizontal">
								  	<div class="control-group">
								    	<label class="control-label" for="hwd-addAddress-name">姓名:</label>
								    	<div class="controls">
								      		<input type="text" id="hwd-addAddress-name" placeholder="请输入您的姓名">
								      		<span class="hwd-test"></span>
								    	</div>
								  	</div>
								  	<div class="control-group">
								    	<label class="control-label" for="hwd-addAddress-address">通讯地址:</label>
								    	<div class="controls">
								      		<input type="text" id="hwd-addAddress-address" placeholder="请输入您的详细地址">
								      		<span class="hwd-test"></span>
								    	</div>
								  	</div>
								  	<div class="control-group">
								    	<label class="control-label" for="hwd-addAddress-phone">手机号码:</label>
								    	<div class="controls">
								      		<input type="text" id="hwd-addAddress-phone" placeholder="请输入您的手机号码">
								      		<span class="hwd-test"></span>
								    	</div>
								  	</div>
								  	<div class="control-group">
								    	<label class="control-label" for="hwd-addAddress-zip">邮政编码:</label>
								    	<div class="controls">
								      		<input type="text" id="hwd-addAddress-zip" placeholder="请输入您的邮编">
								    	<span class="hwd-test"></span>
								    	</div>
								  	</div>
								  	<div class="control-group">
									    <div class="controls">
									      	<button type="button" class="btn hwd-interceptor disabled" id="hwd-addAddress-submit"></button>
									    </div>
									</div>
								</form>
			            	</div>
			            	<div id="hwd-changeAddress" class="hide">
				            	<form class="form-horizontal">
				            	<div id="hwd-getId" style="display:none;"></div>
								  	<div class="control-group">
								    	<label class="control-label" for="hwd-changeAddress-name">姓名:</label>
								    	<div class="controls">
								      		<input type="text" id="hwd-changeAddress-name" placeholder="请输入您的姓名">
								    	<span class="hwd-test hwd-right"></span>
								    	</div>
								  	</div>
								  	<div class="control-group">
								    	<label class="control-label" for="hwd-changeAddress-address">通讯地址:</label>
								    	<div class="controls">
								      		<input type="text" id="hwd-changeAddress-address" placeholder="请输入您的详细地址">
								    	<span class="hwd-test hwd-right"></span>
								    	</div>
								  	</div>
								  	<div class="control-group">
								    	<label class="control-label" for="hwd-changeAddress-phone">手机号码:</label>
								    	<div class="controls">
								      		<input type="text" id="hwd-changeAddress-phone" placeholder="请输入您的手机号码">
								    	<span class="hwd-test hwd-right"></span>
								    	</div>
								  	</div>
								  	<div class="control-group">
								    	<label class="control-label" for="hwd-changeAddress-zip">邮政编码:</label>
								    	<div class="controls">
								      		<input type="text" id="hwd-changeAddress-zip" placeholder="请输入您的邮编">
								    	<span class="hwd-test hwd-right"></span>
								    	</div>
								  	</div>
								  	<div class="control-group">
									    <div class="controls">
									      	<button type="button" class="btn hwd-interceptor" id="hwd-changeAddress-submit"></button>
									    </div>
									</div>
								</form>
			            	</div>
			            </div>
			            <div class="tab-pane fade" id="hwd-changePassword">
			            	<form class="form-horizontal">
								  	<div class="control-group">
								    	<label class="control-label" for="hwd-changePassword-Email">注册邮箱:</label>
								    	<div class="controls">
								    		<div id="hwd-changePassword-Email" class="hwd-personalInfo-show"><s:property value='tUser.userEmail' /></div>
								    	</div>
								  	</div>
								  	<div class="control-group">
								    	<label class="control-label" for="hwd-changePassword-oldPassword">原密码:</label>
								    	<div class="controls">
								      		<input type="password" id="hwd-changePassword-oldPassword" placeholder="oldPassword">
								      		<span class="hwd-test"></span>
								    	</div>
								  	</div>
								  	<div class="control-group">
								    	<label class="control-label" for="hwd-changePassword-newPassword">新密码:</label>
								    	<div class="controls">
								      		<input type="password" id="hwd-changePassword-newPassword" placeholder="newPassword">
								      		<span class="hwd-test"></span>
								    	</div>
								  	</div>
								  	<div class="control-group">
								    	<label class="control-label" for="hwd-changePassword-newPasswordAgain">再次输入:</label>
								    	<div class="controls">
								      		<input type="password" id="hwd-changePassword-newPasswordAgain" placeholder="newPasswordAgain">
								      		<span class="hwd-test"></span>
								    	</div>
								  	</div>
								  	<div class="control-group">
									    <div class="controls">
									      	<button type="button" class="btn btn-primary disabled hwd-interceptor" id="hwd-changePassword-submit"></button>
									    </div>
									</div>
								</form>
			            </div>
			           
			            <div class="tab-pane fade" id="hwd-score">
			            <!-- <s:iterator value='TUserlevelList'>
			           		<s:property value='userlevelDiscount'/>
			           		<s:property value='userlevelLimits'/>
			           		<s:property value='userlevelName'/>
			           </s:iterator> -->
							<p><b>积分:</b><s:property value='tUser.userRewardpoints'/></p>
							<p><b>1、什么是积分：</b>足够多的积分可以让用户升级为更高等级的会员，越高等级的会员每次购买商城商品都将享受更多的折扣</p>
							<p><b>2、获得积分：</b>购买商城任何商品均可获得积分</p>
							<p><b>3、积分与等级：</b>
							<table class="table" style="text-align:center;">
							<tr><th>会员等级</th><th>所需积分</th><th>享受折扣</th></tr>
							<tr><td>普通会员</td><td>0</td><td>无优惠</td></tr>
							<tr><td>银卡会员</td><td>10000</td><td>9.8折</td></tr>
							<tr><td>金卡会员</td><td>50000</td><td>9.6折</td></tr>
							<tr><td>白金会员</td><td>150000</td><td>9.3折</td></tr>
							</table>
							</p>
							<p><b>4、积分规则：</b><p>1.积分专属CShop商城，仅限商城内使用</p><p>2.积分可累积且永久有效</p><p>3.1元现金=1积分用户购买多少元商品将获得多少积分</p><p>4.用户每次购买商品时，都将按照他所在会员等级所对应的折扣对商品进行进一步打折（入商品原来就有折扣则折上折）</p></p>
			            </div>
				    </div>
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
	<script src="js/personalInfo.js"></script>
	<script src="js/settlement.js"></script>
</body>
</html>