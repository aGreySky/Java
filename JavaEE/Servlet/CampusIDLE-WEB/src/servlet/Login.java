package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
@WebServlet("/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public Login() {
        super();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/index.html").forward(request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        User user = new User();
        user.setPassword(SHA.encrypt(request.getParameter("password")));
        ServletContext sc = this.getServletContext();
        DBhelper dc = (DBhelper) sc.getAttribute("dbhelper");
        UserDAO userdao = new UserDAO(dc);
        Pattern p = Pattern.compile(
                "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$");
        Matcher m = p.matcher(request.getParameter("username"));
        if (m.matches()) {
            System.out.println("手机号");
            user.setPhone(request.getParameter("username"));
            user = userdao.ifRightByPhone(user);
        } else if (Pattern.compile(
                "([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})")
                .matcher(request.getParameter("username")).matches()) {
            System.out.println("邮箱");
            user.setUseremail(request.getParameter("username"));
            user = userdao.ifRightByUseremail(user);
        } else {
            System.out.println("用户名");
            user.setUsername(request.getParameter("username"));
            user = userdao.ifRightByUsername(user);
        }

        String json = null;
        if (user != null) {
            request.getSession().setAttribute("user", user);
            if (user.getState().equals("1")) {
                json = "{\"flag\":true}";
            } else {
                json = "{\"res\":\"该账户还未激活，请激活后再登录...\",\"flag\":false}";
            }
        } else {
            json = "{\"res\":\"用户名（昵称、手机号、邮箱其中一项）或密码错误，请重新输入...\",\"flag\":false}";
        }
        PrintWriter out = response.getWriter();
        out.println(json);
    }

    @Override
    public void init() throws ServletException {
    }

}
