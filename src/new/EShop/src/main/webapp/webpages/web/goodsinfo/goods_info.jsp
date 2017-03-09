<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>CShop</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<link rel="stylesheet" type="text/css" href="css/goods_info.css">
	<link  rel="stylesheet" type="text/css"  href="css/goodssearch.css" >
	
</head>
<body>
	<div id="body-wrapper">
		<%@ include file="../common/header.jsp"%>
			<div id="main" class="row-fluid clearfix">
				<div class="hwd-sendnote" class="clearfix">
					<div class="hwd-sendnote-wrapper">
        				<div id="sendnotehead3"></div>
        					<div id="sendnotecontent">
            					<div id="goodsinfo" class="clear">
									<div class="clearfix" >
		    							<div id="photo"  style="padding: 5px;margin:20px;float:left;"><img src="resources${aGoodsInfo.goodsImage}">	</div>
		    								<div id="introduce"  style="margin-top:20px;">
			  									${aGoodsInfo.goodsDescription}
			  									<hr/>
			  									<table style="width:300px;height:auto;padding:5px;">
		  										<s:if test="aGoodsInfo.goodsDiscount != 1" >
		  											<tr>
			  											<td>CShop价:</td>
			  											<td><span style="text-decoration:line-through;margin-right:10px;">¥${aGoodsInfo.goodsPrice}</span>
			  											</td>
		  											</tr>
		  											<tr>
			  											<td>促&nbsp;&nbsp;销&nbsp;&nbsp;价:</td>
			  											<td><span  style="color:red;font-size:16px;"><s:property value="aGoodsInfo.goodsPrice*aGoodsInfo.goodsDiscount"/></span></td>
			  										</tr>
			  									</s:if>
			  									<s:else>
			  									<tr>
			  										<td>CShop价:</td>
			  										<td><span style="color: red;">¥${aGoodsInfo.goodsPrice}</span>
			  										</td>
		  										</tr>
		  										</s:else>
			  									<tr>
			  										<td>商品评分:</td>
			  										<td>
			  											<s:if test="aGoodsInfo.goodsAveragemark <= 1.4">
			  												<img src="resources/advancedSearch/star1.png" />
			  											</s:if>
			  											<s:if test="aGoodsInfo.goodsAveragemark > 1.4 && aGoodsInfo.goodsAveragemark <= 2.4">
			  												<img src="resources/advancedSearch/star2.png" />
			  											</s:if>
			  											<s:if test="aGoodsInfo.goodsAveragemark > 2.4 && aGoodsInfo.goodsAveragemark <=3.4">
			  												<img src="resources/advancedSearch/star3.png" />
			  											</s:if>
			  											<s:if test="aGoodsInfo.goodsAveragemark > 3.4 && aGoodsInfo.goodsAveragemark <= 4.4">
			  												<img src="resources/advancedSearch/star4.png" />
			  											</s:if>
			  											<s:if test="aGoodsInfo.goodsAveragemark > 4.4">
			  												<img src="resources/advancedSearch/star5.png" />
			  											</s:if>
			  										</td>
		  											<tr>
		  											<td>库&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存:</td>
		  											<td>
		  											<s:if test="aGoodsInfo.goodsStock > 0">
		  												有货，下单后立即发货<br/>
		  											</s:if>
		  											<s:else>
		  												很抱歉哦亲，没有货了,先收藏吧<br/>
		  											</s:else>
		  											</td>
		  											<tr>
		  												<td>服&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务:</td>
		  												<td>由CShop商城发货并提供售后服务</td>
		  											</tr>
		  											</table>
		  											<div style="margin-top:5px;">
		  												<a type="button" class="btn btn-primary addshoppingcart1" id="${aGoodsInfo.goodsId}" style="margin-right:30px;"></a>
			       										<a type="button" class="btn btn-primary addcollection1 hwd-interceptor" id="${aGoodsInfo.goodsId}" ></a>       						      
													</div>   
											</div>
									</div>
									<div class="tabbable tabs-top" style="margin:25px;"" >
			        					<ul class="nav nav-tabs">
			            					<li class="active"><a href="#goodsDetail" data-toggle="tab">规格参数</a></li>
			            					<li><a href="#goodsComments" data-toggle="tab">商品评论</a></li>
			           					    <li><a href="#packingList" data-toggle="tab">包装清单</a></li>
			            					<li><a href="#serviceSupport" data-toggle="tab">售后保障</a></li>
			        					</ul>
			        					<div class="tab-content">
			            					<div class="tab-pane fade active in" id="goodsDetail" style="border-bottom:1px dashed #334455; padding:10px;">
			            						<table style="width:400px; height:auto;margin-left:-50px;">
			            							<tr>
			            								<td class="odd">型号：</td>
			            								<td class="even">${aGoodsInfo.goodsModel}</td>
			            							</tr>
			            							<tr>
			            							 <td class="odd">处理器型号：</td>
			            							 <td class="even">${aGoodsInfo.goodsProcessor}</td>
			            							</tr>
			            							<tr>
			            								<td class="odd">屏幕大小：</td>
			            								<td class="even">${aGoodsInfo.goodsScreenSize}</td>
			            							</tr>
			            							<tr>
			            								<td class="odd">硬盘容量：</td>
			            								<td class="even">${aGoodsInfo.goodsHarddiskCapacity}</td>
			            							</tr>
			            							<tr>
			            								<td class="odd">内存大小：</td>
			            								<td class="even">${aGoodsInfo.goodsMemory}</td>
			            							</tr>
			            						</table>
			            					</div>
			            					<div class="tab-pane fade" id="goodsComments"  style="border-bottom:1px dashed #334455; padding:10px;">
			            						<s:if test="aGoodsInfo.tComments.size()<1">
			            							<span style="color:red; font-size:20px;">当前还没有人评论过此商品哦...</span>
			            						</s:if>
			            						<s:else>
			            							<s:iterator value="aGoodsInfo.tComments">
			            								<div style="border:1px dashed; margin-top:20px; padding:20px;">
			            										<span style="font-size:15px;">会员：${tUser.userNickname}</span>
			            									<span style="float:right;">
			            										<s:if test="commentMark <= 1.4">
			  														<img src="resources/advancedSearch/star1.png" />
			  													</s:if>
			  													<s:if test="commentMark > 1.4 && commentMark <= 2.4">
			  														<img src="resources/advancedSearch/star2.png" />
			  													</s:if>
			  													<s:if test="commentMark > 2.4 && commentMark <=3.4">
			  														<img src="resources/advancedSearch/star3.png" />
			  													</s:if>
			  													<s:if test="commentMark > 3.4 && commentMark <= 4.4">
			  														<img src="resources/advancedSearch/star4.png" />
			  													</s:if>
			  													<s:if test="commentMark > 4.4">
			  														<img src="resources/advancedSearch/star5.png" />
			  													</s:if>
			  												</span>
			  												<hr/>
			  												<div>
			  													<table style="width:400px; padding:10px; margin-left:-60px;line-height:40px;">
			  														<tr>
			  															<td class="odd">评论：</td>
			  															<td class="envn">${commentContent}</td>
			  														</tr>
			  														<tr >
			  														<td class="odd">发表时间：</td>
			  														<td class="even">${commentDate}</td>
			  														</tr>
			  													</table>
			  												</div>
			            								</div>
              										</s:iterator>
              									</s:else>
			            					</div>
			            					<div class="tab-pane fade" id="packingList" >
			  			            			<div style="border-bottom:1px dashed #334455 ; padding:10px; margin-left:0px;">笔记本*1 电池*1 电源*1 保修卡*1</div>
			  			            		</div>
			            					<div class="tab-pane fade" id="serviceSupport">
			            						<div style="border-bottom:1px dashed #334455">
			            						<p>本产品全国联保，享受三包服务，质保期为：一年质保</p>
												<p>如因质量问题或故障，凭厂商维修中心或特约维修点的质量检测证明，
												享受7日内退货，15日内换货，15日以上在质保期内享受免费保修等三包服务！
												</p>
												</div>
			            					</div>
				    					</div>
									</div>
									<div style="margin-left:25px;">
										<div style="margin-top:20px; border-bottom:1px dashed #334455 ">
			  			            		<span style="font-weight: bold; font-size:18px; ">服务承诺：</span><br/>
			  			            		<p style="text-indent: 2em; ">CShop商城向您保证所售商品均为正品行货，CShop自营商品自带机打发票，与商品一起寄送。
			  			            			凭质保证书及CShop商城发票，可享受全国联保服务,与您亲临商场选购的商品享受相同的质量保证。
			  			            			CShop商城还为您提供具有竞争力的商品价格和运费政策，请您放心购买！
			  			            		</p>
			  			            	</div>
			  			            	<div style="margin-top:20px; border-bottom:1px dashed #334455 ">
			  			            		<span style="font-weight: bold; font-size:18px;">权力声明：</span><br/>
			  			            		<p style="text-indent: 2em; ">
			  			            			CShop商城上的所有商品信息、客户评价等内容，是CShop商城重要的经营资源，未经许可，禁止非法转载使用。
			  			            		</p>
			  			            	</div>
									</div>
								</div>
							</div>
						</div>
    				</div>
   				</div>
    		</div>
	<%@ include file="../common/footer.jsp"%>
	<%@ include file="../common/login.jsp"%>
	<%@ include file="../common/register.jsp"%>
	<script src="js/jquery-1.7.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/common.js"></script>
	<script src="js/goodsinfo.js"></script>
</body>
</html>