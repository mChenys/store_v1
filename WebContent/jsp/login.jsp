<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
		<script type="text/javascript" src="./js/jquery.validate.js"></script>
		<script type="text/javascript" src="./js/messages_zh.js"></script>

<style>
  body{
   margin-top:20px;
   margin:0 auto;
 }
 .carousel-inner .item img{
	 width:100%;
	 height:300px;
 }
 .container .row div{ 
	 /* position:relative;
	 float:left; */
 }
 
font {
    color: #666;
    font-size: 22px;
    font-weight: normal;
    padding-right:17px;
}
 </style>
 
 <script type="text/javascript">
 	$(function(){
 		$("#formId").validate({
 			rules:{
 				username:"required",
 				password:"required",
 				code:{
					required:true,
					remote:{
						type:"POST",
 						url:"${pageContext.request.contextPath}/user?act=checkCode",
 						data:{rcode:function(){ return $("#code").val() }}
 					}
				}
 			},
 			messages:{
 				username:"用户名必填",
 				password:"密码必填",
 				code:{
 					required:"验证码名必填",
 	 	            remote:"验证码错误"
 				}
 			}
 			
 		})
 		
 		
 		var nameObj = document.getElementsByName("username")[0];
		if(${not empty cookie.saveName}){
			var username = decodeURI("${cookie.saveName.value}");
			nameObj.value = username;
		}
		
		
		$("#codeImg").click(function(){
			$(this).prop("src","${pageContext.request.contextPath}/code?"+Math.random());
		})
 	})
 </script>
</head>
<body>
	
			<!--
            	时间：2015-12-30
            	描述：菜单栏
            -->
			<jsp:include page="header.jsp"></jsp:include>
			
	
	
	
	
	
	
<div class="container"  style="width:100%;height:460px;background:#FF2C4C url('${pageContext.request.contextPath}/images/loginbg.jpg') no-repeat;">
<div class="row"> 
	<div class="col-md-7">
		<!--<img src="${pageContext.request.contextPath}/image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
	</div>
	
	<div class="col-md-5">
				<div style="width:440px;border:1px solid #E7E7E7;padding:20px 0 20px 30px;border-radius:5px;margin-top:60px;background:#fff;">
				<font>会员登录</font>USER LOGIN

				<div>&nbsp;</div>
<form class="form-horizontal" id="formId" method="post" action="${pageContext.request.contextPath}/user?act=login">
  
 <div class="form-group">
    <label for="username" class="col-sm-2 control-label">用户名</label>
    <div class="col-sm-6">
      <input type="text" class="form-control" id="username" placeholder="请输入用户名"  name="username">
    </div>
  </div>
   <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
    <div class="col-sm-6">
      <input type="password" class="form-control" id="inputPassword3" placeholder="请输入密码" name="password">
    </div>
  </div>
   <div class="form-group">
        <label for="inputPassword3" class="col-sm-2 control-label">验证码</label>
    <div class="col-sm-3">
      <input type="text" class="form-control" id="code" placeholder="请输入验证码" name="code">
    </div>
    <div class="col-sm-3">
      <img id="codeImg" src="${pageContext.request.contextPath}/code"/>
    </div>
    
  </div>
   <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
        	<c:if test="${empty cookie.autoLogin}">
        		<input type="checkbox" name="autoLogin"> 自动登录
        	</c:if>
          	<c:if test="${not empty cookie.autoLogin}">
        		<input type="checkbox" name="autoLogin" checked="checked"> 自动登录
        	</c:if>
        </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <label>
        	<c:if test="${empty cookie.saveName}">
        		<input type="checkbox" name="saveName"> 记住用户名
        	</c:if>
          	<c:if test="${not empty cookie.saveName}">
        		<input type="checkbox" name="saveName" checked="checked"> 记住用户名
        	</c:if>
        </label>
      </div>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
    <input type="submit"  width="100" value="登录" name="submit" border="0"
    style="background: url('${pageContext.request.contextPath}/images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
    height:35px;width:100px;color:white;">
   
    </div>
     <span><font class="errortip">${msg }</font></span>
  </div>
</form>
</div>			
	</div>
</div>
</div>	

	<div style="margin-top:50px;">
			<img src="${pageContext.request.contextPath}/image/footer.jpg" width="100%" height="78" alt="我们的优势" title="我们的优势" />
		</div>

		<div style="text-align: center;margin-top: 5px;">
			<ul class="list-inline">
				<li><a>关于我们</a></li>
				<li><a>联系我们</a></li>
				<li><a>招贤纳士</a></li>
				<li><a>法律声明</a></li>
				<li><a>友情链接</a></li>
				<li><a target="_blank">支付方式</a></li>
				<li><a target="_blank">配送方式</a></li>
				<li><a>服务声明</a></li>
				<li><a>广告声明</a></li>
			</ul>
		</div>
		<div style="text-align: center;margin-top: 5px;margin-bottom:20px;">
			Copyright &copy; 2005-2016 传智商城 版权所有
		</div>
</body></html>