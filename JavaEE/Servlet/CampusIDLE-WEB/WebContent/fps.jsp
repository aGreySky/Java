<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设置新密码结果页面</title>
</head>
<body>
	<%	
		if(request.getAttribute("fps")!=null){ 
	%>
		<h1>恭喜您设置新密码成功，请务必牢记密码，快去<a href='http://localhost:8080/CampusIDLE-WEB/index.html'>登录</a>吧！</h1>
	<%
		}else{
	%>
		<h1>很抱歉，您的请求未能通过审核，请重新申述...</h1>
	<%
		} 
	%>
</body>
</html>