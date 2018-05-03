package pers.agreysky.idle.dao;

import java.util.ArrayList;
import java.util.List;

import pers.agreysky.idle.dto.Items;
import pers.agreysky.idle.vo.AllEntityVO;

public interface ItemsDAO {

    //ͨ������id�����ҵ�����
    public Items getFriendsById(Integer id);
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
    //ͨ�����Ͳ�ѯ����
    public ArrayList<Items> getItemsByType(String type);
    //ͨ��������������
    public ArrayList<AllEntityVO> getItemsByUserCdn(String itemsSql);
    //ͨ���û�id������Ʒ��Ϣ
    public List getItemListByUserId(Integer id);

}
