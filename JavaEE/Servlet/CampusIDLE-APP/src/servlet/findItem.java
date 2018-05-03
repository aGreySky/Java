package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemsDAO;
import entity.Items;
import net.sf.json.JSONObject;
import util.DBhelper;

@WebServlet("/find")
/**
 * 1.查找一件商品(url : find )
 *    传入：id
 *    传出：成功 {"item":....}(参数见文档)
 *          失败 {}
 * @author 灰蒙蒙的天空
 *
 */
public class findItem extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public findItem() {
        super();

    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        ServletContext sc = this.getServletContext();
        int id = 1;
        if (request.getParameter("id") != null
                && request.getParameter("id") != "") {
            id = Integer.parseInt(request.getParameter("id"));
        }
        DBhelper dc = (DBhelper) sc.getAttribute("dbhelper");
        ItemsDAO itemsdao = new ItemsDAO(dc);// 创建操作商品dao类的对象
        Items item = itemsdao.getItemsById(id);
        JSONObject json = new JSONObject();
        json.put("item", item);
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
