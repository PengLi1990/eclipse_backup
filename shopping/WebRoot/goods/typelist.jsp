<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'list.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/admin/general.css" />

</head>

<body>
	<div class="top">&nbsp;商品分类列表</div>
	<div class="main">
		<table width="700" cellpadding="1" border="0" cellspacing="1">
		<tr>
			<th>id</th>
			<th>商品名称</th>
			<th>分类</th>
		</tr>
			<s:iterator value="gtlist">
			<tr class="trtd">
				<td><s:property value="id"/></td>
				<td><s:property value="pid"/></td>
				<td><s:property value="typename"/></td>
			</tr>
			</s:iterator>
		</table>
	</div>
	<div class="footer"><jsp:include page="/admin/copyright.jsp"/></div>
</body>
</html>
