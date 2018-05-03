package pers.agreysky.idle.dao;

import pers.agreysky.idle.dto.User;

public interface UserDAO {
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
    //ͨ��id�����û�
    public User getUserByUserId(Integer id);
    //ͨ���ֻ��Ų����û�
    public User getUserByPhone(String phone);
    //�����û�
    public boolean updateUser(User newuser);

}
