package pers.agreysky.idle.vo;

import java.util.ArrayList;

public class PersonVO {
    //用户信息
    private UserInformVO user;

    //商品信息
    private ArrayList<ItemsVO> itemsList;

    //帮助信息
    private ArrayList<HelpsVO> helpsList;

    public ArrayList<ItemsVO> getItemsList() {
        return itemsList;
    }

    public void setItemsList(ArrayList<ItemsVO> itemsList) {
        this.itemsList = itemsList;
    }

    public ArrayList<HelpsVO> getHelpsList() {
        return helpsList;
    }

    public void setHelpsList(ArrayList<HelpsVO> helpsList) {
        this.helpsList = helpsList;
    }

    public UserInformVO getUser() {
        return user;
    }

    public void setUser(UserInformVO user) {
        this.user = user;
    }

}
