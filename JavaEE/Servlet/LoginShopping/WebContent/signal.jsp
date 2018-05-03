<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册界面</title>
    
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
  %>
  <a>已有账号？</a><a href="login.jsp">登录</a>
   <form action="signal" method="post">
   <a>用户名：</a><input type="text" name="username" placeholder="请输入用户名"><br>
   <a>密码：</a><input type="password" name="password" placeholder="请输入密码"><br>
   <a>确认密码：</a><input type="password" name="repassword" placeholder="请再次输入密码"><br>
   <button type="submit">注册</button>
   </form>
  </body>
</html>
