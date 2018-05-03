package pers.agreysky.idle.dao;

import java.util.ArrayList;
import java.util.List;

import pers.agreysky.idle.dto.Items;
import pers.agreysky.idle.vo.AllEntityVO;

public interface ItemsDAO {

    //通过闲置id查找我的闲置
    public Items getFriendsById(Integer id);
    //删除我的一件闲置
    public boolean deleteItem(Items item);
    //闲置是否存在
    public boolean ifItemsExist(String string, String string2);
    //获取最后一件闲置的id
    public int getLatestId();
    //添加闲置
    public boolean addItems(Items item);
    //修改闲置
    public boolean updateItems(Items item);
    //通过类型查询闲置
    public ArrayList<Items> getItemsByType(String type);
    //通过条件查找闲置
    public ArrayList<AllEntityVO> getItemsByUserCdn(String itemsSql);
    //通过用户id查找商品信息
    public List getItemListByUserId(Integer id);

}
