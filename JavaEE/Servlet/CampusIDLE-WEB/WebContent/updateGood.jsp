<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.ItemsDAO"%>
<%@ page import="entity.Items"%>
<%@ page import="util.DBhelper"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改物品信息</title>
<link rel="stylesheet" type="text/css" href="css/login/common.css">
<script src="JS/shop/Item.js"></script>
<style type="text/css">
	input{color:black;}
</style>
</head>
<body onload="update()">
	<%
		ServletContext sc=session.getServletContext();
		DBhelper db=(DBhelper)sc.getAttribute("dbhelper");
		ItemsDAO itemsDao=new ItemsDAO(db);
		int id=Integer.parseInt(request.getParameter("id"));
		Items item=itemsDao.getItemsById(id);
 		if(item!=null){
	%>
	<div class="login_cont">
		<form action="good?action=update&id=<%=id %>" method="post" enctype="multipart/form-data">
		<div>
			<a>闲置图片：</a><br>
			<div style="float:left">
				<img src="images/itemsPicture/<%=item.getPicture() %>?t=+<%=Math.random() %>" width="120" height="90"  border="1" >
			</div>
			<input id="fileupload" type="file" name="picture" multiple>
		</div>
			<div>
				闲置名称：<input class="input" name="name" type="text" placeholder="<%=item.getName() %>">
				闲置类型：<select class="input"  name="type">   
			 				 <option  value="数码">数码</option>   
			  				 <option  value="衣服">衣服</option>  
			  				 <option  value="书本">书本</option>   
			  				 <option  value="食品">食品</option>  
			  				 <option  value="健身器材">健身器材</option> 
			  				 <option  value="其他">其他</option>  
	 					  </select><br><br>
				闲置价格：<input class="input" name="price" type="text" placeholder="<%=item.getPrice() %>">
				联系方式：<select class="input"  name="way" name="方式">   
			 				 <option  value="QQ">QQ</option>   
			  				 <option  value="微信">微信</option>  
			  				 <option  value="手机">手机</option>   
	 					  </select>
						  <input class="input" name="contact" type="text" placeholder="<%=item.getContact() %>">
				闲置简介：<textarea class="input" name="overview" type="text" rows="7" cols="45" placeholder="<%=item.getOverview() %>"></textarea>
			</div>
			<input type="submit" class="button" value="修改">
		</form>	
	</div>
	<%
		}else{
			out.print("服务器出错了！");
		}
	%>	
</body>
</html>