package action;

import com.opensymphony.xwork2.ActionSupport;

public class SubscribeAction extends ActionSupport {
    private String email;
    private String message;

    @Override
    public String execute() {
        if (!email.contains("@")) {
            this.setMessage("����ʧ��");
            return ERROR;
        }
        return SUCCESS;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
