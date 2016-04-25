<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/css/admin/top.css" type="text/css" rel="stylesheet" />
</head>
<body>
<div class="main">
<div class="top_left"><img src="/images/admin/header_left.jpg"/></div>
<div class="center">
<span style="color:#FFFFFF;">
当前管理员：${sessionScope.user.username}</span>&nbsp;&nbsp;
<a href="admin.php?do=menu_system" target="leftFrame">系统设置</a>&nbsp;&nbsp;
<!--
<a href="admin.php?do=menu_content" target="leftFrame">内容管理</a>&nbsp;&nbsp;
<a href="admin.php?do=menu_category" target="leftFrame">栏目管理</a>&nbsp;&nbsp;
<a href="admin.php?do=menu_template" target="leftFrame">模板管理</a>&nbsp;&nbsp;
<a href="admin.php?do=reflash" target="mainFrame">更新管理</a>&nbsp;&nbsp;
<a href="admin.php?do=ext" target="mainFrame">插件管理</a>&nbsp;&nbsp;
-->
<a href="main.jsp" target="mainFrame">管理首页</a>&nbsp;&nbsp;

<a href="/" target="_blank">网站预览</a>&nbsp;&nbsp;

<a href="javascript:if(confirm('确定退出吗？')){location.href='logout.action'}" target="_parent">退出系统</a>	 
</div>
<div class="top_right"><img src="/images/admin/header_right.jpg"/></div>
</div>
</body>
</html>
