<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统</title>
<style type="text/css">
	.font1{
		font-size:13px;
	}
</style>
</head>
<body>
	<table border="1" align="center" width="850" cellpadding="10" cellspacing="0" bgcolor="#71cabf" class="font1">
		<tr bgcolor="#e9edf5">
			<th>封面</th><th>ISBN</th><th>书名</th><th>作者</th><th>出版社</th><th>价格</th><th>库存</th><th>简介</th><th>选择</th>
		</tr>
		<s:iterator value="#request.list" var="book">
		<tr align="center">
			<s:if test="#book.photo!=null">
				<td><img src="getImage.action?book.ISBN=<s:property value="#book.ISBN"/>" id="image" width="50" height="60"></td>
			</s:if>
			<s:else>
				<td><img src="images/default.jpg" id="image" width="50" height="60"></td>
			</s:else>
			<td><s:property value="#book.ISBN"></s:property></td>
			<td><s:property value="#book.bookName"></s:property></td>
			<td><s:property value="#book.author"></s:property></td>
			<td><s:property value="#book.publisher"></s:property></td>
			<td><s:property value="#book.price"></s:property></td>
			<td><s:property value="#book.snum"></s:property></td>
			<td align="left" style="max-width: 200px"><s:property value="#book.summary"></s:property></td>
			<td><a href="sendToLendBook.action?book.ISBN=<s:property value="#book.ISBN"/>">借阅</a>
		</tr>
		</s:iterator>
	</table>
</body>
</html>