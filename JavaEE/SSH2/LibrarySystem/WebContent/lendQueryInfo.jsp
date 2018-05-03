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
	<table height="430" border="1" width="650">
		<tr>
			<td height="380" valign="top">
				<table  border="1" align="center" width="570" cellpadding="10" cellspacing="0" bgcolor="#71cabf" class="font1">
					<tr bgcolor="#e9edf5">
						<th>图书ID</th><th>ISBN</th><th>书名</th><th>出版社</th><th>价格</th><th>借书时间</th><th>选择</th>
					</tr>
					<s:iterator value="#request.list" var="lend">
					<tr>
						<td><s:property value="#lend[0]"></s:property></td>
						<td><s:property value="#lend[1]"></s:property></td>
						<td><s:property value="#lend[2]"></s:property></td>
						<td><s:property value="#lend[3]"></s:property></td>
						<td><s:property value="#lend[4]"></s:property></td>
						<td><s:date name="#lend[5]" format="yyyy-MM-dd"></s:date></td>
						<td><a href="sendToReturnBook.action?lend.ISBN=<s:property value="#lend[1]"/>&lend.readerId=<s:property value="#request.readerId"/>">归还</a>
					</tr>
					</s:iterator>
				</table>
			</td>
		</tr>
		<tr>
			<td align="center">
				<font color="red"><s:property value="message"></s:property></font>
			</td>
		</tr>
		<s:if test="#request.readerId!=null">
		<tr bgcolor="#e9edf5" class="font1">
			<td align="right">
				<!-- 取出page -->
				<s:set var="page" value="#request.page" ></s:set>
				<!-- 首页始终显示第一页 -->
				<a href="selectBook.action?pageNow=1&lend.readerId=<s:property value="#request.readerId"/>">首页</a>
				<!-- 如果有前一页就递交前一页的pageNow值 -->
				<s:if test="#page.hasPre">
					<a href="selectBook.action?pageNow=<s:property value="#page.pageNow-1"/>&lend.readerId=<s:property value="#request.readerId"/>">上一页</a>
				</s:if>
				<!-- 如果没有就递交第一页的pageNow -->
				<s:else>
					<a href="selectBook.action?pageNow=1&check=1&lend.readerId=<s:property value="#request.readerId"/>">上一页</a>
				</s:else>
				<a>【 <s:property value="#page.pageNow"/>/<s:property value="#page.totalPage"/>】</a>
				<!-- 如果有下一页就递交下一页的pageNow值 -->
				<s:if test="#page.hasNext">
					<a href="selectBook.action?pageNow=<s:property value="#page.pageNow+1"/>&lend.readerId=<s:property value="#request.readerId"/>">下一页</a>
				</s:if>
				<!-- 如果没有就递交尾页的pageNow -->
				<s:else>
					<a href="selectBook.action?pageNow=<s:property value="#page.totalPage"/>&check=2&lend.readerId=<s:property value="#request.readerId"/>">下一页</a>
				</s:else>
				<!-- 尾页始终递交最后一页的pageNow值 -->
				<a href="selectBook.action?pageNow=<s:property value="#page.totalPage"/>&lend.readerId=<s:property value="#request.readerId"/>">尾页</a>
			</td>
		</tr>
		</s:if>
	</table>
</body>
</html>