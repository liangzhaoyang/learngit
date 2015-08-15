<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
	String path = request.getContextPath();
//	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>国家保密人才管理系统-登录</title>
<meta name="keywords" content="国家保密人才管理系统" />
<meta name="description" content="国家保密人才管理系统" />
<jsp:include page="/component/common/commonhead.jsp" />
<link href="<%=path%>/css/login/style.css" rel="stylesheet" />
<script type="text/javascript" src="<%=path%>/js/common/md5.js"></script>
<script type="text/javascript" >
$(function() {
	$("#login").click(function(){	
		if($("#id").val() == null || $("#id").val() == "") {
			alert("请输入用户名。");
			return;
		}
		if($("#password").val() == null || $("#password").val() == "") {
			alert("请输入密码。");
			return;
		}
		$("#password").val(MD5($("#password").val()));
		//alert($("#password").val());
		$("#frmLogin").submit();
	});
});
</script>
</head>

<body class="login">
	<form:form id="frmLogin" action="doLogin.do" method="post" modelAttribute="user">
	
<div class="loginbar">
   <div class="login-logo"> 
      <a href="javascript:void(0);"><img src="<%=path%>/images/login/login_logo.png" width="82" height="30" /></a> 
   </div>
   <div class="login-bar">
      <div class="login-bar-in">
         <label>用户名</label>
         <form:input path="id" class="login-input-user" />
<!--          <input class="login-input-user" name="" type="text" /> -->
      </div>
      <div class="login-bar-in">
         <label>密码</label>
         <input class="login-input-lock" name="password" id="password" type="password" />
      </div>
      <form:errors path="*"></form:errors>
      <div class="login-bar-in">
         <input class="login-btn" type="button"  name="login" id="login"  value="" />
      </div>
   </div>
</div>
</form:form>
</body>
</html>
