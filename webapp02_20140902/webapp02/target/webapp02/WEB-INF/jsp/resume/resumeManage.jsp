<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
function myformatter(date){
 var y = date.getFullYear();
 var m = date.getMonth()+1;
 var d = date.getDate();
 return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
 }
 function myparser(s){
 if (!s) return new Date();
 var ss = (s.split('-'));
 var y = parseInt(ss[0],10);
 var m = parseInt(ss[1],10);
 var d = parseInt(ss[2],10);
 if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
 return new Date(y,m-1,d);
 }else{
 return new Date();}
 }
</script>
<% 
	String path = request.getContextPath();
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>简历管理</title>
	<jsp:include page="/component/common/commonhead.jsp" />
	<script type="text/javascript" src="<%=path%>/js/resume/resume.js"></script> 
	<script type="text/javascript" > var path="<%=path%>"</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div style="margin-bottom:2px" >  
		    <table>
		     <tr>
		     	<td>
				 	<label  class="laber-info">姓名:</label><input type="text" id="name" name="name" />
				</td>
				<td>
					<label  class="laber-info">现任职务:</label><input type="text" id="xianrenzhiwu" name="xianrenzhiwu" />
				</td>
				<td>
					<label  class="laber-info">专业技术职务:</label><input type="text" id="zhuanyejishuzhiwu" name="zhuanyejishuzhiwu" />
				</td>
				<td>
					<label  class="laber-info">参加工作时间起:</label><input class="easyui-datebox" type="text" id="canjiagongzuoshijianFrom" name="canjiagongzuoshijianFrom" />
				</td>
				<td>
					<label  class="laber-info">参加工作时间止:</label><input class="easyui-datebox" type="text" id="canjiagongzuoshijianTo" name="canjiagongzuoshijianTo" />
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
	<div id="resumeDialog"></div>
</body>
</html>