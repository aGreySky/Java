package pers.agreysky.idle.service;

import pers.agreysky.idle.dto.Friends;
import pers.agreysky.idle.dto.User;

public interface DoMyFriendsActionService {
    //ͨ��id�����ҵ�ĳ������
    public Friends getFriendsById(Integer id);
    //�����ҵ�ĳ������
    public boolean updateFriends(Friends friend);
    //ɾ���ҵ�ĳ������
    public boolean deleteFriends(Friends friend);
    //�鿴�����Ƿ����
    public boolean ifFriendsExist(String overview, String contact);
    //��ȡ���һ�����ѵ�id
    public int getLatestId();
    //�����ҵĽ���
    public boolean addFriends(Friends friend);
    //ͨ��id�����û�
    public User getUserByUserId(Integer id);

}