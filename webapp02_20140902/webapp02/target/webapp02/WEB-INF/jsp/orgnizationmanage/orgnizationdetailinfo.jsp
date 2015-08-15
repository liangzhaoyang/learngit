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
	<script type="text/javascript" src="<%=path%>/js/orgnizationmanage/orgnizationdetailinfo.js"></script>
</head>
<body>
	<form id="jigouWeihuForm" name="jigouhuForm">
		<div id="departs_add_accordion" class="easyui-accordion" >  

		 <div title="基本信息" icon="icon-save" selected="false"	style="padding: 10px;">
		<table width="98%">
			<tr>
				<td>
				<fieldset><legend>基本信息</legend>
				<div style="padding:2px;height:auto">
					<table id="tborgans" class="easyui-datagrid"></table> 
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
							<table id="tbxingzhengbumen" class="easyui-datagrid"></table> 
				
						</div>
						<div class="sublist" style="padding:2px;height:auto">
							<table id="tbguanlijigou" class="easyui-datagrid"></table> 
						</div>
						<div class="sublist" style="padding:2px;height:auto">
							<table id="tbshiyedanwei" class="easyui-datagrid"></table> 
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
					<table id="tbjichusheshi" class="easyui-datagrid"></table> 
				</div>
				</fieldset>
				</td>
			</tr>
		
		</table>
		</div>
		
		
			<div title="保密局领导帮子及工作人员基本情况" icon="icon-save" selected="false"	style="padding: 10px;">
		<table width="98%">
			<tr>
				<td>
				<fieldset><legend>保密局领导帮子及工作人员基本情况</legend>
				<div style="padding:2px;height:auto">
					<table id="tbbaomijulingdaobangzi" class="easyui-datagrid"></table> 
				</div>
				</fieldset>
				</td>
			</tr>
		
		</table>
		</div>
		
		<div title="在编人员管理" icon="icon-save" selected="false"	style="padding: 10px;">
		<table width="98%">
			<tr>
				<td>
				<fieldset><legend>当前位置：行政管理部门人员管理&gt;&gt;在编人员管理</legend>
				<div style="padding:2px;height:auto">
					<table width="98%" class="formTable">
			<tr>
				<td>
				<fieldset><legend>保密行政管理部门在编人员情况填报表及说明</legend>
                 <table class="table" width="98%">
                    <tr>
                      <th colspan="2" scope="col">&nbsp;</th>
                      <th width="78" scope="col">代码</th>
                      <th width="128" scope="col">数量</th>
                    </tr>
                    <tr>
                      <th colspan="2" scope="row">甲</th>
                      <td>乙</td>
                      <td>1</td>
                    </tr>
                    <tr>
                      <th colspan="2" scope="row">在编人员总计</th>
                      <td>101</td>
                      <td><input type="text" name="zaibianzongji" id="zaibianzongji" class="form-control" value="${zbry101}"></td>
                    </tr>
                    <tr>
                      <th height="50" colspan="2" scope="row">一、按岗位分组</th>
                      <td>一</td>
                      <td>一</td>
                    </tr>
                    <tr>
                      <th width="90" rowspan="4" scope="row">其中：</th>
                      <th width="194" scope="row">行政管理人员</th>
                      <td>102</td>
                      <td>
                        <input type="text" name="zbnum2" id="zbinput102" class="form-control" value="${zbry102}">
                      </td>
                    </tr>
                    <tr>
                      <th scope="row">专业技术人员</th>
                      <td>103</td>
                      <td>
                        <input type="text" name="zbnum3" id="zbinput103" class="form-control" value="${zbry103}">
                      </td>
                    </tr>
                    <tr>
                      <th scope="row">工勤人员</th>
                      <td>104</td>
                      <td>
                        <input type="text" name="zbnum4" id="zbinput104" class="form-control" value="${zbry104}">
                      </td>
                    </tr>
                    <tr>
                      <th scope="row">其他人员</th>
                      <td>105</td>
                      <td>
                        <input type="text" name="zbnum5" id="zbinput105" class="form-control" value="${zbry105}">
                      </td>
                    </tr>
                    <tr>
                      <th height="50" colspan="2" scope="row">二、按年龄结构分组</th>
                      <td>一</td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <th rowspan="4" scope="row">其中：</th>
                      <th scope="row">56岁以上</th>
                      <td>106</td>
                      <td>
                        <input type="text" name="zbnum6" id="zbinput106" class="form-control" value="${zbry106}">
                      </td>
                    </tr>
                    <tr>
                      <th scope="row">46-55岁</th>
                      <td>107</td>
                      <td>
                        <input type="text" name="zbnum7" id="zbinput107" class="form-control" value="${zbry107}">
                      </td>
                    </tr>
                    <tr>
                      <th scope="row">36-45岁</th>
                      <td>108</td>
                      <td>
                        <input type="text" name="zbnum8" id="zbinput108" class="form-control" value="${zbry108}">
                      </td>
                    </tr>
                    <tr>
                      <th scope="row">35岁以下</th>
                      <td>109</td>
                      <td>
                        <input type="text" name="zbnum9" id="zbinput109" class="form-control" value="${zbry109}">
                      </td>
                    </tr>
                    <tr>
                      <th height="50" colspan="2" scope="row">三、按专业分组</th>
                      <td>一</td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <th rowspan="5" scope="row">其中：</th>
                      <th scope="row">文秘</th>
                      <td>110</td>
                      <td>
                        <input type="text" name="zbnum10" id="zbinput110" class="form-control" value="${zbry110}">
                      </td>
                    </tr>
                    <tr>
                      <th scope="row">法律</th>
                      <td>111</td>
                      <td>
                        <input type="text" name="zbnum11" id="zbinput111" class="form-control" value="${zbry111}">
                      </td>
                    </tr>
                    <tr>
                      <th scope="row">管理</th>
                      <td>112</td>
                      <td>
                        <input type="text" name="zbnum12" id="zbinput112" class="form-control" value="${zbry112}">
                      </td>
                    </tr>
                    <tr>
                      <th scope="row">计算机</th>
                      <td>113</td>
                      <td>
                        <input type="text" name="zbnum13" id="zbinput113" class="form-control" value="${zbry113}">
                      </td>
                    </tr>
                    <tr>
                      <th scope="row">其他</th>
                      <td>114</td>
                      <td>
                        <input type="text" name="zbnum14" id="zbinput114" class="form-control" value="${zbry114}">
                      </td>
                    </tr>
                    <tr>
                      <th height="50" colspan="2" scope="row">四、按学历分组</th>
                      <td>一</td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <th rowspan="4" scope="row">其中：</th>
                      <th scope="row">博士研究生</th>
                      <td>115</td>
                      <td>
                        <input type="text" name="zbnum15" id="zbinput115" class="form-control" value="${zbry115}">
                      </td>
                    </tr>
                    <tr>
                      <th scope="row">硕士研究生</th>
                      <td>116</td>
                      <td>
                        <input type="text" name="zbnum16" id="zbinput116" class="form-control" value="${zbry116}">
                      </td>
                    </tr>
                    <tr>
                      <th height="25" scope="row">大学本科</th>
                      <td>117</td>
                      <td>
                        <input type="text" name="zbnum17" id="zbinput117" class="form-control" value="${zbry117}">
                      </td>
                    </tr>
                    <tr>
                      <th scope="row">大专以下</th>
                      <td>118</td>
                      <td>
                        <input type="text" name="zbnum18" id="zbinput118" class="form-control" value="${zbry118}">
                      </td>
                    </tr>
                  </table>
				</fieldset>
				</td>
			</tr>
		</table>	
				</div>
				</fieldset>
				</td>
			</tr>
		
		</table>
		</div>
		
		<div title="领导成员及工作人员管理" icon="icon-save" selected="false"	style="padding: 10px;">
		<table width="98%">
			<tr>
				<td>
				<fieldset><legend>当前位置：领导成员及工作人员管理&gt;&gt;领导成员及工作人员管理</legend>
				<div style="padding:2px;height:auto">
					<table width="98%" class="formTable">
			<tr>
				<td>
				<fieldset><legend>保密行政管理部门人员编制情况填报表及说明</legend>
                  <table class="table" width="98%">
                    <tr>
                      <th colspan="2" scope="col">&nbsp;</th>
                      <th width="78" scope="col">代码</th>
                      <th width="128" scope="col">数量</th>
                    </tr>
                    <tr>
                      <th colspan="2" scope="row">甲</th>
                      <td>乙</td>
                      <td>1</td>
                    </tr>
                    <tr>
                      <th height="57" colspan="2" scope="row">一、编制基本情况</th>
                      <td>一</td>
                      <td>一</td>
                    </tr>
                    <tr>
                      <th width="90" rowspan="4" scope="row">其中：</th>
                      <th width="194" scope="row">公务员</th>
                      <td>101</td>
                      <td>
                        <input type="text" name="num1" id="input101" class="form-control" value="${rybz101}">
                      </td>
                    </tr>
                    <tr>
                      <th scope="row">参照管理事业编制</th>
                      <td>102</td>
                      <td>
                        <input type="text" name="num2" id="input102" class="form-control" value="${rybz102}">

                      </td>
                    </tr>
                    <tr>
                      <th scope="row">全额拨款事业编制</th>
                      <td>103</td>
                      <td>
                        <input type="text" name="num3" id="input103" class="form-control" value="${rybz103}">
                      </td>
                    </tr>
                    <tr>
                      <th scope="row">自收自支事业编制</th>
                      <td>104</td>
                      <td>
                        <input type="text" name="num4" id="input104" class="form-control" value="${rybz104}">
                      </td>
                    </tr>
                    <tr>
                      <th height="62" colspan="2" scope="row">二、领导职数</th>
                      <td>105</td>
                      <td><input type="text" name="num5" id="input105" class="form-control" value="${rybz105}"></td>
                    </tr>
                    <tr>
                      <th rowspan="5" scope="row">其中：</th>
                      <th scope="row">局长</th>
                      <td>106</td>
                      <td>
                        <input type="text" name="num6" id="input106" class="form-control" value="${rybz106}">
                      </td>
                    </tr>
                    <tr>
                      <th scope="row">副局长</th>
                      <td>107</td>
                      <td>
                        <input type="text" name="num7" id="input107" class="form-control" value="${rybz107}">
                      </td>
                    </tr>
                    <tr>
                      <th scope="row">巡视员</th>
                      <td>108</td>
                      <td>
                        <input type="text" name="num8" id="input108" class="form-control" value="${rybz108}">
                      </td>
                    </tr>
                    <tr>
                      <th scope="row">副巡视员</th>
                      <td>109</td>
                      <td>
                        <input type="text" name="num9" id="input109" class="form-control" value="${rybz109}">
                      </td>
                    </tr>
                    <tr>
                      <th scope="row">处室领导职数</th>
                      <td>110</td>
                      <td>
                        <input type="text" name="num10" id="input110" class="form-control" value="${rybz110}">
                      </td>
                    </tr>
                    <tr>
                      <th height="63" colspan="2" scope="row">三、聘用人员</th>
                      <td>111</td>
                      <td>  <input type="text" name="num11" id="input111" class="form-control" value="${rybz111}"></td>
                    </tr>
                    <tr>
                      <th rowspan="4" scope="row">其中：</th>
                      <th scope="row">领导岗位任职人员</th>
                      <td>112</td>
                      <td>
                        <input type="text" name="num12" id="input112" class="form-control" value="${rybz112}">
                      </td>
                    </tr>
                    <tr>
                      <th scope="row">专业技术人员</th>
                      <td>113</td>
                      <td>
                        <input type="text" name="num13" id="input113" class="form-control" value="${rybz113}">
                      </td>
                    </tr>
                    <tr>
                      <th scope="row">工勤人员</th>
                      <td>114</td>
                      <td>
                        <input type="text" name="num14" id="input114" class="form-control" value="${rybz114}">
                      </td>
                    </tr>
                    <tr>
                      <th scope="row">其他人员</th>
                      <td>115</td>
                      <td>
                        <input type="text" name="num15" id="input115" class="form-control" value="${rybz115}">
                      </td>
                    </tr>
                  </table>
				</fieldset>
				</td>
			</tr>
		</table>
				</div>
				</fieldset>
				</td>
			</tr>
		
		</table>
		</div>
		
		
		<div title="保密干部基础情况" icon="icon-save" selected="false"	style="padding: 10px;">
		<table width="98%">
			<tr>
				<td>
				<fieldset><legend>保密干部基本情况</legend>
				<div style="padding:2px;height:auto">
					<table id="tbbaomiganbu" class="easyui-datagrid"></table> 
				</div>
				</fieldset>
				</td>
			</tr>
		
		</table>
		</div>
		
		<div title="涉密人员基本情况" icon="icon-save" selected="false"	style="padding: 10px;">
		<table width="98%">
			<tr>
				<td>
				<fieldset><legend>涉密人员基本情况</legend>
				<div style="padding:2px;height:auto">
					<table id="tbshemirenyuan" class="easyui-datagrid"></table> 
				</div>
				</fieldset>
				</td>
			</tr>
		
		</table>
		</div>
				
		</div>
	
		<div style="padding:2px;height:auto" id=testform>
			<label  class="laber-info">审核人:</label><input type="text" id="shenheren" name="shenheren" readonly/>
			<label  class="laber-info">填表人:</label><input type="text" id="tianbiaoren" name="tianbiaoren" readonly/>
			<label  class="laber-info">填表日期:</label><input type="text" id="tianbiaoriqi" name="tianbiaoriqi" readonly/>
		</div>	
	</form>
</body>
</html>