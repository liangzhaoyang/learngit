<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<% String path = request.getContextPath(); %>
<html>
<head>
	<title>新增机构</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />	
	<jsp:include page="/component/common/commonhead.jsp" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/page/departmentmanage/css/departmanage.css">
</head>
<body>
	<form id="organAddForm" name="organAddForm" action="" method="post">
		<div id="organ_add_accordion" class="easyui-accordion" >  

		 <div title="新增机构" data-options="iconCls:'icon-save'" selected="true" style="overflow:auto;padding:10px;">  
		<table width="98%" class="formTable">
			<tr>
				<td>
				<fieldset><legend>新增机构</legend>
				<table width="98%">
					<tr>
						<td class="formTdLeft"  style="width:10%"><span style='color: red'>*</span>单位名称：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="saveEntity.systemName" value="" id="systemName" class="input"/>
						</td>
						<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>成立时间：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="saveEntity.systemNo" value="" id="systemNo" class="input"/>
						</td>
					</tr>
					<tr>
						<td class="formTdLeft"  style="width:10%"><span style='color: red'>*</span>行政级别：</td>
						<td class="formTdRight" style="width:25%" >
							<select name="saveEntity.recordState" id="systemInfoAtion_saveEntity_recordState" class="select" style="width:50%" onchange="selChange(this.value)">
							    <option value="RECORD0" selected="selected">请选择</option>
							    <option value="RECORD1">正省部级</option>
							    <option value="RECORD2">副省部级</option>
							    <option value="RECORD3">正厅部级</option>
							    <option value="RECORD4">副厅部级</option>
							    <option value="RECORD5">正县部级</option>
							    <option value="RECORD6">副县部级</option>
							    <option value="RECORD7">正乡部级</option>
							    <option value="RECORD8">副乡部级</option>
							</select>
						</td>
						<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>经费来源：</td>
						<td class="formTdRight" colspan="2" >
							<input type="text" name="saveEntity.systemName" value="" id="systemName" class="input"/>
						</td>
					</tr>
					<tr>
						<td class="formTdLeft"  style="width:10%"><span style='color: red'>*</span>是否政府序列：</td>
						<td class="formTdRight" style="width:25%">
							<input type="radio" name="saveEntity.isSubsys" id="isSubsys1" value="1" class="radio" onclick="controlDisplay(this,'parentSysName','parentSysUnitname')"/><label for="isSubsys1">是</label>
							<input type="radio" name="saveEntity.isSubsys" id="isSubsys0" checked="checked" value="0" class="radio" onclick="controlDisplay(this,'parentSysName','parentSysUnitname')"/><label for="isSubsys0">否</label>
						</td>
						<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>机构类别：</td>
						<td class="formTdRight" style="width:25%">
							<input type="radio" name="saveEntity.isSubsys" id="isSubsys1" value="1" class="radio" onclick="controlDisplay(this,'parentSysName','parentSysUnitname')"/><label for="isSubsys1">专门机构</label>
							<input type="radio" name="saveEntity.isSubsys" id="isSubsys0" checked="checked" value="0" class="radio" onclick="controlDisplay(this,'parentSysName','parentSysUnitname')"/><label for="isSubsys0">挂靠机构</label>
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