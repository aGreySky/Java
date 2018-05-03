package pers.agreysky.idle.dao;

import java.util.ArrayList;

import pers.agreysky.idle.dto.Friends;
import pers.agreysky.idle.vo.AllEntityVO;

public interface FriendsDAO {

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
    //����ҵĽ���
    public boolean addFriends(Friends friend);
    //ͨ�����Ͳ�ѯ����
    public ArrayList<Friends> getFriendsByType();
    //ͨ���������ҽ���
    public ArrayList<AllEntityVO> getFriendsByUserCdn(String friendsSql);

}
