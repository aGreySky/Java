<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ page import="dao.ItemsDAO"%>
<%@ page import="entity.Items"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>商品展示</title>
     <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	   hr{
	    border-color:FF7F00; 
	   }
	   div{
	      float:left;
	      margin: 10px;
	   }
	   div dd{
	      margin:0px;
	      font-size:10pt;
	   }
	   div dd.dd_name
	   {
	      color:blue;
	   }
	   div dd.dd_city
	   {
	      color:#000;
	   }
	</style>
  
  </head>
  
  <body>
    <h1>商品展示</h1>
    <hr>
     <center>
	 <table width="750" height="60" cellpadding="0" cellspacing="0" border="0">
	 	<tr>
	 		<td>
	 		<!-- 商品循环开始 -->
	 		<%
	 		ItemsDAO itemsDao=new ItemsDAO();
    		ArrayList<Items> list=itemsDao.getAllItems();
    		if(list!=null&&list.size()>0)
    		{
    		 for(int i=0;i<list.size();i++)
    		 {
	 			 Items item=list.get(i);
	 			
	 		 %>
	 			<div>
	 				<dl>
	 					<dt>
	 					
	 					<a href="details.jsp?id=<%=item.getId() %>"><img src="images/<%=item.getPicture() %>" width="120" height="90"  border="1"></a>
	 					</dt>
	 					<dd class="dd_name"><%=item.getName() %></dd>
	 					<dd class="dd_city">产地:<%=item.getCity() %>&nbsp;&nbsp;价格：<%=item.getPrice() %>元&nbsp;&nbsp;货存：<%=item.getNumber() %>件</dd>
	 				</dl>
	 			</div>
	 			<!-- 商品循环结束 -->
	 			<%
	 					}
	 				}
	 			 %>
	 		</td>
	 	</tr>
	 </table>
     </center>
  </body>
</html>



