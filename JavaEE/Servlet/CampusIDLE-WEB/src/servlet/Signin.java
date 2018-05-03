package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import entity.User;
import util.DBhelper;
import util.SHA;
import util.UUIDUtil;
@WebServlet("/signal")
public class Signin extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Signin() {
        super();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void doGet(final HttpServletRequest request,
            final HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/index.html").forward(request, response);

    }

    @Override
    public void doPost(final HttpServletRequest request,
            final HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        final User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(SHA.encrypt(request.getParameter("password")));
        user.setUseremail(request.getParameter("useremail"));
        user.setPhone(request.getParameter("phone"));
        user.setRank(1);
        user.setState("0");// 0:δ���1������
        String code = UUIDUtil.getUUID() + UUIDUtil.getUUID();
        user.setCode(code);
        ServletContext sc = this.getServletContext();// ���������Ķ���
        DBhelper dc = (DBhelper) sc.getAttribute("dbhelper");
        UserDAO userdao = new UserDAO(dc);
        String json = null;
        if (userdao.ifExist(user)) {
            json = "{\"res\":\"�û����Ѵ��ڣ�����������...\",\"flag\":false}";
            System.out.println("ע��ʧ��");
        } else if (userdao.ifPhoneOrEmailExist(user)) {
            json = "{\"res\":\"�ֻ��Ż������ѱ�ע�ᣬ����������...\",\"flag\":false}";
            System.out.println("ע��ʧ��");
        } else {
            if (userdao.addUser(user)) {
                json = "{\"res\":\"ע��ɹ�,��ȥͨ�����伤���...\",\"flag\":true}";
            } else {
                json = "{\"res\":\"���������ʽ������ע��ʧ�ܣ���ƽֻ̨֧����Ѷ�����ף���������ע��...\",\"flag\":false}";
            }
        }
        PrintWriter out = response.getWriter();
        out.println(json);
    }

    @Override
    public void init() throws ServletException {
    }

}
