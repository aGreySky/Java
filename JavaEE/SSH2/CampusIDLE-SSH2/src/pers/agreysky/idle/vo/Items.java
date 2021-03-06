package pers.agreysky.idle.vo;
// Generated 2018-4-18 16:55:56 by Hibernate Tools 5.2.5.Final

/**
 * Items generated by hbm2java
 */
public class Items implements java.io.Serializable {

    private Integer id;
    private User user;
    private String name;
    private String price;
    private String picture;
    private String type;
    private String overview;
    private String way;
    private String contact;
    private String ifNew;
    private String origPrice;

    public Items() {
    }

    public Items(User user, String name, String price, String picture,
            String type, String overview, String way, String contact) {
        this.user = user;
        this.name = name;
        this.price = price;
        this.picture = picture;
        this.type = type;
        this.overview = overview;
        this.way = way;
        this.contact = contact;
    }
    public Items(User user, String name, String price, String picture,
            String type, String overview, String way, String contact,
            String ifNew, String origPrice) {
        this.user = user;
        this.name = name;
        this.price = price;
        this.picture = picture;
        this.type = type;
        this.overview = overview;
        this.way = way;
        this.contact = contact;
        this.ifNew = ifNew;
        this.origPrice = origPrice;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getOverview() {
        return this.overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
    public String getWay() {
        return this.way;
    }

    public void setWay(String way) {
        this.way = way;
    }
    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getIfNew() {
        return this.ifNew;
    }

    public void setIfNew(String ifNew) {
        this.ifNew = ifNew;
    }
    public String getOrigPrice() {
        return this.origPrice;
    }

    public void setOrigPrice(String origPrice) {
        this.origPrice = origPrice;
    }

}
