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

@WebServlet("/advice")
public class Advice extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Advice() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String advice = request.getParameter("advice");
        int uid = Integer.parseInt(request.getParameter("uid"));
        ServletContext sc = this.getServletContext();
        DBhelper dc = (DBhelper) sc.getAttribute("dbhelper");
        UserDAO userdao = new UserDAO(dc);
        JSONObject json = new JSONObject();
        String flag = "false";
        if (userdao.addAdvice(uid, advice)) {
            flag = "true";
        }
        json.put("flag", flag);
        PrintWriter out = response.getWriter();
        System.out.println(json.toString());
        out.println(json);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
