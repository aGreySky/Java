package pers.agreysky.idle.service.impl;

import pers.agreysky.idle.dao.UserDAO;
import pers.agreysky.idle.dto.User;
import pers.agreysky.idle.service.LoginAndRegisterActionService;

public class LoginAndRegisterActionServiceImpl implements LoginAndRegisterActionService {
    private UserDAO userdao;

    //通过手机号登录
    @Override
    public User ifRightByPhone(User user) {
        return userdao.ifRightByPhone(user);
    }
    //通过邮箱登录
    @Override
    public User ifRightByUseremail(User user) {
        return userdao.ifRightByUseremail(user);
    }
    //通过用户名登录
    @Override
    public User ifRightByUsername(User user) {
        return userdao.ifRightByUsername(user);
    }
    //用户是否存在
    @Override
    public boolean ifExist(User user) {
        return userdao.ifExist(user);
    }
    //添加用户
    @Override
    public void addUser(User user) {
        userdao.addUser(user);
    }
    public UserDAO getUserdao() {
        return userdao;
    }
    public void setUserdao(UserDAO userdao) {
        this.userdao = userdao;
    }
}
