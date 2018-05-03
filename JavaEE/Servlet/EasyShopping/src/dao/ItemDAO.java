package dao;

import java.util.ArrayList;


import entity.Items;


public class ItemDAO {
	
	String[] name={"联想笔记本电脑","小米5","iphone7plus","安踏运动鞋","以纯牛仔裤","李宁文化衫"};
	String[] city={"北京","北京","深圳","福建","上海","北京"};
	Float[] price={5000f,2000f,6000f,400f,300f,150f};
	
	
	public ArrayList<Items> getAllItems(){//添加所有商品进集合
		ArrayList<Items> list=new ArrayList<Items>();//创建商品集合	
		
		
		for(int i=0;i<6;i++){
			Items item=new Items();
			item.setId(i+1);
			item.setName(name[i]);
			item.setCity(city[i]);
			item.setPrice(price[i]);
			list.add(item);
			
		}
	return list;
	}
	public Items getItemById(String id){//根据商品编号获取商品信息
		Items item=new Items();
		int Id=Integer.parseInt(id);
		item.setId(Id);
		item.setName(name[Id-1]);
		item.setCity(city[Id-1]);
		item.setPrice(price[Id-1]);
		
		return item;
	}
	public static void main(String[] args) {
		
		ItemDAO itemdao=new ItemDAO();
		ArrayList<Items> list=itemdao.getAllItems();
		for(int i=0;i<list.size();i++){
			Items item=list.get(i);
			System.out.println(item.getName());
		}
	}
}
