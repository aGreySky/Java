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

    //管理员登录
    public String adminLogin() {
        admin.getAdminId();
        admin = loginService.checkAdminLogin(admin.getAdminId(),
                SHA.encrypt(admin.getPassword()));//加密
        if (admin != null) {
            //获得session对象
            Map session = ActionContext.getContext().getSession();
            session.put("admin", admin);
            return SUCCESS;
        } else {
            this.setMessage("登录失败，账号与密码不匹配,请重新输入...");
            return ERROR;
        }
    }
    //读者登录
    public String studentLogin() {
        student = loginService.checkStudentLogin(student.getReaderId(),
                SHA.encrypt(student.getPassword()));
        if (student != null) {
            //获得session对象
            Map session = ActionContext.getContext().getSession();
            session.put("student", student);
            return SUCCESS;
        } else {
            this.setMessage("登录失败，账号与密码不匹配,请重新输入...");
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
