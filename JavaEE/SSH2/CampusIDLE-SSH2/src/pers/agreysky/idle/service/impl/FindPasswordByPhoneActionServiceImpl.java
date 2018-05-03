package pers.agreysky.idle.service.impl;

import pers.agreysky.idle.dao.UserDAO;
import pers.agreysky.idle.dto.User;
import pers.agreysky.idle.service.FindPasswordByPhoneActionService;

public class FindPasswordByPhoneActionServiceImpl
        implements
            FindPasswordByPhoneActionService {
    private UserDAO userdao;
    //通过手机号查找用户
    @Override
    public User getUserByPhone(String phone) {
        return userdao.getUserByPhone(phone);
    }
    //修改用户
    @Override
    public boolean updateUser(User newuser) {
        return userdao.updateUser(newuser);
    }
    public UserDAO getUserdao() {
        return userdao;
    }
    public void setUserdao(UserDAO userdao) {
        this.userdao = userdao;
    }

}
