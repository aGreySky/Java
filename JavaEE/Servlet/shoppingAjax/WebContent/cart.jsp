<%@ page language="java" import="entity.Cart" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="entity.Items" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>您的购物车</title>
<style type="text/css">
	th{
		width:400px;
		font-size: 20px;
	}
</style>
<script>
function myFunction(){

    if (!confirm("确认要删除？")) {
        window.event.returnValue = false;
    }
}
</script>	
</head>
<body>
	<h1>购物车列表</h1>
	<hr>
	<center>
	<table>
			<tr>
				<th>商品名称</th>
				<th>实物图</th>
				<th>商品价格</th>
				<th>操作</th>
			</tr>
			
			<c:if test="${sessionScope.cart!=null}">
				
			<% 
					Cart cart=(Cart)request.getSession().getAttribute("cart");					
					ArrayList<Items> list=cart.getItem();
					request.setAttribute("list", list);					
			%>
				<c:forEach var="item" items="${list}">
			<tr>
				<th><c:out value="${item.name}"></c:out></th>
				<th><img src="images/<c:out value="${item.picture}"></c:out>"  width="120" height="90"  border="1"></th>
				<th><c:out value="${item.price}"></c:out>元</th>
				<th><a href="Shopping?id=<c:out value="${item.id}"></c:out>&action=delete" onclick="myFunction()">删除</a></th>
			</tr>
			
			
			</c:forEach>
				
			</c:if>
			
	</table>
	<div>总计：<c:out value="${cart.totalPrice}"></c:out>元</div>
		
	</center>
</body>
</html>