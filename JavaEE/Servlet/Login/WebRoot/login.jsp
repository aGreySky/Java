<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>


    <div>
        <div>
            <div>
                <div>
                    <div>
                    <%
                        if(session.getAttribute("user") != null){
                        //  用户已经登录，此时不能再注册
                            response.sendRedirect(request.getContextPath() + "/index.jsp");
                        }

                        if(request.getAttribute("error") != null){
                    %>
                        <div>
                            
                            <strong>警告！</strong><span><%= request.getAttribute("error") %></span>
                        </div>
                    <%

                        }
                    %>
                        <form role="form" action="login" method="post">
                            <span>还没有账号？ <a href="./signin.jsp">注册账号</a></span>
                            <hr />
                            <div>
                                <label for="username">账号</label>
                                <input type="text" name="name" placeholder="输入账号" />
                            </div>
                            <div>
                                <label for="password">密码</label>
                                <input type="password" name="password" placeholder="输入密码" />
                            </div>
                            <button type="submit">登录</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
