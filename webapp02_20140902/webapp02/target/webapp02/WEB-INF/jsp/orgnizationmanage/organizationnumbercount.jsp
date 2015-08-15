<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% 
	String path = request.getContextPath();
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>机构数量统计</title>
	<jsp:include page="/component/common/commonhead.jsp" />
 	<script type="text/javascript">	var path="<%=path%>"; 	</script>
 	<script type="text/javascript" src="<%=path %>/js/orgnizationmanage/organizationnumbercount.js"></script>
</head>
<body> 
	<div  style="padding:2px;height:auto">
		<table id="tborgancounts" class="easyui-datagrid"></table>  
	</div>
</body> 
</html>