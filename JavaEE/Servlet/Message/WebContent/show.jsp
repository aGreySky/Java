<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page  import="entity.GuestBook" %>
<%@ page  import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示页面</title>
</head>
<body>

	<%  
		List list=new ArrayList();
		list=(ArrayList)application.getAttribute("list");
	%>
<table  border="2" height="200" rules="rows">
<tr>
<td  >用户名</td><td>标题</td><td>留言内容</td>
</tr>
<tr>
<%for (int i=0;i<list.size();i++){
	  GuestBook ws=(GuestBook)list.get(i);

	%>
	<td><%=ws.getName() %></td>
	<td ><%=ws.getTitle() %></td>
	<td ><%=ws.getContent() %></td>
</tr>
 
<%
}
%>
	<tr>
		<td colspan="2" align="center"><a href="index.jsp">继续留言</a></td>
	</tr>
</table>
</body>
</html>