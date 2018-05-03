<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
	<title>图书管理系统</title>
</head>
<body>
	<table bgcolor="#71CABF" align="center">
		<tr>
			<td colspan="2"><jsp:include page="head1.jsp"/></td>
		</tr>
		<tr>
			<td height="400"/>
		</tr>
		<!-- 验证失败信息或错误信息在这里显示 -->
		<tr>
			<td align="center">
				<font color="red"><s:fielderror/><s:property value="message"/></font>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<font size="2">南京师范大学：南京市宁海路122号&nbsp;&nbsp;邮编：210097<br>	师教教育研究中心版权所有2010-2015</font>
			</td>
		</tr>
	</table>
</body>
</html>
