package pers.agreysky.idle.service;

import pers.agreysky.idle.dto.User;

public interface FindPasswordByPhoneActionService {
    //ͨ���ֻ��Ų����û�
    public User getUserByPhone(String phone);
    //�޸��û�
    public boolean updateUser(User newuser);

}
