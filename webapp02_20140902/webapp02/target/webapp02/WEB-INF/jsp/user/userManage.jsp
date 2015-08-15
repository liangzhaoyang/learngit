<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%String path = request.getContextPath();%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>用户管理</title>
	<jsp:include page="/component/common/commonhead2.jsp" />
	<script type="text/javascript" src="<%=path%>/js/common/script.js"></script> 
	<script type="text/javascript" src="<%=path%>/js/user/userManage.js"></script> 
	<script type="text/javascript">	var path="<%=path%>"; 	</script>
</head>
<body id="bodydiv" class="easyui-layout">
	    <div data-options="region:'center'" style="padding: 5px; border: 0">
			<!-- toolbar表格内查询 -->
			<div id="tb" style="padding:5px;height:auto"> 
				<form id="frmCondition">
					<table width="100%">
						<tr>
							<td>用户名：<input type="text" name="id" /></td>
							<td>姓名：<input type="text" name="name" /></td>
							<td>机构名称：<input type="text" name="orgName" /></td>
							<td align="center"><a id="btnSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
						</tr>
					</table>
				</form>
			</div>
			<table id="tblUsers" data-options="toolbar:'#tb'"></table>
			<div id="userDialog"></div>
		</div>
</body>
</html>