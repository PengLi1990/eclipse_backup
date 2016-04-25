<%@ page language="java" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../../top.jsp"></jsp:include>
<table width="600" border="1" align="center">
<tr>
<th>用户编号</th>
<th>头像</th>
<th>用户名</th>
<th>部门</th>
<th>操作</th>
</tr>
<c:forEach items="${userList}" var="u">
	<tr>
	<td>${u.id}</td>
	<td><img width="100" height="100" src="${u.pic}"></td>
	<td> ${u.username}</td>
	<td> 
		<c:if test="${u.deptno==1}" >销售部</c:if>
		<c:if test="${u.deptno==2}" >财务部</c:if>
		<c:if test="${u.deptno==3}" >技术部</c:if>
	</td>
	<td><a href="user.do?a=del&id=${u.id}">删除</a></td>
	</tr>
</c:forEach>
</table>
</body>
</html>