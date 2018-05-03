package pers.agreysky.idle.service;

import java.util.ArrayList;
import java.util.List;

import pers.agreysky.idle.vo.PartEntityVO;

public interface FindKindOfItemsAndSearchItemsActionService {
    //ͨ�����Ͳ�ѯ����
    public List getHelpsByType(String type);
    //ͨ�����Ͳ�ѯ����
    public List getFriendsByType();
    //ͨ�����Ͳ�ѯ��ְ
    public ArrayList<PartEntityVO> getJobsByType(String type);
    //ͨ�����Ͳ�ѯ����
    public ArrayList<PartEntityVO> getItemsByType(String type);
    //ͨ��������������
    public ArrayList<PartEntityVO> getItemsByUserCdn(String itemsSql);
    //ͨ���������Ұ���
    public ArrayList<PartEntityVO> getHelpsByUserCdn(String helpsSql);
    //ͨ���������Ҽ�ְ
    public ArrayList<PartEntityVO> getJobsByUserCdn(String jobsSql);
    //ͨ���������ҽ���
    public ArrayList<PartEntityVO> getFriendsByUserCdn(String friendsSql);

}
