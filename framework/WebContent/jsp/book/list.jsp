<%@ page language="java"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
.pages span{
	background-color:#999999;
	border:1px solid #666;
}
</style>
<title>Insert title here</title>
</head>
<body>
<c:forEach items="${list}" var="b">
${b.id },${b.bookname},${b.price}, <a href="test.do?a=delete&id=${b.id}">del</a><br/>
</c:forEach>
<br/>
<div class="pages">${pageText}</div>

</body>
</html>