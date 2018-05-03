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
	<table bgcolor="#F5FBEF" align="center">
		<tr>
			<td colspan="2">
				<jsp:include page="head.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td align="center">
				<jsp:include page="lendAndReturnConfirmMain.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<font size="2">成都大学：成都市十陵上街一号&nbsp;&nbsp;邮编：610000<br>暂无版权</font>
			</td>
		</tr>
	</table>
</body>
</html>