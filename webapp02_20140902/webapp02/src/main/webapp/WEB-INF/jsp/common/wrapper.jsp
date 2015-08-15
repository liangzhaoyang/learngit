<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<iframe id="dialogFrame" src="<%=basePath%>${url}" style="margine:0px;width:100%;height:100%">
</iframe>