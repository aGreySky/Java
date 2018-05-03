package entity;

import java.util.ArrayList;

public class Cart {
	private ArrayList<Items> item;
	private	float totalPrice;
	public Cart(){
		item=new ArrayList<Items>();
		totalPrice=0f;
	}
	public ArrayList<Items> getItem() {
		return item;
	}
	public void setItem(ArrayList<Items> item) {
		this.item = item;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
}
