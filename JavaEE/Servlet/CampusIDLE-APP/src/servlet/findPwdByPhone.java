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
import net.sf.json.JSONObject;
import util.DBhelper;
import util.SHA;

@WebServlet("/findpwd")
public class findPwdByPhone extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public findPwdByPhone() {
        super();

    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String phone = request.getParameter("phone");
        String action = request.getParameter("action");
        ServletContext sc = this.getServletContext();
        DBhelper dc = (DBhelper) sc.getAttribute("dbhelper");
        UserDAO userdao = new UserDAO(dc);
        String flag = "false";
        JSONObject json = new JSONObject();
        if (action.equals("1")) {
            if (userdao.ifPhoneExist(phone)) {
                flag = "true";
            }
        } else if (action.equals("2")) {
            String password = SHA.encrypt(request.getParameter("password"));
            if (userdao.updatePwdByPhone(phone, password)) {
                flag = "true";
            }
        }
        json.put("flag", flag);
        PrintWriter out = response.getWriter();
        //        System.out.println(json);
        out.println(json);

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

}
