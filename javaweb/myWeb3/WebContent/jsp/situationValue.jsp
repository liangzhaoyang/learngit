<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Highstock Example</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/Highcharts/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/Highcharts/js/highcharts.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/Highcharts/js/modules/exporting.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/Highcharts/js/themes/grid.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.blockUI.js"></script>
		<style>
			body{
			margin:0px;
			padding:0px;
			text-align:center;}
			#container{
			padding-top:40px;}
		</style>
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
			
			var timeStart=$.trim($("#start").val());
			var timeEnd=$.trim($("#end").val());
			var timeRange=new Date(timeEnd.replace(/-/g,"/")).getTime()-new Date(timeStart.replace(/-/g,"/")).getTime();
			//alert(new Date(new Date(timeStart.replace(/-/g,"/")).getTime()+300 * 1000));
			var url="${pageContext.request.contextPath }/AlarmServlet?method=getSituationDataSet";
			var args={"time":new Date()};
			$.getJSON(url,args,function(data){
				
				 $('#container').highcharts({
					 global : {
						useUTC : false
					}, 
			        chart: {
			            zoomType: 'x',
			            spacingRight: 20
			        },
			        title: {
			            text: 'Network security situation assessment curve'
			        },
			        /* subtitle: {
			            text: document.ontouchstart === undefined ?
			                'Click and drag in the plot area to zoom in' :
			                'Pinch the chart to zoom in'
			        }, */
			        xAxis: {
			            type: 'datetime',
			            maxZoom: timeRange, // half half days
			            title: {
			                text: null
			            }
			        },
			        yAxis: {
			            title: {
			                text: 'Assessment Value'
			            }
			        },
			        tooltip: {
			            shared: true
			        },
			        legend: {
			            enabled: false
			        },
			        plotOptions: {
			            area: {
			                fillColor: {
			                    linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1},
			                    stops: [
			                        [0, Highcharts.getOptions().colors[0]],
			                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
			                    ]
			                },
			                lineWidth: 1,
			                marker: {
			                    enabled: false
			                },
			                shadow: false,
			                states: {
			                    hover: {
			                        lineWidth: 1
			                    }
			                },
			                threshold: null
			            }
			        },

			        series: [{
			            type: 'area',
			            name: 'Situation value',
			            pointInterval: 300 * 1000,
			            pointStart: date2UTC(new Date(new Date(timeStart.replace(/-/g,"/")).getTime()+300 * 1000)),
			            data: data
			        }],
		            credits:{
		            	enabled:false
		            }
			    }); 
				
			});
			
			function date2UTC(datetime){
		        var year=datetime.getFullYear();
		             //月份重0开始，所以要加1，当小于10月时，为了显示2位的月份，所以补0
		        var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
		        var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
		        var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
		        var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
		        var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
		        return Date.UTC(year, month, date, hour, minute, second);
		    }
		});
		</script>
	</head>
	<body>

		<input type="text" name="start_time" id="start" value='${sessionScope.start_time }' style="display:none"/> 
		<input type="text" name="end_time" id="end" value='${sessionScope.end_time }' style="display:none"/>
		<img alt="" id="loading" src="${pageContext.request.contextPath }/images/loading.gif" style="display:none">
		<div id="container" style="height: 450px; min-width: 300px;" ></div>
		
	</body>
</html>