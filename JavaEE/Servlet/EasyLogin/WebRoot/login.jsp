<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
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
  <% if(request.getAttribute("error") !=null){
  %>
	<div><strong>警告！<%=request.getAttribute("error") %></strong></div>
	<% }
	 %>
	 <% if(request.getAttribute("tishi") !=null){
  %>
	<div><strong>提示！<%=request.getAttribute("tishi") %></strong></div>
	<% }
	 %>
    <a>没有账号？</a><a href="signal.jsp">注册</a>
  	<form action="servlet/Login" method="post">
    	<a>用户名：</a><input type="text" name="username">
    	<a>密码：</a><input type="password" name="password">
    	<button type="submit">登录</button>
  	</form>
  </body>
</html>
