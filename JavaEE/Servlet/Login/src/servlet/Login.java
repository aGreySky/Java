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
 * Servlet implementation class Login
 */
@WebServlet(name="loginservlet",urlPatterns={"/login","/loginto"})
public class Login extends HttpServlet {
    private static final long serialVersionID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // �û�ͨ��url�ķ�ʽ���ʸ�servlet���ض��򵽵�½ҳ��
        request.getRequestDispatcher("/login.jsp").forward(request, response);
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
//      ���ñ��룬���ñ��з��ַ���������
        request.setCharacterEncoding("gbk");
        String username = request.getParameter("name");
        String password = request.getParameter("password");
        String id = "";

        if(username == null || username.length() == 0){
            request.setAttribute("error", "�û�������Ϊ��");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        if(password == null || username.length() == 0){
            request.setAttribute("error", "���벻��Ϊ��");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        password = SHA.encrypt(password);
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        
        UserDAO userdao = new UserDAO();
        id = userdao.userLogin(user);
        if(id == "false"){
            request.setAttribute("error", "�û��������������");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        user.setId(id);

//      ��½�ɹ������û���Ϣ���õ�session�У���������Чʱ��Ϊ4Сʱ(4 * 60 * 60 = 14400s)
//      ע��������servlet�����Բ���ֱ����jsp�����ö���session
        
        request.getSession().setAttribute("user", user);
        request.getSession().setMaxInactiveInterval(14400);
        System.out.println(request.getSession().getAttribute("user"));
        // ������ض�����ҳ
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

}