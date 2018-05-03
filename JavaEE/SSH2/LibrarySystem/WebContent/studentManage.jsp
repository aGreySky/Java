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
<script language="javascript"> 
	function check(thisObject){
		var sTmp="";
		sTmp=thisObject.value;
		var a = /^(\d{4})-(\d{2})-(\d{2})$/
		if (!a.test(sTmp)) { 
		alert("日期格式不正确!正确格式如：1997-01-01") 
		return false 
	} 
	else 
		return true 
	} 
</script>
</head>
<body>
	<table bgcolor="#F5FBEF" align="center" class="font1">
		<tr>
			<td colspan="2">
				<jsp:include page="head.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<s:form theme="simple" action="student" method="post" enctype="multipart/form-data" validate="true">
				<td>
					<jsp:include page="studentManageFunctionSelect.jsp"></jsp:include>
				</td>
				<td>
					<jsp:include page="studentManageInfo.jsp"></jsp:include>
				</td>
			</s:form>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<font size="2">成都大学：成都市十陵上街一号&nbsp;&nbsp;邮编：610000<br>暂无版权</font>
			</td>
		</tr>
	</table>
</body>
</html>