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
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/departmentmanage/lanrenzhijia.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/departmentmanage/departmanage.css">
<script type="text/javascript">	var path="<%=path%>";
</script>
<script type="text/javascript"
	src="<%=path%>/js/departmentmanage/jquery.tabso_yeso.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/departmentmanage/departmentmaintenance.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/datagrid-scrollview.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/datagrid-detailview.js"></script>
</head>
<body id="bodydiv" class="easyui-layout">
	<div data-options="region:'center'" style="padding: 5px; border: 0">
		<form id="jigouweihufrm" name="jigouweihufrm" action="doxzbmbc.do"
			method="post">
			<div id="departs_add_accordion" class="easyui-accordion">
				<div title="基本情况" data-options="iconCls:'icon-save'" selected="true"
					style="overflow: auto; padding: 10px;">
					<table width="98%" class="formTable">
						<tr>
							<td>
								<fieldset>
									<legend>基本情况</legend>
									<table width="98%">
										<tr>
											<td class="formTdLeft" style="width: 10%"><span
												style='color: red'>*</span>单位名称(全称)：</td>
											<td class="formTdRight" style="width: 25%"><input
												type="text" name="orgname" value="${orgnization.orgname}"
												id="orgname" class="input" maxlength="30"/></td>
											<td class="formTdLeft" style="width: 25%"><span
												style='color: red'>*</span>主管单位：</td>
											<td class="formTdRight" style="width: 25%"><input
												type="text" name="suorgid" value="${zhuguanDanwei}"
												id="suorgid" class="input" maxlength="30"/></td>
										</tr>
										<tr>
											<td class="formTdLeft" style="width: 10%"><span
												style='color: red'>*</span>成立时间：</td>
											<td class="formTdRight" style="width: 25%"><input
												type="text" name="chenglishijian"
												value="${orgnization.chenglishijianString}"
												id="chenglishijian" class="easyui-datebox" /></td>
											<td class="formTdLeft" style="width: 25%"><span
												style='color: red'>*</span>行政级别：</td>
											<td class="formTdRight" colspan="2"><select
												name="xingzhengjibie" id="xingzhengjibie" class="select"
												style="width: 50%" onchange="selChange(this.value)">
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
												value="${orgnization.jingfeilaiyuan}" id="jingfeilaiyuan"
												class="input" maxlength="30"/></td>
											<td class="formTdLeft" style="width: 25%"><span
												style='color: red'>*</span>是否政府序列：</td>
											<td class="formTdRight" style="width: 25%"><c:forEach
													var="zfxl" items="${zfxlList}">
													<c:choose>
														<c:when test="${orgnization.zhengfuxulie==zfxl.code}">
															<input name="zhengfuxulie" type="radio"
																value="${zfxl.code}" checked />${zfxl.name}
									</c:when>
														<c:otherwise>
															<input name="zhengfuxulie" type="radio"
																value="${zfxl.code}" />${zfxl.name}
									</c:otherwise>
													</c:choose>
												</c:forEach></td>
										</tr>
										<tr>
											<td class="formTdLeft" style="width: 25%"><span
												style='color: red'>*</span>机构类别：</td>
											<td class="formTdRight" style="width: 25%"><c:forEach
													var="zfxl" items="${jglbList}">
													<c:choose>
														<c:when test="${orgnization.jigouleibie==zfxl.code}">
															<input name="jigouleibie" type="radio"
																value="${zfxl.code}" checked />${zfxl.name}
									</c:when>
														<c:otherwise>
															<input name="jigouleibie" type="radio"
																value="${zfxl.code}" />${zfxl.name}
									</c:otherwise>
													</c:choose>
												</c:forEach></td>
											<!-- 机构分类添加 -->

											<td class="formTdLeft" style="width: 25%"><span
												style='color: red'>*</span>机构分类：</td>
											<td class="formTdRight"><select name="jigoufenlei1"
												id="jigoufenlei1" class="select" style="width: 50%"
												onchange="selChange()">
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
											</select> <select name="jigoufenlei2" id="jigoufenlei2" class="select"
												style="width: 50%" onchange="selectChange()">
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
											</select></td>

										</tr>
										<tr>
											<td class="formTdLeft" style="width: 25%"><span
												style='color: red'>*</span>省份：</td>
											<td class="formTdRight" style="width: 25%"><select
												name="shengfen" id="shengfen" class="select"
												style="width: 50%" onchange="selChange(this.value)">
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
											</select></td>
										</tr>

									</table>
								</fieldset>
							</td>
						</tr>
					</table>
				</div>

				<div title="机构设置情况" data-options="iconCls:'icon-save'"
					style="padding: 10px;overflow: auto;height: 200px">
					<table width="98%">
						<tr>
							<td>
								<fieldset>
									<legend>机构设置情况</legend>
									<ul class="tabbtn" id="move-animate-left">
										<li class="current"><span>行政工作部门设置情况</span></li>
										<li><span>参照管理机构设置情况</span></li>
										<li><span>事业单位设置情况</span></li>
									</ul>
									<div class="tabcon" id="leftcon">
										<div class="subbox">
											<div class="sublist" style="overflow: auto;height: 200px;padding: 2px;">
												<table id="tbxingzhengbumen" class="easyui-datagrid"></table>
												<input type="hidden" name="xzbminsert" value=""
													id="xzbminsert" class="input" /> <input type="hidden"
													name="xzbmupdate" value="" id="xzbmupdate" class="input" />
												<input type="hidden" name="xzbmdelete" value=""
													id="xzbmdelete" class="input" />
											</div>
											<div class="sublist" style="overflow: auto;height: 200px;padding: 2px;">
												<table id="tbguanlijigou" class="easyui-datagrid"></table>
												<input type="hidden" name="gljginsert" value=""
													id="gljginsert" class="input" /> <input type="hidden"
													name="gljgupdate" value="" id="gljgupdate" class="input" />
												<input type="hidden" name="gljgdelete" value=""
													id="gljgdelete" class="input" />

											</div>
											<div class="sublist" style="overflow: auto;height: 200px;padding: 2px;">
												<table id="tbshiyedanwei" class="easyui-datagrid"></table>
												<input type="hidden" name="sydwinsert" value=""
													id="sydwinsert" class="input" /> <input type="hidden"
													name="sydwupdate" value="" id="sydwupdate" class="input" />
												<input type="hidden" name="sydwdelete" value=""
													id="sydwdelete" class="input" />
											</div>

										</div>
										<!--tabcon end-->
									</div>
								</fieldset>
							</td>
						</tr>
					</table>
				</div>

				<div title="保密基础设施建设" data-options="iconCls:'icon-save'"
					selected="false" style="padding: 10px;overflow: auto;height: 200px">
					<table width="98%">
						<tr>
							<td>
								<fieldset>
									<legend>保密基础设施建设</legend>
									<div style="overflow: auto;height: 200px;padding: 2px;">
										<table id="tbjichusheshi" class="easyui-datagrid"></table>
										<input type="hidden" name="jcssinsert" value=""
											id="jcssinsert" class="input" /> <input type="hidden"
											name="jcssupdate" value="" id="jcssupdate" class="input" /> <input
											type="hidden" name="jcssdelete" value="" id="jcssdelete"
											class="input" />
									</div>
								</fieldset>
							</td>
						</tr>

					</table>
				</div>

				<div title="保密办信息" data-options="iconCls:'icon-save'"
					selected="false" style="overflow: auto; padding: 10px;">
					<table width="98%" class="formTable">
						<tr id="baomiban1">
							<td class="formTdLeft" style="width: 10%"><span
								style='color: red'>*</span>编制数：</td>
							<td class="formTdRight" style="width: 25%"><input
								type="text" name="bianzhishu" value="${orgnization.bianzhishu}"
								id="bianzhishu" class="input" maxlength="30"/></td>
							<td class="formTdLeft" style="width: 10%"><span
								style='color: red'>*</span>人数：</td>
							<td class="formTdRight" style="width: 25%"><input
								type="text" name="renshu" value="${orgnization.renshu}"
								id="renshu" class="input" maxlength="30"/></td>
							<td class="formTdLeft" style="width: 10%"><span
								style='color: red'>*</span>干部数：</td>
							<td class="formTdRight" style="width: 25%"><input
								type="text" name="zhuanzhiganbushu"
								value="${orgnization.zhuanzhiganbushu}" id="zhuanzhiganbushu"
								class="input" maxlength="30"/></td>
						</tr>
					</table>
				</div>
				<div title="保密学院信息" data-options="iconCls:'icon-save'"
					selected="false" style="overflow: auto; padding: 10px;">
					<table width="98%" class="formTable">
						<tr id="baomixueyuan">
							<td class="formTdLeft" style="width: 10%"><span
								style='color: red'>*</span>学生数：</td>
							<td class="formTdRight" style="width: 25%"><input
								type="text" name="xueshengshu"
								value="${orgnization.xueshengshu}" id="xueshengshu"
								class="input" maxlength="30"/></td>
						</tr>
						<tr>
							<td class="formTdLeft" style="width: 10%"><span
								style='color: red'>*</span>就业情况：</td>
							<td class="formTdRight" style="width: 25%"><textarea
									rows="5" cols="100" name="jiuyeqingkuang" value=""
									id="jiuyeqingkuang">${orgnization.jiuyeqingkuang}</textarea></td>
						</tr>
					</table>
				</div>

			</div>

			<table width="100%" class="buttonTable">
				<tr>
					<td class="formTdLeft" style="width: 10%"><span
						style='color: red'>*</span>填表人：</td>
					<td class="formTdRight" style="width: 25%"><input type="text"
						name="tianbiaoren" value="${tianbiaoren}" id="tianbiaoren" class="input" /></td>

					<td class="formTdLeft" style="width: 25%"><span
						style='color: red'>*</span>填表日期：</td>
					<td class="formTdRight" style="width: 25%"><input type="text"
						name="tianbiaoriqi" value="${tianbiaoriqi}" id="tianbiaoriqi" class="easyui-datebox" /></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="icon-save" onclick="orgInfoSave()">保存</a></td>
				</tr>
				<tr><td>
				<a href="bmxzdc.do" class="easyui-linkbutton"
						iconCls="icon-save" onclick="">导出</a>
				</td></tr>
			</table>

		</form>
	</div>
</body>
</html>