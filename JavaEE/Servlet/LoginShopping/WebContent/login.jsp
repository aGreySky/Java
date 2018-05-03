<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录界面</title>
    
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
  <% 
  	if(request.getAttribute("error") !=null){
  %>
		<div><strong>警告！<%=request.getAttribute("error") %></strong></div>
	<% 
	}
	 if(request.getAttribute("warn") !=null){
  	%>
		<div><strong>提示！<%=request.getAttribute("warn") %></strong></div>
	<% 
	}
	 %>
    <a>没有账号？</a><a href="signal.jsp">注册</a>
  	<form action="login" method="post">
    	<a>用户名：</a><input type="text" name="username">
    	<br>
    	<a>密码：</a><input type="password" name="password">
    	<br>
    	<button type="submit">登录</button>
  	</form>
  </body>
</html>
