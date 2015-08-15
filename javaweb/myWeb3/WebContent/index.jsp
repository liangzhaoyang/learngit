<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	/*如果还未登录系统*/
	if (session.getAttribute("user")==null)
	{
		out.println("<script>alert('未登录系统！');top.location.href='jsp/login.jsp';</script>");
	}  
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>网络流量分析系统</title>
	</head>
	
	<frameset rows="63,*,23" frameborder="no" border="0" framespacing="0" id="parent">
	  <frame src="jsp/top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" />
	  <frame src="jsp/center.jsp" name="mainFrame" id="mainFrame" />
	  <frame src="jsp/down.jsp" name="bottomFrame" scrolling="No" noresize="noresize" id="bottomFrame" />
	</frameset>
	<body>

	</body>
</html>
