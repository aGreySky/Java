package org.action;

import org.service.UserService;
import org.vo.User;

import com.opensymphony.xwork2.ActionSupport;

public class SaveAction extends ActionSupport {

    private UserService userService;

    private User user;

    @Override
    public String execute() {
        return SUCCESS;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
