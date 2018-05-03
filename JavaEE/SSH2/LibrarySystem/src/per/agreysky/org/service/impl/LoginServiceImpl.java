package per.agreysky.org.service.impl;

import per.agreysky.org.dao.AdminDAO;
import per.agreysky.org.dao.StudentDAO;
import per.agreysky.org.service.LoginService;
import per.agreysky.org.vo.Admin;
import per.agreysky.org.vo.Student;

public class LoginServiceImpl implements LoginService {
    private AdminDAO admindao;
    private StudentDAO studentdao;

    //管理员登录
    @Override
    public Admin checkAdminLogin(String adminId, String password) {
        return admindao.checkAdminLogin(adminId, password);
    }
    //读者登录
    @Override
    public Student checkStudentLogin(String readerId, String password) {
        return studentdao.checkStudentLogin(readerId, password);
    }

    public StudentDAO getStudentdao() {
        return studentdao;
    }
    public void setStudentdao(StudentDAO studentdao) {
        this.studentdao = studentdao;
    }
    public AdminDAO getAdmindao() {
        return admindao;
    }
    public void setAdmindao(AdminDAO admindao) {
        this.admindao = admindao;
    }

}