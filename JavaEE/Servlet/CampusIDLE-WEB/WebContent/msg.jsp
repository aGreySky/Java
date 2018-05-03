<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册信息页面</title>
</head>
<body>
	<%	
		if(request.getAttribute("msg")!=null){ 
	%>
		<h1>恭喜您激活成功，快去<a href='http://localhost:8080/CampusIDLE-WEB/index.html'>登录</a>吧！</h1>
	<%
		}else{
	%>
		<h1>您的激活码有误，请通过邮箱重新激活...</h1>
	<%
		} 
	%>
</body>
</html>