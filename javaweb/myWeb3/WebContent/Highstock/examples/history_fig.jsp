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
		</style>
		<script src="${pageContext.request.contextPath }/js/laydate/laydate.js"></script>
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
				var url="${pageContext.request.contextPath }/netFlowServlet?method=getDataSet2";		
				var args={time:new Date(),start_time:timeStart,end_time:timeEnd};
				$.getJSON(url,args,function(data){

		        // Create a timer
		        var start = +new Date();

		        // Create the chart
		        $('#container').highcharts('StockChart', {
		            chart: {
		                events: {
		                    load: function () {
		                        if (!window.isComparing) {
		                            this.setTitle(null, {
		                                text: 'Built chart in ' + (new Date() - start) + 'ms'
		                            });
		                        }
		                    }
		                },
		                zoomType: 'x'
		            },
		            
		            rangeSelector: {
						buttons: [{
							count: 1,
							type: 'minute',
							text: '1M'
						}, {
							count: 10,
							type: 'minute',
							text: '10M'
						}, {
							type: 'all',
							text: 'All'
						}],
						inputEnabled: false,
						selected: 0
					},
		            yAxis: {
		                title: {
		                    text: 'The size of netflow(bps)'
		                }
		            },

		            title: {
		                text: 'Network flow chart'
		            },

		            subtitle: {
		                text: 'Built chart in ...' // dummy text to reserve space for dynamic subtitle
		            },

		            series: [{
		                name: 'Netflow',
		                data: data,
		                //pointStart: Date.UTC(2000, 3, 7, 22, 24, 39),
		                pointStart: date2UTC(new Date(timeStart.replace(/-/g,"/"))),
		                pointInterval: 1000,
		                tooltip: {
		                    valueDecimals: 1,
		                    valueSuffix: 'bps'
		                }
		            }],
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
