package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import entity.Items;
import util.DBhelper2;


public class ItemDAO {
	DBhelper2 db=new DBhelper2();
	Connection conn=db.conn;
	PreparedStatement stmt =null;
	ResultSet rs=null;
	public ArrayList<Items> getAllItems(){//���������Ʒ������
		ArrayList<Items> list =new ArrayList<Items>();//��Ʒ����
		try
		{
			String sql="select *from items;";//sql���
			stmt =conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				Items item =new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setPrice(rs.getInt("price"));
				item.setNumber(rs.getInt("number"));
				item.setPicture(rs.getString("picture"));
				list.add(item);//��һ����Ʒ���뼯��
			}
			return list;//���ؼ���
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		finally
		{
			//�ͷ����ݼ�����
			if(rs!=null)
			{
				try
				{
					rs.close();
					rs=null;
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			//�ͷ�������
			if(stmt!=null)
			{
				try
				{
					stmt.close();
					stmt=null;
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
	}
	public Items getItemById(int id){//������Ʒ��Ż�ȡ��Ʒ��Ϣ
		
		try
		{
			String sql="select *from items where id=?;";//sql���
			stmt =conn.prepareStatement(sql);
			stmt.setInt(1,id);
			rs = stmt.executeQuery();
			if(rs.next())
			{
				Items item =new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setPrice(rs.getInt("price"));
				item.setNumber(rs.getInt("number"));
				item.setPicture(rs.getString("picture"));
				return item;
			}
			else
			{
			return null;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		finally
		{
			//�ͷ����ݼ�����
			if(rs!=null)
			{
				try
				{
					rs.close();
					rs=null;
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			//�ͷ�������
			if(stmt!=null)
			{
				try
				{
					stmt.close();
					stmt=null;
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			
		}	
	}
}
