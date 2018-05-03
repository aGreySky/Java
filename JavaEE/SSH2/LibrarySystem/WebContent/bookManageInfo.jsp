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
			<td>图书信息</td>
		</tr>
		<tr>
			<td height="400" valign="top" class="font1">
				<s:if test="#request.onebook==null">
					<table class="font1">
						<tr>
							<td width="100">ISBN:</td>
							<td><s:textfield name="book.ISBN" value=""></s:textfield></td>
							<td width="100">价格:</td>
							<td><s:textfield name="book.price" value="" onblur="check(this)"></s:textfield></td>
						</tr>
						<tr>
							<td width="100">书名:</td>
							<td><s:textfield name="book.bookName" value=""></s:textfield></td>
							<td width="100">复本量:</td>
							<td><s:textfield name="book.cnum" value="" onblur="check(this)"></s:textfield></td>
						</tr>
						<tr>
							<td width="100">出版社:</td>
							<td><s:textfield name="book.publisher" value=""></s:textfield></td>
							<td width="100">库存量:</td>
							<td><s:textfield name="book.snum" value=""></s:textfield></td>
						</tr>
						<tr>
							<td width="100">作译者:</td>
							<td><s:textfield name="book.author" value=""></s:textfield></td>
						</tr>
						<tr>
							<td valign="top">内容提要:</td>
							<td><s:textarea name="book.summary" value="" cols="20" rows="6"></s:textarea></td>
							<td colspan="1" valign="top">封面图片:</td>
							<td colspan="2" valign="top">
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
					<s:set var="onebook" value="#request.onebook"></s:set>
					<table>
						<tr>
							<td width="100">ISBN:</td>
							<td>
								<input type="text" value="<s:property value="#onebook.ISBN"/>" name="book.ISBN">
							</td>
							<td width="100">价格:</td>
							<td>
								<input type="text" value="<s:property value="#onebook.price"/>" name="book.price" onblur="check(this)">
							</td>
						</tr>
						<tr>
							<td width="100">书名:</td>
							<td>
								<input type="text" value="<s:property value="#onebook.bookName"/>" name="book.bookName">
							</td>
							<td width="100">复本量:</td>
							<td>
								<input type="text" value="<s:property value="#onebook.cnum"/>" name="book.cnum" onblur="check(this)">
							</td>
						</tr>
						<tr>
							<td width="100">出版社:</td>
							<td>
								<input type="text" value="<s:property value="#onebook.publisher"/>" name="book.publisher">
							</td>
							<td width="100">库存量:</td>
							<td>
								<input type="text" value="<s:property value="#onebook.snum"/>" name="book.snum" onblur="check(this)">
							</td>
						</tr>
						<tr>
							<td width="100">作译者:</td>
							<td>
								<input type="text" value="<s:property value="#onebook.author"/>" name="book.author">
							</td>
						</tr>
						<tr>
							<td valign="top">内容提要:</td>
							<td>
								<s:textarea value="%{#onebook.summary}" name="book.summary"  cols="20" rows="6"></s:textarea>
							</td>
							<td colspan="1" valign="top">封面图片:</td>
							<td>
								<table>
									
									<tr>
										<s:if test="#onebook.photo!=null">
											<img alt="" src="getImage.action?book.ISBN=<s:property value="#onebook.ISBN"/>" id="image" width="100" height="120">
										</s:if>
										<s:else>
											<img alt="" src="images/default.jpg" id="image" width="100" height="120">
										</s:else>
									</tr>
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