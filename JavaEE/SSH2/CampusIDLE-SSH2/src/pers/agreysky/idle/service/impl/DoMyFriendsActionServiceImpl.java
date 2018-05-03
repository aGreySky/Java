package pers.agreysky.idle.service.impl;

import pers.agreysky.idle.dao.FriendsDAO;
import pers.agreysky.idle.dao.UserDAO;
import pers.agreysky.idle.dto.Friends;
import pers.agreysky.idle.dto.User;
import pers.agreysky.idle.service.DoMyFriendsActionService;

public class DoMyFriendsActionServiceImpl implements DoMyFriendsActionService {
    private FriendsDAO friendsdao;
    private UserDAO userdao;
    //ͨ��id�����ҵ�ĳ������
    @Override
    public Friends getFriendsById(Integer id) {
        return friendsdao.getFriendsById(id);
    }
    //�����ҵ�ĳ������
    @Override
    public boolean updateFriends(Friends friend) {
        return friendsdao.updateFriends(friend);
    }
    //ɾ���ҵ�ĳ������
    @Override
    public boolean deleteFriends(Friends friend) {
        return friendsdao.deleteFriends(friend);
    }
    //�鿴�����Ƿ����
    @Override
    public boolean ifFriendsExist(String overview, String contact) {
        return friendsdao.ifFriendsExist(overview, contact);
    }
    //��ȡ���һ�����ѵ�id
    @Override
    public int getLatestId() {
        return friendsdao.getLatestId();
    }
    //����ҵĽ���
    @Override
    public boolean addFriends(Friends friend) {
        return friendsdao.addFriends(friend);
    }
    public FriendsDAO getFriendsdao() {
        return friendsdao;
    }
    public void setFriendsdao(FriendsDAO friendsdao) {
        this.friendsdao = friendsdao;
    }
    //ͨ��id�����û�
    @Override
    public User getUserByUserId(Integer id) {
        return userdao.getUserByUserId(id);
    }
    public UserDAO getUserdao() {
        return userdao;
    }
    public void setUserdao(UserDAO userdao) {
        this.userdao = userdao;
    }

}
