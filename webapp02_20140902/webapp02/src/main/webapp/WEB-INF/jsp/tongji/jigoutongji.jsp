<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>机构统计</title>
	<jsp:include page="/component/common/commonhead2.jsp" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/mainFrame.css" />
	<script>
	$(function(){
		$('#tt').datagrid({  
		    title:'机构统计',  
		    url:'jgtjcx.do', 
		    columns:[[  
		        {field:'name',title:'省份',rowspan:3},  
		        {title:'行政工作部门',colspan:12},
		        {title:'参照管理机构',colspan:12},
		        {title:'事业单位',colspan:12}
		    ],[
		    		{title:'编制人数',colspan:6},
		    		{title:'实有人数',colspan:6},
		    		{title:'编制人数',colspan:6},
		    		{title:'实有人数',colspan:6},
		    		{title:'编制人数',colspan:6},
		    		{title:'实有人数',colspan:6},
		    ],[  
		        {field:'xzglbm_3_bianzhirenshu',title:'正厅局级',align:'right'},  
		        {field:'xzglbm_4_bianzhirenshu',title:'副厅局级',align:'right'},  
		        {field:'xzglbm_5_bianzhirenshu',title:'正县处级',align:'right'},  
		        {field:'xzglbm_6_bianzhirenshu',title:'副县处级',align:'right'},  
		        {field:'xzglbm_7_bianzhirenshu',title:'正乡科级',align:'right'},  
		        {field:'xzglbm_8_bianzhirenshu',title:'副乡科级',align:'right'},  
		        {field:'xzglbm_3_shiyourenshu',title:'正厅局级',align:'right'},  
		        {field:'xzglbm_4_shiyourenshu',title:'副厅局级',align:'right'},  
		        {field:'xzglbm_5_shiyourenshu',title:'正县处级',align:'right'},  
		        {field:'xzglbm_6_shiyourenshu',title:'副县处级',align:'right'},  
		        {field:'xzglbm_7_shiyourenshu',title:'正乡科级',align:'right'},  
		        {field:'xzglbm_8_shiyourenshu',title:'副乡科级',align:'right'},  
		        {field:'czgljg_3_bianzhirenshu',title:'正厅局级',align:'right'},  
		        {field:'czgljg_4_bianzhirenshu',title:'副厅局级',align:'right'},  
		        {field:'czgljg_5_bianzhirenshu',title:'正县处级',align:'right'},  
		        {field:'czgljg_6_bianzhirenshu',title:'副县处级',align:'right'},  
		        {field:'czgljg_7_bianzhirenshu',title:'正乡科级',align:'right'},  
		        {field:'czgljg_8_bianzhirenshu',title:'副乡科级',align:'right'},  
		        {field:'czgljg_3_shiyourenshu',title:'正厅局级',align:'right'},  
		        {field:'czgljg_4_shiyourenshu',title:'副厅局级',align:'right'},  
		        {field:'czgljg_5_shiyourenshu',title:'正县处级',align:'right'},  
		        {field:'czgljg_6_shiyourenshu',title:'副县处级',align:'right'},  
		        {field:'czgljg_7_shiyourenshu',title:'正乡科级',align:'right'},  
		        {field:'czgljg_8_shiyourenshu',title:'副乡科级',align:'right'},  
		        {field:'sydw_3_bianzhirenshu',title:'正厅局级',align:'right'},  
		        {field:'sydw_4_bianzhirenshu',title:'副厅局级',align:'right'},  
		        {field:'sydw_5_bianzhirenshu',title:'正县处级',align:'right'},  
		        {field:'sydw_6_bianzhirenshu',title:'副县处级',align:'right'},  
		        {field:'sydw_7_bianzhirenshu',title:'正乡科级',align:'right'},  
		        {field:'sydw_8_bianzhirenshu',title:'副乡科级',align:'right'},  
		        {field:'sydw_3_shiyourenshu',title:'正厅局级',align:'right'},  
		        {field:'sydw_4_shiyourenshu',title:'副厅局级',align:'right'},  
		        {field:'sydw_5_shiyourenshu',title:'正县处级',align:'right'},  
		        {field:'sydw_6_shiyourenshu',title:'副县处级',align:'right'},  
		        {field:'sydw_7_shiyourenshu',title:'正乡科级',align:'right'},  
		        {field:'sydw_8_shiyourenshu',title:'副乡科级',align:'right'},  
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