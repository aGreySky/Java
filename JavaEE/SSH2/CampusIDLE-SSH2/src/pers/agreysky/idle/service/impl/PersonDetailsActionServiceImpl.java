package pers.agreysky.idle.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;

import pers.agreysky.idle.dao.HelpsDAO;
import pers.agreysky.idle.dao.ItemsDAO;
import pers.agreysky.idle.dao.UserDAO;
import pers.agreysky.idle.dto.Helps;
import pers.agreysky.idle.dto.Items;
import pers.agreysky.idle.dto.User;
import pers.agreysky.idle.service.PersonDetailsActionService;
import pers.agreysky.idle.vo.HelpsVO;
import pers.agreysky.idle.vo.ItemsVO;
import pers.agreysky.idle.vo.UserInformVO;

public class PersonDetailsActionServiceImpl
        implements
            PersonDetailsActionService {
    private UserDAO userdao;
    private ItemsDAO itemsdao;
    private HelpsDAO helpsdao;

    @Override
    public UserInformVO getUserByUserId(Integer id) {
        User user = userdao.getUserByUserId(id);
        UserInformVO userInformVO = new UserInformVO();
        BeanUtils.copyProperties(user, userInformVO);
        return userInformVO;
    }

    @Override
    public ArrayList<HelpsVO> getHelpListByUserId(Integer id) {
        ArrayList<Helps> helpsList = (ArrayList<Helps>) helpsdao
                .getHelpListByUserId(id);
        ArrayList<HelpsVO> helpsVOList = new ArrayList<HelpsVO>();
        for (Helps helps : helpsList) {
            HelpsVO helpsVO = new HelpsVO();
            BeanUtils.copyProperties(helps, helpsVO);
            helpsVOList.add(helpsVO);
        }
        return helpsVOList;
    }

    @Override
    public ArrayList<ItemsVO> getItemListByUserId(Integer id) {
        ArrayList<Items> itemsList = (ArrayList<Items>) itemsdao
                .getItemListByUserId(id);
        ArrayList<ItemsVO> itemsVOList = new ArrayList<ItemsVO>();
        for (Items items : itemsList) {
            ItemsVO itemsVO = new ItemsVO();
            BeanUtils.copyProperties(items, itemsVO);
            itemsVOList.add(itemsVO);
        }
        return itemsVOList;
    }

    public UserDAO getUserdao() {
        return userdao;
    }

    public void setUserdao(UserDAO userdao) {
        this.userdao = userdao;
    }

    public ItemsDAO getItemsdao() {
        return itemsdao;
    }

    public void setItemsdao(ItemsDAO itemsdao) {
        this.itemsdao = itemsdao;
    }

    public HelpsDAO getHelpsdao() {
        return helpsdao;
    }

    public void setHelpsdao(HelpsDAO helpsdao) {
        this.helpsdao = helpsdao;
    }

}
