package per.agreysky.org.service;

import per.agreysky.org.vo.Admin;
import per.agreysky.org.vo.Student;

public interface LoginService {
    //管理员登录
    public Admin checkAdminLogin(String adminId, String password);
    //读者登录
    public Student checkStudentLogin(String readerId, String password);

}
