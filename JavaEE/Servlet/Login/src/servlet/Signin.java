package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import entity.User;
import util.SHA;

/**
 * Servlet implementation class Signin
 */
//��servlet��web.xml�н���������
//@WebServlet("/signin")
public class Signin extends HttpServlet {
    private static final long serialVersionID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signin() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // �û�ͨ��url�ķ�ʽ���ʸ�servlet���ض��򵽵�½ҳ��
        request.getRequestDispatcher("/signin.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("user") != null){
//          �û��Ѿ���¼����ʱ������ע��
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }
//      ��ֹ���з��ַ���������
        request.setCharacterEncoding("gbk");
        String username = (String) request.getParameter("name");
        String password = (String) request.getParameter("password");
        String repassword = (String) request.getParameter("repassword");
//      �û�����������֤
        if(username == null || username.length() == 0){
            request.setAttribute("error", "�û�������Ϊ��");
            request.getRequestDispatcher("/signin.jsp").forward(request, response);
            return;
        }
        if( username.length() > 10){
            request.setAttribute("error", "�û������Ȳ��ܴ���10");
            request.getRequestDispatcher("/signin.jsp").forward(request, response);
            return;
        }
        if(password == null || password.length() == 0){
            request.setAttribute("error", "���벻��Ϊ��");
            request.getRequestDispatcher("/signin.jsp").forward(request, response);
            return;
        }
        if(password.length() < 6 || password.length() > 16){
            request.setAttribute("error", "���볤��Ӧ����6��16֮��");
            request.getRequestDispatcher("/signin.jsp").forward(request, response);
            return;
        }
        if(repassword == null || repassword.length() == 0){
            request.setAttribute("error", "�ظ����벻��Ϊ��");
            request.getRequestDispatcher("/signin.jsp").forward(request, response);
            return;
        }
        if(!password.equals(repassword)){
            request.setAttribute("error", "�����������벻ͬ");
            request.getRequestDispatcher("/signin.jsp").forward(request, response);
            return;
        }
       
        UserDAO userdao = new UserDAO();
        if(userdao.isExistByName(username)){
            request.setAttribute("error", "�û����Ѵ���");
            request.getRequestDispatcher("/signin.jsp").forward(request, response);
            return;
        }
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        user.setPassword(SHA.encrypt(password));

        String id = userdao.addUser(user);
        user.setId(id);
//      ���û���Ϣд�뵽session�У���������Чʱ��Ϊ4Сʱ(14400s)
     
        request.getSession().setAttribute("user", user);
        request.getSession().setMaxInactiveInterval(14400);
//      �ض�����ҳ
        response.sendRedirect(request.getContextPath()+"/index.jsp");

    }
}