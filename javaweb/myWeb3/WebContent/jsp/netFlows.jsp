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
		width:918px;
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
		width:918px;
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
			width:918px;
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
			width:875px;
			text-align:center;}
			#d3{
			float:right;}
			#d3 img{
			width:14px;
			height:29px;}
			#fig{margin-bottom:15px;text-align:center;}
			#fig .submit{ width:155px; height:36px; line-height:0px; font-size:20px;text-decoration:none;background-color:#AAD079;color:#FFF;cursor:pointer;border-radius:8px; border:none; margin-top:8px;magin-bottom:10px;}
			#myfig{display:none;text-align:center; magin-bottom:30px;}
			#container2{float:left; margin-left:55px;padding-bottom:15px;}
			#container3{float:left; margin-left:55px;padding-bottom:15px;}
			
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/Highcharts/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Highcharts/js/highcharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Highcharts/js/modules/exporting.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Highcharts/js/themes/grid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.blockUI.js"></script>
<script src="${pageContext.request.contextPath}/js/laydate/laydate.js"></script>
<script src="${pageContext.request.contextPath}/js/validate.js"></script>
<script type="text/javascript"> 
	$(function(){
		
		$(document).ajaxStart(function(){
			$.blockUI({
	            message: $('#loading'),
	            css: {
	                top:  ($(window).height() - 400) /2 + 'px',
	                left: ($(window).width() - 400) /2 + 'px',
	                width: '400px',
	                border: 'none'
	            },
	            overlayCSS: { backgroundColor:'#fff' }
	        })
		}).ajaxStop($.unblockUI);
		
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
				if(pageNo >= 1 && pageNo <= parseInt("${netFlowpage.totalPageNumber }")){
					flag = true;
				}
			}			
			
			if(!flag){
				alert("输入的不是合法的页码.");
				$(this).val("");
				return;
			}
			
			//3. 页面跳转
			var href = "netFlowServlet?method=getNetFlows&start_time=${start_time}&end_time=${end_time}&pageNo=" + pageNo + "&" + $(":hidden").serialize();
			window.location.href = href;
		});
		
		$("#btnfig").click(function(){
			//alert("hehe");
			var timeStart='<%=request.getAttribute("start_time")%>';
			var timeEnd='<%=request.getAttribute("end_time")%>'; 
			var url0="${pageContext.request.contextPath }/ShowProtoServlet";		
			var args0={time:new Date(),start_time:timeStart,end_time:timeEnd};
			$.getJSON(url0,args0,function(data){
				if(data==1){
					$("#myfig").show();
				}else{
					alert("生成图片失败！");
					return;
				}
			}); 
			//alert(timeStart+"---"+timeEnd);
			$("#myfig").show();
			
			var url="${pageContext.request.contextPath }/netFlowServlet?method=getDataSet3";		
			var args={time:new Date(),start_time:timeStart,end_time:timeEnd};
			$.getJSON(url,args,function(data){
				$('#container2').highcharts({
			        chart: {
			            type: 'column'
			        },
			        title: {
			            text: 'The size of flow of IP host(Top 10)'
			        },
			       
			        xAxis: {
			            type: 'category',
			            labels: {
			                rotation: -45,
			                style: {
			                    fontSize: '13px',
			                    fontFamily: 'Verdana, sans-serif'
			                }
			            }
			        },
			        yAxis: {
			            min: 0,
			            title: {
			                text: 'The size of Flow (bps)'
			            }
			        },
			        legend: {
			            enabled: false
			        },
			        tooltip: {
			            pointFormat: 'Value: <b>{point.y:.1f} bps</b>'
			        },
			        credits:{
			        	enabled:false
			        },
			        series: [{
			            name: 'NetFlow',
			            data: data
			        }]
			    });
	    });
				
			
			var url="${pageContext.request.contextPath }/netFlowServlet?method=getDataSet4";		
			var args={time:new Date(),start_time:timeStart,end_time:timeEnd};
			$.getJSON(url,args,function(data){
				$('#container3').highcharts({
					chart: {
			            plotBackgroundColor: null,
			            plotBorderWidth: null,
			            plotShadow: false
			        },
			        title: {
			            text: 'Protocol Distribution Map'
			        },
			        tooltip: {
			            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			        },
			        plotOptions: {
			            pie: {
			                allowPointSelect: true,
			                cursor: 'pointer',
			                dataLabels: {
			                    enabled: true,
			                    format: '<b>{point.name}</b>: {point.percentage:.2f} %',
			                    style: {
			                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
			                    }
			                }
			            }
			        },
			        credits:{
			        	enabled:false
			        },
			        series: [{
			            type: 'pie',
			            name: 'Protocol type',
			            data: data
	
			        }]
			    });
	    });
			
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
		<form action="${pageContext.request.contextPath}/netFlowServlet?method=getNetFlows" method="post">
			请选择查询时间段：
			<input type="text" name="start_time" class="laydate-icon" id="start" style="width:180px; height:25px;">&nbsp;-&nbsp;
			<input type="text" name="end_time" class="laydate-icon" id="end" style="width:180px;height:25px;" />
			<script>
			var start = {
			    elem: '#start',
			    format: 'YYYY-MM-DD hh:mm:ss',
			    min: '2000-03-07 22:21:36', //设定最小日期为当前日期
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
			    min: '2000-03-07 22:21:36',
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
	<img alt="" id="loading" src="${pageContext.request.contextPath }/images/loading.gif" style="display:none">
	<center>
	<div id="container">
		<div id="top">
			<div id="t1"><img src="${pageContext.request.contextPath}/images/tab_03.gif" /></div>
			<div id="t2"><img src="${pageContext.request.contextPath}/images/311.gif" /><span>流量信息表</span></div>
			<div id="t3"><img src="${pageContext.request.contextPath}/images/tab_07.gif" /></div>
		</div>
		
		<div id="center">
			<table width="918" border="0" cellspacing="0" cellpadding="0">
		  		<tr>
					<td width="9" background="${pageContext.request.contextPath}/images/tab_12.gif" align="left"></td>
					<td bgcolor="e5f1d6">
		 			<table width="900" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CECECE">
						<tr>
							<td width="9%" height="23" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" >序号</div></td>
							<td width="10%" height="23" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" >源IP</div></td>
							<td width="10%" height="23" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" >目的IP</div></td>
							<td width="8%" height="23" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" >版本号</div></td>
							<td width="8%" height="23" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" >头部长</div></td>
							<td width="10%" height="23" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" >服务类型</div></td>
							<td width="10%" height="23" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" >总长度</div></td>
							<td width="10%" height="23" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" >生存时间</div></td>
							<td width="10%" height="23" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" >协议</div></td>
							<td width="15%" height="23" background="${pageContext.request.contextPath}/images/tab_14.gif" class="STYLE1"><div align="center" >发生时间</div></td>
        				</tr>
						<c:forEach items="${netFlowpage.list }" var="netFlow">
							<tr>
			            			<td height="23" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${netFlow.id }</div></td>
									<td height="23" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${netFlow.srcIP }</div></td>
									<td height="23" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${netFlow.dstIP }</div></td>
									<td height="23" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${netFlow.version }</div></td>
									<td height="23" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${netFlow.hlen }</div></td>
									<td height="23" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${netFlow.tos }</div></td>
									<td height="23" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${netFlow.len }</div></td>
									<td height="23" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${netFlow.ttl }</div></td>
									<td height="23" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${netFlow.proto }</div></td>
									<td height="23" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${netFlow.timestamp }</div></td>
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
		<div id="d2"><span>共 ${netFlowpage.totalPageNumber } 页
		&nbsp;&nbsp;
		当前第 ${netFlowpage.pageNo } 页		
		&nbsp;&nbsp;
		
		<c:if test="${netFlowpage.hasPrev }">
			<a href="netFlowServlet?method=getNetFlows&pageNo=1&start_time=${start_time}&end_time=${end_time}">首页</a>
			&nbsp;&nbsp;
			<a href="netFlowServlet?method=getNetFlows&pageNo=${netFlowpage.prevPage }&start_time=${start_time}&end_time=${end_time}">上一页</a>
		</c:if>

		&nbsp;&nbsp;
		
		<c:if test="${netFlowpage.hasNext }">
			<a href="netFlowServlet?method=getNetFlows&pageNo=${netFlowpage.nextPage}&start_time=${start_time}&end_time=${end_time}">下一页</a>
			&nbsp;&nbsp;
			<a href="netFlowServlet?method=getNetFlows&pageNo=${netFlowpage.totalPageNumber}&start_time=${start_time}&end_time=${end_time}">末页</a>
		</c:if>
		
		&nbsp;&nbsp;
		转到 <input type="text" size="1" id="pageNo"/> 页	</span></div>
		<div id="d3"><img src="${pageContext.request.contextPath}/images/tab_22.gif" /></div>
	</div>
</div>		
</center>

	<div id="fig">
		<input name="netFlowFig" type="button" id="btnfig" value="分类统计图" style="cursor: pointer;" class="submit" />
	</div>
	 <div id="myfig">
		<div id="container2" style="height: 350px; width: 510px; "></div>
		<div id="container3" style="width: 410px; height: 350px; "></div>
	</div> 
	
</body>
</html>