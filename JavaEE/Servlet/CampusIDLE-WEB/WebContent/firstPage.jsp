<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@ page import="entity.Items"%>
<%@ page import="entity.User"%>
<%@ page import="entity.Pages"%>
<%@ page import="entity.Cart"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>商品展示</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/shop/title.css">
<script src="JS/jquery-1.8.2.min.js" type="text/javascript"></script>
<script src="JS/shop/part.js" type="text/javascript"></script>
<script src="JS/shop/Item.js" type="text/javascript"></script>
<script src="JS/shop/find.js" type="text/javascript"></script>

</head>

<body onload="Parts();remove();Return()">
	<!--导航栏-->
	<div class="firstPage_head">
		<div class="head_log">
			<img src="images/toolsPicture/b.png" height="50" alt="">
		</div>
		<%
			if (session.getAttribute("user") != null) {
		%>
		<div class="head_wel">
			<a>欢迎你，尊敬的用户</a>
			<div
				style="width: 30px; height: 30px; border-radius: 50%; overflow: hidden; float: left;">
				<img
					src='images/usersPicture/<%=((User) session.getAttribute("user"))
                        .getPicture() != null
                                ? ((User) session.getAttribute("user"))
                                        .getPicture()
                                : "Default.jpg"%>?t=+<%=Math.random()%>'
					width="30" height="30" border="1" alt="">
			</div>
			<a style="color: #FF0000"
				href='userInfo.jsp?id=<%=((User) session.getAttribute("user")).getId()%>'
				target="_blank"><%=((User) session.getAttribute("user")).getUsername()%></a>
			<img
				src='images/toolsPicture/V<%=((User) session.getAttribute("user")).getRank()%>.png'
				width="20" height="20" border="1" alt="">
			&nbsp;&nbsp;&nbsp;&nbsp;<a href="logout">登出</a>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<%
				if (session.getServletContext()
			                        .getAttribute("userNumber") != null) {
			%>
			<a>当前用户在线人数<%=Integer
                            .parseInt(session.getServletContext()
                                    .getAttribute("userNumber").toString())
                            + 1000%></a>
			<%
				}
			%>
		</div>
		<%
			}
		%>
	</div>



	<!--logo和搜索框-->
	<div class="logo_search">
		<!-- 返回首页 -->
		<div class="return" id="return">
			<a href="paging?condition=all"><img
				src="images/toolsPicture/return.jpg" alt="return">返回首页</a>
		</div>
		<!--logo-->
		<div class="logo"><img alt="" src="images/toolsPicture/logo.jpg"></div>

		<!--搜索框-->
		<div class="search">
			<form action="paging" method="get">
				<input type="text" name="condition" class="search_text"
					placeholder="请输入想搜索的词" /> <img src="images/toolsPicture/b.jpg"
					width="50" height="40"
					style="float: left; border-right: 1px solid rgba(204, 204, 204, 1);"
					alt="" />
				<div class="search_button">
					<input type="submit" id="find" value="搜索">
				</div>
			</form>
		</div>
	</div>



	<!--主体商品-->
	<div class="shop_cont">
		<div class="shop_nav">
			<div class="nav_slider">
				<a href="paging?condition=all" class="Item focus">物品信息</a> <a
					href="cart?action=show" class="waitItem">待联系物品</a> <a
					href="good?action=show" class="myItem">我的物品</a>
			</div>



		</div>
		<div class="Item_show active">
			<table width="1200" height="60" cellpadding="0" cellspacing="0"
				border="0">
				<tr>
					<td>
						<!-- 商品循环开始 --> <%
 	if (session.getAttribute("ItemsList") != null) {
                 ArrayList<Items> newList = (ArrayList<Items>) session.getAttribute("ItemsList");
                 if (session.getAttribute("myPages") != null) {
                     Pages myPages = (Pages) session.getAttribute("myPages");
                     if (newList.size() != 0) {
                         for (int i = 0; i < newList.size(); i++) {
                             Items item = newList.get(i);
                             item.setPage(myPages.getPage());
 %>
						<div class="foreach">
							<dl>
								<dt>
									<a
										href="details.jsp?id=<%=item.getId()%>&amp;Page=<%=item.getPage()%>">
										<img src="images/itemsPicture/<%=item.getPicture()%>"
										width="250" height="180" alt="">
									</a>
								</dt>

								<dd class="dd_price">
									￥<%=item.getPrice()%></dd>
								<dd class="dd_name"><%=item.getName()%></dd>
								<dd class="dd_type">
									类型：<%=item.getType()%></dd>
								<dd class="dd_user">
									卖家：
									<div
										style="width: 30px; height: 30px; border-radius: 50%; overflow: hidden; float: left;">
										<img
											src='images/usersPicture/<%=item.getUser().getPicture() != null
                                    ? item.getUser().getPicture()
                                    : "Default.jpg"%>?t=+<%=Math.random()%>'
											width="30" height="30" border="1" alt="">
									</div>
									<a href="userInfo.jsp?id=<%=item.getId()%>"><%=item.getUser().getUsername()%></a>
									<img
										src="images/toolsPicture/V<%=item.getUser().getRank()%>.png"
										class="level" alt="">
								</dd>
							</dl>
						</div> <!-- 商品循环结束 --> 
						<%
 							}
                     } else {
 						%>
 						 <a>暂无此商品</a> 
 						<%
 					 }
 						%>
					</td>
				</tr>
			</table>

			<div style="background-color: #FFFFFF;">
				<%=myPages.printCtrl(myPages.getPage(), "paging",
                            myPages.getMaxPage())%>
			</div>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<%
				session.setAttribute("ItemsList", newList);
			                }
			%>
			<%
				} else {
			%>
			<h1>暂无商品</h1>
			<%
				}
			%>
		</div>
		<div class="waitItem_show">
			<%
				if (session.getAttribute("cart") != null) {
			%>
			<!-- 循环的开始 -->
			<%
				Cart cart = (Cart) session.getAttribute("cart");
			                ArrayList<Items> goods = cart.getGoods();
			                if (!goods.isEmpty()) {
			%>
			<div>
				<a>温馨提示：闲置物品随时可能被物主下架，请及时处理！</a>
			</div>
			<%
				for (Items i : goods) {
			%>
			<div class="foreach">
				<dl>
					<dt>
						<a href="details.jsp?id=<%=i.getId()%>&amp;Page=<%=i.getPage()%>">
							<img src="images/itemsPicture/<%=i.getPicture()%>" width="250"
							height="180" alt="">
						</a>
					</dt>

					<dd class="dd_price">
						￥<%=i.getPrice()%></dd>
					<dd class="dd_name"><%=i.getName()%></dd>
					<dd class="dd_type">
						类型：<%=i.getType()%></dd>
					<dd class="dd_user">
						卖家：
						<div
							style="width: 30px; height: 30px; border-radius: 50%; overflow: hidden; float: left;">
							<img
								src='images/usersPicture/<%=i.getUser().getPicture() != null
                                ? i.getUser().getPicture()
                                : "Default.jpg"%>?t=+<%=Math.random()%>'
								width="30" height="30" border="1" alt="">
						</div>
						<a href="userInfo.jsp?id=<%=i.getId()%>"><%=i.getUser().getUsername()%></a>
						<img src="images/toolsPicture/V<%=i.getUser().getRank()%>.png"
							class="level" alt="">
					</dd>
					<dd class="del_button">
						<a href="cart?action=delete&amp;id=<%=i.getId()%>"
							onclick="ifRemove()"> <img
							src="images/toolsPicture/remove_cart.png" class="up_button"
							border="1" alt=""></a>
					</dd>
				</dl>
			</div>

			<%
				}
			%>
			<!--循环的结束-->
			<div class="clear"></div>
			<div style="text-align: center;">
				<a href="cart?action=clear" onclick="ifClear()"><img
					src="images/toolsPicture/clear_cart.png" class="up_button"
					border="1" alt=""></a>
			</div>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<%
				} else {
			%>
			<div>
				<a>购物车居然是空的...</a><a href="paging?Page=1">去逛逛吧</a>
			</div>
			<%
				}
			            } else {
			%>
			<a>服务器故障！</a>
			<%
				}
			%>
		</div>
		<div class="myItem_show">
			<%
				if (session.getAttribute("goods") != null) {
			%>
			<!-- 循环的开始 -->
			<%
				ArrayList<Items> goods = (ArrayList<Items>) session
			                        .getAttribute("goods");
			                if (!goods.isEmpty()) {
			%>
			<div>
				<a>温馨提示：过期物品请及时下架!</a>
			</div>
			<%
				for (Items i : goods) {
			%>
			<div class="foreach">
				<dl>
					<dt>
						<a href="details.jsp?id=<%=i.getId()%>&amp;Page=<%=i.getPage()%>">
							<img src="images/itemsPicture/<%=i.getPicture()%>" width="250"
							height="180" alt="">
						</a>
					</dt>

					<dd class="dd_price">
						￥<%=i.getPrice()%></dd>
					<dd class="dd_name"><%=i.getName()%></dd>
					<dd class="dd_type">
						类型：<%=i.getType()%></dd>
					<dd class="dd_way">
						联系方式(<%=i.getWay()%>)：<%=i.getContact()%></dd>
					<dd style="margin-left: 40px;">
						<a href="good?action=delete&amp;id=<%=i.getId()%>"
							onclick="ifRemove()"> <img
							src="images/toolsPicture/remove_goods.png" class="up_button"
							border="1" alt=""></a> <a
							href="updateGood.jsp?id=<%=i.getId()%>" target="_blank"> <img
							src="images/toolsPicture/update_goods.png" class="up_button"
							border="1" alt=""></a>
					</dd>
				</dl>
			</div>

			<%
				}
			%>
			<!--循环的结束-->
			<div class="clear"></div>
			<div style="text-align: center;">
				<a href="good?action=clear" onclick="ifClear()"><img
					src="images/toolsPicture/clear_goods.png" class="up_button"
					border="1" alt=""></a> <a
					href='addGood.jsp?uid=<%=((User) session.getAttribute("user")).getId()%>'
					target="_blank"><img src="images/toolsPicture/in_goods.png"
					class="up_button" border="1" alt=""></a>
			</div>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<%
				} else {
			%>
			<a>您还没有闲置物品上架...</a><a
				href='addGood.jsp?uid=<%=((User) session.getAttribute("user")).getId()%>'
				target="_blank">上架闲置</a>
			<%
				}
			            } else {
			%>
			<a>服务器故障！</a>
			<%
				}
			%>
		</div>
	</div>



	<center></center>
	<script type="text/javascript" src="JS/shop/change.js"></script>

	<div></div>
</body>
</html>



