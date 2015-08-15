<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<% String path = request.getContextPath(); %>
<html>
<head>
	<title>添加部门与机构</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />	
	<jsp:include page="/component/common/commonhead.jsp" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/departmentmanage/departmanage.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/departmentmanage/lanrenzhijia.css">
	<script type="text/javascript">	var path="<%=path%>"; 	</script>
	<script type="text/javascript" src="<%=path%>/js/departmentmanage/jquery.1.4.2-min.js.js"></script>
	<script type="text/javascript" src="<%=path%>/js/departmentmanage/jquery.tabso_yeso.js"></script>
	<script type="text/javascript" src="<%=path%>/js/orgnizationmanage/orgnizationauditdetail.js"></script>
	<script type="text/javascript">

	$(function(){
		/**表格**/
		$('#tborgans1').datagrid({
			url:"JgcxInfo.do", 
			singleSelect:false,  
			striped:true,
			loadMsg:"正在加载数据，请稍候...",
			rownumbers:true,
			//sortName: 'create_time',
			//sortOrder: 'desc',
			//pagination:true,
			checkOnSelect:true,
			columns:[[
				{field:'orgname',title:'单位名称(全称)',width:120,align:'center',
				},
				{field:'suorgid',title:'主管单位',width:120,align:'center',
				},
				{field:'chenglishijian',title:'成立时间',width:120,align:'center',
				},
				{field:'xingzhengjibie',title:'行政级别',width:120,align:'center',sortable:true,
				},
				{field:'jingfeilaiyuan',title:'经费来源',width:120,align:'center'},
				{field:'zhengfuxulie',title:'是否政府序列',width:120,align:'center'},
				{field:'jigouleibie',title:'机构类别',width:120,align:'center'}
			]]
		});
		//
		$('#tbxingzhengbumen1').datagrid({
			url:"jgcxxzbm.do", 
			singleSelect:false,  
			striped:true,
			loadMsg:"正在加载数据，请稍候...",
			rownumbers:true,
			//sortName: 'create_time',
			//sortOrder: 'desc',
			//pagination:true,
			checkOnSelect:true,
			//pageList:[1,2,3,4], //设置分页显示的每页显示条数
			//toolbar:toolbarlist,
//			frozenColumns:[[
//				{field:'ck',checkbox:true}
//			]],
			columns:[[
				{field:'xzname',title:'部门名称',width:80,align:'center',
				},
				{field:'xzbianzhirenshu',title:'编制人数',width:80,align:'center',
				},
				{field:'xzshijirenshu',title:'实际人数',width:80,align:'center',
				},
				{field:'xzxingzhengjibie',title:'行政级别',width:80,align:'center',sortable:true,
				}
			]]
		});
		//
		$('#tbguanlijigou1').datagrid({
			url:"jgcxgljg.do", 
			singleSelect:false,  
			striped:true,
			loadMsg:"正在加载数据，请稍候...",
			rownumbers:true,
			//sortName: 'create_time',
			//sortOrder: 'desc',
			//pagination:true,
			checkOnSelect:true,
			//pageList:[1,2,3,4], //设置分页显示的每页显示条数
			//toolbar:toolbarlist,
//			frozenColumns:[[
//				{field:'ck',checkbox:true}
//			]],
			columns:[[
				{field:'glname',title:'部门名称',width:80,align:'center',
				},
				{field:'glbianzhirenshu',title:'编制人数',width:80,align:'center',
				},
				{field:'glshijirenshu',title:'实际人数',width:80,align:'center',
				},
				{field:'glxingzhengjibie',title:'行政级别',width:80,align:'center',sortable:true,
				}
			]]
		});
		//
		$('#tbshiyedanwei1').datagrid({
			url:"jgcxsybm.do", 
			singleSelect:false,  
			striped:true,
			loadMsg:"正在加载数据，请稍候...",
			rownumbers:true,
			//sortName: 'create_time',
			//sortOrder: 'desc',
			//pagination:true,
			checkOnSelect:true,
			//pageList:[1,2,3,4], //设置分页显示的每页显示条数
			//toolbar:toolbarlist,
//			frozenColumns:[[
//				{field:'ck',checkbox:true}
//			]],
			columns:[[
				{field:'syname',title:'部门名称',width:80,align:'center',
				},
				{field:'sybianzhirenshu',title:'编制人数',width:80,align:'center',
				},
				{field:'syshijirenshu',title:'实际人数',width:80,align:'center',
				},
				{field:'syxingzhengjibie',title:'行政级别',width:80,align:'center',sortable:true,
				}
			]]
		});
		//
		$('#tbjichusheshi1').datagrid({
			url:"jgcxjcss.do", 
			singleSelect:false,  
			striped:true,
			loadMsg:"正在加载数据，请稍候...",
			rownumbers:true,
			//sortName: 'create_time',
			//sortOrder: 'desc',
			//pagination:true,
			checkOnSelect:true,
			//pageList:[1,2,3,4], //设置分页显示的每页显示条数
			//toolbar:toolbarlist,
//			frozenColumns:[[
//				{field:'ck',checkbox:true}
//			]],
			columns:[[
				{field:'jcid',title:'序号',width:120,align:'center',
				},
				{field:'jcname',title:'单位名称',width:120,align:'center',
				},
				{field:'jcleibie',title:'基础设施类别',width:120,align:'center',
				},
				{field:'jcmianji',title:'占地面积(平方米)',width:120,align:'center',
				},
				{field:'jctouruzijin',title:'投入资金(万元)',width:120,align:'center',sortable:true,
				},
				{field:'jcjianshedanwei',title:'建设单位',width:120,align:'center'}
			]]
		});
	}); 
	</script>
</head>
<body>
	<form id="jgshForm" action="doJigouShenhe.do" method="post">
		<div id="departs_add_accordion" class="easyui-accordion" >  

		 <div title="基本信息" icon="icon-save" selected="false"	style="padding: 10px;">
		<table width="98%">
			<tr>
				<td>
				<fieldset><legend>基本信息</legend>
				<div style="padding:2px;height:auto">
					<table id="tborgans1" class="easyui-datagrid"></table> 
				</div>
				</fieldset>
				</td>
			</tr>
		
		</table>
		</div>
		
	
		<div title="机构设置情况" icon="icon-save" selected="false"	style="padding: 10px;">
		<table width="98%">
			<tr>
				<td>
				<fieldset><legend>机构设置情况</legend>
				<ul class="tabbtn" id="move-animate-left" width="98%">
					<li class="current"><span>行政工作部门设置情况</span></li>
					<li><span>参照管理机构设置情况</span></li>
					<li><span>事业单位设置情况</span></li>
				</ul>
				<div class="tabcon" id="leftcon">
					<div class="subbox">
						<div class="sublist" style="padding:2px;height:auto">
							<table id="tbxingzhengbumen1" class="easyui-datagrid"></table> 
				
						</div>
						<div class="sublist" style="padding:2px;height:auto">
							<table id="tbguanlijigou1" class="easyui-datagrid"></table> 
						</div>
						<div class="sublist" style="padding:2px;height:auto">
							<table id="tbshiyedanwei1" class="easyui-datagrid"></table> 
						</div>
					</div><!--tabcon end-->
				</div>
				</fieldset>
				</td>
			</tr>
		</table>
		</div>
		
		<div title="保密基础设施建设" icon="icon-save" selected="false"	style="padding: 10px;">
		<table width="98%">
			<tr>
				<td>
				<fieldset><legend>保密基础设施建设</legend>
				<div style="padding:2px;height:auto">
					<table id="tbjichusheshi1" class="easyui-datagrid"></table> 
				</div>
				</fieldset>
				</td>
			</tr>
		
		</table>
		</div>
				
		</div>
			
			<table><tr>
			<td><label  class="laber-info">审核人:</label><input type="text" id="shenheren" name="shenheren" /></td>
			<td>审核状态：</td>
			<td>
					<select id="status" name="status" class="select">
					<option value="2">审核通过</option>
					<option value="3">审核不通过</option>
					</select>
			</td>
			<td><label  class="laber-info">填表人:</label><input type="text" id="tianbiaoren" name="tianbiaoren" readonly/></td>
			<td><label  class="laber-info">填表日期:</label><input type="text" id="tianbiaoriqi" name="tianbiaoriqi" readonly/></td>
			<td><a href="javascript:void(0);" class="easyui-linkbutton"
				onclick="submitForm()">审 核</a> &nbsp;&nbsp; </td>
			</tr>
			</table>
	</form>
	
</body> 
</html>