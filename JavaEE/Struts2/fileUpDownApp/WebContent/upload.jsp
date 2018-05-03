<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传</title>
</head>
<body>
	<form action="Upload"   method="post" enctype="multipart/form-data">
		用户名：<input name="username" type="text">	<br>
		专业：<select name="subject">
			<option value="软件工程">软件工程</option>
			<option value="生物工程">生物工程</option>
			<option value="机械工程">机械工程</option>
		</select><br>
		年级：<select name="classes">
			<option value="15级">15级</option>
			<option value="16级">16级</option>
			<option value="17级">17级</option>
		</select><br>
		头像：<input  type="file" name="icon" multiple><br>
		简历：<input  type="file" name="resume" multiple><br>
		<input type="submit" value="上传">
		<a style="color: red"><s:property value="#request.error" /></a>
		<a style="color: red"><s:fielderror></s:fielderror></a>
	</form>
</body>
</html>