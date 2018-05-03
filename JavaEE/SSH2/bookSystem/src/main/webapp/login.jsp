<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<title>图书管理系统</title>
</head>
<body bgcolor="#71CABF">
<s:form action="login" method="post" theme="simple">
	<table>
		<caption>用户登录</caption>
		<tr>
			<td>登录名<s:textfield name="login.name" size="20"/></td>
		</tr>
		<tr>
			<td>密&nbsp;&nbsp;码<s:password name="login.password" size="21"/></td>
		</tr>
		<tr>
			<td>
				<s:submit value="登录"/>
				<s:reset value="重置"/>
			</td>
		</tr>
		<!-- 验证失败信息或错误信息在这里显示 -->
		<tr>
			<td>
				<font color="red"><s:fielderror/><s:property value="message"/></font>
			</td>
		</tr>
	</table>
</s:form>
</body>
</html>
