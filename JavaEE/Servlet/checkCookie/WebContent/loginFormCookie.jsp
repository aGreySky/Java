<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录界面</title>
</head>
<body>
	
	登录：<form action="LoginCookie" method="post">
		账号：<input type="text" name="stuname"><BR>
		密码：<input type="text" name="stupwd"><BR>
		  	  <input type="checkbox" checked="checked"> 在一个月之内保存登录状态<br>
	      	  <input type="submit" value="登录">
	      </form>
</body>
</html>