<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style>
	*{
		margin:auto;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style="align-text:center;">
<div style="margin-top:200px;width:300px;height:300px;">
${errorMsg}
	<form action="user.do?a=checklogin" method="post">
	<table width="100%" border="0">
		<tr>
			<td align="right">用户名:</td>
			<td><input name="username"/></td>
		</tr>
		<tr>
			<td align="right">密码:</td>
			<td><input name="password"/></td>
		</tr>
		<tr>
			<td align="right">验证码:</td>
			<td><input name="verifycode"/>
			<img style="cursor:pointer;" onclick="javascript:this.src='user.do?a=verifycode&'+Math.random()" src="user.do?a=verifycode"  title="看不清换一张" alt="看不清换一张"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" value="登陆"/></td>
		</tr>
	</table>	
</form>
</div>
</body>
</html>