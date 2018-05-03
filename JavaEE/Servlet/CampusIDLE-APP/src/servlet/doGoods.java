package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

import dao.ItemsDAO;
import entity.Items;
import net.sf.json.JSONObject;
import util.DBhelper;
import util.copyFile;

@WebServlet("/good")

/**
 * �ԡ��ҡ�����Ʒ������ɾ�ģ�
 * 
 * 1.�ϴ�һ����Ʒ( url : good?action=add )
 *�����û����uid ��multiple��(ͼƬ(���������), ����(name),�۸�(price),���(overview),����(type),��ϵ��ʽ(contact))
 *���� �ɹ� {"flag":"true"} ʧ�� {"flag":"false"}

 *2.�޸�һ����Ʒ( url : good?action=update)
 *������Ʒ���id��multiple��(json+ͼƬ)-------(ͼƬ(���������), ����(name),�۸�(price),���(overview),����(type),��ϵ��ʽ(contact))
 *���� �ɹ� {"flag":"true"} ʧ�� {"flag":"false"}

 *3.ɾ��һ����Ʒ( url :good?action=delete)
 *������Ʒ���id
 *���� �ɹ� {"flag":"true"} ʧ�� {"flag":"false"}

 * 
 * @author �����ɵ����
 *
 */
public class doGoods extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String action;
    String localPath = "E:/eclipse/CampusIDLE-WEB/WebContent/images/itemsPicture/";
    String serverPath = "C:/apache-tomcat-8.5.11/wtpwebapps/CampusIDLE-WEB/";
    public doGoods() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        ServletContext sc = this.getServletContext();
        DBhelper dc = (DBhelper) sc.getAttribute("dbhelper");
        ItemsDAO itemsdao = new ItemsDAO(dc);// ����������Ʒdao��Ķ���
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
            if (action.equals("add")) { // ����ҵ�����
                System.out.println("add");
                flag = addGood(request, response, itemsdao, uid, serverPath);
            } else if (action.equals("delete")) { // �¼�
                System.out.println("delete");
                flag = deleteGood(request, response, itemsdao, id, serverPath);
            } else if (action.equals("update")) {// �޸�������Ϣ
                System.out.println("update");
                flag = updateGood(request, response, itemsdao, id, serverPath);
            }
        }
        json.put("flag", Boolean.toString(flag));
        PrintWriter out = response.getWriter();
        System.out.println(json);
        out.println(json);
    }

    // �޸�������Ϣ
    private boolean updateGood(HttpServletRequest request,
            HttpServletResponse response, ItemsDAO itemsdao, int id,
            String serverPath) {
        System.out.println(id);
        Items item = itemsdao.getItemsById(id);

        try {
            DiskFileUpload fu = new DiskFileUpload();
            // ��������ļ��ߴ磬������12MB
            fu.setSizeMax(4194304 * 3);
            // ���û�������С��������4kb
            fu.setSizeThreshold(4096);
            // �����ϴ��ַ�
            fu.setHeaderEncoding("UTF-8");
            // ������ʱĿ¼��
            fu.setRepositoryPath(this.getServletContext().getRealPath("/"));

            // �õ����е��ļ���
            List fileItems = fu.parseRequest(request);
            Iterator i = fileItems.iterator();
            // ���δ���ÿһ���ļ���
            int j = 0;
            String[] update = new String[9];// ��ӵĲ�������
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (fi.isFormField()) { // �������ͨ���ֶ�
                    System.out.println("��ͨ��");
                    String values = fi.getString("utf-8");
                    JSONObject getParas = JSONObject.fromObject(values);
                    String paras[] = {"name", "price", "overview", "type",
                            "contact", "ifNew", "origPrice"};
                    for (int s = 0; s < paras.length; s++) {
                        String value = getParas.getString(paras[s]);
                        if (paras[s].equals("contact")) {
                            Pattern p = Pattern.compile(
                                    "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$");
                            Matcher m = p.matcher(value);
                            if (m.matches()) {
                                update[j++] = "�ֻ�";
                            } else {
                                update[j++] = "QQ";
                            }
                        }
                        update[j++] = value;
                    }
                } else { // �ļ��ֶ�
                    if (fi.getName() != null && !fi.getName().equals("")) {
                        String[] picture = fi.getName().split("\\.");
                        String pic = picture[picture.length - 1];//ͼƬ��ʽ
                        System.out.println(item.getPicture());
                        String[] picUrl = item.getPicture().split("/");//ͼƬȫ��
                        String picName = picUrl[picUrl.length - 1];
                        String[] picFront = picName.split("\\.");//ͼƬ���ƼӸ�ʽ
                        String picNewName = picFront[0] + "." + pic;

                        File f1 = new File(
                                localPath + picNewName);
                        if (f1.exists()) {
                            f1.delete();
                        }
                        fi.write(f1);
                        File f2 = new File(
                                serverPath + "images/itemsPicture/"
                                        + picNewName);
                        if (f2.exists()) {
                            f2.delete();
                        }
                        copyFile.fileChannelCopy(f1, f2);
                        update[8] = picNewName;
                    }

                }
            }
            return itemsdao.updateGood(update, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ���������Ʒ
    private boolean addGood(HttpServletRequest request,
            HttpServletResponse response, ItemsDAO itemsdao, int uid,
            String serverPath) {
        try {
            DiskFileUpload fu = new DiskFileUpload();
            // ��������ļ��ߴ磬������12MB
            fu.setSizeMax(4194304 * 3);
            // ���û�������С��������4kb
            fu.setSizeThreshold(4096);
            // �����ϴ��ַ�
            fu.setHeaderEncoding("UTF-8");
            // ������ʱĿ¼��
            fu.setRepositoryPath(this.getServletContext().getRealPath("/"));

            // �õ����е��ļ���
            List fileItems = fu.parseRequest(request);
            Iterator i = fileItems.iterator();
            // ���δ���ÿһ���ļ���
            int j = 0;
            String[] update = new String[9];// ��ӵĲ�������
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (fi.isFormField()) { // �������ͨ���ֶ�
                    System.out.println("��ͨ��");
                    String values = fi.getString("utf-8");
                    JSONObject getParas = JSONObject.fromObject(values);
                    String paras[] = {"name", "price", "overview", "type",
                            "contact", "ifNew", "origPrice"};
                    if (itemsdao.ifItemExist(getParas.getString("name"),
                            getParas.getString("contact"))) {
                        return false;
                    }
                    for (int s = 0; s < paras.length; s++) {
                        String value = getParas.getString(paras[s]);
                        if (paras[s].equals("contact")) {
                            Pattern p = Pattern.compile(
                                    "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$");
                            Matcher m = p.matcher(value);
                            if (m.matches()) {
                                update[j++] = "�ֻ�";
                            } else {
                                update[j++] = "QQ";
                            }
                        }
                        update[j++] = value;
                    }
                } else { // �ļ��ֶ�
                    if (fi.getName() != null && fi.getName() != "") {
                        String[] picture = fi.getName().split("\\.");
                        String pic = picture[picture.length - 1];
                        int num = itemsdao.getLatestId();
                        System.out.println("num=" + num);
                        File f1 = new File(
                                localPath + uid + "00" + (num + 1) + "." + pic);
                        fi.write(f1);
                        File f2 = new File(
                                serverPath + "images/itemsPicture/" + uid + "00"
                                        + (num + 1) + "." + pic);
                        copyFile.fileChannelCopy(f1, f2);
                        update[8] = uid + "00" + (num + 1) + "." + pic;
                    }
                }
            }
            for (int m = 0; m < update.length; m++) {
                System.out.println(update[m]);
            }
            int id = itemsdao.addGood(update, uid);
            if (id != 0) {
                return true;
            } else {
                System.out.println("false");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ɾ��һ������
    private boolean deleteGood(HttpServletRequest request,
            HttpServletResponse response, ItemsDAO itemsdao, int id,
            String serverPath) {
        System.out.println("id=" + id);
        Items item = itemsdao.getItemsById(id);
        String[] picUrl = item.getPicture().split("/");//ͼƬȫ��
        String picName = picUrl[picUrl.length - 1];
        System.out.println(picName);

        if (itemsdao.deleteGood(id)) {

            File f1 = new File(localPath + picName);
            if (f1.exists()) {
                f1.delete();
            }
            File f2 = new File(serverPath + "images/itemsPicture/" + picName);
            if (f2.exists()) {
                f2.delete();
            }
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
