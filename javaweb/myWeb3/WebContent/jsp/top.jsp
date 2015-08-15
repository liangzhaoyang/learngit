<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="net.domain.User" %>
<%@page import="net.factory.DaoFactory" %>
<%--
	if (session.getAttribute("user")==null)
		out.println("<script>alert('未登录系统！');top.location.href='login.jsp';</script>");
	
--%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>top</title>
		<style>
		body{
		margin:0px;
		padding:0px;
		}
		#style{
		background-image:url(../images/top.gif);
		width:100%px;
		height:64px;
		}
		.home{
		margin-left:350px;
		padding-top:30px;
		float:left;
		height:20px;}
		.home .style{
		padding-top:0px;}
		a:link {font-size:12px; color:#000000; text-decoration:none;}
		a:visited {font-size:12px; color:#000000; text-decoration:none;}
		a:hover {font-size:12px; color:#00CCFF;cursor:hand;text-decoration:none;}
		#user{
		float:right;
		padding-top:33px;
		padding-right:40px;
		}
		#d1{
		width:14px;
		height:14px;
		float:left;}
		#d2{
		font-size:12px;
		color:#000000;
		float:left;
		margin-left:5px;}
		#d3{
		float:left;
		font-size:12px;
		margin-left:8px;}
		#h{
		padding-top:3px;}
		#h1{
		float:left;}
		#h11{
		float:left;
		margin-left:3px;}
		#h2{
		float:left;
		margin-left:7px;}
		#h22{
		float:left;
		margin-left:3px;}
	</style>
	</head>
	<body>
		<div id="style">
		<div class="home">
			<div id="h">
				<div id="h1"><img src="../images/home.gif" width="12" height="13"> </div>
				<div id="h11"><a href="#" onclick="top.mainFrame.location.href='center.jsp';">首页</a></div>
				<div id="h2"> <img src="../images/quit.gif" width="16" height="15"> </div>
				<div id="h22"> <a href="${pageContext.request.contextPath}/LogoutServlet " target="_top">注销</a></div>
			</div>
		</div>
		<div id="user">
			<div id="d1"><img src="../images/uesr.gif"></div>
			<div id="d2"> 当前登录用户：${user.username }</div>
			<div id="d3">[<a href="${pageContext.request.contextPath}/jsp/changePSW.jsp?id=${user.userid} }>" target="I2">修改密码</a>]</div>
		</div>
	</div>
	</body>
</html>