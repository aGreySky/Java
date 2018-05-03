package pers.agreysky.idle.service.impl;

import pers.agreysky.idle.dao.ItemsDAO;
import pers.agreysky.idle.dao.UserDAO;
import pers.agreysky.idle.dto.Items;
import pers.agreysky.idle.dto.User;
import pers.agreysky.idle.service.DoMyItemsActionService;

public class DoMyItemsActionServiceImpl implements DoMyItemsActionService {
    private ItemsDAO itemsdao;
    private UserDAO userdao;
    //ͨ������id�����ҵ�����
    @Override
    public Items getItemsById(Integer id) {
        return itemsdao.getFriendsById(id);
    }
    //ɾ���ҵ�һ������
    @Override
    public boolean deleteItem(Items item) {
        return itemsdao.deleteItem(item);
    }
    //�����Ƿ����
    @Override
    public boolean ifItemsExist(String overview, String contact) {
        return itemsdao.ifItemsExist(overview, contact);
    }
    //��ȡ���һ�����õ�id
    @Override
    public int getLatestId() {
        return itemsdao.getLatestId();
    }
    //�������
    @Override
    public boolean addItems(Items item) {
        return itemsdao.addItems(item);
    }
    //�޸�����
    @Override
    public boolean updateItems(Items item) {
        return itemsdao.updateItems(item);
    }
    //ͨ��id��ѯ�û�
    @Override
    public User getUserByUserId(Integer id) {
        return userdao.getUserByUserId(id);
    }
    //�����û���������
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
