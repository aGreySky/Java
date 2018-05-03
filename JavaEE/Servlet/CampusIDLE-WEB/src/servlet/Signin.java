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
        user.setState("0");// 0:未激活，1：激活
        String code = UUIDUtil.getUUID() + UUIDUtil.getUUID();
        user.setCode(code);
        ServletContext sc = this.getServletContext();// 创建上下文对象
        DBhelper dc = (DBhelper) sc.getAttribute("dbhelper");
        UserDAO userdao = new UserDAO(dc);
        String json = null;
        if (userdao.ifExist(user)) {
            json = "{\"res\":\"用户名已存在，请重新输入...\",\"flag\":false}";
            System.out.println("注册失败");
        } else if (userdao.ifPhoneOrEmailExist(user)) {
            json = "{\"res\":\"手机号或邮箱已被注册，请重新输入...\",\"flag\":false}";
            System.out.println("注册失败");
        } else {
            if (userdao.addUser(user)) {
                json = "{\"res\":\"注册成功,快去通过邮箱激活吧...\",\"flag\":true}";
            } else {
                json = "{\"res\":\"您的邮箱格式有误导致注册失败，本平台只支持腾讯，网易，新浪邮箱注册...\",\"flag\":false}";
            }
        }
        PrintWriter out = response.getWriter();
        out.println(json);
    }

    @Override
    public void init() throws ServletException {
    }

}
