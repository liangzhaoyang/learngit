<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Insert title here</title>
		<style>
			body{
			margin:0px;
			padding:0px;}
			#bg{
			background-image:url(../images/main_25.gif);
			width:100%;
			height:23px;}
			#left{
			height:23px;
			float:left;
			width:181px;}
			#right{
			float:right;
			text-align:right;}
			#date{
			font-size:12px;
			color:#147233;
			float:left;
			padding-top:4px;}
			#img{
			width:25px;
			height:23px;
			float:left;}
		</style>
		<script language="JavaScript">
			function tick() {
			var hours, minutes, seconds;
			var intHours, intMinutes, intSeconds;
			var today;
			today = new Date();
			intYear = today.getFullYear();
			intMonth = today.getMonth() + 1;
			intDay = today.getDate();
			intHours = today.getHours();
			intMinutes = today.getMinutes();
			intSeconds = today.getSeconds();
			
			if (intHours == 0) {
			hours = "00:";
			} else if (intHours < 10) { 
			hours = "0" + intHours+":";
			} else {
			hours = intHours + ":";
			}
			
			if (intMinutes < 10) {
			minutes = "0"+intMinutes+":";
			} else {
			minutes = intMinutes+":";
			}
			if (intSeconds < 10) {
			seconds = "0"+intSeconds+" ";
			} else {
			seconds = intSeconds+" ";
			} 
			timeString = intYear + "年" + intMonth + "月" + intDay + "日" + " " + hours+minutes+seconds;
			Clock.innerHTML = timeString;
			window.setTimeout("tick();", 1000);
			}
			window.onload = tick;
		</script>
	</head>
	<body>
		<div id="bg">
			<div id="left">
				<img src="../images/main_24.gif" />
			</div>
			<div id="right">
				<div id="date">今天是：<span id="Clock"></span></div>
				<div id="img"><img src="../images/main_27.gif" /></div>
			</div>
		</div>
	</body>
</html>