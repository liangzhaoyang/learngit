<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
	String width = request.getParameter("width");
	String height = request.getParameter("height");
	if (width == null || width.length()<=0)
	{
		width = "100%";
	}
	if (height == null || height.length()<=0)
	{
		height = "250";
	}
%>

<html>
	<head>
	</head>
	<body>
		<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"
			codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0"
			width="<%=width %>" height="<%=height %>" id="graph-2" align="middle">
			<param name="allowScriptAccess" value="sameDomain" />
			<param name="movie" value="open-flash-chart.swf" />
			<param name="quality" value="high" />
			<embed src="open-flash-chart.swf" quality="high" bgcolor="#FFFFFF"
				name="open-flash-chart" align="middle" allowScriptAccess="sameDomain"
				 width="<%=width %>"
				 height="<%=height %>"
				type="application/x-shockwave-flash"
				pluginspage="http://www.macromedia.com/go/getflashplayer" />
		</object>
	</body>
</html>

