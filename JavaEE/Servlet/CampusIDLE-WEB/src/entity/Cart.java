package entity;



import java.util.ArrayList;


public class Cart {
	private int cartId;
	private ArrayList<Items> goods;
	public Cart(){
		goods = new ArrayList<Items>();
	}
	
	public ArrayList<Items> getGoods() {
		return goods;
	}
	public void setGoods(ArrayList<Items> goods) {
		this.goods = goods;
	}

	public boolean addGoodToCart(Items item){//�����Ʒ���������Ʒ�б���
		if(goods.contains(item)){
			return false;
		}else{
			goods.add(item);
			return true;
		}
	}

	public boolean removeGoodsFromCart(Items item){//����Ʒ���б����Ƴ�
		System.out.println(item.getName());
		if(goods.contains(item)){
			goods.remove(item);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean clearCart(){//��չ��ﳵ
		goods.clear();
		if(goods.isEmpty()){
			return true;
		}else{
			return false;
		}
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	
	
	
	
}
