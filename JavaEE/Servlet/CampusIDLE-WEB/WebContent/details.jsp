<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@ page import="dao.ItemsDAO"%>
<%@ page import="entity.Items"%>
<%@ page import="util.DBhelper"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<link href="css/shop/product.css" rel="stylesheet" type="text/css">
<script src="JS/shop/Item.js" type="text/javascript"></script>
</head>

<body onload="add()">
	<!--导航栏-->
	<div class="product_head">
		<div class="head_log">
			<img src="images/toolsPicture/b.png" height="50" alt="">
		</div>
	</div>
	<div class="return">
		<a href="paging?Page=<%=request.getParameter("Page") %>"><img src="images/toolsPicture/return.jpg" alt="return">商品列表</a>
	</div>
	<!-- 商品详情 -->
	<%
		ServletContext sc = session.getServletContext();
		DBhelper db = (DBhelper) sc.getAttribute("dbhelper");
		ItemsDAO itemsDao = new ItemsDAO(db);
		Items item = itemsDao.getItemsById(Integer.parseInt(request.getParameter("id")));
		if (item != null) {
	%>
	<div class="product_main">
		<div class="product_msg">
			<div class="product_img">
				<img src="images/itemsPicture/<%=item.getPicture()%>" width="300"
					height="250" alt="">
			</div>
			<div class="product_masg">
				<div>
					<table width="450" cellpadding="0" cellspacing="0" border="0" style="background:rgba(204,204,204,0.3);">
						<tr>
							<td class="dd_name"><%=item.getName()%></td>
						</tr>
						
						<tr>
							<td class="dd_type"><code>类型</code><%=item.getType()%></td>
						</tr>
						<tr>
							<td class="dd_price"><code>价格</code><%=item.getPrice()%>元</td>
						</tr>
						<tr>
							<td class="seller">卖家：
								<div style="width:30px; height:30px; border-radius:50%; overflow:hidden; float: left;">
										<img src="images/usersPicture/<%=item.getUser().getPicture()!=null ? item.getUser().getPicture() : "Default.jpg" %>?t=+<%=Math.random() %>" width="30" height="30"  border="1" >
									</div>
							<a href="userInfo.jsp?id=<%=item.getId()%>"><%=item.getUser().getUsername()%></a>
								<img src="images/toolsPicture/V<%=item.getUser().getRank()%>.png"
								class="level" alt="">
							</td>
						</tr>
						<tr>
							<td class="seller">卖家联系方式(<%=item.getWay()%>)：<%=item.getContact()%></td>
						</tr>
						<tr>
							<td class="introduction" style="height:<%=(item.getOverview().length()/32+1)*35 %>px;">物品简述：<%=item.getOverview()%></td>
						</tr>
						<tr>
							<td>
								<div id="cart" class="sale">
									&nbsp;&nbsp;&nbsp;&nbsp; <a
										href='cart?action=add&amp;id=<%=item.getId()%>&amp;Page=<%=request.getParameter("Page")%>'>
										<img src="images/toolsPicture/in_cart.png" alt="">
									</a> <a href="cart?action=show"><img
										src="images/toolsPicture/view_cart.jpg" alt="" /></a>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<%
			}
		%>
		<%
			String list = "";
			Cookie[] cookies = request.getCookies();//从客户端获得Cookie集合
			if (cookies != null && cookies.length > 0) {//遍历这个Cookie集合
				for (Cookie c : cookies) {
					if (c.getName().equals("ListViewCookie")) {
						list = c.getValue();
					}
				}
			}
			list += request.getParameter("id") + "#";
			//如果浏览记录超过1000条，清零。
			String[] arr = list.split("#");
			if (arr != null && arr.length >= 1000) {
				list = "";
			}
			Cookie cookie = new Cookie("ListViewCookie", list);//将list字符串存入cookie
			response.addCookie(cookie);
		%>
		<div class="product_rec">
			<!-- 浏览过的商品 -->

			<%
				ArrayList<Items> itemlist = itemsDao.getViewList(list);
				if (itemlist != null && itemlist.size() > 0) {
			%>

			<br> <b style="font-size: 14px; color: rgba(153, 153, 153, 1);">您浏览过的商品</b><br>
			<br>
			<!-- 循环开始 -->
			<%
				for (Items i : itemlist) {
			%>
			<div>
				<dl>
					<dt>
						<a
							href='details.jsp?id=<%=i.getId()%>&amp;Page=<%=request.getParameter("Page")%>'><img
							src="images/itemsPicture/<%=i.getPicture()%>" width="120"
							height="90" border="1" alt=""></a>
					</dt><br>
					<dd><%=i.getName()%></dd>
					<dd class="dd_city">
						类型:<%=i.getType()%>&nbsp;&nbsp;价格：<%=i.getPrice()%>元
					</dd>
					<dd class="dd_user">
						卖家：
						<div style="width:30px; height:30px; border-radius:50%; overflow:hidden; float: left;">
							<img src="images/usersPicture/<%=i.getUser().getPicture()!=null ? i.getUser().getPicture() : "Default.jpg" %>?t=+<%=Math.random() %>" width="30" height="30"  border="1" >
						</div>
						<a href="userInfo.jsp?id=<%=i.getId()%>"><%=i.getUser().getUsername()%></a>
						<img src="images/toolsPicture/V<%=i.getUser().getRank()%>.png"
							class="level" border="1" alt="">
					</dd>
				</dl>
			</div>
			<br>

			<%
				}
				}
			%>
			<!-- 循环结束 -->
		</div>
	</div>
</body>
</html>



