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
		</style>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/laydate/laydate.js"></script>
		<style type="text/css">
			${demo.css}
		</style>
		<script type="text/javascript">
		$(function() {
			var url="${pageContext.request.contextPath }/netFlowServlet?method=getDataSet";
			var args={"time":new Date()};
			$.getJSON(url,args,function(data){
				//alert(data.length);
				 
				    var data1 = []; 
				    for(var i = 0; i  < data.length;i++){
				    	
				    	if(i < 400){
				    		
				    		data1[i] = data[i];
				    	}
				    }
				    
		 			var i=400; 
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
										
									}, 500);
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
							text : 'Live netflow data'
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
						tooltip: {
							 formatter: function () {
					                var s = '<b>' + Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '</b>';

					                $.each(this.points, function () {
					                    s += '<br/> value = ' + this.y + ' bps';
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
		<script type="text/javascript">
		$(function(){
			$("#search").click(function(){
				var timeStart=$("#start").val();
				var timeEnd=$("#end").val();
				if(timeStart == ''|| timeEnd == '')
				{
					alert("开始日期和结束时间不能为空!");
					return;
				}
				else{
					if( (timeStart.replace(/-/g,"/"))>(timeEnd.replace(/-/g,"/")) )
				     {
				         alert('结束时间不能小于开始时间！');
				            return;
				     }
					var start  = new Date(timeStart.replace(/-/g,"/")).getTime();
					//alert(start);
					var end = new Date(timeEnd.replace(/-/g,"/")).getTime();
				var url="${pageContext.request.contextPath }/netFlowServlet?method=getDataSet";		
				var args={time:new Date(),start_time:timeStart,end_time:timeEnd};
				$.getJSON(url,args,function(data){
					 var data1 = []; 
					    for(var i = 0; i  < data.length;i++){
					    	
					    	if(i < 400){
					    		
					    		data1[i] = data[i];
					    	}
					    }
			 			var i=400; 
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
	 										
										}, 500);
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
								text : 'Live network flow chart'
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
							tooltip: {
								 formatter: function () {
						                var s = '<b>' + Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '</b>';

						                $.each(this.points, function () {
						                    s += '<br/> value = ' + this.y + ' bps';
						                });

						                return s;
						            }
					            },
							
						});
			 		//}
				//});
					
				function json2TimeStamp(milliseconds){
			        var datetime = new Date();
			        datetime.setTime(milliseconds);
			        var year=datetime.getFullYear();
			             //月份重0开始，所以要加1，当小于10月时，为了显示2位的月份，所以补0
			        var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
			        var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
			        var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
			        var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
			        var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
			        return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
			    }
				});
		}
			});
			
		});
		</script>
	</head>
	<body>
		
		<div id="style1">
			请选择查询时间段：
			<input type="text" name="start_time" class="laydate-icon" id="start" style="width:180px;height:25px; "/>&nbsp;-&nbsp;
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
			&nbsp;<input type="submit" id="search" value="查询" style="width:50px;"/>
			</div>
		<div id="container" style="height: 450px; min-width: 300px;" ></div>
	</body>
</html>
