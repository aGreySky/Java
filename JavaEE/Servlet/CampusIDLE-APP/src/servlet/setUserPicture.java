package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
import net.sf.json.JSONObject;
import util.DBhelper;
import util.copyFile;

@WebServlet("/setupic")
public class setUserPicture extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String localPath = "E:/eclipse/CampusIDLE-WEB/WebContent/images/usersPicture/";
    String serverPath = "C:/apache-tomcat-8.5.11/wtpwebapps/CampusIDLE-WEB/";
    private String serverImagesPath = "http://xiaoyou.ngrok.cc/CampusIDLE-WEB/images/usersPicture/";

    public setUserPicture() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        ServletContext sc = this.getServletContext();
        DBhelper dc = (DBhelper) sc.getAttribute("dbhelper");
        UserDAO userdao = new UserDAO(dc);// ���������û�dao��Ķ���
        int uid = Integer.parseInt(request.getParameter("uid"));
        User user = userdao.getUserByUserId(uid);
        JSONObject json = new JSONObject();
        boolean flag = false;
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
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (fi.getName() != null && !fi.getName().equals("")) {
                    String[] picture = fi.getName().split("\\.");
                    String pic = picture[picture.length - 1];//ͼƬ��ʽ
                    System.out.println(user.getPicture());
                    String picNewName = uid + "." + pic;
                    File f1 = new File(
                            localPath + picNewName);
                    if (f1.exists()) {
                        f1.delete();
                    }
                    fi.write(f1);
                    File f2 = new File(
                            serverPath + "images/usersPicture/" + picNewName);
                    if (f2.exists()) {
                        f2.delete();
                    }
                    copyFile.fileChannelCopy(f1, f2);
                    flag = userdao.updateUserPicture(uid, picNewName);
                    if (flag) {
                        json.put("url", serverImagesPath + picNewName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        json.put("flag", Boolean.toString(flag));
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
