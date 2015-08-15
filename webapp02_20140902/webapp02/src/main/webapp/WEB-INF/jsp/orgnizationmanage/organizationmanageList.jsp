<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% 
	String path = request.getContextPath();
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>机构查询</title>
	<jsp:include page="/component/common/commonhead2.jsp" />
 	<script type="text/javascript">	var path="<%=path%>"; 	</script>
 	<script type="text/javascript" src="<%=path %>/page/orgnizationmanage/js/organizationmanageList.js"></script>
</head>
<body> 
	<!-- toolbar表格内查询:<%=path %>/page/info/infosysAdd.jsp -->
	<div  style="margin-bottom:2px">
		<label  class="laber-info">机构名称:</label><input type="text" id="name" name="name" />
		<label  class="laber-info">成立时间:</label><input type="text" id="name" name="name" />
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="query();" >查询</a>
	</div>
	
	<div  style="padding:2px;height:auto">
		<table id="tborgans" class="easyui-datagrid"></table>  
	</div>
</body> 
</html>