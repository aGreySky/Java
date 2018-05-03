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
import net.sf.json.JSONObject;
import util.DBhelper;
import util.SHA;

@WebServlet("/loginregister")
/**
 * 
 * 用户登录和注册
 * 
 *1.登录
 *     传入：username，password
 *     传出：成功 {"userId":1,"flag":"true"}
 *           失败 {"flag":false}
 * 2.注册
 *     传入：username，password，phone
 *     传出：成功 {"flag":"true"}
 *           失败 {"flag":"false"}
 * 
 * @author 灰蒙蒙的天空
 *
 */
public class LoginAndRegister extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginAndRegister() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        ServletContext sc = this.getServletContext();
        DBhelper dc = (DBhelper) sc.getAttribute("dbhelper");
        UserDAO userdao = new UserDAO(dc);
        JSONObject json = new JSONObject();
        String flag = "false";
        String method = request.getParameter("method");
        if (method.equals("login")) {
            User user = new User();
            user.setPassword(SHA.encrypt(request.getParameter("password")));
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
            if (user != null) {
                json.put("userId", Integer.toString(user.getId()));
                json.put("userPicture", user.getPicture());
                flag = "true";
            }
        } else if (method.equals("register")) {
            User user = new User();
            user.setUsername(request.getParameter("username"));
            user.setPassword(SHA.encrypt(request.getParameter("password")));
            user.setPhone(request.getParameter("phone"));
            user.setState("1");
            if (!userdao.ifExist(user)) {
                userdao.addUser(user);
                flag = "true";
            }
        }
        json.put("flag", flag);
        PrintWriter out = response.getWriter();
        System.out.println(json.toString());
        out.println(json);
    }

}
