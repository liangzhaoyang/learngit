<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>密码修改</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/component/common/commonhead.jsp" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/mainFrame.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/common/iframeStyle.css">
<script type="text/javascript" src="<%=path%>/js/common/md5.js"></script>
<script>
	function submitFormMmxg() {
		$('#frmMmxgj').form('submit', {
			onSubmit : function() {

				var rtn = true;
				if($("#oldPassword").val() == "") {
					$("#oldPasswordMsg").html("请输入旧密码。");
					rtn = false;
				}

				if($("#password").val() == "") {
					$("#passwordMsg").html("请输入新密码。");
					rtn = false;
				}

				if($("#password").val() == $("#oldPassword").val()) {
					$("#passwordMsg").html("新密码不能与旧密码相同。");
					rtn = false;
				}

				if($("#passwordConfirm").val() == "") {
					$("#passwordConfirmMsg").html("请再次输入新密码。");
					rtn = false;
				}

				if($("#passwordConfirm").val() != $("#password").val() ) {
					$("#passwordConfirmMsg").html("新密码输入不一致，请重新输入。");
					rtn = false;
				}

				if(rtn) {
					$("#oldPassword").val(MD5($("#oldPassword").val()));
					$("#password").val(MD5($("#password").val()));
					$("#passwordConfirm").val(MD5($("#passwordConfirm").val()));
				}
				return rtn;
			},
			success : function(data) {
				var json = $.parseJSON(data);
				if (json.errMsg == null && json.dbErr == null) {
					window.parent.$("#changePasswdDialog").dialog("close");
				} else {
					if (json.errMsg != null) {
						$("#oldPasswordMsg").html(json.errMsg.oldPassword);
						$("#passwordMsg").html(json.errMsg.password);
						$("#passwordConfirmMsg").html(json.errMsg.passwordConfirm);
					}
					if (json.dbErr != null) {
						$("#divMainErrMsg").html(json.dbErr);
					}
				}
			}
		});
	}

	function cancelClick() {
		window.parent.$("#changePasswdDialog").dialog("close");
	}

</script>
</head>
<body>
<div style="padding: 5px; border: 0">
	<form id="frmMmxgj" name="frmMmxgj" action="doMmxg.do" method="post">
		<table width="98%" height="98%" class="formTable">
			<tr>
				<td>
					<fieldset>
					<div id="divMainErrMsg"></div> <!-- 为错误信息预留 -->
						<table width="98%">
							<tr>
								<td class="formTdLeft" style="width: 20%">旧密码：</td>
								<td class="formTdRight" style="width: 70%">
									<input type="password" name="oldPassword" value="" id="oldPassword" class="input" />
									<div id="oldPasswordMsg"></div>
								</td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">新密码：</td>
								<td class="formTdRight" style="width: 70%">
									<input type="password" name="password" value="" id="password" class="input" />
									<div id="passwordMsg"></div>
								</td>
							</tr>
							<tr>
								<td class="formTdLeft" style="width: 20%">新密码确认：</td>
								<td class="formTdRight" style="width: 70%">
									<input type="password" name="passwordConfirm" value="" id="passwordConfirm" class="input" />
									<div id="passwordConfirmMsg"></div>
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
			<td><a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="submitFormMmxg()">保存</a> &nbsp;&nbsp; <a id="btnCancel"
				href="javascript:void(0)" class="easyui-linkbutton"
				onclick="cancelClick();">取消</a></td>
		</tr>
	</table>
	</div>
</body>
</html>