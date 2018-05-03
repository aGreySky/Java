<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.messageDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<% 
		messageDAO MD=new messageDAO();
		String[] content=MD.turn();
	%>
<div>
	<a>留言者：<%=content[0] %></a>
	<a>留言标题：<%=content[1] %></a>
	<a>留言内容：<%=content[2] %></a>
</div>

</body>
</html>