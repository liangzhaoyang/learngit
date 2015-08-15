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
<title>添加部门与机构</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/component/common/commonhead2.jsp" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/departmentmanage/lanrenzhijia.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/departmentmanage/departmanage.css">
<script type="text/javascript">	var path="<%=path%>";</script>
<script type="text/javascript" src="<%=path%>/js/departmentmanage/jquery.tabso_yeso.js"></script>
<script type="text/javascript" src="<%=path%>/js/departmentmanage/departmanageAdd.js"></script>
</head>
<body id="bodydiv" class="easyui-layout">
	<div data-options="region:'center'" style="padding: 5px; border: 0">
		<form id="bumenWeihuForm" name="bumenWeihuForm" action="doxzbmbc.do"
			method="post">
			<div id="departs_add_accordion" class="easyui-accordion">

				<div title="基本情况" data-options="iconCls:'icon-save'" selected="true" style="overflow: auto; padding: 10px;">
					<table width="98%" class="formTable">
						<tr>
							<td>
								<fieldset>
									<legend>基本情况</legend>
									<table width="98%">
										<tr>
											<td class="formTdLeft" style="width: 10%"><span style='color: red'>*</span>单位名称(全称)：</td>
											<td class="formTdRight" style="width: 25%">
												<input type="text" name="orgname" value="${orgnization.orgname}" id="orgname" class="input" />
											</td>
											<td class="formTdLeft" style="width: 25%"><span style='color: red'>*</span>主管单位：</td>
											<td class="formTdRight" style="width: 25%">
												<input type="text" name="suorgid" value="${zhuguanDanwei}" id="suorgid" class="input" /></td>
										</tr>
										<tr>
											<td class="formTdLeft" style="width: 10%"><span style='color: red'>*</span>成立时间：</td>
											<td class="formTdRight" style="width: 25%">
												<input name="chenglishijian" value="${orgnization.chenglishijianString}" id="chenglishijian" class="easyui-datebox"  />
											</td>
											<td class="formTdLeft" style="width: 25%"><span
												style='color: red'>*</span>行政级别：</td>
											<td class="formTdRight" colspan="2">
												<select name="xingzhengjibie" id="xingzhengjibie" class="select" style="width: 50%" onchange="selChange(this.value)">
													<option value="0" selected="selected">请选择</option>
													<c:forEach var="xzjb" items="${xzjbList}">
														<c:choose>
															<c:when test="${xzjb.code == orgnization.xingzhengjibie}">
																<option value="${xzjb.code}" selected="selected">${xzjb.name}</option>
															</c:when>
															<c:otherwise>
																<option value="${xzjb.code}">${xzjb.name}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
											</select></td>
										</tr>
										<tr>
											<td class="formTdLeft" style="width: 10%"><span
												style='color: red'>*</span>经费来源：</td>
											<td class="formTdRight" style="width: 25%"><input
												type="text" name="jingfeilaiyuan"
												value=" ${orgnization.jingfeilaiyuan}" id="jingfeilaiyuan"
												class="input" /></td>
											<td class="formTdLeft" style="width: 25%"><span
												style='color: red'>*</span>是否政府序列：</td>
											<td class="formTdRight" style="width: 25%">
												<c:forEach var="zfxl" items="${zfxlList}">
													<c:choose>
														<c:when test="${orgnization.zhengfuxulie==zfxl.code}">
															<input name="zhengfuxulie" type="radio" value="${zfxl.code}" checked />${zfxl.name}
														</c:when>
														<c:otherwise>
															<input name="zhengfuxulie" type="radio" value="${zfxl.code}" />${zfxl.name}
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</td>
										</tr>
										<tr>
											<td class="formTdLeft" style="width: 25%"><span
												style='color: red'>*</span>机构类别：</td>
											<td class="formTdRight" style="width: 25%">
												<c:forEach var="zfxl" items="${jglbList}">
													<c:choose>
														<c:when test="${orgnization.jigouleibie==zfxl.code}">
															<input name="jigouleibie" type="radio"
																value="${zfxl.code}" checked />${zfxl.name}
														</c:when>
														<c:otherwise>
															<input name="jigouleibie" type="radio" value="${zfxl.code}" />${zfxl.name}
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</td>
											<!-- 机构分类添加 -->

											<td class="formTdLeft" style="width: 25%"><span style='color: red'>*</span>机构分类：</td>
											<td class="formTdRight">
												<select name="jigoufenlei1" id="jigoufenlei1" class="select" style="width: 50%" onchange="selChange()">
													<option value="0" selected="selected">请选择</option>
													<c:forEach var="xzjb" items="${jgflList1}">
														<c:choose>
															<c:when test="${xzjb.code == orgnization.jigoufenlei1}">
																<option value="${xzjb.code}" selected="selected">${xzjb.name}</option>
															</c:when>
															<c:otherwise>
																<option value="${xzjb.code}">${xzjb.name}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select> 
												<select name="jigoufenlei2" id="jigoufenlei2" class="select" style="width: 50%" onchange="selectChange()">
													<option value="0" selected="selected">请选择</option>
													<c:forEach var="xzjb" items="${jgflList2}">
														<c:choose>
															<c:when test="${xzjb.code == orgnization.jigoufenlei2}">
																<option value="${xzjb.code}" selected="selected">${xzjb.name}</option>
															</c:when>
															<c:otherwise>
																<option value="${xzjb.code}">${xzjb.name}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>
											</td>
										</tr>
										<tr>
											<td class="formTdLeft" style="width: 25%"><span style='color: red'>*</span>省份：</td>
											<td class="formTdRight" style="width: 25%">
												<select name="shengfen" id="shengfen" class="select" style="width: 50%" onchange="selChange(this.value)">
													<option value="0" selected="selected">请选择</option>
													<c:forEach var="xzjb" items="${shengfenList}">
														<c:choose>
															<c:when test="${xzjb.code == orgnization.shengfen}">
																<option value="${xzjb.code}" selected="selected">${xzjb.name}</option>
															</c:when>
															<c:otherwise>
																<option value="${xzjb.code}">${xzjb.name}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>
											</td>
										</tr>

									</table>
								</fieldset>
							</td>
						</tr>
					</table>
				</div>

				<div title="机构设置情况" data-options="iconCls:'icon-save'"  selected="false" style="overflow: auto; padding: 10px;">
					<table width="98%">
						<tr>
							<td>
								<fieldset>
									<legend>机构设置情况</legend>
									<ul class="tabbtn" id="move-animate-left" style="width: 98%">
										<li class="current"><span>行政工作部门设置情况</span></li>
										<li><span>参照管理机构设置情况</span></li>
										<li><span>事业单位设置情况</span></li>
									</ul>
									<div class="tabcon" id="leftcon">
										<div class="subbox">
											<div class="sublist" style="padding: 2px; height: auto">
												<table id="tbdepartsadd1">
													<tr>
														<th>部门名称</th>
														<th width="100">编制人数</th>
														<th width="80">实有人数</th>
														<th width="80">行政级别</th>
													</tr>
													<tr>
														<td><input type="text" name="xzbmname"
															value="${xzbmname1}" id="xzbmname" class="input"
															size="30" maxlength="30" /></td>
														<td><input type="text" name="xzbmbianzhirenshu"
															value="${xzbmbianzhirenshu1}" id="xzbmbianzhirenshu"
															class="input" /></td>
														<td><input type="text" name="xzbmshijirenshu"
															value="${xzbmshijirenshu1}" id="xzbmshijirenshu"
															class="input" /></td>
														<td><select name="xzbmxingzhengjibie"
															id="xzbmxingzhengjibie" class="select"
															style="width: 120px">
																<option value="0" selected="selected">请选择</option>
																<c:forEach var="xzjb" items="${xzjb_2}">
																	<c:choose>
																		<c:when test="${xzjb.code == xzbmxingzhengjibie1}">
																			<option value="${xzjb.code}" selected="selected">${xzjb.name}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${xzjb.code}">${xzjb.name}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
														</select></td>
														<td><input type="hidden" name="xzbmid"
															value="${xzbmid1}" id="xzbmid" class="input" /></td>
													</tr>
													<tr>
														<td><input type="text" name="xzbmname"
															value="${xzbmname2}" id="xzbmname" class="input"
															size="30" maxlength="30" /></td>
														<td><input type="text" name="xzbmbianzhirenshu"
															value="${xzbmbianzhirenshu2}" id="xzbmbianzhirenshu"
															class="input" /></td>
														<td><input type="text" name="xzbmshijirenshu"
															value="${xzbmshijirenshu2}" id="xzbmshijirenshu"
															class="input" /></td>
														<td><select name="xzbmxingzhengjibie"
															id="xzbmxingzhengjibie" class="select"
															style="width: 120px">
																<option value="0" selected="selected">请选择</option>
																<c:forEach var="xzjb" items="${xzjb_2}">
																	<c:choose>
																		<c:when test="${xzjb.code == xzbmxingzhengjibie2}">
																			<option value="${xzjb.code}" selected="selected">${xzjb.name}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${xzjb.code}">${xzjb.name}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
														</select></td>
														<td><input type="hidden" name="xzbmid"
															value="${xzbmid2}" id="xzbmid" class="input" /></td>
													</tr>
													<tr>
														<td><input type="text" name="xzbmname"
															value="${xzbmname3}" id="xzbmname" class="input"
															size="30" maxlength="30" /></td>
														<td><input type="text" name="xzbmbianzhirenshu"
															value="${xzbmbianzhirenshu3}" id="xzbmbianzhirenshu"
															class="input" /></td>
														<td><input type="text" name="xzbmshijirenshu"
															value="${xzbmshijirenshu3}" id="xzbmshijirenshu"
															class="input" /></td>
														<td><select name="xzbmxingzhengjibie"
															id="xzbmxingzhengjibie" class="select"
															style="width: 120px">
																<option value="0" selected="selected">请选择</option>
																<c:forEach var="xzjb" items="${xzjb_2}">
																	<c:choose>
																		<c:when test="${xzjb.code == xzbmxingzhengjibie3}">
																			<option value="${xzjb.code}" selected="selected">${xzjb.name}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${xzjb.code}">${xzjb.name}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
														</select></td>
														<td><input type="hidden" name="xzbmid"
															value="${xzbmid3}" id="xzbmid" class="input" /></td>
													</tr>
												</table>
											</div>
											<div class="sublist" style="padding: 2px; height: auto">
												<table id="tbdepartsadd2" border="1">
													<tr>
														<th>部门名称</th>
														<th width="100">编制人数</th>
														<th width="80">实有人数</th>
														<th width="80">行政级别</th>
													</tr>
													<tr>
														<td><input type="text" name="gljgname"
															value="${gljgname1}" id="gljgname" class="input"
															size="30" maxlength="30" /></td>
														<td><input type="text" name="gljgbianzhirenshu"
															value="${gljgbianzhirenshu1}" id="gljgbianzhirenshu"
															class="input" /></td>
														<td><input type="text" name="gljgshijirenshu"
															value="${gljgshijirenshu1}" id="gljgshijirenshu"
															class="input" /></td>
														<td><select name="gljgxingzhengjibie"
															id="gljgxingzhengjibie" class="select"
															style="width: 120px">
																<option value="0" selected="selected">请选择</option>
																<c:forEach var="xzjb" items="${xzjb_2}">
																	<c:choose>
																		<c:when test="${xzjb.code == gljgxingzhengjibie1}">
																			<option value="${xzjb.code}" selected="selected">${xzjb.name}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${xzjb.code}">${xzjb.name}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
														</select></td>
														<td><input type="hidden" name="gljgid"
															value="${gljgid1}" id="gljgid" class="input" /></td>
													</tr>
													<tr>
														<td><input type="text" name="gljgname"
															value="${gljgname2}" id="gljgname" class="input"
															size="30" maxlength="30" /></td>
														<td><input type="text" name="gljgbianzhirenshu"
															value="${gljgbianzhirenshu2}" id="gljgbianzhirenshu"
															class="input" /></td>
														<td><input type="text" name="gljgshijirenshu"
															value="${gljgshijirenshu2}" id="gljgshijirenshu"
															class="input" /></td>
														<td><select name="gljgxingzhengjibie"
															id="gljgxingzhengjibie" class="select"
															style="width: 120px">
																<option value="0" selected="selected">请选择</option>
																<c:forEach var="xzjb" items="${xzjb_2}">
																	<c:choose>
																		<c:when test="${xzjb.code == gljgxingzhengjibie2}">
																			<option value="${xzjb.code}" selected="selected">${xzjb.name}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${xzjb.code}">${xzjb.name}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
														</select></td>
														<td><input type="hidden" name="gljgid"
															value="${gljgid2}" id="gljgid" class="input" /></td>
													</tr>
													<tr>
														<td><input type="text" name="gljgname"
															value="${gljgname3}" id="gljgname" class="input"
															size="30" maxlength="30" /></td>
														<td><input type="text" name="gljgbianzhirenshu"
															value="${gljgbianzhirenshu3}" id="gljgbianzhirenshu"
															class="input" /></td>
														<td><input type="text" name="gljgshijirenshu"
															value="${gljgshijirenshu3}" id="gljgshijirenshu"
															class="input" /></td>
														<td><select name="gljgxingzhengjibie"
															id="gljgxingzhengjibie" class="select"
															style="width: 120px">
																<option value="0" selected="selected">请选择</option>
																<c:forEach var="xzjb" items="${xzjb_2}">
																	<c:choose>
																		<c:when test="${xzjb.code == gljgxingzhengjibie3}">
																			<option value="${xzjb.code}" selected="selected">${xzjb.name}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${xzjb.code}">${xzjb.name}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
														</select></td>
														<td><input type="hidden" name="gljgid"
															value="${gljgid3}" id="gljgid" class="input" /></td>
													</tr>
												</table>
											</div>
											<div class="sublist" style="padding: 2px; height: auto">
												<table id="tbdepartsadd3" border="1">
													<tr>
														<th>部门名称</th>
														<th width="100">编制人数</th>
														<th width="80">实有人数</th>
														<th width="80">行政级别</th>
													</tr>
													<tr>
														<td><input type="text" name="sydwname"
															value="${sydwname1}" id="sydwname" class="input"
															size="30" maxlength="30" /></td>
														<td><input type="text" name="sydwbianzhirenshu"
															value="${sydwbianzhirenshu1}" id="sydwbianzhirenshu"
															class="input" /></td>
														<td><input type="text" name="sydwshijirenshu"
															value="${sydwshijirenshu1}" id="sydwshijirenshu"
															class="input" /></td>
														<td><select name="sydwxingzhengjibie"
															id="sydwxingzhengjibie" class="select"
															style="width: 120px">
																<option value="0" selected="selected">请选择</option>
																<c:forEach var="xzjb" items="${xzjb_2}">
																	<c:choose>
																		<c:when test="${xzjb.code == sydwxingzhengjibie1}">
																			<option value="${xzjb.code}" selected="selected">${xzjb.name}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${xzjb.code}">${xzjb.name}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
														</select></td>
														<td><input type="hidden" name="sydwid"
															value="${sydwid1}" id="sydwid" class="input" /></td>
													</tr>
													<tr>
														<td><input type="text" name="sydwname"
															value="${sydwname2}" id="sydwname" class="input"
															size="30" maxlength="30" /></td>
														<td><input type="text" name="sydwbianzhirenshu"
															value="${sydwbianzhirenshu2}" id="sydwbianzhirenshu"
															class="input" /></td>
														<td><input type="text" name="sydwshijirenshu"
															value="${sydwshijirenshu2}" id="sydwshijirenshu"
															class="input" /></td>
														<td><select name="sydwxingzhengjibie"
															id="sydwxingzhengjibie" class="select"
															style="width: 120px">
																<option value="0" selected="selected">请选择</option>
																<c:forEach var="xzjb" items="${xzjb_2}">
																	<c:choose>
																		<c:when test="${xzjb.code == sydwxingzhengjibie2}">
																			<option value="${xzjb.code}" selected="selected">${xzjb.name}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${xzjb.code}">${xzjb.name}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
														</select></td>
														<td><input type="hidden" name="sydwid"
															value="${sydwid2}" id="sydwid" class="input" /></td>
													</tr>
													<tr>
														<td><input type="text" name="sydwname"
															value="${sydwname3}" id="sydwname" class="input"
															size="30" maxlength="30" /></td>
														<td><input type="text" name="sydwbianzhirenshu"
															value="${sydwbianzhirenshu3}" id="sydwbianzhirenshu"
															class="input" /></td>
														<td><input type="text" name="sydwshijirenshu"
															value="${sydwshijirenshu3}" id="sydwshijirenshu"
															class="input" /></td>
														<td><select name="sydwxingzhengjibie"
															id="sydwxingzhengjibie" class="select"
															style="width: 120px">
																<option value="0" selected="selected">请选择</option>
																<c:forEach var="xzjb" items="${xzjb_2}">
																	<c:choose>
																		<c:when test="${xzjb.code == sydwxingzhengjibie3}">
																			<option value="${xzjb.code}" selected="selected">${xzjb.name}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${xzjb.code}">${xzjb.name}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
														</select></td>
														<td><input type="hidden" name="sydwid"
															value="${sydwid3}" id="sydwid" class="input" /></td>
													</tr>
												</table>
											</div>
										</div>
										<!--tabcon end-->
									</div>
								</fieldset>
							</td>
						</tr>
					</table>
				</div>

				<div title="保密基础设施建设"  data-options="iconCls:'icon-save'"  selected="false" style="overflow: auto; padding: 10px;"">
					<table width="98%">
						<tr>
							<td>
								<fieldset>
									<legend>保密基础设施建设</legend>
									<div style="padding: 2px; height: auto">
										<table id="tbdepartsadd4" border="1">
											<tr>
												<th width="80">单位名称</th>
												<th width="100">基础设施类别</th>
												<th width="80">占地面积(平方米)</th>
												<th width="80">投入资金(万元)</th>
												<th width="80">施工单位名称</th>
											</tr>
											<tr>
												<td><input type="text" name="jcssname"
													value="${jcssname1}" id="jcssname" class="input" size="30"
													maxlength="30" /></td>
												<td><input type="text" name="jcssleibie"
													value="${jcssleibie1}" id="jcssleibie" class="input" /></td>
												<td><input type="text" name="jcssmianji"
													value="${jcssmianji1}" id="jcssmianji" class="input" /></td>
												<td><input type="text" name="jcsstouruzijin"
													value="${jcsstouruzijin1}" id="jcsstouruzijin"
													class="input" /></td>
												<td><input type="text" name="jcssjianshedanwei"
													value="${jcssjianshedanwei1}" id="jcssjianshedanwei"
													class="input" size="30" maxlength="30" /></td>
												<td><input type="hidden" name="jcssid"
													value="${jcssid1}" id="jcssid" class="input" /></td>
											</tr>
											<tr>
												<td><input type="text" name="jcssname"
													value="${jcssname2}" id="jcssname" class="input" size="30"
													maxlength="30" /></td>
												<td><input type="text" name="jcssleibie"
													value="${jcssleibie2}" id="jcssleibie" class="input" /></td>
												<td><input type="text" name="jcssmianji"
													value="${jcssmianji2}" id="jcssmianji" class="input" /></td>
												<td><input type="text" name="jcsstouruzijin"
													value="${jcsstouruzijin2}" id="jcsstouruzijin"
													class="input" /></td>
												<td><input type="text" name="jcssjianshedanwei"
													value="${jcssjianshedanwei2}" id="jcssjianshedanwei"
													class="input" size="30" maxlength="30" /></td>
												<td><input type="hidden" name="jcssid"
													value="${jcssid2}" id="jcssid" class="input" /></td>
											</tr>
											<tr>
												<td><input type="text" name="jcssname"
													value="${jcssname3}" id="jcssname" class="input" size="30"
													maxlength="30" /></td>
												<td><input type="text" name="jcssleibie"
													value="${jcssleibie3}" id="jcssleibie" class="input" /></td>
												<td><input type="text" name="jcssmianji"
													value="${jcssmianji3}" id="jcssmianji" class="input" /></td>
												<td><input type="text" name="jcsstouruzijin"
													value="${jcsstouruzijin3}" id="jcsstouruzijin"
													class="input" /></td>
												<td><input type="text" name="jcssjianshedanwei"
													value="${jcssjianshedanwei3}" id="jcssjianshedanwei"
													class="input" size="30" maxlength="30" /></td>
												<td><input type="hidden" name="jcssid"
													value="${jcssid3}" id="jcssid" class="input" /></td>
											</tr>
										</table>
									</div>
								</fieldset>
							</td>
						</tr>

					</table>
				</div>

				<div title="保密办信息" data-options="iconCls:'icon-save'" selected="false" style="overflow: auto; padding: 10px;">
					<table width="98%" class="formTable">
						<tr id="baomiban1">
							<td class="formTdLeft" style="width: 10%"><span style='color: red'>*</span>编制数：</td>
							<td class="formTdRight" style="width: 25%">
								<input type="text" name="bianzhishu" value="${orgnization.bianzhishu}" id="bianzhishu" class="input" />
							</td>
							<td class="formTdLeft" style="width: 10%"><span style='color: red'>*</span>人数：</td>
							<td class="formTdRight" style="width: 25%">
								<input type="text" name="renshu" value="${orgnization.renshu}" id="renshu" class="input" />
							</td>
							<td class="formTdLeft" style="width: 10%"><span style='color: red'>*</span>干部数：</td>
							<td class="formTdRight" style="width: 25%">
								<input type="text" name="zhuanzhiganbushu" value="${orgnization.zhuanzhiganbushu}" id="zhuanzhiganbushu" class="input" />
							</td>
						</tr>
					</table>
				</div>
				<div title="保密学院信息" data-options="iconCls:'icon-save'" selected="false" style="overflow: auto; padding: 10px;">
					<table width="98%" class="formTable">
										<tr id="baomixueyuan">
											<td class="formTdLeft" style="width: 10%"><span style='color: red'>*</span>学生数：</td>
											<td class="formTdRight" style="width: 25%">
												<input type="text" name="xueshengshu" value="${orgnization.xueshengshu}" id="xueshengshu" class="input" />
											</td>
										</tr>
										<tr>
											<td class="formTdLeft" style="width: 10%"><span style='color: red'>*</span>就业情况：</td>
											<td class="formTdRight" style="width: 25%">
												<textarea rows="5" cols="100" name="jiuyeqingkuang" value=""  id="jiuyeqingkuang" >${orgnization.jiuyeqingkuang}</textarea>
											</td>
										</tr>
					</table>
				</div>
			</div>
			<table width="100%" class="buttonTable">
				<tr>
					<td>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" onclick="infosysSave();">保存</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>