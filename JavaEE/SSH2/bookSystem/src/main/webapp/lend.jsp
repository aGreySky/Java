<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
	<title>图书管理系统</title>
	<style>
		.font1{font-size:13px;}
	</style>
</head>
<body>
	<table bgcolor="#71CABF" align="center" >
		<tr>
			<td colspan="2"><jsp:include page="head.jsp"/></td>
		</tr>
		<tr>
			<td><jsp:include page="search.jsp"/></td>
			<td><jsp:include page="lendbook.jsp"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center" class="font1">
					南京师范大学：南京市宁海路122号&nbsp;&nbsp;邮编：210097<br>师教教育研究中心版权所有2010-2015
				</td>
			</tr>
	</table>
</body>
</html>
