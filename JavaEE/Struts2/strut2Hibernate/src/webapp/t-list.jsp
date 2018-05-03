<%@ page language="java"  contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" align="center" width="850" cellpadding="10" cellspacing="0" bgcolor="#71cabf">
		<tr bgcolor="#e9edf5">
			<th>老师ID</th><th>姓名</th><th>年龄</th><th>操作</th>
		</tr>
		<s:iterator value="#request.list">
		<tr align="center">
			<td>${id }</td>
			<td>${name }</td>
			<td>${age }</td>
			<td><a href="t-edit.action?id=${id }">修改</a>&nbsp;&nbsp;<a href="t-delete.action?id=${id }">删除</a></td>
		</tr>
		</s:iterator>
	</table>
	<br>
	<center><a href="t-add.jsp">新增员工</a></center>
</body>
</html>