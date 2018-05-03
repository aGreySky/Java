package entity;

public class Jobs {
    private int id; // 交友id
    private String name; // 交友名称
    private User user; // 交友主人
    private String type; // 交友类型
    private String overview;// 交友简介
    private String way; // 交友联系类型
    private String contact; // 交友联系方式

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
    public int hashCode() {
        return this.getId() + this.getName().hashCode();
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
}
