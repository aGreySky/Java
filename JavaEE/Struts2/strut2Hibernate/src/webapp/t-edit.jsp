<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
//确认删除
function ifConfirm(){
    if (!confirm("确认本次操作？")) {
        window.event.returnValue = false;
    }
}
</script>
</head>
<body>
	<table  width="599" cellspacing="1">
		<tr>
			<td height="50">老师信息</td>
		</tr>
		<s:form action="t-update" method="post">
		<tr>
			<td width="100">老师ID:</td>
			<td><s:textfield name="id" value="%{id}"></s:textfield></td>
		</tr>
		<tr>
			<td width="100">姓名:</td>
			<td><s:textfield name="name" value="%{name}"></s:textfield></td>
		</tr>
		<tr>
			<td width="100">年龄:</td>
			<td><s:textfield name="age" value="%{age}"></s:textfield></td>
		</tr>
		<tr>
			<td><s:submit value="修改" onclick="ifConfirm()"/></td>
		</tr>
		</s:form>
	</table>
</body>
</html>