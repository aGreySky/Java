package per.agreysky.org.service;

import per.agreysky.org.vo.Admin;
import per.agreysky.org.vo.Student;

public interface LoginService {
    //����Ա��¼
    public Admin checkAdminLogin(String adminId, String password);
    //���ߵ�¼
    public Student checkStudentLogin(String readerId, String password);

}
