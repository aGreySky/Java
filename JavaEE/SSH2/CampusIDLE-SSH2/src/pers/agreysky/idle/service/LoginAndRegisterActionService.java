package pers.agreysky.idle.service;

import pers.agreysky.idle.dto.User;

public interface LoginAndRegisterActionService {

    //ͨ���ֻ��ŵ�¼
    public User ifRightByPhone(User user);
    //ͨ�������¼
    public User ifRightByUseremail(User user);
    //ͨ���û�����¼
    public User ifRightByUsername(User user);
    //�û��Ƿ����
    public boolean ifExist(User user);
    //����û�
    public void addUser(User user);

}
