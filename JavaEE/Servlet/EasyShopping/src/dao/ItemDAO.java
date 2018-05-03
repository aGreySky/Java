package dao;

import java.util.ArrayList;


import entity.Items;


public class ItemDAO {
	
	String[] name={"����ʼǱ�����","С��5","iphone7plus","��̤�˶�Ь","�Դ�ţ�п�","�����Ļ���"};
	String[] city={"����","����","����","����","�Ϻ�","����"};
	Float[] price={5000f,2000f,6000f,400f,300f,150f};
	
	
	public ArrayList<Items> getAllItems(){//���������Ʒ������
		ArrayList<Items> list=new ArrayList<Items>();//������Ʒ����	
		
		
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
	public Items getItemById(String id){//������Ʒ��Ż�ȡ��Ʒ��Ϣ
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
