<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.UserDAO"%>
<%@ page import="entity.User"%>
<%@ page import="util.DBhelper"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%
	ServletContext sc=session.getServletContext();
	DBhelper db=(DBhelper)sc.getAttribute("dbhelper");
	UserDAO userDao=new UserDAO(db);
	int id=Integer.parseInt(request.getParameter("id"));
	User user=userDao.getUserByUserId(id);
%>
<title><%=user!=null ? user.getUsername() : null %>个人信息</title>
<link rel="stylesheet" type="text/css" href="css/login/common.css">
<script src="JS/shop/Item.js"></script>
<style type="text/css">
	.product_head{
		width:100%;
		height:50px;
		background-color:rgba(204,204,204,0.5);
	}
	.head_log{
		font-family:MV Boli;
		font-size:24px;
	}	
	input{color:black;}	
</style>
</head>
<body onload="update()">
<!--导航栏-->
	<div class="product_head">
		<div class="head_log">
			<img src="images/toolsPicture/b.png" height="50" alt="">
		</div>
	</div>
	<% 
		if(user!=null){
			if(user.getId()==((User)session.getAttribute("user")).getId()){
	%>
				<div class="login_cont">
					<form action="info?id=<%=id %>" method="post" enctype="multipart/form-data">
					<div>
						<div style="float:left">
							<div style="width:90px; height:90px; border-radius:50%; overflow:hidden;">
								<img src="images/usersPicture/<%=user.getPicture()!=null ? user.getPicture() : "Default.jpg" %>?t=+<%=Math.random() %>" width="120" height="90"  border="1" >
							</div>
						</div>
						<input id="fileupload" type="file" name="picture" multiple>
					</div>
						<div class="input_signup">
							用户名：<input class="input" name="username" type="text" aria-label="用户名（包含字母／数字／下划线" placeholder="<%=user.getUsername() %>">
									<div class="hint">请填写符合格式的用户名</div>
							密码：未知&nbsp;&nbsp;&nbsp;&nbsp;<a href="forget.html" target="_blank" style="color:blue;">修改密码</a><br><br>
							邮箱：<input class="input" name="useremail" type="text" aria-label="邮箱" placeholder="<%=user.getUseremail() %>">
									<div class="hint">请填写邮箱</div>
							手机：<input class="input" name="phone" type="text" class="account" aria-label="手机号（仅支持中国大陆）" placeholder="<%=user.getPhone() %>">
									<div class="hint">请填写手机号</div>
						</div>
						<input type="submit" class="button" value="修改">
					</form>	
				</div>
	<%	
			}
			else{
				%>
				<div>
					<img src="images/usersPicture/<%=user.getPicture()!=null ? user.getPicture() : "Default.jpg" %>?t=+<%=Math.random() %>" width="120" height="90"  border="1" >
					<a>用户名：<%=user.getUsername() %></a><br>
					<a>邮箱：<%=user.getUseremail() %></a><br>
					<a>手机：<%=user.getPhone() %></a>
				</div>
				<%
			}
		}
	%>
</body>
</html>