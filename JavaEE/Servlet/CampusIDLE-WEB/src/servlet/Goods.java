package servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import entity.User;
import util.DBhelper;
import util.copyFile;

@WebServlet("/good")
public class Goods extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String action;
    String localPath = "E:/eclipse/CampusIDLE-WEB/WebContent/images/itemsPicture/";

    public Goods() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String serverPath = this.getServletContext().getRealPath("/");
        ServletContext sc = this.getServletContext();
        DBhelper dc = (DBhelper) sc.getAttribute("dbhelper");
        ItemsDAO itemsdao = new ItemsDAO(dc);//创建操作商品dao类的对象
        int uid = ((User) request.getSession().getAttribute("user")).getId();
        int id = 1;
        if (request.getParameter("id") != null) {
            id = Integer.parseInt(request.getParameter("id"));
        }

        if (request.getSession().getAttribute("goods") == null) {//如果是第一次访问我的商品，则创建我的闲置集合
            ArrayList<Items> goods = new ArrayList<Items>();
            goods = itemsdao.getAllMyItems(uid);
            request.getSession().setAttribute("goods", goods);
        }

        if (request.getParameter("action") != null) {
            this.action = request.getParameter("action");
            if (action.equals("add")) { //添加我的闲置
                int ug = addGood(request, response, itemsdao, uid, serverPath);
                if (ug == 0) {
                    request.getSession().setAttribute("myPages", null);//重新分页
                    response.sendRedirect("addGood.jsp?id=" + id + "&flag=0");//成功
                } else if (ug == 1) {
                    response.sendRedirect("addGood.jsp?id=" + id + "&flag=1");//数据不能为空
                } else if (ug == 2) {
                    response.sendRedirect("addGood.jsp?id=" + id + "&flag=2");//图片格式出错
                } else if (ug == 3) {
                    response.sendRedirect("addGood.jsp?id=" + id + "&flag=3");//数据格式出错
                } else if (ug == 4) {
                    response.sendRedirect("addGood.jsp?id=" + id + "&flag=4");//服务器出错
                } else if (ug == 5) {
                    response.sendRedirect("addGood.jsp?id=" + id + "&flag=5");//服务器出错
                }
            } else if (action.equals("show")) {//查看我的闲置
                response.sendRedirect("firstPage.jsp?div=3");
            } else if (action.equals("delete")) { //下架
                if (deleteGood(request, response, itemsdao, uid, id,
                        serverPath)) {
                    request.getSession().setAttribute("myPages", null);//重新分页
                    response.sendRedirect("firstPage.jsp?div=3" + "&flag=true");
                } else {
                    response.sendRedirect(
                            "firstPage.jsp?div=3" + "&flag=false");
                }
            } else if (action.equals("clear")) {//下架所有闲置
                if (clearCart(request, response, itemsdao, uid, serverPath)) {
                    request.getSession().setAttribute("myPages", null);//重新分页
                    response.sendRedirect("firstPage.jsp?div=3" + "&flag=true");
                } else {
                    response.sendRedirect(
                            "firstPage.jsp?div=3" + "&flag=false");
                }
            } else if (action.equals("update")) {//修改闲置信息
                int ug = updateGood(request, response, itemsdao, uid, id,
                        serverPath);
                if (ug == 0) {
                    response.sendRedirect(
                            "updateGood.jsp?id=" + id + "&flag=0");//成功
                } else if (ug == 1) {
                    response.sendRedirect(
                            "updateGood.jsp?id=" + id + "&flag=1");//权限不足
                } else if (ug == 2) {
                    response.sendRedirect(
                            "updateGood.jsp?id=" + id + "&flag=2");//图片格式出错
                } else if (ug == 3) {
                    response.sendRedirect(
                            "updateGood.jsp?id=" + id + "&flag=3");//数据格式原因
                } else if (ug == 4) {
                    response.sendRedirect(
                            "updateGood.jsp?id=" + id + "&flag=4");//服务器原因
                }
            }
        }
    }

    //修改闲置信息
    private int updateGood(HttpServletRequest request,
            HttpServletResponse response, ItemsDAO itemsdao, int uid, int id,
            String serverPath) {
        System.out.println(serverPath);
        Items item = itemsdao.getItemsById(id);
        if (item.getUser().getId() != uid) {//判断操作权限
            return 1;
        } else {
            try {
                DiskFileUpload fu = new DiskFileUpload();
                // 设置最大文件尺寸，这里是12MB   
                fu.setSizeMax(4194304 * 3);
                // 设置缓冲区大小，这里是4kb   
                fu.setSizeThreshold(4096);
                //设置上传字符
                fu.setHeaderEncoding("UTF-8");
                // 设置临时目录：   
                fu.setRepositoryPath(this.getServletContext().getRealPath("/"));

                // 得到所有的文件：   
                List fileItems = fu.parseRequest(request);
                Iterator i = fileItems.iterator();
                // 依次处理每一个文件：   
                String sql = "update items ";//修改的sql语句
                String[] update = new String[7];//修改的参数数组
                int j = 0, flag = 0;
                while (i.hasNext()) {
                    FileItem fi = (FileItem) i.next();
                    if (fi.isFormField()) { //如果是普通表单字段  
                        String name = fi.getFieldName();
                        String value = fi.getString("utf-8");
                        if (value != null && !value.equals("")) {
                            if (j != 0) {
                                sql = sql + ", ";
                            } else {
                                sql = sql + "set ";
                            }
                            sql = sql + name + "=? ";
                            flag = 1;
                            if (j <= 7) {
                                update[j++] = value;
                            }
                            if (name.equals("price")
                                    && !(value.matches("[0-9]+"))) {
                                return 3;
                            }
                        }
                    } else { //文件字段 
                        if (fi.getName() != null && !fi.getName().equals("")) {
                            String[] picture = fi.getName().split("\\.");
                            String pic = picture[picture.length - 1];//图片格式
                            String[] picUrl = item.getPicture().split("/");//图片全称
                            String picName = picUrl[picUrl.length - 1];

                            String[] picFront = picName.split("\\.");//图片名称加格式
                            String picNewName = picFront[0] + "." + pic;
                            if (!(pic.equals("jpg") || pic.equals("jpeg")
                                    || pic.equals("png"))) {
                                return 2;
                            } else {
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
                                if (j != 0) {
                                    sql = sql + ", ";
                                } else {
                                    sql = sql + "set ";
                                }
                                sql = sql + "picture =? ";
                                if (j <= 7) {
                                    update[j++] = picNewName;
                                }
                            }
                        }
                    }
                }
                sql = sql + "where id=?";
                System.out.println(sql);
                String Update[] = new String[j];
                for (int k = 0; k < j; k++) {
                    Update[k] = update[k];
                }
                if (flag == 1) {
                    if (itemsdao.updateGood(id, sql, Update)) {
                    } else {
                        return 4;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    //添加闲置物品
    private int addGood(HttpServletRequest request,
            HttpServletResponse response, ItemsDAO itemsdao, int uid,
            String serverPath) {
        ArrayList<Items> goods = (ArrayList<Items>) request.getSession()
                .getAttribute("goods");
        try {
            DiskFileUpload fu = new DiskFileUpload();
            // 设置最大文件尺寸，这里是12MB   
            fu.setSizeMax(4194304 * 3);
            // 设置缓冲区大小，这里是4kb   
            fu.setSizeThreshold(4096);
            //设置上传字符
            fu.setHeaderEncoding("UTF-8");
            // 设置临时目录：   
            fu.setRepositoryPath(this.getServletContext().getRealPath("/"));

            // 得到所有的文件：   
            List fileItems = fu.parseRequest(request);
            Iterator i = fileItems.iterator();
            // 依次处理每一个文件：    
            int j = 0;
            String[] update = new String[7];//添加的参数数组
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (fi.isFormField()) { //如果是普通表单字段  
                    String name = fi.getFieldName();
                    String value = fi.getString("utf-8");
                    if (value == null && value.equals("")) {
                        return 1;
                    } else {
                        update[j++] = value;
                    }
                    if (name.equals("price") && !(value.matches("[0-9]+"))) {
                        return 3;
                    }
                } else { //文件字段 
                    if (fi.getName() != null && fi.getName() != "") {
                        String[] picture = fi.getName().split("\\.");
                        String pic = picture[picture.length - 1];
                        if (!(pic.equals("jpg") || pic.equals("jpeg")
                                || pic.equals("png"))) {
                            return 2;
                        } else {
                            int num = itemsdao.getAllMyItems(uid).size();
                            File f1 = new File(localPath + uid + "00"
                                    + (num + 1) + "." + pic);
                            fi.write(f1);
                            File f2 = new File(
                                    serverPath + "images/itemsPicture/" + uid
                                            + "00" + (num + 1) + "." + pic);
                            copyFile.fileChannelCopy(f1, f2);
                            update[6] = uid + "00" + (num + 1) + "." + pic;
                        }
                    }
                }
            }
            if (itemsdao.ifItemExist(update[0], update[4])) {
                return 5;
            }
            int id = itemsdao.addGood(update, uid);
            if (id != 0) {
                goods.add(itemsdao.getItemsById(id));
            } else {
                return 4;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //删除一件闲置
    private boolean deleteGood(HttpServletRequest request,
            HttpServletResponse response, ItemsDAO itemsdao, int uid, int id,
            String serverPath) {
        @SuppressWarnings("unchecked")
        ArrayList<Items> goods = (ArrayList<Items>) request.getSession()
                .getAttribute("goods");
        Items item = itemsdao.getItemsById(id);
        if (item.getUser().getId() != uid) {//判断操作权限
            return false;
        } else {
            if (goods.remove(item)) {
                if (itemsdao.deleteGood(id)) {
                    File f1 = new File(localPath + item.getPicture());
                    if (f1.exists()) {
                        f1.delete();
                    }
                    File f2 = new File(serverPath + "images/itemsPicture/"
                            + item.getPicture());
                    if (f2.exists()) {
                        f2.delete();
                    }
                    return true;
                }
            }
        }
        return false;
    }
    //下架所有闲置
    private boolean clearCart(HttpServletRequest request,
            HttpServletResponse response, ItemsDAO itemsdao, int uid,
            String serverPath) {
        ArrayList<Items> goods = (ArrayList<Items>) request.getSession()
                .getAttribute("goods");
        if (itemsdao.deleteAllMyGoodByUid(uid)) {
            while (!goods.isEmpty()) {
                String picture = goods.get(0).getPicture();
                File f1 = new File(localPath + picture);
                if (f1.exists()) {
                    f1.delete();
                }
                File f2 = new File(
                        serverPath + "images/itemsPicture/" + picture);
                if (f2.exists()) {
                    f2.delete();
                }
                goods.remove(0);
            }
            if (goods.isEmpty()) {
                return true;
            }
        }
        return false;
    }
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
