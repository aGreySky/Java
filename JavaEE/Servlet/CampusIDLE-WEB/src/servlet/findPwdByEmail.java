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
import util.pwdMail;

@WebServlet("/findpwd")
public class findPwdByEmail extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public findPwdByEmail() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        User user = new User();
        user.setId(Integer.parseInt(request.getParameter("id")));
        user.setPassword(request.getParameter("pwd"));
        ServletContext sc = this.getServletContext();
        DBhelper dc = (DBhelper) sc.getAttribute("dbhelper");
        UserDAO userdao = new UserDAO(dc);
        String sql = "update user set password=? where id=?;";
        String[] update = {user.getPassword()};
        if (userdao.updateUser(user.getId(), sql, update)) {
            request.setAttribute("fps", "1");
        }
        // 页面跳转
        request.getRequestDispatcher("/fps.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        System.out.println("1");
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setUseremail(request.getParameter("useremail"));
        String password = SHA.encrypt(request.getParameter("password"));
        ServletContext sc = this.getServletContext();
        DBhelper dc = (DBhelper) sc.getAttribute("dbhelper");
        UserDAO userdao = new UserDAO(dc);
        String json = null;
        int id = userdao.ifRightByEmail(user);
        if (id != 0) {
            try {
                pwdMail.sendMail(user.getUseremail(), id, password);
                json = "{\"res\":\"恭喜您！设置新密码的邮件已发往您的邮箱，请前往邮箱查看...\",\"flag\":true}";
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            json = "{\"res\":\"您的用户名和邮箱不匹配，请重新输入...\",\"flag\":false}";
        }
        PrintWriter out = response.getWriter();
        out.println(json);
    }

}
