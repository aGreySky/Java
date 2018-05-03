package pers.agreysky.idle.service;

import java.util.ArrayList;

import pers.agreysky.idle.vo.HelpsVO;
import pers.agreysky.idle.vo.ItemsVO;
import pers.agreysky.idle.vo.UserInformVO;

public interface PersonDetailsActionService {
    //ͨ���û�id�����û���Ϣ
    UserInformVO getUserByUserId(Integer id);
    //ͨ���û�id������Ʒ��Ϣ
    ArrayList<HelpsVO> getHelpListByUserId(Integer id);
    //ͨ���û�id���Ұ�����Ϣ
    ArrayList<ItemsVO> getItemListByUserId(Integer id);

}
