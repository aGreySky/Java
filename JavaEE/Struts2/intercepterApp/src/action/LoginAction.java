package action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
    private String username;
    private String password;
    @Override
    public String execute() {
        if (username.equals("admin") || password.equals("admin")) {
            Map<String, Object> session = ActionContext.getContext()
                    .getSession();
            session.put("username", username);
            return SUCCESS;
        }
        return ERROR;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
