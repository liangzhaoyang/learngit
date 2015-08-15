<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%String path = request.getContextPath();%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>用户管理</title>
	<jsp:include page="/component/common/commonhead2.jsp" />
	<script type="text/javascript" src="<%=path%>/js/tongji/renyuanbianzhitongji.js"></script> 
	<script type="text/javascript">	var path="<%=path%>"; 	</script>
</head>
<body id="bodydiv" class="easyui-layout">
    <div data-options="region:'center'" style="padding: 5px; border: 0">
		<table id="tt" data-options="toolbar:'#tb'"></table>
	</div>
</body>
</html>