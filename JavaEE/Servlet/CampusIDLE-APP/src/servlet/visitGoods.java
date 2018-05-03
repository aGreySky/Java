package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FriendsDAO;
import dao.HelpsDAO;
import dao.ItemsDAO;
import dao.JobsDAO;
import net.sf.json.JSONArray;
import util.DBhelper;

/**
 * 6.�鿴�ҵ�����( url: visit)
 *   ���룺uid ,type(things����helps����jobs����friends)
 *   ������������ �ɹ� (���ɸ�items������ɵļ���)[{"items":...}{"items":...}](�������ĵ�)
 *               ʧ��[]
 * 
 * @author �����ɵ����
 *
 */
@WebServlet("/visit")
public class visitGoods extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public visitGoods() {
        super();

    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        ServletContext sc = this.getServletContext();
        DBhelper dc = (DBhelper) sc.getAttribute("dbhelper");
        List goods = null;
        int uid = 1;
        if (request.getParameter("uid") != null
                && request.getParameter("uid") != "") {
            uid = Integer.parseInt(request.getParameter("uid"));
        }
        String type = request.getParameter("type");
        if (type.equals("things")) {
            ItemsDAO itemsdao = new ItemsDAO(dc);// ����������Ʒdao��Ķ���
            goods = itemsdao.getAllMyItems(uid);
        } else if (type.equals("helps")) {
            HelpsDAO helpsdao = new HelpsDAO(dc);// ������������dao��Ķ���
            goods = helpsdao.getAllMyHelps(uid);
        } else if (type.equals("jobs")) {
            JobsDAO jobsdao = new JobsDAO(dc);// ����������ְdao��Ķ���
            goods = jobsdao.getAllMyJobs(uid);
        } else if (type.equals("friends")) {
            FriendsDAO friendsDAO = new FriendsDAO(dc);// ������������dao��Ķ���
            goods = friendsDAO.getAllMyFriends(uid);
        }
        JSONArray json = JSONArray.fromObject(goods);
        PrintWriter out = response.getWriter();
        System.out.println(json);
        out.println(json);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
