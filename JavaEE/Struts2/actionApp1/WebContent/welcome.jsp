<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a>欢迎你，<s:property value="username"></s:property></a><br>
	服务器处理结果：<s:property value="message"></s:property><br>
	服务器访问次数：<s:property value="#application.num"></s:property><br>
	<s:fielderror></s:fielderror>
</body>
</html>