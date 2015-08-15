<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>保密人才管理系统</title>
<jsp:include page="/component/common/commonhead.jsp" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/mainFrame.css" />
<script type="text/javascript" src="<%=path%>/js/sys/mainFrame.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/script.js"></script>
<script type="text/javascript">
window.onbeforeunload = function (e) {
	var n=window.event.screenX-window.screenLeft; 
	var b=n>document.documentElement.scrollWidth-20; 
	if(b&&window.event.clientY<0||window.event.altKey){ 
		$.post("quit.do");
	}	
};

var firstTimeLogin = "${ user.firsttime }" ;
function showModifyPasswdWnd() {
	$('#changePasswdDialog').dialog({
		title : '修改密码',
		width : 600,
		height : 400,
		closed : false,
		cache : false,
		href : "showDialog.do?url='mmxg.do'",
		modal : true
	});
}

$(function(){	
	// 角色切换
	$("#roleSelection").change(function(){
		var requestStr = "changeRole.do?roleId=" + document.getElementById("roleSelection").value;
		$.get(requestStr, function(data, status) {
			if(status == "success") {
				$("#leftdiv").html(data);
				$("#maindiv").html("");
			}
		});
	});
	
	var requestStr = "changeRole.do?roleId=" + document.getElementById("roleSelection").value;
	$.get(requestStr, function(data, status){
		if(status == "success") {
			//alert($("#leftdiv").html());
			$("#leftdiv").html(data);
			//alert($("#leftdiv").html());
			$("#maindiv").html("");
		};
/*		if(firstTimeLogin) {
			showModifyPasswdWnd();
		}
*/
	});
}); 

$(function() {
	$("#quit").click(function() {
		$.post("quit.do");
		window.close();
	});
})
</script>
</head>
<body id="bodydiv" class="easyui-layout">
	<!-- 页面头部:一级菜单区域 -->
	<div id="topdiv" data-options="region:'north',border:false,closable:true" style="overflow: hidden;height:100px">
		<div class="navbar">
			<div class="logo">
				<a href="javascript:void(0);"><img src="<%=path%>/images/sys/logo.jpg"
					width="408" height="40" /> </a>
			</div>
		</div>
		<div id="divtabs1" title="welcome" style="padding:0px;closable:false;">
			<div class="content-info">
				<div class="info">
					<p>欢迎您，${user.name}！</p>
				</div>
				<div class="content-setting">
					<select id="roleSelection" class="content-menu">
					<c:forEach var="role" items="${roleList}">
						<c:choose>
							<c:when test="${role.id} == ${curRole.id}">
								<option value="${role.id}" selected="selected">${role.name}</option>
							</c:when>
							<c:otherwise>
								<option value="${role.id}">${role.name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					</select>
					<a href="javascript:void(0);"  class="icon-info-out" id="btnModifyPasswd" onClick="showModifyPasswdWnd();"> <span>修改密码</span> </a> <a  id="quit" href="javascript:void(0);" class="icon-info-out"> <span>退出</span> </a>
				</div>
			</div>
		</div>
	</div>
	<!-- 页面左侧:二级菜单区域 -->
	<div id="leftdiv" data-options="region:'west',split:true,title:'功能菜单列表',onExpand:function(){ showWestTab=true; },onCollapse:function(){ showWestTab=false; }" style="padding: 0px; width: 250px">
			<div id='secondMenu_Infosys' parent='firstMenu_Infosys' class='easyui-accordion' data-options='fit:true,border:false'>
			</div>
	</div>
	<div id="maindiv" data-options="region:'center', href:'javascript:void(0);'">
	</div>
	<div id="changePasswdDialog"></div>
</body>
</html>