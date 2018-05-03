package pers.agreysky.idle.service.impl;

import pers.agreysky.idle.dao.UserDAO;
import pers.agreysky.idle.dto.User;
import pers.agreysky.idle.service.LoginAndRegisterActionService;

public class LoginAndRegisterActionServiceImpl implements LoginAndRegisterActionService {
    private UserDAO userdao;

    //ͨ���ֻ��ŵ�¼
    @Override
    public User ifRightByPhone(User user) {
        return userdao.ifRightByPhone(user);
    }
    //ͨ�������¼
    @Override
    public User ifRightByUseremail(User user) {
        return userdao.ifRightByUseremail(user);
    }
    //ͨ���û�����¼
    @Override
    public User ifRightByUsername(User user) {
        return userdao.ifRightByUsername(user);
    }
    //�û��Ƿ����
    @Override
    public boolean ifExist(User user) {
        return userdao.ifExist(user);
    }
    //����û�
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
