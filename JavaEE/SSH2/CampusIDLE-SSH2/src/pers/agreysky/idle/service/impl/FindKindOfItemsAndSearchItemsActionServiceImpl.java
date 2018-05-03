package pers.agreysky.idle.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;

import pers.agreysky.idle.dao.FriendsDAO;
import pers.agreysky.idle.dao.HelpsDAO;
import pers.agreysky.idle.dao.ItemsDAO;
import pers.agreysky.idle.dao.JobsDAO;
import pers.agreysky.idle.dto.Friends;
import pers.agreysky.idle.dto.Helps;
import pers.agreysky.idle.dto.Items;
import pers.agreysky.idle.dto.Jobs;
import pers.agreysky.idle.dto.User;
import pers.agreysky.idle.service.FindKindOfItemsAndSearchItemsActionService;
import pers.agreysky.idle.vo.AllEntityVO;
import pers.agreysky.idle.vo.PartEntityVO;
import pers.agreysky.idle.vo.UserVO;

public class FindKindOfItemsAndSearchItemsActionServiceImpl
        implements
            FindKindOfItemsAndSearchItemsActionService {
    private ItemsDAO itemsdao;
    private JobsDAO jobsdao;
    private FriendsDAO friendsdao;
    private HelpsDAO helpsdao;

    //返回查询结果
    public ArrayList<PartEntityVO> getPartEntityVOList(ArrayList entityVOList) {
        ArrayList<PartEntityVO> partEntityVOList = new ArrayList<PartEntityVO>();
        for (int i = 0; i < entityVOList.size(); i++) {
            PartEntityVO partEntityVO = new PartEntityVO();
            AllEntityVO allEntityVO = new AllEntityVO();
            BeanUtils.copyProperties(entityVOList.get(i), allEntityVO);
            BeanUtils.copyProperties(entityVOList.get(i), partEntityVO);
            User user = allEntityVO.getUser();
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            partEntityVO.setUser(userVO);
            partEntityVOList.add(partEntityVO);
        }
        return partEntityVOList;
    }
    //通过类型查询帮助
    @Override
    public ArrayList<PartEntityVO> getHelpsByType(String type) {
        ArrayList<Helps> HelpsList = helpsdao.getHelpsByType(type);
        return getPartEntityVOList(HelpsList);
    }
    //通过类型查询交友
    @Override
    public ArrayList<PartEntityVO> getFriendsByType() {
        ArrayList<Friends> friendsList = friendsdao.getFriendsByType();
        return getPartEntityVOList(friendsList);
    }
    //通过类型查询兼职
    @Override
    public ArrayList<PartEntityVO> getJobsByType(String type) {
        ArrayList<Jobs> jobsList = jobsdao.getJobsByType(type);
        return getPartEntityVOList(jobsList);
    }
    //通过类型查询闲置
    @Override
    public ArrayList<PartEntityVO> getItemsByType(String type) {
        ArrayList<Items> itemsList = itemsdao.getItemsByType(type);
        return getPartEntityVOList(itemsList);
    }
    //通过条件查找闲置
    @Override
    public ArrayList<PartEntityVO> getItemsByUserCdn(String itemsSql) {
        ArrayList<AllEntityVO> allEntityVOList = itemsdao
                .getItemsByUserCdn(itemsSql);
        return getPartEntityVOList(allEntityVOList);
    }
    //通过条件查找帮助
    @Override
    public ArrayList<PartEntityVO> getHelpsByUserCdn(String helpsSql) {
        ArrayList<AllEntityVO> allEntityVOList = helpsdao
                .getHelpsByUserCdn(helpsSql);
        return getPartEntityVOList(allEntityVOList);
    }
    //通过条件查找兼职
    @Override
    public ArrayList<PartEntityVO> getJobsByUserCdn(String jobsSql) {
        ArrayList<AllEntityVO> allEntityVOList = jobsdao
                .getJobsByUserCdn(jobsSql);
        return getPartEntityVOList(allEntityVOList);
    }
    //通过条件查找交友
    @Override
    public ArrayList<PartEntityVO> getFriendsByUserCdn(String friendsSql) {
        ArrayList<AllEntityVO> allEntityVOList = friendsdao
                .getFriendsByUserCdn(friendsSql);
        return getPartEntityVOList(allEntityVOList);
    }
    public ItemsDAO getItemsdao() {
        return itemsdao;
    }
    public void setItemsdao(ItemsDAO itemsdao) {
        this.itemsdao = itemsdao;
    }
    public JobsDAO getJobsdao() {
        return jobsdao;
    }
    public void setJobsdao(JobsDAO jobsdao) {
        this.jobsdao = jobsdao;
    }
    public FriendsDAO getFriendsdao() {
        return friendsdao;
    }
    public void setFriendsdao(FriendsDAO friendsdao) {
        this.friendsdao = friendsdao;
    }
    public HelpsDAO getHelpsdao() {
        return helpsdao;
    }
    public void setHelpsdao(HelpsDAO helpsdao) {
        this.helpsdao = helpsdao;
    }

}
