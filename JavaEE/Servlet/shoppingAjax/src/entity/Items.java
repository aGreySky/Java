package entity;

public class Items {
	private String name;
	private int id;
	private String city;
	private float price;
	private int number;
	private String picture;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public boolean equals(Object obj){
		if(this==obj){
			return true;
		}
		if(obj instanceof Items){
			Items i=(Items) obj;
			if(this.getId()==i.getId()&&this.getName().equals(i.getName()))
					{
					return true;
					}
				else{
					return false;
					}
		}
		
		else{
			return false;
		}
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
}
