package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HelpsDAO;
import net.sf.json.JSONObject;
import util.DBhelper;

@WebServlet("/help")
/**
 * 对“我”的帮助进行增删改，
 * 
 * 1.上传一件帮助( url : help?action=add )
 *传入用户编号uid 和表单( 名称(name),简介(overview),类型(type),联系方式(contact))
 *传出 成功 {"flag":"true"} 失败 {"flag":"false"}

 *2.修改一件帮助( url : help?action=update)
 *传入帮助编号id和表单(-------(名称(name),简介(overview),类型(type),联系方式(contact))
 *传出 成功 {"flag":"true"} 失败 {"flag":"false"}

 *3.删除一件帮助( url :help?action=delete)
 *传入帮助编号id
 *传出 成功 {"flag":"true"} 失败 {"flag":"false"}

 * 
 * @author 灰蒙蒙的天空
 *
 */
public class doHelps extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String action;
    public doHelps() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        ServletContext sc = this.getServletContext();
        DBhelper dc = (DBhelper) sc.getAttribute("dbhelper");
        HelpsDAO helpsdao = new HelpsDAO(dc);// 创建操作帮助dao类的对象
        int id = 1;
        if (request.getParameter("id") != null
                && request.getParameter("id") != "") {
            id = Integer.parseInt(request.getParameter("id"));
        }

        int uid = 1;
        if (request.getParameter("uid") != null
                && request.getParameter("uid") != "") {
            uid = Integer.parseInt(request.getParameter("uid"));
        }

        JSONObject json = new JSONObject();
        boolean flag = false;
        if (request.getParameter("action") != null
                && request.getParameter("action") != "") {
            this.action = request.getParameter("action");
            if (action.equals("add")) { // 添加我的帮助信息
                System.out.println("add");
                flag = addHelp(request, response, helpsdao, uid);
            } else if (action.equals("delete")) { // 下架帮助信息
                System.out.println("delete");
                flag = deleteHelp(request, response, helpsdao, id);
            } else if (action.equals("update")) {// 修改帮助信息
                flag = updateHelp(request, response, helpsdao, id);
            }
        }
        json.put("flag", Boolean.toString(flag));
        PrintWriter out = response.getWriter();
        System.out.println(json);
        out.println(json);
    }

    //修改帮助信息
    private boolean updateHelp(HttpServletRequest request,
            HttpServletResponse response, HelpsDAO helpsdao, int id) {
        String[] update = new String[5];
        String[] getParas = {"name", "overview", "type", "contact"};
        int j = 0;//计数
        for (int i = 0; i < getParas.length; i++) {
            if (getParas[i].equals("contact")) {
                Pattern p = Pattern.compile(
                        "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$");
                Matcher m = p.matcher(request.getParameter(getParas[i]));
                if (m.matches()) {
                    update[j++] = "手机";
                } else {
                    update[j++] = "QQ";
                }
            }
            update[j++] = request.getParameter(getParas[i]);
        }
        for (int m = 0; m < update.length; m++) {
            System.out.println(update[m]);
        }
        if (helpsdao.updateHelps(id, update)) {
            return true;
        }
        return false;
    }

    //删除一件帮助
    private boolean deleteHelp(HttpServletRequest request,
            HttpServletResponse response, HelpsDAO helpsdao, int id) {
        if (helpsdao.deleteHelps(id)) {
            System.out.println("id:" + id);
            return true;
        }
        return false;
    }

    //上传一件帮助
    private boolean addHelp(HttpServletRequest request,
            HttpServletResponse response, HelpsDAO helpsdao, int uid) {
        String[] update = new String[5];
        String[] getParas = {"name", "overview", "type", "contact"};
        if (helpsdao.ifHelpExist(request.getParameter("name"),
                request.getParameter("contact"))) {
            return false;
        }
        int j = 0;//计数
        for (int i = 0; i < getParas.length; i++) {
            if (getParas[i].equals("contact")) {
                Pattern p = Pattern.compile(
                        "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$");
                Matcher m = p.matcher(request.getParameter(getParas[i]));
                if (m.matches()) {
                    update[j++] = "手机";
                } else {
                    update[j++] = "QQ";
                }
            }
            update[j++] = request.getParameter(getParas[i]);
        }
        for (int m = 0; m < update.length; m++) {
            System.out.println(update[m]);
        }
        int id = helpsdao.addHelps(update, uid);
        if (id != 0) {
            return true;
        }
        return false;
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
