package pers.agreysky.idle.service;

import pers.agreysky.idle.dto.Friends;
import pers.agreysky.idle.dto.User;

public interface DoMyFriendsActionService {
    //通过id查找我的某个交友
    public Friends getFriendsById(Integer id);
    //更新我的某个交友
    public boolean updateFriends(Friends friend);
    //删除我的某个交友
    public boolean deleteFriends(Friends friend);
    //查看交友是否存在
    public boolean ifFriendsExist(String overview, String contact);
    //获取最后一件交友的id
    public int getLatestId();
    //添加我的交友
    public boolean addFriends(Friends friend);
    //通过id查找用户
    public User getUserByUserId(Integer id);

}
