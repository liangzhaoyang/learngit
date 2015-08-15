<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Highstock Example</title>
		<script type="text/javascript" src="../jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="../js/highstock.js"></script>
		<script type="text/javascript" src="../js/modules/exporting.js"></script>
		<script type="text/javascript" src="../js/themes/grid.js"></script>
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
		</style>
		<script src="../../js/laydate/laydate.js"></script>
		<style type="text/css">
			${demo.css}
		</style>
		<script type="text/javascript">
	
		$(function () {
			$("#search").click(function(){
			var timeStart=$.trim($("#start").val());
			var timeEnd=$.trim($("#end").val());
			if(timeStart == ''|| timeEnd == '')
			{
				alert("开始日期和结束时间不能为空，并且的跨度不能大于12个小时！");
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
				if(end-start>(12*60*60*1000)){
					alert('查询时间跨度不能超过12个小时');
					return;
				}
				var url="${pageContext.request.contextPath }/netFlowServlet?method=getDataSet3";		
				var args={time:new Date(),start_time:timeStart,end_time:timeEnd};
				$.getJSON(url,args,function(data){
					$('#container').highcharts({
				        chart: {
				            type: 'column'
				        },
				        title: {
				            text: 'World\'s largest cities per 2014'
				        },
				        subtitle: {
				            text: 'Source: <a href="http://en.wikipedia.org/wiki/List_of_cities_proper_by_population">Wikipedia</a>'
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
				                text: 'Population (millions)'
				            }
				        },
				        legend: {
				            enabled: false
				        },
				        tooltip: {
				            pointFormat: 'Population in 2008: <b>{point.y:.1f} millions</b>'
				        },
				        series: [{
				            name: 'Population',
				            data: data,
				            dataLabels: {
				                enabled: true,
				                rotation: -90,
				                color: '#FFFFFF',
				                align: 'right',
				                format: '{point.y:.1f}', // one decimal
				                y: 10, // 10 pixels down from the top
				                style: {
				                    fontSize: '13px',
				                    fontFamily: 'Verdana, sans-serif'
				                }
				            }
				        }]
				    });
		        
		    });
			 }
			
			});
		});
		</script>
	</head>
	<body>
		
		<div id="style1">
			请选择查询时间段：
			<input type="text" name="start_time" class="laydate-icon" id="start" style="width:180px;height:25px; " />&nbsp;-&nbsp;
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
			&nbsp;&nbsp;<input type="submit" id="search" value="查询" style="width:50px;"/>
			</div>
		<div id="container" style="height: 400px; min-width: 310px"></div>
	</body>
</html>
