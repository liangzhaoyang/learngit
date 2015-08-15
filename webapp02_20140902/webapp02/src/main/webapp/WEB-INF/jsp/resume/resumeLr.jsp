<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
	String path = request.getContextPath();
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>信息系统列表</title>
	<jsp:include page="/component/common/commonhead.jsp" />
 	<script type="text/javascript">	var path="<%=path%>"; 	</script>
 	<script type="text/javascript" src="<%=path %>/js/resume/resumeLr.js"></script>
</head>
<body> 
	<!-- toolbar表格内查询:<%=path %>/page/info/infosysAdd.jsp -->
	<div  style="margin-bottom:2px">			
		<label  class="laber-info">姓名:</label><input type="text" id="name" name="name" />	
		<label  class="laber-info">性别:</label>			
        <select name="xingbie" id="xingbie" class="select">
         <option value="7">请选择</option>
           <c:forEach var="xingbie" items="${xingbieList}">							
			<option value="${xingbie.code}">${xingbie.name}</option>
			</c:forEach>
        </select>
                                 
        <!--  &nbsp;&nbsp;&nbsp;<label for="xingbie" class="col-sm-3 control-label">性别:</label>
       		男<input type="radio" name="xingbie" id="xingbie" value="1" checked="checked">
        	女<input class="margin-l-10" type="radio" name="xingbie" id="xingbie1" value="2">-->
		&nbsp;&nbsp;&nbsp;<label  class="laber-info">出生年月起:</label><input class="easyui-datebox" type="text" id="date1" name="date1" />
		&nbsp;&nbsp;&nbsp;<label  class="laber-info">出生年月止:</label><input class="easyui-datebox" type="text" id="date2" name="date2" />
		&nbsp;&nbsp;&nbsp;<label  class="laber-info">专业:</label><input type="text" name="zhuanye" id="zhuanye"/>
		<br><label  class="laber-info">政治面貌:</label>			
        <select name="zhengzhimianmao" id="zhengzhimianmao" class="select">
        	<option value="7">请选择</option>
           <c:forEach var="zhengzhimianmao" items="${zhengzhimianmaoList}">							
			<option value="${zhengzhimianmao.code}">${zhengzhimianmao.name}</option>
			</c:forEach>
        </select>
		<label  class="laber-info">是否专职:</label>			
        <select name="shifouzhuanzhi" id="shifouzhuanzhi" class="select">
       <option value="7">请选择</option>
           <c:forEach var="shifouzhuanzhi" items="${shifouzhuanzhiList}">							
			<option value="${shifouzhuanzhi.code}">${shifouzhuanzhi.name}</option>
			</c:forEach>
        </select>
		<!-- &nbsp;&nbsp;&nbsp;<label  class="laber-info">是否专职:</label>
			 是<input type="radio" name="full-time" id="full-time" value="1" checked="checked">
			 否<input class="margin-l-10" type="radio" name="full-time" id="not-full-time" value="2"> -->
        &nbsp;&nbsp;&nbsp;<label  class="laber-info">单位名称:</label>
        	<input type="text" id="danweimingcheng" name="danweimingcheng" />
		&nbsp;&nbsp;&nbsp;<label  class="laber-info">部门名称:</label>
			<input type="text" name="suoshubumen" id="suoshubumen"/>
		<br><a href="javascript:void(0);" class="easyui-linkbutton" onclick="query();" >查询</a>
	</div>
	
	
	
	
	<div  style="padding:2px;height:auto">
		<table id="tbInfosys" class="easyui-datagrid"></table>  
	</div>
	<div id="personDialog"></div>
</body> 
</html>