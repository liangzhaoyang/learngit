<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>按照业务类型统计</title>
<jsp:include page="/component/common/commonhead2.jsp" />
<script type="text/javascript" src="<%=path%>/js/log/logsQuery.js"></script>
<script type="text/javascript"> var path="<%=path%>"
</script>
</head>
<body id="bodydiv" class="easyui-layout">
	<div data-options="region:'center'" style="padding: 5px; border: 0">
		<div id="tb" style="padding: 5px; height: auto">
			<div style="margin-bottom: 2px">
				<form name="rizifrm" id="rizifrm" action="" method="post">
					<table>
						<tr>
							<td><label class="laber-info">用户名称:</label><input
								type="text" id="name" name="name" maxlength="30"/></td>
							<td><label class="laber-info">日志类型:</label> <select
								name="logtype" id="logtype" class="select" style="width: 50%"
								onchange="selChange(this.value)">
									<option value="0" selected="selected">请选择</option>
									<c:forEach var="xzjb" items="${logtypeList}">

										<option value="${xzjb.code}">${xzjb.name}</option>
									</c:forEach>
							</select> <!-- <input type="text" id="logtype" name="logtype" /> --></td>
							<td><label class="laber-info">产生时间范围:</label><input
								type="text" id="dateFrom" name="dateFrom"
								class="easyui-datetimebox" /></td>
							<td><label class="laber-info">~</label><input type="text"
								id="dateTo" name="dateTo" class="easyui-datetimebox" /></td>
							<td><label class="laber-info">描述:</label><input type="text"
								id="miaoshu" name="miaoshu" maxlength="100"/></td>
							<td style="width: 20px"></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton"
								onclick="query();">查询</a></td>
						</tr>
					</table>
				</form>
			</div>

			<table id="tbLog" class="easyui-datagrid"></table>
		</div>
	</div>
</body>
</html>