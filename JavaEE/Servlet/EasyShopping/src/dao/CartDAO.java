package dao;

import entity.Cart;
import entity.Items;

public class CartDAO {
	public void addItemToCart(Cart cart,Items item){//�����Ʒ�����ﳵ
		cart.getItem().add(item);
		cart.setTotalPrice(cart.getTotalPrice()+item.getPrice());
	}
	public void DeleteItem(Cart cart,Items item){//ɾ����Ʒ
		
		if(cart.getItem().remove(item)){
			System.out.println("true");
		}else{
			System.out.println("false");
		}
		cart.setTotalPrice(cart.getTotalPrice()-item.getPrice());
	}
}
