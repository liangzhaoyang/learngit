<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>添加领导成员及工作人员</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/component/common/commonhead2.jsp" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/common/iframeStyle.css">
<script type="text/javascript" src="<%=path%>/js/bianzhimingxi/zaibianRenyuanMingxi.js"></script>
</head>
<body id="bodydiv" class="easyui-layout">
	<div data-options="region:'center'" style="padding: 5px; border: 0">
		<form id="zbrymxbc" name="zbrymxbc" action="zbrymxbc.do" method="post">
			<div align="center">
				<table class="formTable">
					<tr>
						<td>
							<fieldset>
								<legend><h2>保密行政管理部门在编人员情况填报表及说明</h2></legend>
								<table class="table" width="98%">
									<tr>
										<th colspan="3" scope="col" style="width:300px">&nbsp;</th>
										<th scope="col" style="width:40px">代码</th>
										<th scope="col" style="width:60px">数量</th>
									</tr>
									<tr>
										<th colspan="3" scope="row">甲</th>
										<th>乙</th>
										<td>1</td>
										</tr>
									<tr>
										<td scope="row" colspan="3" style="text-align:left" ><b>在编人员总计</b></td>
										<td>101</td>
										<td><input type="text" name="num1" id="input101" class="form-control" value=""></td>
									</tr>
									<tr>
										<td scope="row" colspan="3" style="text-align:left" ><b>一、按岗位分组</b></td>
										<td>一</td>
										<td>一</td>
									</tr>
									<tr>
										<td width="90" rowspan="4" colspan="2"  style="text-align:right" scope="row"><b>其中：</b></td>
										<td scope="row" style="width:200px; text-align:left" ><b>行政管理人员</b></td>
										<td>102</td>
										<td><input type="text" name="num2" id="input102"
											class="form-control" value=""></td>
									</tr>
									<tr>
										<td scope="row" style="width:200px; text-align:left" ><b>专业技术人员</b></td>
										<td>103</td>
										<td><input type="text" name="num3" id="input103"
											class="form-control" value=""></td>
									</tr>
									<tr>
										<td scope="row" style="width:200px; text-align:left" ><b>工勤人员</b></td>
										<td>104</td>
										<td><input type="text" name="num4" id="input104"
											class="form-control" value=""></td>
									</tr>
									<tr>
										<td scope="row" style="width:200px; text-align:left" ><b>其他人员</b></td>
										<td>105</td>
										<td><input type="text" name="num5" id="input105"
											class="form-control" value=""></td>
									</tr>
									<tr>
										<td scope="row" colspan="3" style="text-align:left" ><b>二、按年龄结构分组</b></td>
										<td>一</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td width="90" rowspan="4" colspan="2"  style="text-align:right" scope="row"><b>其中：</b></td>
										<td scope="row" style="width:200px; text-align:left" ><b>56岁以上</b></td>
										<td>106</td>
										<td><input type="text" name="num6" id="input106"
											class="form-control" value=""></td>
									</tr>
									<tr>
										<td scope="row" style="width:200px; text-align:left" ><b>46-55岁</b></td>
										<td>107</td>
										<td><input type="text" name="num7" id="input107"
											class="form-control" value=""></td>
									</tr>
									<tr>
										<td scope="row" style="width:200px; text-align:left" ><b>36-45岁</b></td>
										<td>108</td>
										<td><input type="text" name="num8" id="input108"
											class="form-control" value=""></td>
									</tr>
									<tr>
										<td scope="row" style="width:200px; text-align:left" ><b>35岁以下</b></td>
										<td>109</td>
										<td><input type="text" name="num9" id="input109"
											class="form-control" value=""></td>
									</tr>
									<tr>
										<td scope="row" colspan="3" style="text-align:left" ><b>三、按专业分组</b></td>
										<td>一</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td width="90" rowspan="5" colspan="2"  style="text-align:right" scope="row"><b>其中：</b></td>
										<td scope="row" style="width:200px; text-align:left" ><b>文秘</b></td>
										<td>110</td>
										<td><input type="text" name="num10" id="input110"
											class="form-control" value=""></td>
									</tr>
									<tr>
										<td scope="row" style="width:200px; text-align:left" ><b>法律</b></td>
										<td>111</td>
										<td><input type="text" name="num11" id="input111"
											class="form-control" value=""></td>
									</tr>
									<tr>
										<td scope="row" style="width:200px; text-align:left" ><b>管理</b></td>
										<td>112</td>
										<td><input type="text" name="num12" id="input112"
											class="form-control" value=""></td>
									</tr>
									<tr>
										<td scope="row" style="width:200px; text-align:left" ><b>计算机</b></td>
										<td>113</td>
										<td><input type="text" name="num13" id="input113"
											class="form-control" value=""></td>
									</tr>
									<tr>
										<td scope="row" style="width:200px; text-align:left" ><b>其他</b></td>
										<td>114</td>
										<td><input type="text" name="num14" id="input114"
											class="form-control" value=""></td>
									</tr>
									<tr>
										<td scope="row" colspan="3" style="text-align:left" ><b>四、按学历分组</b></td>
										<td>一</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td width="90" rowspan="4" colspan="2"  style="text-align:right" scope="row"><b>其中：</b></td>
										<td scope="row" style="width:200px; text-align:left" ><b>博士研究生</b></td>
										<td>115</td>
										<td><input type="text" name="num15" id="input115"
											class="form-control" value=""></td>
									</tr>
									<tr>
										<td scope="row" style="width:200px; text-align:left" ><b>硕士研究生</b></td>
										<td>116</td>
										<td><input type="text" name="num16" id="input116"
											class="form-control" value=""></td>
									</tr>
									<tr>
										<td scope="row" style="width:200px; text-align:left" ><b>大学本科</b></td>
										<td>117</td>
										<td><input type="text" name="num17" id="input117"
											class="form-control" value=""></td>
									</tr>
									<tr>
										<td scope="row" style="width:200px; text-align:left" ><b>大专以下</b></td>
										<td>118</td>
										<td><input type="text" name="num18" id="input118"
											class="form-control" value=""></td>
									</tr>
								</table>
							</fieldset>
						</td>
					</tr>
				</table>
				<table style="width: 400px" class="buttonTable">
					<tr>
						<td style="width: 400px">
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="rybzmxbc1();">保存</a>
							&nbsp;&nbsp; 
							<a id="btnCancel" href="javascript:void(0)" class="easyui-linkbutton" onclick="cancelClick();">取消</a>
						</td>
					</tr>
				</table>
			</div>
		</form>
</body>
</html>