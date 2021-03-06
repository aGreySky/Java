package pers.agreysky.idle.service;

import pers.agreysky.idle.dto.Items;
import pers.agreysky.idle.dto.User;

public interface DoMyItemsActionService {
    //通过闲置id查找我的闲置
    public Items getItemsById(Integer id);
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
    //通过id查找用户
    public User getUserByUserId(Integer id);
    //更新用户闲置数量
    public void updateUserItemsNum(User user);

}
