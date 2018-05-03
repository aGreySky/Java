package per.agreysky.action;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import per.agreysky.service.LoginService;
import per.agreysky.vo.Login;
public class LoginAction extends ActionSupport {
    private Login login;
    private LoginService loginservice;//++
    private String message;
    //�����û������ execute ����
    @Override
    public String execute() throws Exception {
        //ֱ��ʹ��ҵ��ӿ��з�װ�ķ���
        //LoginDao loginDao = new LoginDaoImpl();
        Login l = loginservice.checkLogin(login.getName(), login.getPassword());//++
        if (l != null) { //�����¼�ɹ�
            Map session = ActionContext.getContext().getSession(); //��ûỰ���������浱ǰ��¼�û�����Ϣ
            session.put("login", l); //�ѻ�ȡ�Ķ��󱣴��� Session ��
            //return SUCCESS;											//��֤�ɹ������ַ���SUCCESS����ʱ Session ���Ѿ����û�����
            //��¼�ɹ����жϽ�ɫΪ����Ա����ѧ����true��ʾ����Ա��false��ʾѧ��
            if (l.getRole()) {
                return "admin"; //����Ա��ݵ�¼
            } else {
                return "student"; //ѧ����ݵ�¼
            }
        } else {
            return ERROR; //��֤ʧ�ܷ����ַ���ERROR
        }
    }
    //��֤��¼���������Ƿ�Ϊ��
    @Override
    public void validate() {
        if (login.getName() == null || login.getName().equals("")) {
            this.addFieldError("name", "�û�������Ϊ�գ�");
        } else if (login.getPassword() == null
                || login.getPassword().equals("")) {
            this.addFieldError("password", "���벻��Ϊ�գ�");
        }
    }

    //���� login �� get/set ����
    public Login getLogin() {
        return login;
    }
    public void setLogin(Login login) {
        this.login = login;
    }

    //���� loginservice �� get/set ����
    public LoginService getLoginservice() {
        return loginservice;
    }
    public void setLoginservice(LoginService loginservice) {
        this.loginservice = loginservice;
    }

    //���� message �� get/set ����
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
