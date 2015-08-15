<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
	String path = request.getContextPath();
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>授权管理</title>
	<jsp:include page="/component/common/commonhead2.jsp" />
	<script type="text/javascript" src="<%=path%>/js/auth/auth.js"></script> 
	<script type="text/javascript" > var path="<%=path%>"</script>
</head>
<body id="bodydiv" class="easyui-layout">
	<div data-options="region:'center'" style="padding: 5px; border: 0">
		<div style="margin-bottom:2px" >  
		    <table>
		     <tr>
		     	<td>
					<label  class="laber-info">用户名:</label><input type="text" id="id" name="id" />
				</td>
		     	<td>
				 	<label  class="laber-info">姓名:</label><input type="text" id="name" name="name" />
				</td>
				<td>
					<label  class="laber-info">角色:</label>			
			        <select name="role" id="role" class="select">
			        	<option value="-1"></option>
			           <option value="1">系统管理员</option>
			           <option value="2">安全保密管理员</option>
			           <option value="3">安全审计人员</option>
			           <option value="4">国家人员</option>
			           <option value="5">地方人员</option>
			        </select>
				</td>
				<td>
					<label  class="laber-info">机构名称:</label><input type="text" id="orgName" name="orgName" />
				</td>
				<td>
					<label  class="laber-info">授权状态:</label>			
			        <select name="status" id="status" class="select">
			        	<option value="-1"></option>
			           <option value="0">未授权</option>
			           <option value="1">正常</option>
			           <option value="2">禁用</option>
			        </select>
				</td>
				<td style="width:20px">
				</td>
				<td>
					<a href="javascript:void(0);" class="easyui-linkbutton" onclick="query();" >查询</a>
				</td>
			</tr>
		  </table>
		</div>
		
		<div  style="padding:2px;height:auto">
			<table id="tbInfosys" class="easyui-datagrid"></table>  
		</div>
	</div>
</body>
</html>