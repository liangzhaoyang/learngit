<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<% 
	String path = request.getContextPath();
%>
<html>
<head>
	<title>添加领导成员及工作人员</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />	
	<jsp:include page="/component/common/commonhead.jsp" />
	<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/mainFrame.css">
	<script type="text/javascript" src="<%=path%>/js/person/personAdd.js"></script>
</head>
<body>
<tr>
<a href="javascript:void(0)" class="easyui-linkbutton" onclick="choose()">人员选择</a> &nbsp;&nbsp; 
</tr>
	<form id="frmRyTj" name="frmRyTj" action="dorytj.do" method="post">
		<table width="98%" height="98%" class="formTable">
			<tr>
				<td>
					<fieldset>
						<table width="98%">
						<tr>
								<td class="formTdLeft" style="width: 20%"  ></td>
								<td class="formTdRight" style="width: 70%"><input
									type="hidden" name="leixing" value="${leixing}" id="leixing" class="input" readonly="readonly" 
									/>
							</tr>
							  <tr>
								<td class="formTdLeft" style="width: 20%"  ></td>
								<td class="formTdRight" style="width: 70%"><input
									type="hidden" name="panduan" value="" id="panduan" class="input" readonly="readonly" 
									/>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">姓名：</td>
								<td class="formTdRight" style="width: 70%"><input
									type="text" name="name" value="" id="name" class="input"
									/>
								<div id="nameMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">性别：</td>
								<td class="formTdRight" style="width: 70%">
								<select id="xingbie" name="xingbie" class="select">
								<option value=" ">请选择</option>
								<c:forEach var="xingbie" items="${xingbieList}">								
											<option value="${xingbie.code}">${xingbie.name}</option>
								</c:forEach>
								</select>
								<div id="xingbieMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">民族：</td>
								<td class="formTdRight" style="width: 70%">
								<select id="minzu" name="minzu" class="select">
								<option value=" ">请选择</option>
								<c:forEach var="minzu" items="${minzuList}">								
											<option value="${minzu.code}">${minzu.name}</option>
								</c:forEach>
								</select>
								<div id="minzuMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">职务：</td>
								<td class="formTdRight" style="width: 70%">
								<select id="zhiwu" name="zhiwu" class="select">
								<option value=" ">请选择</option>
								<c:forEach var="zhiwu" items="${zhiwuList}">								
											<option value="${zhiwu.code}">${zhiwu.name}</option>
								</c:forEach>
								</select>
								<div id="zhiwuMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">出生年月：</td>
								<td class="formTdRight" style="width: 70%"><input
									type="text" name="chushengnianyue" value="" id="chushengnianyue" class="easyui-datebox" />
								<div id="chushengnianyueMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">学历：</td>
								<td class="formTdRight" style="width: 70%">
								<select id="xueli" name="xueli" class="select">
								<option value=" ">请选择</option>
								<c:forEach var="xueli" items="${xueliList}">								
											<option value="${xueli.code}">${xueli.name}</option>
								</c:forEach>
								</select>
								
								<div id="xueliMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">政治面貌：</td>
								<td class="formTdRight" style="width: 70%">
								<select id="zhengzhimianmao" name="zhengzhimianmao" class="select">
								<option value=" ">请选择</option>
								<c:forEach var="zhengzhimianmao" items="${zhengzhimianmaoList}">								
											<option value="${zhengzhimianmao.code}">${zhengzhimianmao.name}</option>
								</c:forEach>
								</select>
								
								<div id="zhengzhimianmaoMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">专业：</td>
								<td class="formTdRight" style="width: 70%"><input
									type="text" name="zhuanye" value="" id="zhuanye" class="input" />
								<div id="zhuanyeMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">行政级别：</td>
								<td class="formTdRight" style="width: 70%">
								<select id="xingzhengjibie" name="xingzhengjibie" class="select">
								<option value=" ">请选择</option>
								<c:forEach var="xingzhengjibie" items="${xingzhengjibieList}">								
											<option value="${xingzhengjibie.code}">${xingzhengjibie.name}</option>
								</c:forEach>
								</select>
								
								<div id="xingzhengjibieMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">技术职称：</td>
								<td class="formTdRight" style="width: 70%">
								<select id="jishuzhicheng" name="jishuzhicheng" class="select">
								<option value=" ">请选择</option>	
								<c:forEach var="jishuzhicheng" items="${jishuzhichengList}">								
								<option value="${jishuzhicheng.code}">${jishuzhicheng.name}</option>
								</c:forEach>
								</select>
								
								<div id="jishuzhichengMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">任命单位：</td>
								<td class="formTdRight" style="width: 70%"><input
									type="text" name="renmingdanwei" value="" id="renmingdanwei" class="input" />
								<div id="renmingdanweiMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">是否专职：</td>
								<td class="formTdRight" style="width: 70%">
								<select id="shifouzhuanzhi" name="shifouzhuanzhi" class="select">
								<option value=" ">请选择</option>	
								<c:forEach var="shifouzhuanzhi" items="${shifouzhuanzhiList}">	
								<option value="${shifouzhuanzhi.code}">${shifouzhuanzhi.name}</option>
								</c:forEach>
								</select>
				
								<div id="shifouzhuanzhiMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">兼任其他职务：</td>
								<td class="formTdRight" style="width: 70%"><input
									type="text" name="jianrenqitazhiwu" value="" id="jianrenqitazhiwu" class="input" />
								<div id="jianrenqitazhiwuMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">开始日期：</td>
								<td class="formTdRight" style="width: 70%"><input
									type="text" name="kaishiriqi" value="" id="kaishiriqi" class="easyui-datebox" />
								<div id="kaishiriqiMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">所属部门：</td>
								<td class="formTdRight" style="width: 70%"><input
									type="text" name="suoshubumen" value="" id="suoshubumen" class="input" />
								<div id="suoshubumenMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">单位名称：</td>
								<td class="formTdRight" style="width: 70%"><input
									type="text" name="danweimingcheng" value="" id="danweimingcheng" class="input" />
								<div id="danweimingchengMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">座机电话：</td>
								<td class="formTdRight" style="width: 70%"><input
									type="text" name="tel" value="" id="tel" class="input" />
								<div id="telMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">移动电话：</td>
								<td class="formTdRight" style="width: 70%"><input
									type="text" name="mobilenum" value="" id="mobilenum" class="input" />
								<div id="mobilenumMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">涉密等级：</td>
								<td class="formTdRight" style="width: 70%">
								<select id="shemidengji" name="shemidengji" class="select">
								<option value=" ">请选择</option>
								<c:forEach var="shemidengji" items="${shemidengjiList}">								
											<option value="${shemidengji.code}">${shemidengji.name}</option>
								</c:forEach>
								</select>
								
								<div id="shemidengjiMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">是否要害部门人员：</td>
								<td class="formTdRight" style="width: 70%">
								<input name="yaohaibumengrenyuan" type="radio" value="1" checked="checked"/>是
								<input name="yaohaibumengrenyuan" type="radio" value="2"/>否
								
								<div id="yaohaibumengrenyuanMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">人员类型：</td>
								<td class="formTdRight" style="width: 70%">
								<select id="renyuanleixing" name="renyuanleixing" class="select">
								<option value=" ">请选择</option>
								<c:forEach var="renyuanleixing" items="${renyuanleixingList}">	
							<option value="${renyuanleixing.code}">${renyuanleixing.name}</option>
								</c:forEach>
								</select>
								
								<div id="renyuanleixingMsg"></div></td>
								
							</tr>
						</table>
					</fieldset>
				</td>
			</tr>
		</table>
	</form>
	
	<table>
		<tr>
				<td>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormPersonAdd()">保存</a> &nbsp;&nbsp; 
					
					<a id="btnCancel" href="javascript:void(0)" class="easyui-linkbutton" onclick="cancelClick();">取消</a>
				</td>
		</tr>
	</table>
	<div id="personDialog"></div>
</body>
</html>