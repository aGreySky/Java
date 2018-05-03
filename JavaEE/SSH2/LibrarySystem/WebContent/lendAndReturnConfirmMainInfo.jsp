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
	<table border="1" align="center" width="850" cellpadding="10" cellspacing="0" bgcolor="#71cabf" class="font1">
		<tr bgcolor="#e9edf5">
			<th>ISBN</th><th>借书证号</th><th>操作</th><th>选择</th>
		</tr>
		<s:iterator value="#request.list" var="lend">
		<tr align="center">
			<td><s:property value="#lend.ISBN"></s:property></td>
			<td><s:property value="#lend.readerId"></s:property></td>
			<s:if test="!#lend.lendConfirm">
				<td>借阅</td>
				<td>
					<a href="lendBookConfirm.action?lend.id=<s:property value="#lend.id"/>&confirm=true" onclick="ifConfirm()">确认</a>&nbsp;&nbsp;
					<a href="lendBookConfirm.action?lend.id=<s:property value="#lend.id"/>&confirm=false" onclick="ifConfirm()">拒绝</a>
				</td>
			</s:if>
			<s:elseif test="#lend.returnRequest">
				<td>归还</td>
				<td>
					<a href="returnRequestConfirm.action?lend.id=<s:property value="#lend.id"/>&confirm=true" onclick="ifConfirm()">确认</a>&nbsp;&nbsp;
					<a href="returnRequestConfirm.action?lend.id=<s:property value="#lend.id"/>&confirm=false" onclick="ifConfirm()">拒绝</a>
				</td>
			</s:elseif>
			
			
		</tr>
		</s:iterator>
	</table>
</body>
</html>