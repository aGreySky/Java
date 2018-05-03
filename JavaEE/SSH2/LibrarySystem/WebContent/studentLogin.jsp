<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>用户登录</title>

    <link rel="stylesheet" href="common/css/materialize.min.css">
    <link rel="stylesheet" href="common/css/animate.css">
    <link rel="stylesheet" href="common/css/normalize.css">
    <link rel="stylesheet" href="common/css/login.css">

    <link rel="icon" href="common/images/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="common/images/favicon.ico" type="image/x-icon" />
</head>

<body>
    <div id="login">
        <div class="container">
            <div class="form-animate-change"></div>
            <form id="login-form" action="studentLogin" method="post" class="center-align animated bounceInDown">
                <div class="login-form-body">
                    <div class="login-form-title">
                        <a href="index.html" tabindex="-1">用户登录</a>
                    </div>
                    <input id="username" name="student.readerId" type="text" placeholder="账号" autofocus="">
                    <input id="password" name="student.password" type="password" placeholder="密码">
                    <input id="submit-btn" type="submit" class="btn" value="登录">
                </div>
                <font color="red"><s:property value="message"></s:property></font>
            </form>
        </div>
    </div>

    <script src="../common/js/jquery-3.1.1.min.js"></script>
    <script src="../common/js/materialize.min.js"></script>
    <script src="../common/layer/layer.js"></script>
    <script src="../common/js/login.js"></script>
    <script src="/common/js/common.js"></script>
</body>

</html>