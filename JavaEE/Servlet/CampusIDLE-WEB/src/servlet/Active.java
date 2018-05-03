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
        // ���ռ�����
        String code = request.getParameter("code");
        // ���ݼ������ѯ�û�
        ServletContext sc = this.getServletContext();// ���������Ķ���
        DBhelper dc = (DBhelper) sc.getAttribute("dbhelper");
        UserDAO userdao = new UserDAO(dc);
        User user = userdao.findUserByCode(code);
        if (user != null) {
            // �Ѿ���ѯ�����޸��û�״̬
            user.setState("1");
            user.setCode(null);
            String sql = "update user set state = ? , code = ? where id = ?";
            String update[] = {user.getState(), user.getCode()};
            userdao.updateUser(user.getId(), sql, update);
            request.setAttribute("msg", "1");
        }
        // ҳ����ת
        request.getRequestDispatcher("/msg.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

}
