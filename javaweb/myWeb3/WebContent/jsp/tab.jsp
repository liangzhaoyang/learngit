<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="net.domain.User" %>
<%@page import="net.factory.DaoFactory" %>
<%@page import="java.util.List" %>
<html style="width:100%">
<head>
<title>main</title>
	<style>
		body{
		margin:0px;
		padding:0px;
		width:100%;}
		#top{
		width:100%;
		height:30px;
		background-image:url(${pageContext.request.contextPath}/images/tab_05.gif);}
		#t1{
		float:left;}
		#t1 img{
		width:15px;
		height:30px;}
		#t2{
		float:left;
		margin-top:5px;}
		#t2 img{
		width:16px;
		height:16px;}
		#t2 span{
		font-size:14px;
		font-weight:bold;
		color:#1F4A65;
		margin-left:10px;}
		#t3{
		float:right;
		width:86px;}
		#t31{
		float:left;
		margin-top:8px;}
		#t31 img{
		height:18px;
		width:18px;}
		#t33{
		float:left;
		margin-left:5px;
		margin-top:10px;}
		#t32{
		float:right;}
		#t32 img{
		width:14px;
		height:30px;}
		#center{
		width:100%;
		magin:0px;
		padding:0px;}
		.STYLE1 {font-size: 12px}
		.STYLE4 {
			font-size: 12px;
			color: #1F4A65;
			font-weight: bold;}
		a:link {
			font-size: 12px;
			color: #06482a;
			text-decoration: none;}
		a:visited {
			font-size: 12px;
			color: #06482a;
			text-decoration: none;}
		a:hover {
			font-size: 12px;
			color: #FF0000;
			text-decoration: underline;}
		a:active {
			font-size: 12px;
			color: #FF0000;
			text-decoration: none;}
			#down{
			width:100%;
			height:29px;
			background-image:url(${pageContext.request.contextPath}/images/tab_21.gif);}
			#d1{
			float:left;}
			#d1 img{
			width:15px;
			height:29px;}
			#d2{
			float:left;
			font-size:12px;
			padding-top:5px;
			magin-left:10px;}
			#d3{
			float:right;}
			#d3 img{
			width:14px;
			height:29px;}
	</style>
</head>

<body>
<% if(request.getAttribute("result")!= null) {%>
	    <script>
	         alert('<%=request.getAttribute("result")%>');
	    </script> 
	<% 
		String flag=(String)session.getAttribute("flag");
		if(flag!="" && flag.equals("true")){%>
	<script>
		window.parent.parent.location.href='jsp/login.jsp';
	</script>
	<%}} %>
	<div id="container">
		<div id="top">
			<div id="t1"><img src="${pageContext.request.contextPath}/images/tab_03.gif" /></div>
			<div id="t2"><img src="${pageContext.request.contextPath}/images/311.gif" /><span>管理员信息表</span></div>
			<div id="t3">
				<div id="t31"><img src="${pageContext.request.contextPath}/images/add2.gif" /></div>
				<div id="t33"><a href="${pageContext.request.contextPath}/jsp/addAdmin.jsp">[添加]</a></div>
				<div id="t32"><img src="${pageContext.request.contextPath}/images/tab_07.gif" /></div>
			</div>
		</div>
		<div id="center">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td width="9" background="${pageContext.request.contextPath}/images/tab_12.gif" align="left"></td>
			<td bgcolor="e5f1d6">
				<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CECECE">
			  		<tr>
						<td width="10%" height="25" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">序号</div></td>
						<td width="15%" height="25" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">账号</div></td>
						<td width="15%" height="25" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">密码</div></td>
						<td width="10%" height="25" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">权限</div></td>
						<td width="18%" height="25" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">Email</div></td>
						<td width="18%" height="25" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">手机</div></td>
						<td width="7%" height="25" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">编辑</div></td>
						<td width="7%" height="25" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">删除</div></td>
        			</tr>
        			<%
						ArrayList list=new ArrayList();
						list=(ArrayList)DaoFactory.getUserDaoInstance().getUserList();
						for(int i=0;i<list.size();i++){
							User user=(User)list.get(i);
					%>
        			<tr>
            			<td height="25" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1"><%=user.getUserid() %></div></td>
						<td height="25" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=user.getUsername() %></div></td>
						<td height="25" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=user.getPassword() %></div></td>
						<td height="25" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=user.getPermission()%></div></td>
						<td height="25" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=user.getEmail()%></div></td>
						<td height="25" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=user.getIphone()%></div></td>
						<td height="25" bgcolor="#FFFFFF"><div align="center"><img src="${pageContext.request.contextPath}/images/037.gif" width="9" height="9" /><span class="STYLE1"> [</span><a href="${pageContext.request.contextPath}/jsp/updateAdmin.jsp?id=<%=user.getUserid()%>">编辑</a><span class="STYLE1">]</span></div></td>
						<td height="25" bgcolor="#FFFFFF"><div align="center"><span class="STYLE2"><img src="${pageContext.request.contextPath}/images/010.gif" width="9" height="9" /> </span><span class="STYLE1">[</span><a href="${pageContext.request.contextPath}/UserManageServlet?action=del&id=<%=user.getUserid() %>" onclick="javascript:return confirm('确定删除此记录？');">删除</a><span class="STYLE1">]</span></div></td>
          			</tr>
          			<%
						}
					%>
        		</table>
        	</td>
        	<td width="9" background="${pageContext.request.contextPath}/images/tab_16.gif"></td>
      	</tr>
     </table>
	</div>
		<div id="down">
			<div id="d1"><img src="${pageContext.request.contextPath}/images/tab_20.gif" /></div>
			<div id="d2"></div>
			<div id="d3"><img src="${pageContext.request.contextPath}/images/tab_22.gif" /></div>
		</div>
	</div>
</body>
</html>
