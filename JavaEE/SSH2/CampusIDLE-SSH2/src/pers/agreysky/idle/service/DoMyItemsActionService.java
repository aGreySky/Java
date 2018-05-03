package pers.agreysky.idle.service;

import pers.agreysky.idle.dto.Items;
import pers.agreysky.idle.dto.User;

public interface DoMyItemsActionService {
    //ͨ������id�����ҵ�����
    public Items getItemsById(Integer id);
    //ɾ���ҵ�һ������
    public boolean deleteItem(Items item);
    //�����Ƿ����
    public boolean ifItemsExist(String string, String string2);
    //��ȡ���һ�����õ�id
    public int getLatestId();
    //�������
    public boolean addItems(Items item);
    //�޸�����
    public boolean updateItems(Items item);
    //ͨ��id�����û�
    public User getUserByUserId(Integer id);
    //�����û���������
    public void updateUserItemsNum(User user);

}
