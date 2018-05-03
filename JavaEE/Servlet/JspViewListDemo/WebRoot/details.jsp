<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ page import="dao.ItemsDAO"%>
<%@ page import="entity.Items"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>商品详情</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="css/main.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/lhgcore.js"></script>
    <script type="text/javascript" src="js/lhgdialog.js"></script>
    <script type="text/javascript">
      function selflog_show(id)
      { 
         var num =  document.getElementById("number").value; 
         J.dialog.get({id: 'haoyue_creat',title: '购物成功',width: 600,height:400,
          link: '<%=path%>/servlet/CartServlet?id='+id+'&num='+num+'&action=add', cover:true});
        
      }
      function add()
      {
         var num = parseInt(document.getElementById("number").value);
         if(num<100)
         {
            document.getElementById("number").value = ++num;
         }
      }
      function sub()
      {
         var num = parseInt(document.getElementById("number").value);
         if(num>1)
         {
            document.getElementById("number").value = --num;
         }
      }
     
    </script>
	<style type="text/css">
		div{
			float:left;
			margin-left:30px;
			margin-right:30px;
			margin-top:5px;
			margin-bottom:5px;
		}
		div dd{
			margin:0px;
			font-size:10px;
		}
		div dd.dd_name{
			color:blue;
		}
		div dd.dd_city{
			color:#000;
		}
	</style>
  </head>
  
  <body>
    <h1>商品详情</h1>
    <hr>
     <center>
	 <table width="750" height="60" cellpadding="0" cellspacing="0" border="0">
	 	<tr>
			<!-- 商品详情 -->
			<%
				ItemsDAO itemDao=new ItemsDAO();
				Items item=itemDao.getItemsById(Integer.parseInt(request.getParameter("id")));
			 	if(item!=null)
			 	{
			 %>
			<td width="70% valign="top">
			<table>
				<tr>
					<td rowspan="5">
						<img src="images/<%=item.getPicture() %>" width="200" height="160">
					</td>
				</tr>
				<tr>
					<td><b><%=item.getName() %></b></td>
				</tr>
				<tr>
					<td>产地：<%=item.getCity() %></td>
				</tr>
				<tr>
					<td>价格：<%=item.getPrice() %></td>
				</tr>
			 <tr>
                 <td>购买数量：<span id="sub" onclick="sub();"><a href="javascript:void(0);">-</a></span>
                 <input type="text" id="number" name="number" value="1" size="2"/>
                 <span id="add" onclick="add();"><a href="javascript:void(0);">+</a></span></td>
               </tr> 
             </table>
             <div id="cart">
               <img src="images/buy_now.png">
               <a href="javascript:selflog_show(<%=item.getId()%>)"><img src="images/in_cart.png"></a>
               <a href="servlet/CartServlet?action=show"><img src="images/view_cart.jpg"/></a>
             </div>
			</td>
			<%
				}
			%>
			 <%
			 		String list="";
			 		//从客户端获得Cookie集合
			 		Cookie[] cookies=request.getCookies();
			 		//遍历这个Cookie集合
			 		if(cookies!=null&&cookies.length>0)
			 		{
			 			for(Cookie c:cookies)
			 			{
			 				if(c.getName().equals("ListViewCookie"))
			 				{
			 					list=c.getValue();
			 				}	
			 			}
			 		}
			 		list+=request.getParameter("id")+",";
			 		//如果浏览记录超过1000条，清零。
			 		String[] arr=list.split(",");
			 		if(arr!=null&&arr.length>0)
			 		{
			 				if(arr.length>=1000)
			 				{
			 					list="";
			 				}
			 		}
			 		Cookie cookie =new Cookie("ListViewCookie",list);
			 		response.addCookie(cookie);
			 		
			  %>
			 <!-- 浏览过的商品 -->
			 <td width="30%" align="center">
			 <br>
			 <b>您浏览过的商品</b><br>
			 <!-- 循环开始 -->
			 <%
			 	ArrayList<Items> itemlist=itemDao.getViewList(list);
			 	if(itemlist!=null&&itemlist.size()>0)
			 	{
			 		System.out.println("itemlist.size="+itemlist.size());
			 		for(Items i:itemlist)
			 		{
			  %>
			<div>
	 				<dl>
	 					<dt>
	 						<a href="details.jsp?id=<%=i.getId() %>"><img src="images/<%=i.getPicture() %>" width="120" height="90" border="1"></a>
	 					</dt>
	 					<dd class="dd_name"><%=i.getName() %></dd>
	 					<dd class="dd_city">产地:<%=item.getCity() %>&nbsp;&nbsp;价格：<%=item.getPrice() %>元&nbsp;&nbsp;货存：<%=item.getNumber() %>件</dd>
	 				</dl>
	 			</div>
	 			
	 			<%
	 					}
	 				}
	 			 %>
				<!-- 循环结束 -->
			</td>
		</tr>
	 </table>
     </center>
  </body>
</html>



