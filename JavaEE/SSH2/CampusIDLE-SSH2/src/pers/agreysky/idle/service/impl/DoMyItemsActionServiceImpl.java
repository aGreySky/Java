package pers.agreysky.idle.service.impl;

import pers.agreysky.idle.dao.ItemsDAO;
import pers.agreysky.idle.dao.UserDAO;
import pers.agreysky.idle.dto.Items;
import pers.agreysky.idle.dto.User;
import pers.agreysky.idle.service.DoMyItemsActionService;

public class DoMyItemsActionServiceImpl implements DoMyItemsActionService {
    private ItemsDAO itemsdao;
    private UserDAO userdao;
    //通过闲置id查找我的闲置
    @Override
    public Items getItemsById(Integer id) {
        return itemsdao.getFriendsById(id);
    }
    //删除我的一件闲置
    @Override
    public boolean deleteItem(Items item) {
        return itemsdao.deleteItem(item);
    }
    //闲置是否存在
    @Override
    public boolean ifItemsExist(String overview, String contact) {
        return itemsdao.ifItemsExist(overview, contact);
    }
    //获取最后一件闲置的id
    @Override
    public int getLatestId() {
        return itemsdao.getLatestId();
    }
    //添加闲置
    @Override
    public boolean addItems(Items item) {
        return itemsdao.addItems(item);
    }
    //修改闲置
    @Override
    public boolean updateItems(Items item) {
        return itemsdao.updateItems(item);
    }
    //通过id查询用户
    @Override
    public User getUserByUserId(Integer id) {
        return userdao.getUserByUserId(id);
    }
    //更新用户闲置数量
    @Override
    public void updateUserItemsNum(User user) {
        userdao.updateUser(user);
    }

    public ItemsDAO getItemsdao() {
        return itemsdao;
    }
    public void setItemsdao(ItemsDAO itemsdao) {
        this.itemsdao = itemsdao;
    }

    public UserDAO getUserdao() {
        return userdao;
    }
    public void setUserdao(UserDAO userdao) {
        this.userdao = userdao;
    }

}
