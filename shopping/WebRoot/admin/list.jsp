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

   		<table width="700" align="center" border="0" cellpadding="0" cellspacing="0">
   		<s:iterator value="list">
			<tr><td colspan="3"><img width="100" height="100" src='<s:property value="header"/>'/></td></tr>
			<tr>
				
				<td>用户名：<s:property value="title"/></td>
				<td>标题：<s:property value="username"/></td>
				<td>时间：<s:property value="gtime"/></td>
			</tr> 
			<tr>
				<td colspan="3">内容：<s:property value="content"/></td>
			</tr>  	
			<tr><td colspan="3">回复：
			<hr/>
				<s:iterator value="replys">
					<s:property value="content" />&nbsp;<s:property value="rtime" /><br/>
				</s:iterator>
				<form action="savereply" method="post">
					<input type="hidden" name="gid" value='<s:property value="id"/>'/>
					<textarea name="replycontent" cols=20></textarea>
					<input type="submit" value="回复"/>
				</form>
			</td></tr>
			<tr><td colspan="3">&nbsp;</td></tr>		
		</s:iterator>
   		</table>

  </body>
</html>
