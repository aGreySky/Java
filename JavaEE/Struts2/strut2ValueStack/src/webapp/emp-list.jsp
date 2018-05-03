<%@ page language="java" contentType="text/html; charset=UTF-8"
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
			<th>员工号</th><th>姓</th><th>名</th><th>邮箱</th><th>操作</th>
		</tr>
		
		<s:iterator value="#request.list" var="emp">
		<tr align="center">
			<td><s:property value="#emp.employeeId"></s:property></td>
			<td><s:property value="#emp.firstName"></s:property></td>
			<td><s:property value="#emp.lastName"></s:property></td>
			<td><s:property value="#emp.email"></s:property></td>
			<td><a href="emp-edit.action?emp.employeeId=<s:property value="#emp.employeeId"/>">修改</a>&nbsp;&nbsp;<a href="emp-delete.action?emp.employeeId=<s:property value="#emp.employeeId"/>">删除</a></td>
		</tr>
		</s:iterator>
	</table>
	<br>
	<center><a href="emp-add.jsp">新增员工</a></center>
</body>
</html>