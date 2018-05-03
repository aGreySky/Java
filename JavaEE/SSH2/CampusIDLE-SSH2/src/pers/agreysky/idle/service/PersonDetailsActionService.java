package pers.agreysky.idle.service;

import java.util.ArrayList;

import pers.agreysky.idle.vo.HelpsVO;
import pers.agreysky.idle.vo.ItemsVO;
import pers.agreysky.idle.vo.UserInformVO;

public interface PersonDetailsActionService {
    //通过用户id查找用户信息
    UserInformVO getUserByUserId(Integer id);
    //通过用户id查找商品信息
    ArrayList<HelpsVO> getHelpListByUserId(Integer id);
    //通过用户id查找帮助信息
    ArrayList<ItemsVO> getItemListByUserId(Integer id);

}
