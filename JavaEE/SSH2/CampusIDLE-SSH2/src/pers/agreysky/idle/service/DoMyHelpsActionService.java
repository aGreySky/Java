package pers.agreysky.idle.service;

import pers.agreysky.idle.dto.Helps;
import pers.agreysky.idle.dto.User;

public interface DoMyHelpsActionService {
    //ɾ��һ���ҵİ���
    public boolean deleteHelps(Helps help);
    //ͨ��id�����û�
    public User getUserByUserId(Integer id);
    //���һ���ҵİ���
    public boolean addHelps(Helps help);
    //�޸�һ���ҵİ���
    public boolean updateHelps(Helps help);
    //����һ���ҵİ���
    public Helps getHelpsById(Integer id);
    //�жϰ�����Ϣ�Ƿ����
    public boolean ifHelpsExist(String overview, String contact);

}
