package entity;


public class Items {
	private int id;			//物品id
	private String name;	//物品名称
	private String price;		//物品价格
	private String picture; //物品图片
	private User user;		//物品主人
	private String type;	//物品类型
	private String overview;//物品简介
	private String way;		//物品联系类型
	private String contact; //物品联系方式
	private int Page;		//物品所在页
	
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
	public int hashCode(){
		return this.getId()+this.getName().hashCode();
	}
	public boolean equals(Object obj){
		if(this==obj){
			return true;
		}
		if(obj instanceof Items){
			Items i=(Items) obj;
			if(this.getId()==i.getId()&&this.getName().equals(i.getName())){
				return true;
			}
			else{
				return false;
			}
		}else{
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
	public int getPage() {
		return Page;
	}
	public void setPage(int page) {
		Page = page;
	}
	
	
	
	
	
	
}
