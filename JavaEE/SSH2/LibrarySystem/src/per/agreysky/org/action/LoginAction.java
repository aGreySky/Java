package per.agreysky.org.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import per.agreysky.org.service.LoginService;
import per.agreysky.org.util.SHA;
import per.agreysky.org.vo.Admin;
import per.agreysky.org.vo.Student;

public class LoginAction extends ActionSupport {
    private Admin admin;
    private Student student;
    private String message;
    private LoginService loginService;

    //����Ա��¼
    public String adminLogin() {
        admin.getAdminId();
        admin = loginService.checkAdminLogin(admin.getAdminId(),
                SHA.encrypt(admin.getPassword()));//����
        if (admin != null) {
            //���session����
            Map session = ActionContext.getContext().getSession();
            session.put("admin", admin);
            return SUCCESS;
        } else {
            this.setMessage("��¼ʧ�ܣ��˺������벻ƥ��,����������...");
            return ERROR;
        }
    }
    //���ߵ�¼
    public String studentLogin() {
        student = loginService.checkStudentLogin(student.getReaderId(),
                SHA.encrypt(student.getPassword()));
        if (student != null) {
            //���session����
            Map session = ActionContext.getContext().getSession();
            session.put("student", student);
            return SUCCESS;
        } else {
            this.setMessage("��¼ʧ�ܣ��˺������벻ƥ��,����������...");
            return ERROR;
        }
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Admin getAdmin() {
        return admin;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
