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
 * �û���¼��ע��
 * 
 *1.��¼
 *     ���룺username��password
 *     �������ɹ� {"userId":1,"flag":"true"}
 *           ʧ�� {"flag":false}
 * 2.ע��
 *     ���룺username��password��phone
 *     �������ɹ� {"flag":"true"}
 *           ʧ�� {"flag":"false"}
 * 
 * @author �����ɵ����
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
                System.out.println("�ֻ���");
                user.setPhone(request.getParameter("username"));
                user = userdao.ifRightByPhone(user);
            } else if (Pattern.compile(
                    "([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})")
                    .matcher(request.getParameter("username")).matches()) {
                System.out.println("����");
                user.setUseremail(request.getParameter("username"));
                user = userdao.ifRightByUseremail(user);
            } else {
                System.out.println("�û���");
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
