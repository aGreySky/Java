package pers.agreysky.idle.service;

import java.util.ArrayList;
import java.util.List;

import pers.agreysky.idle.vo.PartEntityVO;

public interface FindKindOfItemsAndSearchItemsActionService {
    //通过类型查询帮助
    public List getHelpsByType(String type);
    //通过类型查询交友
    public List getFriendsByType();
    //通过类型查询兼职
    public ArrayList<PartEntityVO> getJobsByType(String type);
    //通过类型查询闲置
    public ArrayList<PartEntityVO> getItemsByType(String type);
    //通过条件查找闲置
    public ArrayList<PartEntityVO> getItemsByUserCdn(String itemsSql);
    //通过条件查找帮助
    public ArrayList<PartEntityVO> getHelpsByUserCdn(String helpsSql);
    //通过条件查找兼职
    public ArrayList<PartEntityVO> getJobsByUserCdn(String jobsSql);
    //通过条件查找交友
    public ArrayList<PartEntityVO> getFriendsByUserCdn(String friendsSql);

}
