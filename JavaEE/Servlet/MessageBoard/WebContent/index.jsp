<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>留言板</title>
</head>
<body>
<%if(request.getAttribute("tishi")==null){ %>
<form action="message" method="post">
	留言者：<input type="text" name="name" size="25">
	<br>
	留言标题：<input type="text" name="title" size="31">
	<br>
	留言内容：<textarea name="content" row="7" cols="30"></textarea>
	<p>
	<input type="submit" value="递交">
	<input type="reset" value="重置">
</form>
<%}else{ %>
<a href="show.jsp"><%=request.getAttribute("tishi") %></a>
<%} %>
</body>
</html>