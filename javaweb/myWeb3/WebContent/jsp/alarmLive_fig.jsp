<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Highstock Example</title>
		<script type="text/javascript" src="${pageContext.request.contextPath }/Highstock/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/Highstock/js/highstock.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/Highstock/js/modules/exporting.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/Highstock/js/themes/grid.js"></script>
		<style>
			body{
			margin:0px;
			padding:0px;
			text-align:center;}
			#style1{
			margin-top:20px;
			margin-bottom:20px;}
			#style1 input{
			width:60px;
			}
			#btn_style{
			display:none;
			magin-bottom:0px;}
			#container{
			padding-top:40px;}
		</style>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/laydate/laydate.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.blockUI.js"></script>
		<style type="text/css">
			${demo.css}
		</style>
	
		<script type="text/javascript">
		$(function() {
			
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
			
			var url="${pageContext.request.contextPath }/AlarmServlet?method=getAlarmDataSet";
			var args={"time":new Date()};
			$.getJSON(url,args,function(data){
				//alert(data.length);
				 
				    var data1 = []; 
				    for(var i = 0; i  < data.length;i++){
				    	
				    	if(i < 100){
				    		
				    		data1[i] = data[i];
				    	}
				    }
				    
		 			var i=100; 
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
										
 										if(i<data.length)
 										{
 											series.addPoint([data[i].timestamp, data[i].totalValue], true, true);
 											i++;
 										}
										
									}, 1000);
								}
							}
						},
						series : [{
							name : 'Random data',
							data : (function() {
								// generate an array of random data 初始的
								    var data = [], i;
										for(i=0;i<data1.length;i++){
											data.push([
											      
											      data1[i].timestamp,
											      data1[i].totalValue
											]);
										}
								return data;  
							})()
						}],
						rangeSelector: {
							buttons: [{
								count: 10,
								type: 'minute',
								text: '10M'
							},{
								count: 20,
								type: 'minute',
								text: '20M'
							}, {
								type: 'all',
								text: 'All'
							}],
							inputEnabled: false,
							selected: 0
						},
						
						title : {
							text : 'The　number of alarm distribution'
						},
						
						exporting: {
							enabled: true,
						},
						 xAxis: {
					       	type: 'datetime',
					       	tickPixelInterval:50,
					               title: {
					                   text: null
					               },
					               dateTimeLabelFormats: {
					                   second: '%H:%M:%S',
					                   minute: '%m-%e %H:%M',
					                   hour: '%m-%e %H:%M',
					                   day: '%m-%e %H:%M',
					                   week: '%e. %m',
					                   month: '%y-%m',
					                   year: '%Y'
					               }
					       }, 
					       yAxis: {
				                title: {
				                    text: 'The number of alarm'
				                }
				            },
						tooltip: {
							 formatter: function () {
					                var s = '<b>' + Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '</b>';

					                $.each(this.points, function () {
					                    s += '<br/> value = ' + this.y + ' (个) ';
					                });

					                return s;
					            }
				            },
						credits:{
							enabled:false
						}
					});		
		})	
			
		});
		</script>
	</head>
	<body>
		<%-- <input type="text" name="start_time" id="start" value='${sessionScope.start_time }' style="display:none"/> 
		<input type="text" name="end_time" id="end" value='${sessionScope.end_time }' style="display:none"/> --%>
		<img alt="" id="loading" src="${pageContext.request.contextPath }/images/loading.gif" style="display:none">
		<div id="container" style="height: 450px; min-width: 300px;" ></div>
		
	</body>
</html>
