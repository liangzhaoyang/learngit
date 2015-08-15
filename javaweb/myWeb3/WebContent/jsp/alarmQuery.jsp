<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	body{
	margin:0px;
	padding:0px;
	text-align:center;}
	#timeSelector{
	margin-top:15px;
	margin-bottom:15px;}

	#top{
		width:1018px;
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
		float:right;}
		#t4{
		float:right;}
		#t3 img{
		width:14px;
		height:30px;}
		#center{
		width:1018px;
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
			width:1018px;
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
			padding-top:2px;
			width:975px;
			text-align:center;}
			#d3{
			float:right;}
			#d3 img{
			width:14px;
			height:29px;}
			#fig{margin-bottom:15px;text-align:center;}
	#fig .submit{ width:155px; height:36px; line-height:0px; font-size:20px;text-decoration:none;background-color:#AAD079;color:#FFF;cursor:pointer;border-radius:8px; border:none; margin-top:8px;magin-bottom:10px;}
	#fig .update{ width:85px; height:36px; line-height:0px; font-size:20px; text-decoration:none;background-color:#AAD079;color:#FFF;cursor:pointer;border-radius:8px; border:none; margin-left:30px; margin-top:8px;magin-bottom:10px;}			
			
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/laydate/laydate.js"></script>
<script src="${pageContext.request.contextPath}/js/validate.js"></script>
<script type="text/javascript">
	
	$(function(){
		
		$("#pageNo").change(function(){
			
			var val = $(this).val();
			val = $.trim(val);
			
			//1. 校验 val 是否为数字 1, 2, 而不是 a12, b
			var flag = false;
			var reg = /^\d+$/g;
			var pageNo = 0;
			
			if(reg.test(val)){
				//2. 校验 val 在一个合法的范围内： 1-totalPageNumber
				pageNo = parseInt(val);
				if(pageNo >= 1 && pageNo <= parseInt("${alarmpage.totalPageNumber }")){
					flag = true;
				}
			}
			
			
			if(!flag){
				alert("输入的不是合法的页码.");
				$(this).val("");
				return;
			}
			var href = "AlarmServlet?method=getAlarmEvent&start_time=${start_time}&end_time=${end_time}&pageNo=" + pageNo + "&" + $(":hidden").serialize();
			window.location.href = href;
		});
		
		
	})
	
	function validate(){
		var timeStart=$("#start").val();
		var timeEnd=$("#end").val();
		if(timeStart == ''|| timeEnd == '')
		{
			alert("开始日期和结束时间不能为空!");
			return false;
		}
		else{
			if( (timeStart.replace(/-/g,"/"))>(timeEnd.replace(/-/g,"/")) )
		     {
		         alert('结束时间不能小于开始时间！');
		            return false;
		     }
			return true;
		}
		
	}
</script>

</head>
<body>
	
	<div id="timeSelector">
		<form action="${pageContext.request.contextPath}/AlarmServlet?method=getAlarmEvent" method="post">
			请选择查询时间段：
			<input type="text" name="start_time" class="laydate-icon" id="start" style="width:180px; height:25px;">&nbsp;-&nbsp;
			<input type="text" name="end_time" class="laydate-icon" id="end" style="width:180px;height:25px;" />
			<script>
			var start = {
			    elem: '#start',
			    format: 'YYYY-MM-DD hh:mm:ss',
			    min: '2000-03-07 22:24:39', //设定最小日期为当前日期
			    max: laydate.now(), //最大日期
			    istime: true,
			    istoday: false,
			    choose: function(datas){
			         end.min = datas; //开始日选好后，重置结束日的最小日期
			         end.start = datas //将结束日的初始值设定为开始日
			    }
			};
			var end = {
			    elem: '#end',
			    format: 'YYYY-MM-DD hh:mm:ss',
			    min: '2000-03-07 22:24:39',
			    max: laydate.now(),
			    istime: true,
			    istoday: false,
			    choose: function(datas){
			        
			    	start.max = datas; //结束日选好后，重置开始日的最大日期
			    }
			};
			laydate(start);
			laydate(end);
		</script>
			&nbsp;&nbsp;<input type="submit" value="查询" onclick="return validate()" style="width:50px;"/>
		</form>
	</div>
	
	<center>
	<div id="container">
		<div id="top">
			<div id="t1"><img src="${pageContext.request.contextPath}/images/tab_03.gif" /></div>
			<div id="t2"><img src="${pageContext.request.contextPath}/images/311.gif" /><span>告警信息表</span></div>
			<div id="t3"><img src="${pageContext.request.contextPath}/images/tab_07.gif" /></div>
		</div>
		
		<div id="center">
			<table width="1018" border="0" cellspacing="0" cellpadding="0">
		  		<tr>
					<td width="9" background="${pageContext.request.contextPath}/images/tab_12.gif" align="left"></td>
					<td bgcolor="e5f1d6">
		 			<table width="1000" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CECECE">
						<tr>
							<td width="6%" height="23" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" >序号</div></td>
							<td width="10%" height="23" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" >源IP</div></td>
							<td width="10%" height="23" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" >目的IP</div></td>
							<td width="6%" height="23" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" >源端口</div></td>
							<td width="6%" height="23" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" >目的端口</div></td>
							<td width="20%" height="23" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" >告警</div></td>
							<td width="19%" height="23" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" >告警类型</div></td>
							<td width="5%" height="23" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" >严重程度</div></td>
							<td width="5%" height="23" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" >协议</div></td>
							<td width="13%" height="23" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" >发生时间</div></td>
        				</tr>
						<c:forEach items="${alarmpage.list }" var="alarm">
							<tr>
			            			<td height="23" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${alarm.id }</div></td>
									<td height="23" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${alarm.srcIP }</div></td>
									<td height="23" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${alarm.dstIP }</div></td>
									<td height="23" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${alarm.sport }</div></td>
									<td height="23" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${alarm.dport }</div></td>
									<td height="23" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${alarm.alarm_msg }</div></td>
									<td height="23" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${alarm.alarm_class }</div></td>
									<td height="23" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${alarm.severity }</div></td>
									<td height="23" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${alarm.proto }</div></td>
									<td height="23" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${alarm.timestamp }</div></td>
			          			</tr>
						</c:forEach>
					</table>
				</td>
        		<td width="9" background="${pageContext.request.contextPath}/images/tab_16.gif"></td>
      		</tr>
     	</table>
	</div>
	
	<div id="down">
		<div id="d1"><img src="${pageContext.request.contextPath}/images/tab_20.gif" /></div>
		<div id="d2"><span>共 ${alarmpage.totalPageNumber } 页
		&nbsp;&nbsp;
		当前第 ${alarmpage.pageNo } 页		
		&nbsp;&nbsp;
		
		<c:if test="${alarmpage.hasPrev }">
			<a href="AlarmServlet?method=getAlarmEvent&pageNo=1&start_time=${start_time}&end_time=${end_time}">首页</a>
			&nbsp;&nbsp;
			<a href="AlarmServlet?method=getAlarmEvent&pageNo=${alarmpage.prevPage }&start_time=${start_time}&end_time=${end_time}">上一页</a>
		</c:if>

		&nbsp;&nbsp;
		
		<c:if test="${alarmpage.hasNext }">
			<a href="AlarmServlet?method=getAlarmEvent&pageNo=${alarmpage.nextPage}&start_time=${start_time}&end_time=${end_time}">下一页</a>
			&nbsp;&nbsp;
			<a href="AlarmServlet?method=getAlarmEvent&pageNo=${alarmpage.totalPageNumber}&start_time=${start_time}&end_time=${end_time}">末页</a>
		</c:if>
		
		&nbsp;&nbsp;
		转到 <input type="text" size="1" id="pageNo"/> 页	</span></div>
		<div id="d3"><img src="${pageContext.request.contextPath}/images/tab_22.gif" /></div>
	</div>
	</div>		
	</center>

</body>
</html>