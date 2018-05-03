package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemsDAO;
import entity.Items;
import entity.Pages;
import util.DBhelper;

@WebServlet("/paging")
public class Paging extends HttpServlet {
    Pages myPages = null;
    ArrayList<Items> newList = null;
    private static final long serialVersionUID = 1L;
    public Paging() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String strPage = request.getParameter("Page");
        int Page = 1;
        if (request.getSession().getAttribute("myPages") == null
                || request.getParameter("condition") != null) {
            myPages = new Pages();
            ServletContext sc = request.getServletContext();
            DBhelper db = (DBhelper) sc.getAttribute("dbhelper");
            ItemsDAO itemsDao = new ItemsDAO(db);
            ArrayList<Items> list;
            String Cdn = request.getParameter("condition");
            if (Cdn != null && Cdn != "" && !Cdn.equals("all")) {
                String sql = "select * from items where name like '%" + Cdn
                        + "%' OR overview like '%" + Cdn + "%' OR type like '%"
                        + Cdn + "%'";
                list = itemsDao.getItemsByUserCdn(sql);
            } else {
                list = itemsDao.getAllItems();
            }
            int recordCount = list.size();
            int pagesize = 12;
            int maxPage = myPages.getMaxPage();
            myPages.setPagesize(pagesize);
            myPages.setList(list);
            myPages.setMaxPage(maxPage);
            myPages.setRecordCount(recordCount);
            newList = myPages.getInitPage(list, 1, pagesize);//初始化分页信息，即获取到存在数据库中的list,同时获取到首页数据
        } else {
            myPages = (Pages) request.getSession().getAttribute("myPages");
            Page = myPages.getPage(strPage);
            newList = myPages.getAppointPage(Page);//获取当前页数据
        }
        myPages.setPage(Page);
        request.getSession().setAttribute("myPages", myPages);
        request.getSession().setAttribute("ItemsList", newList);
        if (request.getParameter("condition") != null
                && !request.getParameter("condition").equals("all")) {
            response.sendRedirect("firstPage.jsp?action=find");
        } else {
            response.sendRedirect("firstPage.jsp");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
