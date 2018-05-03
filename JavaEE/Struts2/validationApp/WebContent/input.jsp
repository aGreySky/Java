<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>输入页</title>
</head>
<body>
	<form action="SaveBook">
		书名：<input name="book.name"><br>
		作者：<input name="book.author"><br>
		书号：<input name="book.number"><br>
		出版社：<input name="book.publisher"><br>
		出版日期：<input name="book.publishDate"><br>
		价格：<input name="book.price"><br>
		库存数量：<input name="book.sum"><br>
		<input type="submit" value="提交">
		<a style="color: red"><s:fielderror></s:fielderror></a>
	</form>
</body>
</html>