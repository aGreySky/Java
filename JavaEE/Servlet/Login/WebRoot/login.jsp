<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>


    <div>
        <div>
            <div>
                <div>
                    <div>
                    <%
                        if(session.getAttribute("user") != null){
                        //  �û��Ѿ���¼����ʱ������ע��
                            response.sendRedirect(request.getContextPath() + "/index.jsp");
                        }

                        if(request.getAttribute("error") != null){
                    %>
                        <div>
                            
                            <strong>���棡</strong><span><%= request.getAttribute("error") %></span>
                        </div>
                    <%

                        }
                    %>
                        <form role="form" action="login" method="post">
                            <span>��û���˺ţ� <a href="./signin.jsp">ע���˺�</a></span>
                            <hr />
                            <div>
                                <label for="username">�˺�</label>
                                <input type="text" name="name" placeholder="�����˺�" />
                            </div>
                            <div>
                                <label for="password">����</label>
                                <input type="password" name="password" placeholder="��������" />
                            </div>
                            <button type="submit">��¼</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
