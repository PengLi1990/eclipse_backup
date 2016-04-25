<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="http://localhost:8080/usermanager/" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
body{
	text-align:center;
}
</style>
</head>

<body>
<div>
当前登陆用户:${sessionScope.user.username}
<hr/>
<a href="user.do?a=list">查看用户</a> 
<a href="jsp/user/add.jsp">添加用户</a>
</div>
</body>
</html>