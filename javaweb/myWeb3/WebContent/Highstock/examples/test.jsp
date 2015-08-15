<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
		<script type="text/javascript" src="../jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="../js/highstock.js"></script>
		<script type="text/javascript" src="../js/modules/exporting.js"></script>
		<script type="text/javascript" src="../js/themes/grid.js"></script>
		<style type="text/css">
			${demo.css}
		</style>
		<style>
			body{
			margin:0px;
			padding:0px;
			text-align:center;}
		</style>
		<script type="text/javascript">
		$(function () {

		    Highcharts.setOptions({
		        global : {
		            useUTC : false
		        }
		    });

		    // Create the chart
		    $('#container').highcharts('StockChart', {
		        chart : {
		            events : {
		                load : function () {

		                    // set up the updating of the chart each second
		                    var series = this.series[0];
		                    setInterval(function () {
		                    	var url="${pageContext.request.contextPath }/netFlowServlet?method=getData";
		            			var args={"time":new Date()};
		            			$.getJSON(url,args,function(data){
		            				for(var i=0;i<data.length;i++){
		            					series.addPoint([data[0], data[1]], true, true);
		            				}
		            			});
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
		            name : 'Random data',//鼠标移到趋势线上时显示的属性名 
		            data : (function () { //属性值
		                // generate an array of random data
		                var data = [], time = (new Date()).getTime(), i;

		                for (i = -999; i <= 0; i += 1) {
		                    data.push([
		                        time + i * 1000,
		                        Math.round(Math.random() * 100)
		                    ]);
		                }
		                return data;
		            }())
		        }]
		    });

		});
		</script>
</head>
<body>
<center>
<div id="container" style="height: 450px; min-width: 300px;max-width:900px;"></div>
</center>
</body>
</html>