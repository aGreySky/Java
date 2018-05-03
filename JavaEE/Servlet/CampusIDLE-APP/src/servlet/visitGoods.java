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
 * 6.查看我的闲置( url: visit)
 *   传入：uid ,type(things或者helps或者jobs或者friends)
 *   传出：传出： 成功 (若干个items对象组成的集合)[{"items":...}{"items":...}](参数见文档)
 *               失败[]
 * 
 * @author 灰蒙蒙的天空
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
            ItemsDAO itemsdao = new ItemsDAO(dc);// 创建操作商品dao类的对象
            goods = itemsdao.getAllMyItems(uid);
        } else if (type.equals("helps")) {
            HelpsDAO helpsdao = new HelpsDAO(dc);// 创建操作帮助dao类的对象
            goods = helpsdao.getAllMyHelps(uid);
        } else if (type.equals("jobs")) {
            JobsDAO jobsdao = new JobsDAO(dc);// 创建操作兼职dao类的对象
            goods = jobsdao.getAllMyJobs(uid);
        } else if (type.equals("friends")) {
            FriendsDAO friendsDAO = new FriendsDAO(dc);// 创建操作交友dao类的对象
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
