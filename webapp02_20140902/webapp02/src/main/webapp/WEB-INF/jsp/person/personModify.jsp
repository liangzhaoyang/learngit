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
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/common/iframeStyle.css">
	<script type="text/javascript" src="<%=path%>/js/person/personModify.js"></script>
</head>
<body>
	<form id="frmryXg" name="frmryXg" action="doryxg11.do" method="post">
		<table width="98%" height="98%" class="formTable">
			<tr>
				<td>
					<fieldset>
						<table width="98%">
							<tr>
								<td class="formTdLeft" style="width: 20%">姓名：</td>
								<td class="formTdRight" style="width: 70%"><input
									type="text" name="name" value=${person.name} id="name" class="input"
									/>
								<div id="nameMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">性别：</td>
								<td class="formTdRight" style="width: 70%">
								<c:choose>
									<c:when test="${person.xingbie==1}">
										<input name="xingbie" type="radio" value="1" checked/>男
										<input name="xingbie" type="radio" value="2" />女
									</c:when>
									<c:otherwise>
										<input name="xingbie" type="radio" value="1" />男
										<input name="xingbie" type="radio" value="2" checked/>女
									</c:otherwise>
									</c:choose>		
								
								<div id="xingbieMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">民族：</td>
								<td class="formTdRight" style="width: 70%">
								<select id="minzu" name="minzu" class="select">
								<c:forEach var="minzu" items="${minzuList}">	
									<c:choose>
									<c:when test="${minzu.code == person.minzu}">
										<option value="${minzu.code}" selected>${minzu.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${minzu.code}">${minzu.name}</option>
									</c:otherwise>
									</c:choose>							
									
								</c:forEach>
								</select>
								<div id="minzuMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">职务：</td>
								<td class="formTdRight" style="width: 70%">
								<select id="zhiwu" name="zhiwu" class="select">
								<c:forEach var="zhiwu" items="${zhiwuList}">
									<c:choose>
									<c:when test="${zhiwu.code == person.zhiwu}">
										<option value="${zhiwu.code}" selected="selected">${zhiwu.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${zhiwu.code}">${zhiwu.name}</option>
									</c:otherwise>
									</c:choose>		

								</c:forEach>
								</select>
								<div id="zhiwuMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">出生年月：</td>
								<td class="formTdRight" style="width: 70%"><input
									type="text" name="chushengnianyue" value="${person.chushengnianyue}" id="chushengnianyue" class="easyui-datebox" />
								<div id="chushengnianyueMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">学历：</td>
								<td class="formTdRight" style="width: 70%">
								<select id="xueli" name="xueli" class="select">
								<c:forEach var="xueli" items="${xueliList}">
									<c:choose>
									<c:when test="${xueli.code == person.xueli}">
										<option value="${xueli.code}" selected="selected">${xueli.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${xueli.code}">${xueli.name}</option>
									</c:otherwise>
									</c:choose>										
										
								</c:forEach>
								</select>
								
								<div id="xueliMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">政治面貌：</td>
								<td class="formTdRight" style="width: 70%">
								<select id="zhengzhimianmao" name="zhengzhimianmao" class="select">
								<c:forEach var="zhengzhimianmao" items="${zhengzhimianmaoList}">	
									<c:choose>
									<c:when test="${zhengzhimianmao.code == person.zhengzhimianmao}">
										<option value="${zhengzhimianmao.code}" selected="selected">${zhengzhimianmao.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${zhengzhimianmao.code}">${zhengzhimianmao.name}</option>
									</c:otherwise>
									</c:choose>									
											
								</c:forEach>
								</select>
								
								<div id="zhengzhimianmaoMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">专业：</td>
								<td class="formTdRight" style="width: 70%"><input
									type="text" name="zhuanye" value=${person.zhuanye}  id="zhuanye" class="input" />
								<div id="zhuanyeMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">行政级别：</td>
								<td class="formTdRight" style="width: 70%">
								<select id="xingzhengjibie" name="xingzhengjibie" class="select">
								<c:forEach var="xingzhengjibie" items="${xingzhengjibieList}">	
									<c:choose>
									<c:when test="${xingzhengjibie.code == person.xingzhengjibie}">
										<option value="${xingzhengjibie.code}" selected="selected">${xingzhengjibie.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${xingzhengjibie.code}">${xingzhengjibie.name}</option>
									</c:otherwise>
									</c:choose>									
											
								</c:forEach>
								</select>
								
								<div id="xingzhengjibieMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">技术职称：</td>
								<td class="formTdRight" style="width: 70%">
								<select id="jishuzhicheng" name="jishuzhicheng" class="select">
								<c:forEach var="jishuzhicheng" items="${jishuzhichengList}">	
									<c:choose>
									<c:when test="${jishuzhicheng.code == person.jishuzhicheng}">
										<option value="${jishuzhicheng.code}" selected="selected">${jishuzhicheng.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${jishuzhicheng.code}">${jishuzhicheng.name}</option>
									</c:otherwise>
									</c:choose>									
											
								</c:forEach>
								</select>
								
								<div id="jishuzhichengMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">任命单位：</td>
								<td class="formTdRight" style="width: 70%"><input
									type="text" name="renmingdanwei" value=${person.renmingdanwei} id="renmingdanwei" class="input" />
								<div id="renmingdanweiMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">是否专职：</td>
								<td class="formTdRight" style="width: 70%">
								<c:choose>
									<c:when test="${person.shifouzhuanzhi==1}">
										<input name="shifouzhuanzhi" type="radio" value="1" checked="checked"/>是
										<input name="shifouzhuanzhi" type="radio" value="2"/>否
									</c:when>
									<c:otherwise>
										<input name="shifouzhuanzhi" type="radio" value="1" />是
										<input name="shifouzhuanzhi" type="radio" value="2" checked="checked"/>否
									</c:otherwise>
									</c:choose>			
				
								<div id="shifouzhuanzhiMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">兼任其他职务：</td>
								<td class="formTdRight" style="width: 70%"><input
									type="text" name="jianrenqitazhiwu" value=${person.jianrenqitazhiwu} id="jianrenqitazhiwu" class="input" />
								<div id="jianrenqitazhiwuMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">开始日期：</td>
								<td class="formTdRight" style="width: 70%"><input
									type="text" name="kaishiriqi" value="${person.kaishiriqi}" id="kaishiriqi" class="easyui-datebox" />
								<div id="kaishiriqiMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">所属部门：</td>
								<td class="formTdRight" style="width: 70%"><input
									type="text" name="suoshubumen" value=${person.suoshubumen} id="suoshubumen" class="input" />
								<div id="suoshubumenMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">单位名称：</td>
								<td class="formTdRight" style="width: 70%"><input
									type="text" name="danweimingcheng" value=${person.danweimingcheng} id="danweimingcheng" class="input" />
								<div id="danweimingchengMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">座机电话：</td>
								<td class="formTdRight" style="width: 70%"><input
									type="text" name="tel" value=${person.tel} id="tel" class="input" />
								<div id="telMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">移动电话：</td>
								<td class="formTdRight" style="width: 70%"><input
									type="text" name="mobilenum" value=${person.mobilenum} id="mobilenum" class="input" />
								<div id="mobilenumMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">涉密等级：</td>
								<td class="formTdRight" style="width: 70%">
								<select id="shemidengji" name="shemidengji" class="select">
								<c:forEach var="shemidengji" items="${shemidengjiList}">
								<c:choose>
									<c:when test="${shemidengji.code == person.shemidengji}">
										<option value="${shemidengji.code}" selected="selected">${shemidengji.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${shemidengji.code}">${shemidengji.name}</option>
									</c:otherwise>
									</c:choose>										
											
								</c:forEach>
								</select>
								
								<div id="shemidengjiMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">要害部门人员：</td>
								<td class="formTdRight" style="width: 70%">
								<c:choose>
									<c:when test="${person.yaohaibumengrenyuan ==1}">
										<input name="yaohaibumengrenyuan" type="radio" value="1" checked="checked"/>是
										<input name="yaohaibumengrenyuan" type="radio" value="2"/>否
									</c:when>
									<c:otherwise>
										<input name="yaohaibumengrenyuan" type="radio" value="1" />是
										<input name="yaohaibumengrenyuan" type="radio" value="2" checked="checked"/>否
									</c:otherwise>
									</c:choose>		
								
								
								<div id="yaohaibumengrenyuanMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">人员类型：</td>
								<td class="formTdRight" style="width: 70%">
								<select id="renyuanleixing" name="renyuanleixing" class="select">
								<c:forEach var="renyuanleixing" items="${renyuanleixingList}">	
								<c:choose>
									<c:when test="${renyuanleixing.code == person.renyuanleixing}">
										<option value="${renyuanleixing.code}" selected="selected">${renyuanleixing.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${renyuanleixing.code}">${renyuanleixing.name}</option>
									</c:otherwise>
									</c:choose>									
											
								</c:forEach>
								</select>
								
								<div id="renyuanleixingMsg"></div></td>
							</tr>
							<tr>
								<td> 
									<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitModifyForm()">保存</a> &nbsp;&nbsp; 
									<a id="btnCancel" href="javascript:void(0)" class="easyui-linkbutton" onclick="cancelModifyClick();">取消</a>
								</td>
							</tr>
						</table>
					</fieldset>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>