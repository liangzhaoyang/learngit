<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<% String path = request.getContextPath(); %>
<html>
<head>
	<title>新增新用户</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />	
	<jsp:include page="/component/common/commonhead.jsp" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/page/departmentmanage/css/departmanage.css">
</head>
<body>
	<form id="organAddForm" name="organAddForm" action="" method="post">
		<div id="organ_add_accordion" class="easyui-accordion" >  

		 <div title="新增新用户" data-options="iconCls:'icon-save'" selected="true" style="overflow:auto;padding:10px;">  
		<table width="98%" class="formTable">
			<tr>
				<td>
				<fieldset><legend>新增新用户</legend>
				<table width="98%">
					<tr>
						<td class="formTdLeft"  style="width:10%"><span style='color: red'>*</span>用户名：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="saveEntity.systemName" value="" id="systemName" class="input"/>
						</td>
						<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>姓名：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="saveEntity.systemNo" value="" id="systemNo" class="input"/>
						</td>
					</tr>
					<tr>
						<td class="formTdLeft"  style="width:10%"><span style='color: red'>*</span>密码：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="saveEntity.systemName" value="" id="systemName" class="input"/>
						</td>
						<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>密码确认：</td>
						<td class="formTdRight" colspan="2" >
							<input type="text" name="saveEntity.systemName" value="" id="systemName" class="input"/>
						</td>
					</tr>
					<tr>
						<td class="formTdLeft"  style="width:10%"><span style='color: red'>*</span>用户角色：</td>
						<td class="formTdRight" style="width:25%">
							<select name="saveEntity.recordState" id="systemInfoAtion_saveEntity_recordState" class="select" style="width:50%" onchange="selChange(this.value)">
							    <option value="RECORD0" selected="selected">请选择角色</option>
							    <option value="RECORD1">系统管理员</option>
							    <option value="RECORD2">安全保密管理员</option>
							    <option value="RECORD3">安全审计员</option>
							    <option value="RECORD4">国家局用户</option>
							    <option value="RECORD5">安全局用户</option>
							</select>
						</td>
						<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>机构名称：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="saveEntity.systemName" value="" id="systemName" class="input"/>
						</td>
					</tr>
					<tr>
						<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>主管单位：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="saveEntity.systemName" value="" id="systemName" class="input"/>
						</td>
					</tr>
				</table>
				</fieldset>
				</td>
			</tr>
		</table>
		</div>
		</div>
		<table width="100%" class="buttonTable">
			<tr>
				<td>
					
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" onclick="infosysSave();" >保存</a>
				</td>
			</tr>
		</table>		
	</form>
</body>
</html>