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
	
		$(function () {
			$("#search").click(function(){
			var timeStart=$.trim($("#start").val());
			var timeEnd=$.trim($("#end").val());
			if(timeStart == ''|| timeEnd == '')
			{
				alert("开始日期和结束时间不能为空，并且的跨度不能大于6个小时！");
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
				if(end-start>(6*60*60*1000)){
					alert('查询时间跨度不能超过6个小时');
				}
				/**
			     * Load new data depending on the selected min and max
			     */
			    function afterSetExtremes(e) {

			        var chart = $('#container').highcharts();
					alert(json2TimeStamp(Math.round(e.min))+"--"+json2TimeStamp(Math.round(e.max)));
			        chart.showLoading('Loading data from server...');
			        var url="${pageContext.request.contextPath }/netFlowServlet?method=getDataSet3";		
					var args={time:new Date,start_time:json2TimeStamp(Math.round(e.min)),end_time:json2TimeStamp(Math.round(e.max))};
			        $.getJSON(url, args, function (data) {

			                chart.series[0].setData(data);
			                chart.hideLoading();
			            });
			    }

			    // See source code from the JSONP handler at https://github.com/highslide-software/highcharts.com/blob/master/samples/data/from-sql.php
			    var url="${pageContext.request.contextPath }/netFlowServlet?method=getDataSet3";		
				var args={time:new Date,start_time:timeStart,end_time:timeEnd};
			    $.getJSON(url, args, function (data) {

			        // Add a null value for the end date
			        //data = [].concat(data, [[new Date(timeEnd.replace(/-/g,"/")), 0]]);
					alert(data.length);
			        // create the chart
			        $('#container').highcharts('StockChart', {
			            chart : {
			                type: 'candlestick',
			                zoomType: 'x'
			            },

			            navigator : {
			                adaptToUpdatedData: false,
			                series : {
			                    data : data
			                }
			            },

			            scrollbar: {
			                liveRedraw: false
			            },

			            title: {
			                text: 'AAPL history by the minute from 1998 to 2011'
			            },

			            subtitle: {
			                text: 'Displaying 1.7 million data points in Highcharts Stock by async server loading'
			            },

			            rangeSelector : {
			                buttons: [{
			                    type: 'second',
			                    count: 1,
			                    text: '1s'
			                }, {
			                    type: 'hour',
			                    count: 1,
			                    text: '1h'
			                }, {
			                    type: 'month',
			                    count: 1,
			                    text: '1m'
			                }, {
			                    type: 'year',
			                    count: 1,
			                    text: '1y'
			                }, {
			                    type: 'all',
			                    text: 'All'
			                }],
			                inputEnabled: false, // it supports only days
			                selected : 4 // all
			            },

			            xAxis : {
			                events : {
			                    afterSetExtremes : afterSetExtremes
			                },
			                minRange: 1000 // one minute
			            },

			            yAxis: {
			                floor: 0
			            },

			            series : [{
			                data : data,
			                dataGrouping: {
			                    enabled: false
			                }
			            }]
			        });
			        function date2UTC(datetime){
				        var year=datetime.getFullYear();
				             //月份重0开始，所以要加1，当小于10月时，为了显示2位的月份，所以补0
				        var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
				        var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
				        var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
				        var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
				        var second = datetime.getSeconds()< 10 ? "0" + (datetime.getSeconds()+1) : (datetime.getSeconds()+1);
				        return Date.UTC(year, month, date, hour, minute, second);
				    }
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
			请输入查询时间段：
			<input type="text" name="start_time" class="laydate-icon" id="start" style="width:180px; ">&nbsp;-&nbsp;
			<input type="text" name="end_time" class="laydate-icon" id="end" style="width:180px;">
			<script>
			var start = {
			    elem: '#start',
			    format: 'YYYY-MM-DD hh:mm:ss',
			    min: '2000-03-07 22:21:36', //设定最小日期为当前日期
			    max: '2000-03-08 01:33:24', //最大日期
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
			    min: '2000-03-07 22:21:37',
			    max: '2000-03-08 01:33:25',
			    istime: true,
			    istoday: false,
			    choose: function(datas){
			        
			    	start.max = datas; //结束日选好后，重置开始日的最大日期
			    }
			};
			laydate(start);
			laydate(end);
		</script>
			&nbsp;<input type="submit" id="search" value="查询"/>
			</div>
		<div id="container" style="height: 400px; min-width: 310px"></div>
	</body>
</html>
