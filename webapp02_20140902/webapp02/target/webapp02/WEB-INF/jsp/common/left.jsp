<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
%>
	<c:forEach items="${menus}" var="menu">
		<c:choose>
			<c:when test="${not empty menu.entryPoint}">
				<div class="panel">
					<div style="width:100%;" class="twolevel auto panel-body accodion-body" title="" data-options="selected:false">
						<a href="javascript:void(0);" onclick="javascript:changeRightFrame('${menu.entryPoint}');" style="white-space: nowrap">${menu.name}</a>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="panel">
					<div class="panel-header accordion-header accordion-header-selected">
						<div class="panel-title">${menu.name}</div>
						<div class="panel-tool">
							<a class="accordion-collapse" href="javascript:void(0)"></a>
						</div>
					</div>
					<div style="width:100%;" class="twolevel auto panel-body accodion-body" title="${menu.name}" data-options="selected:false">
						<c:forEach items="${menu.subItems}" var="subMenu">
						<!-- <a href="javascript:changeRightFrame('${subMenu.entryPoint}');" style="white-space: nowrap">${subMenu.name}</a> -->
						<a href="javascript:void(0);" onclick="changeRightFrame('${subMenu.entryPoint}');" style="white-space: nowrap">${subMenu.name}</a>					
						</c:forEach>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</c:forEach>
