<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传成功</title>
</head>
<body>
		用户名：<input type="text"  value="<s:property value="username"/>">	<br>
		专业：<input value="<s:property value="subject" />"><br>
		年级：<input value="<s:property value="classes"/>"><br>
		头像：<img src="<s:property value="#request.iconUrl"/>"><br>
		简历：<a href='<s:property value="#request.resumeUrl"/>'><s:property value="#request.resumeUrl"/></a><br>
</body>
</html>