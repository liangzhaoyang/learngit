<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
	String path = request.getContextPath();
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>按照业务类型统计</title>
	<jsp:include page="/component/common/commonhead2.jsp" />
	<script type="text/javascript" src="<%=path%>/js/log/logsQuery.js"></script> 
	<script type="text/javascript" > var path="<%=path%>"</script>
</head>
<body id="bodydiv" class="easyui-layout">
<div data-options="region:'center'" style="padding: 5px; border: 0">
	<div id="tb" style="padding:5px;height:auto"> 
		<div style="margin-bottom:2px" > 
			<form id="frmCondition">
		    <table>
		     <tr>
		     	<td>
				 	<label  class="laber-info">用户名称:</label><input type="text" id="name" name="name" />
				</td>
				<td>
					<label  class="laber-info">日志类型:</label><input type="text" id="logtype" name="logtype" />
				</td>
				<td>
					<label  class="laber-info">产生时间起:</label><input type="text" id="dateFrom" name="date" />
				</td>
				<td>
					<label  class="laber-info">产生时间止:</label><input type="text" id="dateTo" name="date" />
				</td>
				<td>
					<label  class="laber-info">描述:</label><input type="text" id="miaoshu" name="miaoshu" />
				</td>
				<td style="width:20px">
				</td>
				<td>
					<a href="javascript:void(0);" class="easyui-linkbutton" onclick="query();" >查询</a>
				</td>
			</tr>
		  </table>
		  </form>
		</div>
		
		<table id="tbLog" class="easyui-datagrid"></table>  
	</div>
</div>
</body>
</html>