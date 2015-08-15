<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<% 
	String path = request.getContextPath();
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>机构审核 </title>
	<jsp:include page="/component/common/commonhead2.jsp" />
	<script type="text/javascript" src="<%=path%>/js/orgnizationmanage/orgnizationaudit.js"></script> 
</head>
<body id="bodydiv" class="easyui-layout">
	    <div data-options="region:'center'" style="padding: 5px; border: 0">
	<form id="shenheConForm">
		<table width="90%">
			<tr>
				<td>机构名称：<input type="text" name="orgname" id="orgname"/></td>
				<td>机构类别：
				<select id="leibieSelection" name="leibieSelection" class="select">
					<option value="0"></option>
					<c:forEach var="jglb" items="${jglbList}">								
								<option value="${jglb.code}">${jglb.name}</option>
					</c:forEach>
					</select>
				</td>
				<td>成立时间范围起：<input type="text" name="chenglishijianFrom" id="chenglishijianFrom"/></td>
				<td>成立时间范围止：<input type="text" name="chenglishijianTo" id="chenglishijianTo"/></td>
			</tr>
			 <tr>
				<td>行政级别：
				<select id="jibieSelection" name="jibieSelection" class="select">
					<option value="0"></option>
					<c:forEach var="jglb" items="${xzjbList}">								
								<option value="${jglb.code}">${jglb.name}</option>
					</c:forEach>
					</select>
				</td>
				<td>经费来源：<input type="text" name="jingfeilaiyuan" id="jingfeilaiyuan"/></td>
				<td>是否政府序列：
				<select id="xulieSelection" name="xulieSelection" class="select">
					<option value="0"></option>
					<c:forEach var="jglb" items="${zfxlList}">								
								<option value="${jglb.code}">${jglb.name}</option>
					</c:forEach>
					</select>
				</td>
				<td align="center"><a id="btnSearch" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
			</tr>
		</table>
	</form>
	<table id="tblShenhe" class="easyui-datagrid"></table>
	<div id="shenheDialog"></div>
</div>
</body>
</html>