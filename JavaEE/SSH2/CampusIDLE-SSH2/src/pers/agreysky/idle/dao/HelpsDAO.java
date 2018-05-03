package pers.agreysky.idle.dao;

import java.util.ArrayList;
import java.util.List;

import pers.agreysky.idle.dto.Helps;
import pers.agreysky.idle.vo.AllEntityVO;

public interface HelpsDAO {
    //ɾ��һ���ҵİ���
    public boolean deleteHelps(Helps help);
    //���һ���ҵİ���
    public boolean addHelps(Helps help);
    //�޸�һ���ҵİ���
    public boolean updateHelps(Helps help);
    //ͨ�����Ͳ�ѯ����
    public ArrayList<Helps> getHelpsByType(String type);
    //ͨ���������Ұ���
    public ArrayList<AllEntityVO> getHelpsByUserCdn(String helpsSql);
    //ͨ��id���Ұ���
    public Helps getHelpsById(Integer id);
    //ͨ���û�id���Ұ�����Ϣ
    public List getHelpListByUserId(Integer id);
    //�жϰ����Ƿ����
    public boolean ifHelpsExist(String overview, String contact);
}
