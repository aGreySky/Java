<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
	<table bgcolor="#e9edf5" height="440" border="1" width="900">
		<tr height="400">
			<td align="center">
			<s:if test="#request.book==null">
				<img alt="" src="images/default.jpg" width="300">
			</s:if>
			<s:else>
				<s:set var="book" value="#request.book"></s:set>
				<s:if test="#book.photo!=null">
					<img alt="" src="getImage.action?book.ISBN=<s:property value="#book.ISBN"/>" id="image" width="300" style="max-height: 400px">
				</s:if>
				<s:else>
					<img alt="" src="images/default.jpg" id="image" width="300">
				</s:else>
			</s:else>
			</td>
		</tr>
		<!-- 还书表单  -->
		<s:form action="returnBook" method="post" theme="simple" validate="true">
			<tr class="font1">
					<td colspan="2" align="center">
						ISBN号<s:textfield name="lend.ISBN" size="30" value="%{#request.lend.ISBN}"></s:textfield>
						借书证号
						<s:if test="#session.student!=null">
							<s:textfield name="lend.readerId" size="30" value="%{#session.student.readerId}" readonly="true"></s:textfield>
						</s:if>
						<s:else>
							<s:textfield name="lend.readerId" size="30" value="%{#request.lend.readerId}"></s:textfield>
						</s:else>
						<s:submit value="预览" method="readBook"></s:submit>
						<s:submit value="还书" method="returnBook"></s:submit>
					</td>
			</tr>
		</s:form>
		<tr>
			<td colspan="4" align="center">
				<font color="red"><s:property value="message"></s:property></font>
			</td>
		</tr>
	</table>
</body>
</html>