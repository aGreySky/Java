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
		书名：<s:property value="book.name"/><br>
		作者：<s:property value="book.author"/><br>
		书号：<s:property value="book.number"/><br>
		出版社：<s:property value="book.publisher"/><br>
		出版日期：<s:property value="book.publishDate"/><br>
		价格：<s:property value="book.price"/><br>
		库存数量：<s:property value="book.sum"/><br>
</body>
</html>