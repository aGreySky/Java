<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统</title>
<style type="text/css">
	.font{
		font-size:13px;
	}
</style>
</head>
<body>
	<s:form action="selectBook" method="post" theme="simple">
		<table border="1" width="200" cellspacing="1" class="font1">
			<tr bgcolor="#F0F8FF">
				<td>内容选择</td>
			</tr>
			<tr>
				<td align="left" valign="top" height="400">
					<br>借书证号：<br><br>
					<s:if test="#session.student!=null">
						<s:textfield name="lend.readerId" size="15" value="%{#session.student.readerId}" readonly="true"></s:textfield>
					</s:if>
					<s:else>
						<s:textfield name="lend.readerId" size="15"></s:textfield>
					</s:else>
					<s:submit value="查询"></s:submit>
				</td>
			</tr>
		</table>
	</s:form>
	
</body>
</html>