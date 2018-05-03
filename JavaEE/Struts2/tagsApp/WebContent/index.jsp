<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>初始页</title>
</head>
<body>

		<s:bean name="vo.Person" var="per">
			<s:param name="name" >tom</s:param>
			<s:param name="birthday" >1996-6-1</s:param>
		</s:bean>
		
	
		<s:property   value="#per.name"/>
		<br>
		<s:date name="#per.birthday" format="yyyy年MM月dd日"/>
		<s:action name="tag" namespace="/" executeResult="false">
			<s:param name="per.name" >tom</s:param>
			<s:param name="per.birthday" >1996-6-1</s:param>
		</s:action>
		<br>
		<jsp:include page="success.jsp"></jsp:include>
		<br>
		<s:url  value="tag">
			<s:param name="id">101</s:param>
		</s:url>
</body>
</html>