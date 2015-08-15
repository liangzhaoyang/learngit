<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>机构导入</title>
<jsp:include page="/component/common/commonhead2.jsp" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/common/iframeStyle.css">
<script>
	$(function() {
		$("#btnSubmit").click(function() {
			$('#frm').form('submit', {
				onSubmit : function() {
					var reg = /.zip$/;
					if (!reg.test($("#fileName").val())) {
						$("#errMsg").html("文件类型不是zip，请确认。");
						return false;
					}
					$("#errMsg").html("");
					return true;
				},
				success : function(data) {
					var json = $.parseJSON(data);
					if (json.errMsg == null && json.dbErr == null) {
						alert("机构导入成功");
					} else {
						if (json.errMsg != null) {
						}
						if (json.dbErr != null) {
							alert(json.dbErr);
						}
					}
				}
			});
		});
	});
</script>
</head>
<body id="bodydiv" class="easyui-layout">
	<div data-options="region:'center'" style="padding: 5px; border: 0">
		<form name="frm" id="frm" action="doJgdr.do" method="POST" enctype="multipart/form-data">
			<table>
				<tr>
					<td>
				<input type="file" id="fileName" name="fileName" value="选择文件" />
					</td>
				</tr>
				<div id="errMsg"></div>
		</form>
		<table width="100%" class="buttonTable">
			<tr>
				<td><a id="btnSubmit" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save">导入</a></td>
			</tr>
		</table>
	</div>
</body>
</html>