package pers.agreysky.idle.dao;

import java.util.ArrayList;

import pers.agreysky.idle.dto.Friends;
import pers.agreysky.idle.vo.AllEntityVO;

public interface FriendsDAO {

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
    //通过类型查询交友
    public ArrayList<Friends> getFriendsByType();
    //通过条件查找交友
    public ArrayList<AllEntityVO> getFriendsByUserCdn(String friendsSql);

}
