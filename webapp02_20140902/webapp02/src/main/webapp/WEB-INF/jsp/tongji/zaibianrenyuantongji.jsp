<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>在编人员统计</title>
	<jsp:include page="/component/common/commonhead2.jsp" />
	<script>
	$(function(){
		$('#tt').datagrid({  
		    title:'在编人员统计',  
		    url:'zbrytjcx.do',  
		    autoRowHeight: true,
		    columns:[[  
				        {field:'name',title:'省份',rowspan:2,sortable:true},  
				        {title:'按岗位分组',colspan:4},
				        {title:'按年龄结构分组',colspan:4},
				        {title:'按专业分组',colspan:5},
				        {title:'按学历分组',colspan:4}
				    ],[  
				        {field:'Col_102',title:'行政管理人员',align:'right'},  
				        {field:'Col_103',title:'专业技术人员',align:'right'},  
				        {field:'Col_104',title:'工勤人员',align:'right'},  
				        {field:'Col_105',title:'其他人员',align:'right'},  
				        {field:'Col_106',title:'56岁以上',align:'right'},  
				        {field:'Col_107',title:'46-55岁',align:'right'},  
				        {field:'Col_108',title:'36-45岁',align:'right'},  
				        {field:'Col_109',title:'35岁以下',align:'right'},  
				        {field:'Col_110',title:'文秘',align:'right'},  
				        {field:'Col_111',title:'法律',align:'right'},  
				        {field:'Col_112',title:'管理',align:'right'},  
				        {field:'Col_113',title:'计算机',align:'right'},  
				        {field:'Col_114',title:'其他',align:'right'},  
				        {field:'Col_115',title:'博士研究生',align:'right'},  
				        {field:'Col_116',title:'硕士研究生',align:'right'},  
				        {field:'Col_117',title:'大学本科',align:'right'},  
				        {field:'Col_118',title:'大专以下',align:'right'},  
		    ]],  
		    rownumbers:false
		}); 
	});
	</script>
</head>
	<body id="bodydiv" class="easyui-layout">
	    <div data-options="region:'center'" style="padding: 5px; border: 0">
	    	<table id="tt" data-options="toolbar:'#tb'"></table>
		</div>
	</body>
</html>