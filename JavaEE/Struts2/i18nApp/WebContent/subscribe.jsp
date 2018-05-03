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
	<s:i18n name="mess">
	<s:text name="subscribePage"></s:text>
	<form action="Subscribe">
		<s:textfield name="email" key="inputEmail"></s:textfield>
		<s:submit value="%{getText('submit')}"></s:submit>
	</form>
	<s:text name="language"></s:text>
	<a href="ChangeLanguage?request_locale=zh_CN"><s:text name="Simplified Chinese"></s:text></a>
	<a href="ChangeLanguage?request_locale=zh_HK"><s:text name="Traditional Chinese"></s:text></a>
 </s:i18n>
</body>
</html>