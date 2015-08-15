<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Highstock Example</title>
		<script type="text/javascript" src="../jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="../js/highstock.js"></script>
		<script type="text/javascript" src="../js/modules/exporting.js"></script>
		<script type="text/javascript" src="../js/themes/gray.js"></script>
		<style>
			body{
			margin:0px;
			padding:0px;
			text-align:center;}
			#style1{
			margin-top:30px;
			margin-bottom:20px;}
			#style1 input{
			width:60px;
			}
		</style>
		<script src="../../js/laydate/laydate.js"></script>
		<style type="text/css">
			${demo.css}
		</style>
		<script type="text/javascript">
		$(function() {
			
			
			Highcharts.setOptions({
				global : {
					useUTC : false
				}
			});
			
			// Create the chart
			$('#container').highcharts('StockChart', {
				chart : {
					events : {
						load : function() {

							// set up the updating of the chart each second
							var series = this.series[0];
							setInterval(function() {
								var x = (new Date()).getTime(), // current time
								y = Math.round(Math.random() * 100);
								series.addPoint([x, y], true, true);
							}, 1000);
						}
					}
				},
				
				rangeSelector: {
					buttons: [{
						count: 1,
						type: 'minute',
						text: '1M'
					}, {
						count: 5,
						type: 'minute',
						text: '5M'
					}, {
						type: 'all',
						text: 'All'
					}],
					inputEnabled: false,
					selected: 0
				},
				
				title : {
					text : 'Live random data'
				},
				
				exporting: {
					enabled: false
				},
				
				series : [{
					name : 'Random data',
					data : (function() {
						// generate an array of random data
						var data = [], time = (new Date()).getTime(), i;

						for( i = -999; i <= 0; i++) {
							data.push([
								time + i * 1000,
								Math.round(Math.random() * 100)
							]);
						}
						return data;
					})()
				}]
			});

		});

		</script>
	</head>
	<body>
		
		<div id="style1">
			<form action="${pageContext.request.contextPath}/NetFlowServlet?method=getDataSet" method="post">
					请输入查询时间段：<input type="text" name="start_time" class="laydate-icon" id="start" style="width:180px; ">&nbsp;-&nbsp;<input type="text" name="end_time" class="laydate-icon" id="end" style="width:180px;">
					<script>
					var start = {
					    elem: '#start',
					    format: 'YYYY-MM-DD hh:mm:ss',
					    min: '2000-03-07 22:24:39', 
					    max: '2000-03-08 01:33:24',
					    istime: true,
					    istoday: false,
					    choose: function(datas){
					         end.min = datas; 
					         end.start = datas 
					    }
					};
					var end = {
					    elem: '#end',
					    format: 'YYYY-MM-DD hh:mm:ss',
					    min: '2000-03-07 22:24:40',
					    max: '2000-03-08 01:33:25',
					    istime: true,
					    istoday: false,
					    choose: function(datas){
					        start.max = datas;
					    }
					};
					laydate(start);
					laydate(end);
				</script>
					&nbsp;<input type="submit" value="submit" />
				</form>
			</div>
		<div id="container" style="height: 400px; min-width: 310px"></div>
	</body>
</html>
