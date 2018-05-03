<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统</title>

<script type="text/JavaScript">
	function noRoot(){
		alert("没有权限使用此功能！");
	}
	
    function startTime(){
        var today=new Date()
        var week=new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
        var year=today.getFullYear()
        var month=today.getMonth()+1
        var date=today.getDate()
        var day=today.getDay()
        var h=today.getHours()
        var m=today.getMinutes()
        var s=today.getSeconds()
        // add a zero in front of numbers<10
        h=checkTime(h)
        m=checkTime(m)
        s=checkTime(s)
        document.getElementById('time').innerHTML=" "+year+"年"+month+"月"+date+"日  "+week[day]+"  "+h+":"+m+":"+s+" "
        t=setTimeout('startTime()',500)
       }
       
       function checkTime(i){
       if (i<10)  
         {i="0" + i}
         return i
       }
</script>
</head>
<body bgcolor="#ffffff" leftmargin="0" topmargin="0" marginheight="0" marginwidth="0" onload="startTime()">
	<table id="_01" width="898" height="120" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td rowspan="2">
<!-- 				<img alt="" src="images/小组成员.jpg" width="291" height="120"> -->
				<s:if test="#session.student!=null">
					<s:if test=" #session.student.photo == null">
						<img alt="" src="images/Studefault.jpg" id="image" width="80" height="100" >
					</s:if>
					<s:else>
						<img alt="" src="getStuImage.action?student.readerId=<s:property value="%{#session.student.readerId}"/>" id="image" width="80" height="100">
					</s:else>
					<br>
					<a style="font-size: 12px">欢迎您，<s:property value="%{#session.student.name}" /></a>
				</s:if>
				<s:else>
					<img alt="" src="images/Studefault.jpg" id="image" width="80" height="100" ><br>
					<a style="font-size: 12px">欢迎您，管理员</a>
				</s:else>
			</td>
			<td rowspan="2" width="200">
				<center>
					<img alt="" src="images/timg.gif"  height="100">
					<label id="time" style="font-size: 12px;"></label>
				</center>
			</td>
			<td colspan="7">
				<img alt="" src="images/图书管理系统.jpg" width="610" height="80">
			</td>
		</tr>
		<tr>
			<td>
				<a href="bookQuery.jsp"><img alt="" src="images/图书查询.jpg" width="80" height="40"></a>
			</td>
			<td>
				<a href="lendQuery.jsp"><img alt="" src="images/借书查询.jpg" width="80" height="40"></a>
			</td>
			<td>
				<a href="lendBook.jsp"><img alt="" src="images/借书.jpg" width="80" height="40"></a>
			</td>
			<td>
				<a href="returnBook.jsp"><img alt="" src="images/还书.jpg" width="80" height="40"></a>
			</td>
			<s:if test="#session.admin!=null">
			<td>
				<a href="studentManage.jsp"><img src="images/读者管理.jpg" width="80" height="40"></a>
			</td>
			<td>
				<a href="bookManage.jsp"><img alt="" src="images/图书管理.jpg" width="80" height="40"></a>
			</td>
			<td>
				<a href="uncheckList.action"><img alt="" src="images/借还书确认.jpg" width="130" height="40"></a>
			</td>
			</s:if> 
			<s:else>
				<td>
					<a href="#" onclick="noRoot()"><img src="images/读者管理1.jpg" width="80" height="40"></a>
				</td>
				<td>
					<a href="#" onclick="noRoot()"><img alt="" src="images/图书管理1.jpg" width="81.5" height="40"></a>
				</td>
				<td>
					<a href="about.jsp"><img alt="" src="images/关于.jpg" width="129" height="40"></a>
				</td>
			</s:else>
			
		</tr>
	</table>
</body>
</html>