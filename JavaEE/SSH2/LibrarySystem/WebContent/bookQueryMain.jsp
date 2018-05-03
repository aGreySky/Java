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
	<table border="1" width="900">
		<!-- 借书表单  -->
		<s:form action="findBook" method="post" theme="simple">
			<tr bgcolor="#e9edf5" class="font1">
					<td colspan="2" align="center">
						图书信息<s:textfield name="word" size="30"></s:textfield>
						<s:submit value="搜索"></s:submit>
					</td>
			</tr>
			
		</s:form>
		<tr>
			<td height="360" valign="top">
				<jsp:include page="bookQueryMainInfo.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td align="center">
				<font color="red"><s:property value="message"></s:property></font>
			</td>
		</tr>
		<tr bgcolor="#e9edf5" class="font1">
			<td align="right">
				<!-- 取出page -->
				<s:set var="page" value="#request.page" ></s:set>
				<!-- 首页始终显示第一页 -->
				<a href="findBook.action?pageNow=1&word=<s:property value="#request.word"/>">首页</a>
				<!-- 如果有前一页就递交前一页的pageNow值 -->
				<s:if test="#page.hasPre">
					<a href="findBook.action?pageNow=<s:property value="#page.pageNow-1"/>&word=<s:property value="#request.word"/>">上一页</a>
				</s:if>
				<!-- 如果没有就递交第一页的pageNow -->
				<s:else>
					<a href="findBook.action?pageNow=1&check=1&word=<s:property value="#request.word"/>">上一页</a>
				</s:else>
				<a>【 <s:property value="#page.pageNow"/>/<s:property value="#page.totalPage"/>】</a>
				<!-- 如果有下一页就递交下一页的pageNow值 -->
				<s:if test="#page.hasNext">
					<a href="findBook.action?pageNow=<s:property value="#page.pageNow+1"/>&word=<s:property value="#request.word"/>">下一页</a>
				</s:if>
				<!-- 如果没有就递交尾页的pageNow -->
				<s:else>
					<a href="findBook.action?pageNow=<s:property value="#page.totalPage"/>&check=2&word=<s:property value="#request.word"/>">下一页</a>
				</s:else>
				<!-- 尾页始终递交最后一页的pageNow值 -->
				<a href="findBook.action?pageNow=<s:property value="#page.totalPage"/>&word=<s:property value="#request.word"/>">尾页</a>
			</td>
		</tr>
	</table>
</body>
</html>