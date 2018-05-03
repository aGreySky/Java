package servlet;

import java.io.File;
import java.io.IOException;
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

import dao.UserDAO;
import entity.User;
import util.DBhelper;
import util.copyFile;

@SuppressWarnings("deprecation")
@WebServlet("/info")
public class Info extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String localPath = "E:/eclipse/CampusIDLE-WEB/WebContent/images/usersPicture/";

    public Info() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String serverPath = this.getServletContext().getRealPath("/");
        ServletContext sc = this.getServletContext();
        DBhelper dc = (DBhelper) sc.getAttribute("dbhelper");
        UserDAO userdao = new UserDAO(dc);//���������û���Ϣdao��Ķ���
        int id = Integer.parseInt(request.getParameter("id"));
        int ug = updateUser(request, response, userdao, id, serverPath);
        if (ug == 0) {
            response.sendRedirect("userInfo.jsp?id=" + id + "&flag=0");//�ɹ�
        } else if (ug == 1) {
            response.sendRedirect("userInfo.jsp?id=" + id + "&flag=1");//Ȩ�޲���
        } else if (ug == 2) {
            response.sendRedirect("userInfo.jsp?id=" + id + "&flag=2");//ͼƬ��ʽ����
        } else if (ug == 3) {
            response.sendRedirect("userInfo.jsp?id=" + id + "&flag=3");//���ݸ�ʽԭ��
        } else if (ug == 4) {
            response.sendRedirect("userInfo.jsp?id=" + id + "&flag=4");//������ԭ��
        }
    }

    //�����û���Ϣ
    private int updateUser(HttpServletRequest request,
            HttpServletResponse response, UserDAO userdao, int id,
            String serverPath) {
        int ug = 0;
        User user = userdao.getUserByUserId(id);
        if (id != ((User) request.getSession().getAttribute("user")).getId()) {//�жϲ���Ȩ��
            ug = 1;
        } else {
            try {
                DiskFileUpload fu = new DiskFileUpload();
                // ��������ļ��ߴ磬������12MB   
                fu.setSizeMax(4194304 * 3);
                // ���û�������С��������4kb   
                fu.setSizeThreshold(4096);
                //�����ϴ��ַ�
                fu.setHeaderEncoding("UTF-8");
                // ������ʱĿ¼��   
                fu.setRepositoryPath(this.getServletContext().getRealPath("/"));

                // �õ����е��ļ���   
                List fileItems = fu.parseRequest(request);
                Iterator i = fileItems.iterator();
                // ���δ���ÿһ���ļ���   
                String sql = "update user ";//�޸ĵ�sql���
                String[] update = new String[4];//�޸ĵĲ�������
                int j = 0, flag = 0;
                while (i.hasNext()) {
                    FileItem fi = (FileItem) i.next();
                    if (fi.isFormField()) { //�������ͨ���ֶ�  
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
                            update[j++] = value;
                            if (name.equals("phone")
                                    && !(value.matches("[0-9]+"))) {
                                ug = 3;
                            }
                        }
                    } else { //�ļ��ֶ� 
                        if (fi.getName() != null && fi.getName() != "") {
                            String[] picture = fi.getName().split("\\.");
                            String pic = picture[picture.length - 1];
                            if (!(pic.equals("jpg") || pic.equals("jpeg")
                                    || pic.equals("png"))) {
                                ug = 2;
                            } else {
                                user.setPicture(user.getId() + "." + pic);
                                File f1 = new File(
                                        localPath + user.getId() + "." + pic);
                                File f2 = new File(
                                        serverPath + "images/usersPicture/"
                                                + user.getId() + "." + pic);

                                if (f1.exists()) {
                                    f1.delete();
                                }
                                fi.write(f1);
                                if (f2.exists()) {
                                    f2.delete();
                                }
                                copyFile.fileChannelCopy(f1, f2);
                            }
                        }
                    }
                }
                if (flag == 1) {
                    sql = sql + ", ";
                }
                sql = sql + "set picture=? where id=?";
                System.out.println(sql);
                String Update[] = new String[j + 1];
                for (int k = 0; k < j; k++) {
                    Update[k] = update[k];
                }
                Update[j] = user.getPicture();
                if (ug == 0) {
                    if (userdao.updateUser(id, sql, Update)) {
                    } else {
                        ug = 4;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ug;
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
