<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>ע�����</title>
    
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
	<div><strong>���棡<%=request.getAttribute("error") %></strong></div>
	<% }
	 %>
  <a>�����˺ţ�</a><a href="login.jsp">��¼</a>
   <form action="servlet/Signal" method="post">
   <a>�û�����</a><input type="text" name="username" placeholder="�������û���">
   <a>���룺</a><input type="password" name="password" placeholder="����������">
   <a>ȷ�����룺</a><input type="password" name="repassword" placeholder="���ٴ���������">
   <button type="submit">ע��</button>
   </form>
  </body>
</html>
