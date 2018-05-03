<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>起始页</title>
</head>
<body>
<!-- 先调用一次action -->
		<s:action name="bookList" namespace="/" ></s:action>
	<s:form action="select">
		<s:set var="ds" value="#{'四川':{'成都','广元','西昌'},'江苏':{'南京','无锡','苏州'},'安徽':{'合肥','六安','芜湖'}}"></s:set>
		<s:doubleselect list="#ds.keySet()" doubleList="#ds.get(top)"
			doubleName="city" name="province" label="请选择城市" labelposition="top" >
		</s:doubleselect>
		
		<s:radio list="#request.bookMap" label="请选择图书" listKey="value"
				listValue="key" name="author">
	 	</s:radio> 
	 	<s:submit value="提交" ></s:submit>
 	</s:form>
</body>
</html>