<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>审核部门与机构</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/component/common/commonhead.jsp" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/departmentmanage/departmanage.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/departmentmanage/lanrenzhijia.css">
<script type="text/javascript">	var path="<%=path%>";
</script>
<script type="text/javascript"
	src="<%=path%>/js/departmentmanage/jquery.1.4.2-min.js.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/departmentmanage/jquery.tabso_yeso.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/orgnizationmanage/orgnizationauditdetail.js"></script>

</head>
<body>
	<form id="jgshForm" name="jgshForm" action="doJigouShenhe.do"
		method="post">
		<div id="departs_add_accordion" class="easyui-accordion">

			<div title="基本信息" icon="icon-save" selected="false"
				style="padding: 10px;">
				<table width="98%">
					<tr>
						<td>
							<fieldset>
								<div style="padding: 2px; height: auto">
									<table>
										<tr>
											<td><a href="jgcxbmxzdc.do" class="easyui-linkbutton"
												iconCls="icon-save" onclick="">导出</a></td>
										</tr>
									</table>
									<table id="tborgans" class="easyui-datagrid"></table>
								</div>
							</fieldset>
						</td>
					</tr>

				</table>
			</div>


			<div title="机构设置情况" icon="icon-save" selected="false"
				style="padding: 10px;">
				<table width="98%">
					<tr>
						<td>
							<fieldset>
								<ul class="tabbtn" id="move-animate-left" STYLE="width: 98%">
									<li class="current"><span>行政工作部门设置情况</span></li>
									<li><span>参照管理机构设置情况</span></li>
									<li><span>事业单位设置情况</span></li>
								</ul>
								<div class="tabcon" id="leftcon">
									<div class="subbox">
										<div class="sublist"
											style="overflow: auto; height: 200px; padding: 2px;">
											<table id="tbxingzhengbumen" class="easyui-datagrid"></table>

										</div>
										<div class="sublist"
											style="overflow: auto; height: 200px; padding: 2px;">
											<table id="tbguanlijigou" class="easyui-datagrid"></table>
										</div>
										<div class="sublist"
											style="overflow: auto; height: 200px; padding: 2px;">
											<table id="tbshiyedanwei" class="easyui-datagrid"></table>
										</div>
									</div>
									<!--tabcon end-->
								</div>
							</fieldset>
						</td>
					</tr>
				</table>
			</div>

			<div title="保密基础设施建设" icon="icon-save" selected="false"
				style="padding: 10px;">
				<table width="98%">
					<tr>
						<td>
							<fieldset>
								<div style="overflow: auto; height: 200px; padding: 2px;">
									<table id="tbjichusheshi" class="easyui-datagrid"></table>
								</div>
							</fieldset>
						</td>
					</tr>

				</table>
			</div>


			<div title="保密委员会办公室、保密局领导班子成员及工作人员基本情况" icon="icon-save"
				selected="false" style="padding: 10px; overflow-x: auto">
				<table width="98%">
					<tr>
						<td>
							<fieldset>
								<div style="padding: 2px; height: auto">
									<table>
										<tr>
											<td><a href="jgcxldbzdc.do" class="easyui-linkbutton"
												iconCls="icon-save" onclick="">导出</a></td>
										</tr>
									</table>
									<table id="tbbaomijulingdaobanzi" class="easyui-datagrid"></table>
								</div>
							</fieldset>
						</td>
					</tr>

				</table>
			</div>



			<div title="保密行政管理部门人员编制情况" icon="icon-save" selected="false"
				style="padding: 10px;">
				<table width="98%">
					<tr>
						<td>
							<fieldset>
								<div style="padding: 2px; height: auto">
									<table>
										<tr>
											<td><a href="jgcxrybzdc.do" class="easyui-linkbutton"
												iconCls="icon-save" onclick="">导出</a></td>
										</tr>
									</table>
									<table width="98%" class="formTable">
										<tr>
											<td>
												<fieldset>
													<table class="table">
														<tr>
															<th colspan="3" scope="col" style="width: 300px">&nbsp;</th>
															<th scope="col" style="width: 40px">代码</th>
															<th scope="col" style="width: 60px">数量</th>
														</tr>
														<tr>
															<th colspan="3" scope="row">甲</th>
															<th>乙</th>
															<td>1</td>
														</tr>
														<tr>
															<td scope="row" colspan="3" style="text-align: left"><b>一、编制基本情况</b></td>
															<td>一</td>
															<td>一</td>
														</tr>
														<tr>
															<td width="90" rowspan="4" colspan="2"
																style="text-align: right" scope="row"><b>其中：</b></td>
															<td scope="row" style="width: 200px; text-align: left"><b>公务员</b></td>
															<td>101</td>
															<td><input type="text" name="num1" id="input101"
																class="form-control" value="${rybz101}"></td>
														</tr>
														<tr>
															<td scope="row" style="text-align: left"><b>参照管理事业编制</b></td>
															<td>102</td>
															<td><input type="text" name="num2" id="input102"
																class="form-control" value="${rybz102}"></td>
														</tr>
														<tr>
															<td scope="row" style="text-align: left"><b>全额拨款事业编制</b></td>
															<td>103</td>
															<td><input type="text" name="num3" id="input103"
																class="form-control" value="${rybz103}"></td>
														</tr>
														<tr>
															<td scope="row" style="text-align: left"><b>自收自支事业编制</b></td>
															<td>104</td>
															<td><input type="text" name="num4" id="input104"
																class="form-control" value="${rybz104}"></td>
														</tr>
														<tr>
															<td scope="row" colspan="3" style="text-align: left"><b>二、领导职数</b></td>
															<td>105</td>
															<td><input type="text" name="num5" id="input105"
																class="form-control" value="${rybz105}"></td>
														</tr>
														<tr>
															<td width="90" rowspan="5" colspan="2"
																style="text-align: right" scope="row"><b>其中：</b></td>
															<td scope="row" style="text-align: left"><b>局长</b></td>
															<td>106</td>
															<td><input type="text" name="num6" id="input106"
																class="form-control" value="${rybz106}"></td>
														</tr>
														<tr>
															<td scope="row" style="text-align: left"><b>副局长</b></td>
															<td>107</td>
															<td><input type="text" name="num7" id="input107"
																class="form-control" value="${rybz107}"></td>
														</tr>
														<tr>
															<td scope="row" style="text-align: left"><b>巡视员</b></td>
															<td>108</td>
															<td><input type="text" name="num8" id="input108"
																class="form-control" value="${rybz108}"></td>
														</tr>
														<tr>
															<td scope="row" style="text-align: left"><b>副巡视员</b></td>
															<td>109</td>
															<td><input type="text" name="num9" id="input109"
																class="form-control" value="${rybz109}"></td>
														</tr>
														<tr>
															<td scope="row" style="text-align: left"><b>处室领导职数</b></td>
															<td>110</td>
															<td><input type="text" name="num10" id="input110"
																class="form-control" value="${rybz110}"></td>
														</tr>
														<tr>
															<td scope="row" colspan="3" style="text-align: left"><b>三、聘用人员</b></td>
															<td>111</td>
															<td><input type="text" name="num11" id="input111"
																class="form-control" value="${rybz111}"></td>
														</tr>
														<tr>
															<td width="90" rowspan="4" colspan="2"
																style="text-align: right" scope="row"><b>其中：</b></td>
															<td scope="row" style="text-align: left"><b>领导岗位任职人员</b></td>
															<td>112</td>
															<td><input type="text" name="num12" id="input112"
																class="form-control" value="${rybz112}"></td>
														</tr>
														<tr>
															<td scope="row" style="text-align: left"><b>专业技术人员</b></td>
															<td>113</td>
															<td><input type="text" name="num13" id="input113"
																class="form-control" value="${rybz113}"></td>
														</tr>
														<tr>
															<td scope="row" style="text-align: left"><b>工勤人员</b></td>
															<td>114</td>
															<td><input type="text" name="num14" id="input114"
																class="form-control" value="${rybz114}"></td>
														</tr>
														<tr>
															<td scope="row" style="text-align: left"><b>其他人员</b></td>
															<td>115</td>
															<td><input type="text" name="num15" id="input115"
																class="form-control" value="${rybz115}"></td>
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

			<div title="保密行政管理部门在编人员情况" icon="icon-save" selected="false"
				style="padding: 10px;">
				<table width="98%">
					<tr>
						<td>
							<fieldset>
								<div style="padding: 2px; height: auto">
									<table width="98%" class="formTable">
										<tr>
											<td>
												<fieldset>
													<table>
														<tr>
															<td><a href="jgcxzbrydc.do"
																class="easyui-linkbutton" iconCls="icon-save" onclick="">导出</a></td>
														</tr>
													</table>
													<table class="table">
														<tr>
															<th colspan="3" scope="col" style="width: 300px">&nbsp;</th>
															<th scope="col" style="width: 40px">代码</th>
															<th scope="col" style="width: 60px">数量</th>
														</tr>
														<tr>
															<th colspan="3" scope="row">甲</th>
															<th>乙</th>
															<td>1</td>
														</tr>
														<tr>
															<td scope="row" colspan="3" style="text-align: left"><b>在编人员总计</b></td>
															<td>101</td>
															<td><input type="text" name="zbnum1" id="zbinput101"
																class="form-control" value="${zbry101}"></td>
														</tr>
														<tr>
															<td scope="row" colspan="3" style="text-align: left"><b>一、按岗位分组</b></td>
															<td>一</td>
															<td>一</td>
														</tr>
														<tr>
															<td width="90" rowspan="4" colspan="2"
																style="text-align: right" scope="row"><b>其中：</b></td>
															<td scope="row" style="width: 200px; text-align: left"><b>行政管理人员</b></td>
															<td>102</td>
															<td><input type="text" name="zbnum2" id="zbinput102"
																class="form-control" value="${zbry102}"></td>
														</tr>
														<tr>
															<td scope="row" style="width: 200px; text-align: left"><b>专业技术人员</b></td>
															<td>103</td>
															<td><input type="text" name="zbnum3" id="zbinput103"
																class="form-control" value="${zbry103}"></td>
														</tr>
														<tr>
															<td scope="row" style="width: 200px; text-align: left"><b>工勤人员</b></td>
															<td>104</td>
															<td><input type="text" name="zbnum4" id="zbinput104"
																class="form-control" value="${zbry104}"></td>
														</tr>
														<tr>
															<td scope="row" style="width: 200px; text-align: left"><b>其他人员</b></td>
															<td>105</td>
															<td><input type="text" name="zbnum5" id="zbinput105"
																class="form-control" value="${zbry105}"></td>
														</tr>
														<tr>
															<td scope="row" colspan="3" style="text-align: left"><b>二、按年龄结构分组</b></td>
															<td>一</td>
															<td>&nbsp;</td>
														</tr>
														<tr>
															<td width="90" rowspan="4" colspan="2"
																style="text-align: right" scope="row"><b>其中：</b></td>
															<td scope="row" style="width: 200px; text-align: left"><b>56岁以上</b></td>
															<td>106</td>
															<td><input type="text" name="zbnum6" id="zbinput106"
																class="form-control" value="${zbry106}"></td>
														</tr>
														<tr>
															<td scope="row" style="width: 200px; text-align: left"><b>46-55岁</b></td>
															<td>107</td>
															<td><input type="text" name="zbnum7" id="zbinput107"
																class="form-control" value="${zbry107}"></td>
														</tr>
														<tr>
															<td scope="row" style="width: 200px; text-align: left"><b>36-45岁</b></td>
															<td>108</td>
															<td><input type="text" name="num8" id="input108"
																class="form-control" value="${zbry108}"></td>
														</tr>
														<tr>
															<td scope="row" style="width: 200px; text-align: left"><b>35岁以下</b></td>
															<td>109</td>
															<td><input type="text" name="num9" id="input109"
																class="form-control" value="${zbry109}"></td>
														</tr>
														<tr>
															<td scope="row" colspan="3" style="text-align: left"><b>三、按专业分组</b></td>
															<td>一</td>
															<td>&nbsp;</td>
														</tr>
														<tr>
															<td width="90" rowspan="5" colspan="2"
																style="text-align: right" scope="row"><b>其中：</b></td>
															<td scope="row" style="width: 200px; text-align: left"><b>文秘</b></td>
															<td>110</td>
															<td><input type="text" name="num10" id="input110"
																class="form-control" value="${zbry110}"></td>
														</tr>
														<tr>
															<td scope="row" style="width: 200px; text-align: left"><b>法律</b></td>
															<td>111</td>
															<td><input type="text" name="zbnum11"
																id="zbinput111" class="form-control" value="${zbry111}"></td>
														</tr>
														<tr>
															<td scope="row" style="width: 200px; text-align: left"><b>管理</b></td>
															<td>112</td>
															<td><input type="text" name="zbnum12"
																id="zbinput112" class="form-control" value="${zbry112}"></td>
														</tr>
														<tr>
															<td scope="row" style="width: 200px; text-align: left"><b>计算机</b></td>
															<td>113</td>
															<td><input type="text" name="zbnum13"
																id="zbinput113" class="form-control" value="${zbry113}"></td>
														</tr>
														<tr>
															<td scope="row" style="width: 200px; text-align: left"><b>其他</b></td>
															<td>114</td>
															<td><input type="text" name="zbnum14"
																id="zbinput114" class="form-control" value="${zbry114}"></td>
														</tr>
														<tr>
															<td scope="row" colspan="3" style="text-align: left"><b>四、按学历分组</b></td>
															<td>一</td>
															<td>&nbsp;</td>
														</tr>
														<tr>
															<td width="90" rowspan="4" colspan="2"
																style="text-align: right" scope="row"><b>其中：</b></td>
															<td scope="row" style="width: 200px; text-align: left"><b>博士研究生</b></td>
															<td>115</td>
															<td><input type="text" name="zbnum15"
																id="zbinput115" class="form-control" value="${zbry115}"></td>
														</tr>
														<tr>
															<td scope="row" style="width: 200px; text-align: left"><b>硕士研究生</b></td>
															<td>116</td>
															<td><input type="text" name="zbnum16"
																id="zbinput116" class="form-control" value="${zbry116}"></td>
														</tr>
														<tr>
															<td scope="row" style="width: 200px; text-align: left"><b>大学本科</b></td>
															<td>117</td>
															<td><input type="text" name="zbnum17"
																id="zbinput117" class="form-control" value="${zbry117}"></td>
														</tr>
														<tr>
															<td scope="row" style="width: 200px; text-align: left"><b>大专以下</b></td>
															<td>118</td>
															<td><input type="text" name="zbnum18"
																id="zbinput118" class="form-control" value="${zbry118}"></td>
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

			<div title="保密工作机构保密干部基本情况" icon="icon-save" selected="false"
				style="padding: 10px;">
				<table width="98%">
					<tr>
						<td>
							<fieldset>
								<div style="padding: 2px; height: auto">
									<table>
										<tr>
											<td><a href="jgcxbmgbdc.do" class="easyui-linkbutton"
												iconCls="icon-save" onclick="">导出</a></td>
										</tr>
									</table>
									<table>
										<table>
											<tr>

												<td>机构类别：<input type="text" name="jigouleibie"
													id="jigouleibie" value="${jigouleibie}" />&nbsp &nbsp
													&nbsp
												</td>
												<td>行政级别：<input type="text" name="xingzhengjibie"
													id="xingzhengjibie" value="${xingzhengjibie}" />&nbsp
													&nbsp &nbsp
												</td>
												<td>编制人数：<input type="text" name="bianzhirenshu"
													id="bianzhirenshu" value="${bianzhirenshu}" />&nbsp &nbsp
													&nbsp
												</td>
											</tr>
										</table>
										<table id="tbbaomiganbu" class="easyui-datagrid"></table>
								</div>
							</fieldset>
						</td>
					</tr>

				</table>
			</div>

			<div title="涉密人员基本情况" icon="icon-save" selected="false"
				style="padding: 10px;">
				<table width="98%">
					<tr>
						<td>
							<fieldset>
								<div style="padding: 2px; height: auto">
									<table>
										<tr>
											<td><a href="jgcxsmrydc.do" class="easyui-linkbutton"
												iconCls="icon-save" onclick="">导出</a></td>
										</tr>
									</table>
									<table id="tbshemirenyuan" class="easyui-datagrid"></table>
								</div>
							</fieldset>
						</td>
					</tr>

				</table>
			</div>
			<div title="简历" icon="icon-save" selected="false"
				style="padding: 10px;">
				<table width="98%">
					<tr>
						<td>
							<fieldset>
								<div style="padding: 2px; height: auto">
									<table id="tbjianli" class="easyui-datagrid"></table>
								</div>
							</fieldset>
						</td>
					</tr>

				</table>
			</div>

		</div>

		<table>
			<tr>
				<td><label class="laber-info">审核人:</label><input type="text"
					id="shenheren" name="shenheren" /></td>
				<td>审核状态：</td>
				<td><select id="status" name="status" class="select">
						<option value="2">审核通过</option>
						<option value="3">审核不通过</option>
				</select></td>
				<td><label class="laber-info">填表人:</label><input type="text"
					id="tianbiaoren" name="tianbiaoren" readonly /></td>
				<td><label class="laber-info">填表日期:</label><input type="text"
					id="tianbiaoriqi" name="tianbiaoriqi" readonly /></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton"
					onclick="submitShenhe()">审 核</a> &nbsp;&nbsp;</td>
			</tr>
		</table>
	</form>
	<div id="jianlixiangqingDialog"></div>
</body>
</html>