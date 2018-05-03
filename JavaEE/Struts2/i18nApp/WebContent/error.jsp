<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订阅页面</title>
</head>
<body>
		<s:text name="email1"></s:text>&nbsp;&nbsp;
  <s:property value="email"/>&nbsp;&nbsp;&nbsp;<br>
  <h3><s:text name="subscribeFailure"></s:text></h3>
</body>
</html>