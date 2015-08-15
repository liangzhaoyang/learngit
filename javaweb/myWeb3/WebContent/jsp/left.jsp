<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height:100%">
	<head>
		<title>left</title>
	<style>
		body{
		margin:0px;
		padding:0px;
		height:100%;
		font-size:12px;
		text-align:center;
		overflow:hidden;}
		#left{
		width:173px;
		height:100%;}
		#l_left{
		background-image:url(../images/main_20.gif);
		width:4px;
		float:left;
		height:100%;
		background-repeat:repeat-y;}
		#l_right{
		float:right;
		height:100%;
		background-color:#FFFFFF;}
		#r1{
		height:20px;
		width:169px;
		background-image:url(../images/main_11.gif);}
		#r1 div{
		font-size:12px;
		font-weight:bold;
		color:#FFFFFF;
		padding-top:3px;
		margin-left:-60px;}
		#r2{
		margin-top:8px;
		text-align:center;
		margin-left:12px;}
		.style1{
		width:145px;
		height:28px;
		background-image:url(../images/1.png);}
		.style11{
		float:left;
		margin-left:18px;
		margin-top:5px;}
		.style12{
		float:left;
		margin-left:10px;
		margin-top:7px;
		font-size:12px;
		color:#FFFFFF;}
		.style12 a{
		color:#FFFFFF;
		text-decoration:none;}
		.style2{
		border:1px solid #AFD280;
		height:130px;
		width:136px;
		margin-left:2.5px;
		margin-top:-2px;
		text-align:left;
		margin-bottom:5px;}
		.style2 ul{
		list-style-type:none;
		list-style-image:url(../images/left.gif);
		margin-top:10px;
		margin-left:-5px;}
		.style2 ul li{
		margin-bottom:10px;}
		.style2 ul li a{
		color:#000000;
		text-decoration:none;}
		.style2 ul li a:hover{
		text-decoration:underline;
		font-size:13px;}
		#style1{
		display:none;}
	</style>	
	<script>
		function hiddenDiv(div){
			div.style.display=(div.style.display=='none'?'block':'none');
		}
	</script>
	</head>
	<body>
		<div id="left">
			<div id="l_left">
			</div>
			<div id="l_right">
				<div id="r1">
					<div>系统菜单</div>
				</div>
				<div id="r2">
			
					<div class="style1">
						<div class="style11"><img src="../images/list.gif"/></div>
						<div class="style12"><a href="javascript:void(0)" onclick="hiddenDiv(document.getElementById('style1'))">流量分析</a></div>
					</div>
					<div class="style2" id="style1">
						<ul>
							<li><a href="${pageContext.request.contextPath}/netFlowServlet?method=getNetFlows" target='I2'>流量查询</a></li>
							<li><a href="${pageContext.request.contextPath}/jsp/live_fig.jsp" target='I2'>动态流量显示</a></li>
							<li><a href="${pageContext.request.contextPath}/jsp/history_fig.jsp" target='I2'>静态流量显示</a></li>

						</ul>
					</div>
				
					<div class="style1">
						<div class="style11"><img src="../images/list.gif"/></div>
						<div class="style12"><a href="javascript:void(0)" onclick="hiddenDiv(document.getElementById('style2'))">态势评估</a></div>
					</div>
					<div class="style2" id="style2">
						<ul>
							<li><a href="${pageContext.request.contextPath}/AlarmServlet?method=getAlarmEvent" target='I2'>告警事件查询</a></li>
							<li><a href="${pageContext.request.contextPath}/jsp/alarmLive_fig.jsp" target='I2'>动态告警数量统计</a></li>
							<li><a href="${pageContext.request.contextPath}/jsp/situationValue.jsp" target='I2'>态势评估曲线</a></li>

						</ul>
					</div>
					<div class="style1">
						<div class="style11"><img src="../images/list.gif"/></div>
						<div class="style12"><a href="javascript:void(0)" onclick="hiddenDiv(document.getElementById('style3'))">拓扑关联</a></div>
					</div>
					<div class="style2" id="style3">
						<ul>
							<li><a href="${pageContext.request.contextPath}/jsp/topo1.jsp" target='I2'>网络拓扑图1</a></li>
							<li><a href="${pageContext.request.contextPath}/jsp/topo3.jsp" target='I2'>网络拓扑图2</a></li>
							<li><a href="${pageContext.request.contextPath}/jsp/topo5.jsp" target='I2'>网络拓扑图3</a></li>
							<li><a href="${pageContext.request.contextPath}/jsp/topo8.jsp" target='I2'>网络拓扑图4</a></li>
							<li><a href="${pageContext.request.contextPath}/images/devided0.gif" target='I2'>社团划分</a></li>
							
						</ul>
					</div>
				
			</div>
		</div>
	</div>
	</body>
</html>