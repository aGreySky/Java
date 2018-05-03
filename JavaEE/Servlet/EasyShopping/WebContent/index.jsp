<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="dao.ItemDAO" %>
<%@ page import="entity.Items" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品显示</title>
<script>
function myFunction(id)
				{
					alert("您添加了一件编号为"+id+"的商品!");
				}
</script>
</head>
<body>
<h1>商品预览</h1>
<hr>
<center>
<table width="750" height="60" cellpadding="0" cellspacing="0" border="0">
	 	<tr>
	 		<td>
				<%
					ItemDAO itemdao=new ItemDAO();
					ArrayList<Items> list=itemdao.getAllItems();
					request.setAttribute("list", list);
					
				%>
				<c:forEach var="item" items="${list}" varStatus="it">
				<div>
					<dl>
						<dt>
 							
								
								<dd><a>物品编号：<c:out value="${item.id}"></c:out></a></dd>
								<img src="image/<c:out value="${item.id}"></c:out>.jpg" width="120" height="90"  border="1"> 
 								<dd><a>名称：<c:out value="${item.name}"></c:out></a></dd> 
								<dd><a>产地：<c:out value="${item.city}"></c:out></a></dd> 
 								<dd><a>价格：<c:out value="${item.price}"></c:out>元</a></dd> 
 								<a href="Shopping?id=<c:out value="${item.id}"></c:out>&action=add"><img src="image/cart.png" onclick="myFunction(<c:out value="${item.id}"></c:out>)"></a> 
						
						
					</dl>
				</div>
				<br><br>
				</c:forEach>
				</td>
			</tr>
			
		</table>
		<div class="look"><a href="Cart.jsp"><img alt="查看购物车" src="image/Lcart.jpg"></a></div>
		</center>
</body>
</html>