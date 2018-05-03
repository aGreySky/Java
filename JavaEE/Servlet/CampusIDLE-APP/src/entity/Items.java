package entity;

public class Items {
    private int id; // ��Ʒid
    private String name; // ��Ʒ����
    private String price; // ��Ʒ�۸�
    private String picture; // ��ƷͼƬ
    private User user; // ��Ʒ����
    private String type; // ��Ʒ����
    private String overview;// ��Ʒ���
    private String way; // ��Ʒ��ϵ����
    private String contact; // ��Ʒ��ϵ��ʽ
    private String ifNew;//�Ƿ�ȫ��
    private String origPrice;//ԭ��

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Items) {
            Items i = (Items) obj;
            if (this.getId() == i.getId()
                    && this.getName().equals(i.getName())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
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

}
