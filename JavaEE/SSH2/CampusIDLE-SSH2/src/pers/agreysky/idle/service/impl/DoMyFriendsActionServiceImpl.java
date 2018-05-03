package pers.agreysky.idle.service.impl;

import pers.agreysky.idle.dao.FriendsDAO;
import pers.agreysky.idle.dao.UserDAO;
import pers.agreysky.idle.dto.Friends;
import pers.agreysky.idle.dto.User;
import pers.agreysky.idle.service.DoMyFriendsActionService;

public class DoMyFriendsActionServiceImpl implements DoMyFriendsActionService {
    private FriendsDAO friendsdao;
    private UserDAO userdao;
    //通过id查找我的某个交友
    @Override
    public Friends getFriendsById(Integer id) {
        return friendsdao.getFriendsById(id);
    }
    //更新我的某个交友
    @Override
    public boolean updateFriends(Friends friend) {
        return friendsdao.updateFriends(friend);
    }
    //删除我的某个交友
    @Override
    public boolean deleteFriends(Friends friend) {
        return friendsdao.deleteFriends(friend);
    }
    //查看交友是否存在
    @Override
    public boolean ifFriendsExist(String overview, String contact) {
        return friendsdao.ifFriendsExist(overview, contact);
    }
    //获取最后一件交友的id
    @Override
    public int getLatestId() {
        return friendsdao.getLatestId();
    }
    //添加我的交友
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
    //通过id查找用户
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
