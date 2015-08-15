<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>添加用户</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/component/common/commonhead.jsp" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/common/iframeStyle.css">
<script type="text/javascript" src="<%=path%>/js/user/userManage.js"></script>
<script>
	function submitFormYhtj() {
		$('#frmYhtj').form('submit', {
			onSubmit : function() {
				if (!checkInput()) {
					return false;
				}
				return true;
			},
			success : function(data) {
				var json = $.parseJSON(data);
				if (json.errMsg == null && json.dbErr == null) {
					window.parent.$("#userDialog").dialog("close");
				} else {
					if (json.errMsg != null) {
						$("#idMsg").html(json.errMsg.id);
						$("#nameMsg").html(json.errMsg.name);
						$("#rolesMsg").html(json.errMsg.roles);
						$("#orgNameMsg").html(json.errMsg.orgName);
						$("#suOrgNameMsg").html(json.errMsg.suOrgName);
					}
					if (json.dbErr != null) {
						alert(json.dbErr);
					}
				}
			}
		});
	}

	function cancelClick() {
		window.parent.$("#userDialog").dialog("close");
	}

	function idExistCheck() {
		if ($("#id").val() != "") {
			$.post("userExistCheck.do?id=" + $("#id").val(), function(data) {
				$("#idMsg").html(data.message);
			});
		}
	}

	$(function() {
		$("#subwndTest").click(function() {
			$('#popWindow').dialog({
				title : 'test',
				width : 600,
				height : 400,
				closed : false,
				cache : false,
				href : "showDialog.do?url='subWndTest.do'",
				modal : true,
				minimizable : true,
				resizable : true,
				onClose : function() {
					alert($("#rtnValue").val());
				}
			});
		});
	});
</script>
</head>
<body>
	<form id="frmYhtj" name="frmYhtj" action="doyhtj.do" method="post">
		<table width="98%" height="98%" class="formTable">
			<tr>
				<td>
					<fieldset>
						<table width="98%">
							<tr>
								<td class="formTdLeft" style="width: 20%"><span
									style='color: red'>*</span>用户名称：</td>
								<td class="formTdRight" style="width: 70%"><input
									type="text" name="id" value="" id="id" class="input"
									onBlur="idExistCheck();" />
									<div id="idMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%"><span
									style='color: red'>*</span>姓名：</td>
								<td class="formTdRight" style="width: 70%"><input
									type="text" name="name" value="" id="name" class="input" />
									<div id="nameMsg"></div>
								</td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%"><span
									style='color: red'>*</span>用户角色：</td>
								<td class="formTdRight" style="width: 70%">
									<table>
										<tr>
											<c:forEach var="role" items="${roles}">
												<td style="valigh: middle"><input type="checkbox"
													id="roles" name="roles" value="${role.id}" /></td>
												<td>${role.name}&nbsp;</td>
											</c:forEach>
										</tr>
									</table>
									<div id="rolesMsg"></div>
								</td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">机构名称：</td>
								<td class="formTdRight" style="width: 70%"><input
									type="text" name="orgName" maxlength="30" value="" id="orgName"
									class="input" />
									<div id="orgNameMsg"></div></td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">主管单位：</td>
								<td class="formTdRight" style="width: 70%"><input
									type="text" name="suOrgName" maxlength="30" value=""
									id="suOrgName" class="input" />
									<div id="suOrgNameMsg"></div>
								</td>
							</tr>
						</table>
					</fieldset>
				</td>
			</tr>
		</table>
	</form>
	<table width="100%" class="buttonTable">
		<tr>
			<td>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormYhtj()">保存</a> 
				&nbsp;&nbsp; 
				<a id="btnCancel" href="javascript:void(0)" class="easyui-linkbutton" onclick="cancelClick();">取消</a></td>
		</tr>
	</table>
</body>
</html>