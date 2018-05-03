package pers.agreysky.idle.service.impl;

import pers.agreysky.idle.dao.HelpsDAO;
import pers.agreysky.idle.dao.UserDAO;
import pers.agreysky.idle.dto.Helps;
import pers.agreysky.idle.dto.User;
import pers.agreysky.idle.service.DoMyHelpsActionService;

public class DoMyHelpsActionServiceImpl implements DoMyHelpsActionService {
    private HelpsDAO helpsdao;
    private UserDAO userdao;
    //ɾ��һ���ҵİ���
    @Override
    public boolean deleteHelps(Helps help) {
        return helpsdao.deleteHelps(help);
    }
    //ͨ��id�����û�
    @Override
    public User getUserByUserId(Integer id) {
        return userdao.getUserByUserId(id);
    }
    //���һ���ҵİ���
    @Override
    public boolean addHelps(Helps help) {
        return helpsdao.addHelps(help);
    }
    //�޸�һ���ҵİ���
    @Override
    public boolean updateHelps(Helps help) {
        return helpsdao.updateHelps(help);
    }
    //����һ���ҵİ���
    @Override
    public Helps getHelpsById(Integer id) {
        return helpsdao.getHelpsById(id);
    }

    //�жϰ�����Ϣ�Ƿ����
    @Override
    public boolean ifHelpsExist(String overview, String contact) {
        return helpsdao.ifHelpsExist(overview, contact);
    }
    public HelpsDAO getHelpsdao() {
        return helpsdao;
    }
    public void setHelpsdao(HelpsDAO helpsdao) {
        this.helpsdao = helpsdao;
    }
    public UserDAO getUserdao() {
        return userdao;
    }
    public void setUserdao(UserDAO userdao) {
        this.userdao = userdao;
    }

}
