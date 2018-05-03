package pers.agreysky.idle.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class PartEntityVO {
    private Integer id;
    private UserVO user;
    private String name;
    private String price;
    private String picture;
    private String type;
    private String overview;
    private String way;
    private String contact;
    private String ifNew;
    private String origPrice;
    /**
     * 交友中的参数
     */
    private String sex;//性别
    private String label;//标签
    /**
     * 帮助中的参数
     */
    private String reward;//报酬
    /**
     * 兼职中的参数
     */
    private String personNum;//人数
    private String welfare;//福利
    private String accDate;//结算方式
    private String schedule;//时间
    private String pName;//负责人称呼
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public UserVO getUser() {
        return user;
    }
    public void setUser(UserVO user) {
        this.user = user;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public String getWay() {
        return way;
    }
    public void setWay(String way) {
        this.way = way;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getIfNew() {
        return ifNew;
    }
    public void setIfNew(String ifNew) {
        this.ifNew = ifNew;
    }
    public String getOrigPrice() {
        return origPrice;
    }
    public void setOrigPrice(String origPrice) {
        this.origPrice = origPrice;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public String getReward() {
        return reward;
    }
    public void setReward(String reward) {
        this.reward = reward;
    }
    public String getPersonNum() {
        return personNum;
    }
    public void setPersonNum(String personNum) {
        this.personNum = personNum;
    }
    public String getWelfare() {
        return welfare;
    }
    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }
    public String getAccDate() {
        return accDate;
    }
    public void setAccDate(String accDate) {
        this.accDate = accDate;
    }
    public String getSchedule() {
        return schedule;
    }
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
    public String getpName() {
        return pName;
    }
    public void setpName(String pName) {
        this.pName = pName;
    }

}
