<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<style>
	.font1{font-size:13px;}
</style>
</head>
<body>
<table  border="1" align="center" width="570" cellspacing="0"  bgcolor="#71CABF" class="font1">
	<tr bgcolor="#E9EDF5">
	<th>图书ID</th><th>ISBN</th><th>书名</th><th>出版社</th><th>价格</th><th>借书时间</th>
	</tr>
	<s:iterator value="#request.list" var="lend">
	<tr>
		<td><s:property value="#lend[0]"/></td>
		<td><s:property value="#lend[1]"/></td>
		<td><s:property value="#lend[2]"/></td>
		<td><s:property value="#lend[3]"/></td>
		<td><s:property value="#lend[4]"/></td>
		<td><s:date name="#lend[5]" format="yyyy-MM-dd"/></td>
	</tr>
	</s:iterator>
</table>

</body>
</html>