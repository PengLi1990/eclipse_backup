<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  <form action="savegbook" method="post" enctype="multipart/form-data">
  <table width="300" border="0" align="center">
  	<s:textfield label="用户名" name="username"></s:textfield>
  	<s:file label="头　像" name="pic"/>
  	<s:textfield label="密　码" name="password"></s:textfield>
  	<s:textfield label="标　题" name="title"></s:textfield>
  	<s:textfield label="内　容" name="content"></s:textfield>
  	<s:submit value="添加留言"/>
  </table>
   </form>
  </body>
</html>
