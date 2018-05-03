package pers.agreysky.idle.service;

import pers.agreysky.idle.dto.User;

public interface FindPasswordByPhoneActionService {
    //通过手机号查找用户
    public User getUserByPhone(String phone);
    //修改用户
    public boolean updateUser(User newuser);

}
