<%@ page language="java" import="java.text.SimpleDateFormat,java.util.Date" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<link rel="stylesheet" type="text/css" href="css/admin/general.css" />
</head>

<body>
<div class="top">管理中心首页</div>
<div class="main">
<table width="100%" border="0" cellpadding="2" cellspacing="2">
<tr>
	<td width="110"><img src="images/admin/admin_p.gif"/></td>
	<td align="left">
		当前时间：<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())%><br/> <br/>
		<span style="color:#000000;font-size:20px;font-weight:900">${sessionScope.user.username}</span><br /><br />
		欢迎进入网站管理中心！	</td>
</tr>
<tr>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
</tr>
<tr>
	<td>&nbsp;</td>
	<td>服务器配置信息</td>
</tr>
<tr>
	<td>&nbsp;</td>
	<td>主机地址：<%=request.getLocalName() %>&nbsp;IP地址：<%=request.getLocalAddr() %></td>
</tr>
<tr>
	<td>&nbsp;</td>
	<td>主机目录：<%=request.getRealPath("/") %></td>
</tr>
<tr>
	<td>&nbsp;</td>
	<td>操作系统：<%=System.getProperties().get("os.name") %></td>
</tr>
<%-- <tr>
	<td>&nbsp;</td>
	<td>WEB服务器：<%=request.getScheme() %></td>
</tr> --%>
<tr>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
</tr>
<tr>
	<td>&nbsp;</td>
	<td>程序相关信息</td>
</tr>
<tr>
	<td>&nbsp;</td>
	<td>程序名称：XFCMS内容管理系统</td>
</tr>
<tr>
	<td>&nbsp;</td>
	<td>当前版本号：1.0.0</td>
</tr>
</table>
</div>
<div class="footer">
<jsp:include page="copyright.jsp" />
</div>
</body>
</html>
