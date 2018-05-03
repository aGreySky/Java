<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示页</title>
</head>
<body>
<!-- 先访问一次action -->
	<s:action name="index" namespace="/"></s:action>
		籍贯是四川的学生姓名：
		<s:iterator value="#session.studentList" var="stu">
			<s:if test="#stu.address=='四川'">
				<s:property value="#stu.stuName"/>
			</s:if>
		</s:iterator>
		<br><br>
		年龄小于20的第一个学生的姓名：
		<s:iterator value="#session.studentList.{^#this.age<20}" var="stu">
			<s:property value="#stu.stuName"/>
		</s:iterator>
		<br><br>
		最后一个女同学的姓名：
		<s:iterator value="#session.studentList.{$#this.sex==false}" var="stu">
			<s:property value="#stu.stuName"/>
		</s:iterator>
		<br><br>
		所有年龄为18的学生籍贯：
		<s:iterator value="#session.studentList" var="stu" >
			<s:if test="#stu.age=='18'">
				<s:property value="#stu.address"/>
			</s:if>
		</s:iterator>
		<br><br>
		获取名为“李伟”的学生的学号：
		<s:iterator value="#session.studentList" var="stu" >
			<s:if test="#stu.stuName=='李伟'">
				<s:property value="#stu.stuNum"/>
			</s:if>
		</s:iterator>
	<br><br>
	E值:
  	<s:property value="@java.lang.Math@E"/>
  	<br><br>
  	环境变量:
 	 <s:property value="#session.CLASSPATH"/>
</body>
</html>