package pers.agreysky.idle.dto;
// Generated 2017-10-20 21:00:46 by Hibernate Tools 5.2.5.Final

/**
 * User generated by hbm2java
 */

public class User implements java.io.Serializable {

    private Integer id;
    private String username;
    private String password;
    private String useremail;
    private String phone;
    private String picture;
    private String state;
    private String code;
    private String itemsNum;
    private String signature;
    private String beLikeNum;
    private String attentionNum;
    private String fansNum;

    public User() {
    }

    public User(String username, String password, String state,
            String itemsNum) {
        this.username = username;
        this.password = password;
        this.state = state;
    }
    public User(String username, String password, String useremail,
            String phone, String picture, String state, String code,
            String itemsNum, String signature, String beLikeNum,
            String attentionNum, String fansNum) {
        this.username = username;
        this.password = password;
        this.useremail = useremail;
        this.phone = phone;
        this.picture = picture;
        this.state = state;
        this.code = code;
        this.itemsNum = itemsNum;
        this.signature = signature;
        this.beLikeNum = beLikeNum;
        this.attentionNum = attentionNum;
        this.fansNum = fansNum;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUseremail() {
        return this.useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getItemsNum() {
        return itemsNum;
    }

    public void setItemsNum(String itemsNum) {
        this.itemsNum = itemsNum;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getBeLikeNum() {
        return beLikeNum;
    }

    public void setBeLikeNum(String beLikeNum) {
        this.beLikeNum = beLikeNum;
    }

    public String getAttentionNum() {
        return attentionNum;
    }

    public void setAttentionNum(String attentionNum) {
        this.attentionNum = attentionNum;
    }

    public String getFansNum() {
        return fansNum;
    }

    public void setFansNum(String fansNum) {
        this.fansNum = fansNum;
    }

}