<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../../top.jsp" flush="false" />
<br/>
<form action="user.do?a=save" method="post" enctype="multipart/form-data">
<table width="400" border="0" align="center">
	<tr>
		<td align="right">用户名:</td>
		<td align="left"><input type="text" name="username"/></td>
	</tr>
	<tr>
		<td align="right">头像:</td>
		<td align="left"><input type="file" name="pic"/></td>
	</tr>
	<tr>
		<td align="right">密码:</td>
		<td align="left"><input name="password"/></td>
	</tr>
	<tr>
		<td align="right">权限:</td>
		<td align="left">
			<select name="rule">
				<option value="1">超级管理员</option>
				<option value="2">管理员</option>
				<option value="3">普通用户</option>
			</select>
		</td>
	</tr>
	<tr>
		<td align="right">部门:</td>
		<td align="left">
			<select name="dept">
				<option value="1">销售部</option>
				<option value="2">技术部</option>
				<option value="3">财务部</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td align="left"><input type="submit" value="注册"/></td>
	</tr>
</table>
</form>
</body>
</html>