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
	<table border="1" width="200" cellspacing="1" class="font1">
		<tr bgcolor="#E9EDF5">
			<td>功能选择</td>
		</tr>
		<tr>
			<td align="left" valign="top" height="400">
				<br><s:submit value="读者查询" method="selectStudent"></s:submit> 
				<br><s:submit value="读者删除" method="deleteStudent"></s:submit> 
				<br><s:submit value="读者修改" method="updateStudent"></s:submit> 
				<br><s:submit value="读者新加" method="addStudent"></s:submit> 
			</td>
		</tr>
	</table>
</body>
</html>