package servlet;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import dao.messageDAO;
import entity.GuestBook;
import util.DBhelper;
@WebServlet("/message")

public class message extends HttpServlet {

    private Connection conn = null;
    ArrayList<GuestBook> list = null;

    private static final long serialVersionUID = 1L;
    public message() {
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
        request.setCharacterEncoding("utf-8");
        GuestBook gb = new GuestBook();
        gb.setName(request.getParameter("name"));
        gb.setTitle(request.getParameter("title"));
        gb.setContent(request.getParameter("content"));

        ServletContext sc = this.getServletContext();
        DBhelper dc = (DBhelper) sc.getAttribute("dbhelper");
        conn = (Connection) dc.getConnection();
        messageDAO dao = new messageDAO(conn);
        String sql = "insert into  messageList(name,title,content) values(?,?,?)";
        dao.update(sql, gb);
        sql = "select * from messageList";// ��ѯ���
        list = dao.selectAll(sql);// ���ò�ѯ������ע�ⷵ�ص���һ��ArrayList���ϼ����е�Ԫ����GuestBook���ʵ����
        sc.setAttribute("list", list);
        response.sendRedirect("show.jsp");
    }

}
