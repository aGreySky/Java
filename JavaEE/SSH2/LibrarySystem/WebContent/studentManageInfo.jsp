<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统</title>
<style type="text/css">
	.font1{
		font-size:13px;
	}
</style>
</head>
<body>
	<table border="1" width="599" cellspacing="1" class="font1">
		<tr bgcolor="#e9edf5">
			<td>读者信息</td>
		</tr>
		<tr>
			<td height="400" valign="top" class="font1">
				<s:if test="#request.onestudent==null">
					<table class="font1">
						<tr>
							<td width="100">借书证号</td>
							<td><s:textfield name="student.readerId"></s:textfield></td>
							<td width="100">密码</td>
							<td><s:textfield name="student.password"></s:textfield></td>
						</tr>
						<tr>
							<td width="100">姓名:</td>
							<td><s:textfield name="student.name" value=""></s:textfield></td>
							<td width="100">性别:</td>
							<td><select name="student.sex"><option value="true">男</option><option value="false">女</option></select></td>
						</tr>
						<tr>
							<td width="100">出生日期:</td>
							<td><s:textfield name="student.born" value=""  onblur="check(this)"/></td>
							<td width="100">专业:</td>
							<td><s:textfield name="student.spec" value=""></s:textfield></td>
						</tr>
						<tr>
							<td colspan="1" valign="top">照片:</td>
							<td colspan="2" valign="top">
<!-- 							 onchange="document.all['image'].src=this.value;" -->
								<s:file name="photo" accept="image/*"></s:file>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<font color="red"><s:property value="message"></s:property></font>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<font color="red"><s:fielderror></s:fielderror></font>
							</td>
						</tr>
					</table>
				</s:if>
				<s:else>
					<s:set var="onestudent" value="#request.onestudent"></s:set>
					<table>
						<tr>
							<td width="100">借书证号</td>
							<td><input type="text" value="<s:property value="#onestudent.readerId"/>" name="student.readerId"></td>
							<td width="100">密码</td>
							<td><input type="text" value="********" name="student.password"></td>
							
						</tr>
						<tr>
							<td width="100">姓名:</td>
							<td>
								<input type="text" value="<s:property value="#onestudent.name"/>" name="student.name" >
							</td>
							<td width="100">性别:</td>
							<td><select name="student.sex">
							<s:if test="#onestudent.sex==1" >
								<option value="true">男</option>
								<option value="false">女</option>
							</s:if>
							<s:else>
								<option value="false">女</option>
								<option value="true">男</option>
							</s:else>
								</select></td>
						</tr>
						<tr>
							<td width="100">出生日期:</td>
							<td>
								<input value="<s:date name="#onestudent.born" format="yyyy-MM-dd"/>" name="student.born"  onblur="check(this)">
							</td>
							<td width="100">专业:</td>
							<td>
								<input type="text" value="<s:property value="#onestudent.spec"/>" name="student.spec">
							</td>
						</tr>
						<tr>
							<td width="100">借书量:</td>
							<td>
								<input type="text" value="<s:property value="#onestudent.num"/>" name="student.num" readonly="readonly">
							</td>
							<td colspan="1" valign="top">照片:</td>
							<td>
								<table>
									<s:if test="#onestudent.photo!=null">
										<tr>
											<img alt="" src="getStuImage.action?student.readerId=<s:property value="#onestudent.readerId"/>" id="image" width="100" height="120">
										</tr>
									</s:if>
									<s:else>
										<tr>
											<img alt="" src="images/Studefault.jpg" id="image" width="100" height="120">
										</tr>
									</s:else>
									<tr>
										<s:file name="photo" accept="image/*"></s:file>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<font color="red"><s:property value="message"></s:property></font>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<font color="red"><s:fielderror></s:fielderror></font>
							</td>
						</tr>
					</table>
				</s:else>
			</td>
		</tr>
	</table>
</body>
</html>