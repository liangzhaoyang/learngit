<DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getContextPath(); %>
<html>
<head>
	<title>简历录入</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />	
	<jsp:include page="/component/common/commonhead.jsp" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/page/departmentmanage/css/departmanage.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/page/departmentmanage/css/lanrenzhijia.css">
	<script type="text/javascript">	var path="<%=path%>"; 	</script>
	<script type="text/javascript" src="<%=path%>/js/departmentmanage/jquery.1.4.2-min.js.js"></script>
	<script type="text/javascript" src="<%=path%>/js/departmentmanage/jquery.tabso_yeso.js"></script>
	<script type="text/javascript" src="<%=path%>/js/departmentmanage/departmanageAdd.js"></script>
	<script type="text/javascript" src="<%=path%>/js/resume/resumeAdd.js"></script>
 
</head>
<body>
	<form id="jlmxbc" name="jlmxbc" action="jlmxbc.do" method="post">
		<div id="departs_add_accordion" class="easyui-accordion" >  

		 <div title="基本情况" data-options="iconCls:'icon-save'" selected="true" style="overflow:auto;padding:10px;">  
		<table width="98%" class="formTable">
			<tr>
				<td>
				<fieldset><legend>简历录入</legend>
				<table width="98%">
					<tr>
						
						<td class="formTdLeft"  style="width:10%"><span style='color: red'>*</span>姓名：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="name" value="" id="name" class="input"/>
						</td>
						<td class="formTdLeft"  style="width:10%"><span style='color: red'>*</span>性别：</td>
						 <td class="formTdRight" style="width:25%">
						 	<input type="radio" name="xingbie" value="1" checked>男&nbsp;<input type="radio" name="xingbie" value="2" >女 
						 </td>
					</tr>
					<tr>
						<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>民族：</td>
							<td class="formTdRight" style="width: 25%">
								<select id="minzu" name="minzu" class="select">
								<option value=" ">请选择</option>
								<c:forEach var="minzu" items="${minzuList}">								
											<option value="${minzu.code}">${minzu.name}</option>
								</c:forEach>
								</select>
							
						<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>出生年月：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="chushengnianyue" value="" id="chushengnianyue" class="easyui-datebox"/>
						</td>
					</tr>
					<tr>
						<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>政治面貌：</td>
						<td class="formTdRight" style="width:25%">
							<select name="zhengzhimianmao" id="zhengzhimianmao" class="select" style="width:50%" onchange="selChange(this.value)">
							    <option value="7" selected="selected">请选择</option>
							    <option value="2">团员</option>
							    <option value="1">党员</option>
							    <option value="3">民主党派</option>
							    <option value="4">其他</option>
							</select>
						</td>
						<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>参加工作时间：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="canjiagongzuoshijian" value="" id="canjiagongzuoshijian" class="easyui-datebox"/>
						</td>
					</tr>
					<tr>
						<td class="formTdLeft"  style="width:10%"><span style='color: red'>*</span>专业技术职务：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="zhuanyejishuzhiwu" value="" id="zhuanyejishuzhiwu" class="input"/>
						</td>
						<td class="formTdLeft"  style="width:10%"><span style='color: red'>*</span>熟悉专业有何专长：</td>
						 <td class="formTdRight" style="width:25%">
						 	<input type="text" name="zhuanchang" value="" id="zhuanchang" class="input"/>
						 </td>
					</tr>
					<tr>
						<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>学历：</td>
						<td class="formTdRight" style="width:25%">
							<select name="leibie" id="leibie" class="select" style="width:50%" onchange="selChange(this.value)">
							    <option value="7" selected="selected">请选择</option>
							    <option value="1">全日制教育</option>
							    <option value="2">在职教育</option>
							</select>
						</td>
						<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>学位：</td>
						<td class="formTdRight" style="width:25%">
							<select name="xueli" id="xueli" class="select" style="width:50%" onchange="selChange(this.value)">
							    <option value="7" selected="selected">请选择</option>
							    <option value="1">博士</option>
							    <option value="2">硕士</option>
							    <option value="3">本科</option>
							    <option value="4">大专以下</option>
							</select>
						</td>
						
					</tr>
					<tr>
						<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>毕业院校：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="biyeyuanxiao" value="" id="biyeyuanxiao" class="input"/>
						</td>
						
						<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>专业：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="zhuanye" value="" id="zhuanye" class="input"/>
						</td>
						</tr>
						<tr>
						<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>现任职务：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="xianrenzhuiwu" value="" id="xianrenzhuiwu" class="input"/>
						</td>
					</tr>
				</table>
				</fieldset>
				</td>
			</tr>
		</table>
		</div>
	
		<div title="简历" icon="icon-save" selected="false"	style="padding: 10px;">
			<textarea style="width:100%;height:150px" name="jianli"  id="jianli"></textarea>
		</div>
		
		<div title="奖惩情况" icon="icon-save" selected="false"	style="padding: 10px;">
			<textarea style="width:100%;height:150px" name="jiangcheng"  id="jiangcheng"></textarea>
		</div>
		
		<div title="主要成果" icon="icon-save" selected="false"	style="padding: 10px;">
			<textarea style="width:100%;height:150px" name="chengguo"  id="chengguo"></textarea>
		</div>
		
		 <!--  <div title="家庭主要成员及重要社会关系" icon="icon-save" selected="false" style="padding:10px">
		<table id="resumeadd4" class="easyui-datagrid"></table>  
	</div>
	 -->
	 
	
		 <div title="家庭主要成员及重要社会关系" icon="icon-save" selected="false" style="padding:10px;">  
		<table width="98%" class="formTable">
			<tr>
				<td>
				<fieldset><legend>成员1</legend>
				<table width="98%">
					<tr>
						<td class="formTdLeft"  style="width:10%"><span style='color: red'>*</span>称谓：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="chengwei1" value="" id="chengwei1" class="input"/>
							
						</td>
						<td class="formTdLeft"  style="width:10%"><span style='color: red'>*</span>姓名：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="xingming1" value="" id="xingming1" class="input"/>
						</td>
					</tr>
					<tr>
						<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>出生年份 ：</td>
							<td class="formTdRight" style="width:25%">
							<input type="text" name="chushengnianfen1" value="" id="chushengnianfen1" class="easyui-datebox"/>
							
					<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>政治面貌：</td>
						<td class="formTdRight" style="width:25%">
							<select name="zhengzhimianmao1" id="zhengzhimianmao1" class="select" style="width:50%" onchange="selChange(this.value)">
							    <option value=" " selected="selected">请选择</option>
							      <option value="团员">团员</option>
							    <option value="党员">党员</option>
							    <option value="民主党派">民主党派</option>
							    <option value="其他">其他</option>
							</select>
						</td>
					</tr>
					<tr>
					
						<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>工作单位及职务：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="gongzuodanweijizhiwu1" value="" id="gongzuodanweijizhiwu1" class="input"/>
						</td>
					</tr>
					
				</table>
				</fieldset>
				
				<fieldset><legend>成员2</legend>
				<table width="98%">
					<tr>
						<td class="formTdLeft"  style="width:10%"><span style='color: red'>*</span>称谓：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="chengwei2" value="" id="chengwei2" class="input"/>
							
						</td>
						<td class="formTdLeft"  style="width:10%"><span style='color: red'>*</span>姓名：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="xingming2" value="" id="xingming2" class="input"/>
						</td>
					</tr>
					<tr>
						<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>出生年份 ：</td>
							<td class="formTdRight" style="width:25%">
							<input type="text" name="chushengnianfen2" value="" id="chushengnianfen2" class="easyui-datebox"/>
							
					<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>政治面貌：</td>
						<td class="formTdRight" style="width:25%">
							<select name="zhengzhimianmao2" id="zhengzhimianmao2" class="select" style="width:50%" onchange="selChange(this.value)">
							    <option value="" selected="selected">请选择</option>
							     <option value="团员">团员</option>
							    <option value="党员">党员</option>
							    <option value="民主党派">民主党派</option>
							    <option value="其他">其他</option>
							</select>
						</td>
					</tr>
					<tr>
					
						<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>工作单位及职务：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="gongzuodanweijizhiwu2" value="" id="gongzuodanweijizhiwu2" class="input"/>
						</td>
					</tr>
					
				</table>
				</fieldset>
				<fieldset><legend>成员3</legend>
				<table width="98%">
					<tr>
						<td class="formTdLeft"  style="width:10%"><span style='color: red'>*</span>称谓：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="chengwei3" value="" id="chengwei3" class="input"/>
							
						</td>
						<td class="formTdLeft"  style="width:10%"><span style='color: red'>*</span>姓名：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="xingming3" value="" id="xingming3" class="input"/>
						</td>
					</tr>
					<tr>
						<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>出生年份 ：</td>
							<td class="formTdRight" style="width:25%">
							<input type="text" name="chushengnianfen3" value="" id="chushengnianfen3" class="easyui-datebox"/>
							
					<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>政治面貌：</td>
						<td class="formTdRight" style="width:25%">
							<select name="zhengzhimianmao3" id="zhengzhimianmao3" class="select" style="width:50%" onchange="selChange(this.value)">
							    <option value=" " selected="selected">请选择</option>
							     <option value="团员">团员</option>
							    <option value="党员">党员</option>
							    <option value="民主党派">民主党派</option>
							    <option value="其他">其他</option>
							</select>
						</td>
					</tr>
					<tr>
					
						<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>工作单位及职务：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="gongzuodanweijizhiwu3" value="" id="gongzuodanweijizhiwu3" class="input"/>
						</td>
					</tr>
					
				</table>
				</fieldset>
				<fieldset><legend>成员4</legend>
				<table width="98%">
					<tr>
						<td class="formTdLeft"  style="width:10%"><span style='color: red'>*</span>称谓：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="chengwei4" value="" id="chengwei4" class="input"/>
							
						</td>
						<td class="formTdLeft"  style="width:10%"><span style='color: red'>*</span>姓名：</td>
						<td class="formTdRight" style="width:25%"> 
							<input type="text" name="xingming4" value="" id="xingming4" class="input"/>
						</td>
					</tr>
					<tr>
						<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>出生年份 ：</td>
							<td class="formTdRight" style="width:25%">
							<input type="text" name="chushengnianfen4" value="" id="chushengnianfen4" class="easyui-datebox"/>
							
					<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>政治面貌：</td>
						<td class="formTdRight" style="width:25%">
							<select name="zhengzhimianmao4" id="zhengzhimianmao4" class="select" style="width:50%" onchange="selChange(this.value)">
							    <option value="" selected="selected">请选择</option>
							    <option value="团员">团员</option>
							    <option value="党员">党员</option>
							    <option value="民主党派">民主党派</option>
							    <option value="其他">其他</option>
							</select>
						</td>
					</tr>
					<tr>
					
						<td class="formTdLeft" style="width:25%"><span style='color: red'>*</span>工作单位及职务：</td>
						<td class="formTdRight" style="width:25%">
							<input type="text" name="gongzuodanweijizhiwu4" value=" " id="gongzuodanweijizhiwu4" class="input"/>
						</td>
					</tr>
					
				</table>
				</fieldset>
				</td>
			</tr>
		</table>
		</div>
	 
	 
		<div title="备注" icon="icon-save" selected="false"	style="padding: 10px;">
			<textarea style="width:100%;height:150px" name="beizhu"  id="beizhu"></textarea>
		</div>
		
		</div>
		<table width="100%" class="buttonTable">
			<tr>
				<td>
					<!-- <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" onclick="jlmxbc();" >保存</a> -->
					<input type="submit" id="submitForm" value="保存" class="button" onClick="jlmxbc()"/>
					&nbsp;&nbsp;
					<input name="Button" type="button" class="button" id="Button" value="取消" onClick="javascript:history.go(-1)" />
				</td>
			</tr>
		</table>		
	</form>
</body>
</html>