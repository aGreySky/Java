package servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import entity.User;
import util.DBhelper;

@WebServlet("/active")
public class Active extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Active() {
        super();

    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // 接收激活码
        String code = request.getParameter("code");
        // 根据激活码查询用户
        ServletContext sc = this.getServletContext();// 创建上下文对象
        DBhelper dc = (DBhelper) sc.getAttribute("dbhelper");
        UserDAO userdao = new UserDAO(dc);
        User user = userdao.findUserByCode(code);
        if (user != null) {
            // 已经查询到，修改用户状态
            user.setState("1");
            user.setCode(null);
            String sql = "update user set state = ? , code = ? where id = ?";
            String update[] = {user.getState(), user.getCode()};
            userdao.updateUser(user.getId(), sql, update);
            request.setAttribute("msg", "1");
        }
        // 页面跳转
        request.getRequestDispatcher("/msg.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

}
