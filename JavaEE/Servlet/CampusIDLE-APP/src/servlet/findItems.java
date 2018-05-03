package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
import entity.Items;
import net.sf.json.JSONArray;
import util.DBhelper;

@WebServlet("/items")
/**
 * 1.����һ����Ʒ( url : items)
 *      ���룺 kind 
 *      ���� �ɹ� (���ɸ�items������ɵļ���)[{"items":...}{"items":...}](�������ĵ�)
 *          ʧ��[]
 * 2.������Ʒ( url : items)
 *      ���룺 title 
 *      ���� �ɹ� (���ɸ�items������ɵļ���)[{"items":...}{"items":...}](�������ĵ�)
 *          ʧ��[]
 * @author �����ɵ����
 *
 */
public class findItems extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public findItems() {
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

        JSONArray json = null;
        if (request.getParameter("kind") != null
                && request.getParameter("kind") != "") {
            String type = request.getParameter("kind");
            System.out.println("1");
            if (type.equals("����") || type.equals("����")) {
                HelpsDAO helpsdao = new HelpsDAO(dc);
                json = JSONArray.fromObject(helpsdao.getHelpsByType(type));
            } else if (type.equals("����")) {
                FriendsDAO friendsDAO = new FriendsDAO(dc);
                json = JSONArray.fromObject(friendsDAO.getFriendsByType());
            } else if (type.equals("��ְ") || type.equals("��ְ")) {
                JobsDAO jobsDAO = new JobsDAO(dc);
                json = JSONArray.fromObject(jobsDAO.getJobsByType(type));
            } else {
                ItemsDAO itemsdao = new ItemsDAO(dc);
                json = JSONArray.fromObject(itemsdao.getItemsByType(type));
            }
        } else if (request.getParameter("title") != null
                && request.getParameter("title") != "") {
            String Cdn = request.getParameter("title");
            String itemsSql = "select * from items where name like '%" + Cdn
                    + "%' OR overview like '%" + Cdn + "%' OR type like '%"
                    + Cdn + "%'";
            String helpsSql = "select * from helps where name like '%" + Cdn
                    + "%' OR overview like '%" + Cdn + "%' OR type like '%"
                    + Cdn + "%'";
            String jobsSql = "select * from jobs where name like '%" + Cdn
                    + "%' OR overview like '%" + Cdn + "%' OR type like '%"
                    + Cdn + "%'";
            String friendsSql = "select * from friends where overview like '%"
                    + Cdn + "%' ";
            ItemsDAO itemsdao = new ItemsDAO(dc);
            ArrayList<Items> itemsList = itemsdao.getItemsByUserCdn(itemsSql);
            HelpsDAO helpsdao = new HelpsDAO(dc);
            ArrayList<Items> helpsList = helpsdao.getHelpsByUserCdn(helpsSql);
            JobsDAO jobsdao = new JobsDAO(dc);
            ArrayList<Items> jobsList = jobsdao.getJobsByUserCdn(jobsSql);
            FriendsDAO friendsdao = new FriendsDAO(dc);
            ArrayList<Items> friendsList = friendsdao
                    .getFriendsByUserCdn(friendsSql);
            ArrayList<Items> list = new ArrayList<Items>();
            if (!itemsList.isEmpty()) {
                list.addAll(itemsList);
            }
            if (!helpsList.isEmpty()) {
                list.addAll(helpsList);
            }
            if (!jobsList.isEmpty()) {
                list.addAll(jobsList);
            }
            if (!friendsList.isEmpty()) {
                list.addAll(friendsList);
            }
            json = JSONArray.fromObject(list);
        }
        PrintWriter out = response.getWriter();
        System.out.println(json);
        out.println(json);
    }

}
