package pers.agreysky.idle.service;

import pers.agreysky.idle.dto.User;

public interface LoginAndRegisterActionService {

    //通过手机号登录
    public User ifRightByPhone(User user);
    //通过邮箱登录
    public User ifRightByUseremail(User user);
    //通过用户名登录
    public User ifRightByUsername(User user);
    //用户是否存在
    public boolean ifExist(User user);
    //添加用户
    public void addUser(User user);

}
