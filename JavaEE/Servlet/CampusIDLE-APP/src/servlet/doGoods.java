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
 * 对“我”的商品进行增删改，
 * 
 * 1.上传一件商品( url : good?action=add )
 *传入用户编号uid 和multiple表单(图片(无需参数名), 名称(name),价格(price),简介(overview),类型(type),联系方式(contact))
 *传出 成功 {"flag":"true"} 失败 {"flag":"false"}

 *2.修改一件商品( url : good?action=update)
 *传入物品编号id和multiple表单(json+图片)-------(图片(无需参数名), 名称(name),价格(price),简介(overview),类型(type),联系方式(contact))
 *传出 成功 {"flag":"true"} 失败 {"flag":"false"}

 *3.删除一件商品( url :good?action=delete)
 *传入物品编号id
 *传出 成功 {"flag":"true"} 失败 {"flag":"false"}

 * 
 * @author 灰蒙蒙的天空
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
        ItemsDAO itemsdao = new ItemsDAO(dc);// 创建操作商品dao类的对象
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
            if (action.equals("add")) { // 添加我的闲置
                System.out.println("add");
                flag = addGood(request, response, itemsdao, uid, serverPath);
            } else if (action.equals("delete")) { // 下架
                System.out.println("delete");
                flag = deleteGood(request, response, itemsdao, id, serverPath);
            } else if (action.equals("update")) {// 修改闲置信息
                System.out.println("update");
                flag = updateGood(request, response, itemsdao, id, serverPath);
            }
        }
        json.put("flag", Boolean.toString(flag));
        PrintWriter out = response.getWriter();
        System.out.println(json);
        out.println(json);
    }

    // 修改闲置信息
    private boolean updateGood(HttpServletRequest request,
            HttpServletResponse response, ItemsDAO itemsdao, int id,
            String serverPath) {
        System.out.println(id);
        Items item = itemsdao.getItemsById(id);

        try {
            DiskFileUpload fu = new DiskFileUpload();
            // 设置最大文件尺寸，这里是12MB
            fu.setSizeMax(4194304 * 3);
            // 设置缓冲区大小，这里是4kb
            fu.setSizeThreshold(4096);
            // 设置上传字符
            fu.setHeaderEncoding("UTF-8");
            // 设置临时目录：
            fu.setRepositoryPath(this.getServletContext().getRealPath("/"));

            // 得到所有的文件：
            List fileItems = fu.parseRequest(request);
            Iterator i = fileItems.iterator();
            // 依次处理每一个文件：
            int j = 0;
            String[] update = new String[9];// 添加的参数数组
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (fi.isFormField()) { // 如果是普通表单字段
                    System.out.println("普通表单");
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
                                update[j++] = "手机";
                            } else {
                                update[j++] = "QQ";
                            }
                        }
                        update[j++] = value;
                    }
                } else { // 文件字段
                    if (fi.getName() != null && !fi.getName().equals("")) {
                        String[] picture = fi.getName().split("\\.");
                        String pic = picture[picture.length - 1];//图片格式
                        System.out.println(item.getPicture());
                        String[] picUrl = item.getPicture().split("/");//图片全称
                        String picName = picUrl[picUrl.length - 1];
                        String[] picFront = picName.split("\\.");//图片名称加格式
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

    // 添加闲置物品
    private boolean addGood(HttpServletRequest request,
            HttpServletResponse response, ItemsDAO itemsdao, int uid,
            String serverPath) {
        try {
            DiskFileUpload fu = new DiskFileUpload();
            // 设置最大文件尺寸，这里是12MB
            fu.setSizeMax(4194304 * 3);
            // 设置缓冲区大小，这里是4kb
            fu.setSizeThreshold(4096);
            // 设置上传字符
            fu.setHeaderEncoding("UTF-8");
            // 设置临时目录：
            fu.setRepositoryPath(this.getServletContext().getRealPath("/"));

            // 得到所有的文件：
            List fileItems = fu.parseRequest(request);
            Iterator i = fileItems.iterator();
            // 依次处理每一个文件：
            int j = 0;
            String[] update = new String[9];// 添加的参数数组
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (fi.isFormField()) { // 如果是普通表单字段
                    System.out.println("普通表单");
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
                                update[j++] = "手机";
                            } else {
                                update[j++] = "QQ";
                            }
                        }
                        update[j++] = value;
                    }
                } else { // 文件字段
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

    // 删除一件闲置
    private boolean deleteGood(HttpServletRequest request,
            HttpServletResponse response, ItemsDAO itemsdao, int id,
            String serverPath) {
        System.out.println("id=" + id);
        Items item = itemsdao.getItemsById(id);
        String[] picUrl = item.getPicture().split("/");//图片全称
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
