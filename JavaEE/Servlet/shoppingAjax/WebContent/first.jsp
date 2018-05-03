<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="dao.ItemDAO" %>
<%@ page import="entity.Items" %>
<%@ page import="entity.User" %>
<%@ page import="tools.Pages" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="myPage" class="tools.Pages" scope="session"></jsp:useBean>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品显示</title>
<style type="text/css">
	   hr{
	    border-color:FF7F00; 
	   }
 	   div{ 
 	      float:left;
	      margin:5px;
	   } 
	   div dd{
	      margin:0px;
	      font-size:10pt;
	   }
	   
</style>
<script>
	function myFunction(id){
			alert("您添加了一件编号为"+id+"的商品!");
		}
</script>
</head>
<body>
	<h1>商品预览</h1>
	<hr>
	<center>
	<div>
		<%	if(session.getAttribute("user")!=null){ %>
       			<a>欢迎你，用户<%=((User)session.getAttribute("user")).getUsername()%></a>
        		<a href="Logout">登出</a>
    	<%
    		} 
    	%>
    </div>
    <%
		if(session.getAttribute("ItemsList")!=null){
	%>
			<table width="750" height="70" cellpadding="0" cellspacing="0" border="0">
			 	<tr>
			 		<td>
						<c:forEach var="item" items="${ItemsList}">
							<div>
								<dl>
									<dt>
										<dd><a>物品编号：<c:out value="${item.id}"></c:out></a></dd>
										<img src="images/<c:out value="${item.picture}"></c:out>" width="120" height="90"  border="1"> 
		 								<dd><a>名称：<c:out value="${item.name}"></c:out></a></dd> 
										<dd><a>产地：<c:out value="${item.city}"></c:out></a></dd> 
		 								<dd><a>价格：<c:out value="${item.price}"></c:out>元</a></dd> 
		 								<dd><a>库存：<c:out value="${item.number}"></c:out>件</a></dd>
		 								<a href="Shopping?id=<c:out value="${item.id}"></c:out>&action=add">
		 									<img src="images/in_cart.png" onclick="myFunction(<c:out value="${item.id}"></c:out>)">
		 								</a>
		 							</dl>
							</div>
						</c:forEach>
					</td>
				</tr>	
			</table>
			<div style="background-color:#FFFFFF;">
	    		<%=myPage.printCtrl(Integer.parseInt(session.getAttribute("Page").toString()),"paging",((Pages)session.getAttribute("myPages")).getMaxPage()) %>
	     	</div>
 	 <%
   		 }else{
  	 %>	
   	 	<h1>暂无商品</h1>
    <% 
   		 } 
    %>
		<a href="cart.jsp"><img alt="查看购物车" src="images/view_cart.jpg"></a>
		</center>
</body>
</html>