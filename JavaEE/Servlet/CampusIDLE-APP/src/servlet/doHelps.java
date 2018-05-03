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
 * �ԡ��ҡ��İ���������ɾ�ģ�
 * 
 * 1.�ϴ�һ������( url : help?action=add )
 *�����û����uid �ͱ�( ����(name),���(overview),����(type),��ϵ��ʽ(contact))
 *���� �ɹ� {"flag":"true"} ʧ�� {"flag":"false"}

 *2.�޸�һ������( url : help?action=update)
 *����������id�ͱ�(-------(����(name),���(overview),����(type),��ϵ��ʽ(contact))
 *���� �ɹ� {"flag":"true"} ʧ�� {"flag":"false"}

 *3.ɾ��һ������( url :help?action=delete)
 *����������id
 *���� �ɹ� {"flag":"true"} ʧ�� {"flag":"false"}

 * 
 * @author �����ɵ����
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
        HelpsDAO helpsdao = new HelpsDAO(dc);// ������������dao��Ķ���
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
            if (action.equals("add")) { // ����ҵİ�����Ϣ
                System.out.println("add");
                flag = addHelp(request, response, helpsdao, uid);
            } else if (action.equals("delete")) { // �¼ܰ�����Ϣ
                System.out.println("delete");
                flag = deleteHelp(request, response, helpsdao, id);
            } else if (action.equals("update")) {// �޸İ�����Ϣ
                flag = updateHelp(request, response, helpsdao, id);
            }
        }
        json.put("flag", Boolean.toString(flag));
        PrintWriter out = response.getWriter();
        System.out.println(json);
        out.println(json);
    }

    //�޸İ�����Ϣ
    private boolean updateHelp(HttpServletRequest request,
            HttpServletResponse response, HelpsDAO helpsdao, int id) {
        String[] update = new String[5];
        String[] getParas = {"name", "overview", "type", "contact"};
        int j = 0;//����
        for (int i = 0; i < getParas.length; i++) {
            if (getParas[i].equals("contact")) {
                Pattern p = Pattern.compile(
                        "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$");
                Matcher m = p.matcher(request.getParameter(getParas[i]));
                if (m.matches()) {
                    update[j++] = "�ֻ�";
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

    //ɾ��һ������
    private boolean deleteHelp(HttpServletRequest request,
            HttpServletResponse response, HelpsDAO helpsdao, int id) {
        if (helpsdao.deleteHelps(id)) {
            System.out.println("id:" + id);
            return true;
        }
        return false;
    }

    //�ϴ�һ������
    private boolean addHelp(HttpServletRequest request,
            HttpServletResponse response, HelpsDAO helpsdao, int uid) {
        String[] update = new String[5];
        String[] getParas = {"name", "overview", "type", "contact"};
        if (helpsdao.ifHelpExist(request.getParameter("name"),
                request.getParameter("contact"))) {
            return false;
        }
        int j = 0;//����
        for (int i = 0; i < getParas.length; i++) {
            if (getParas[i].equals("contact")) {
                Pattern p = Pattern.compile(
                        "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$");
                Matcher m = p.matcher(request.getParameter(getParas[i]));
                if (m.matches()) {
                    update[j++] = "�ֻ�";
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
