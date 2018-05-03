<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ page import="entity.User" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
  </head>
  
  <body>
    <a>·Ã¿Í </a>
    <%
    if(session.getAttribute("user") == null){
    %>
    <ul>
        <li><a href="signin.jsp">×¢²á</a></li>
        <li><a href="login.jsp">µÇÂ¼</a></li>
    </ul>
    <%
    }else{
    %>
    <ul>
        <li><a href="#">»¶Ó­Äã£¬±àºÅ<%= ((User)session.getAttribute("user")).getId() %></a></li>
        <li><a href="logout">µÇ³ö</a></li>
    </ul>
    <%
    }
    %>

  </body>
</html>
